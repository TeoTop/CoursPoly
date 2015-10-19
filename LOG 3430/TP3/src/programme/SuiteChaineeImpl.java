package programme;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.naming.SizeLimitExceededException;
import javax.swing.SizeRequirements;

public class SuiteChaineeImpl implements SuiteChainee {

	private static final int BEGININDEX = 0; 
	private static final int SIZELIMIT = 10;

	private MyList list;
	private CalculatorImpl calculator;
	private String fichier;
	private int tailleChaine;
	private int val1;
	private int val2;
	private boolean vide;
	private int startCurseur;
	

	public SuiteChaineeImpl(String chemin, int val1, int val2, String operateur, int tailleListe, boolean vide) throws Exception {
		int newVal = 0;
		Elem curser = null;
		
		this.list = new MyListImpl();
		this.tailleChaine = 0;
		this.calculator = new CalculatorImpl();
		
		if(!this.calculator.isValide(operateur)) {
			throw new IllegalArgumentException("Erreur SuiteChaine(String chemin, String operateur, int val1, " +
			"int val2, int tailleListe, boolean vide) : Operateur doit être 'add', 'sub', 'mul' ou 'div'.");
			
		}		
		
		try{
			readSuiteChaineFromFile(chemin);
		} catch (Exception e) {
			if(e.getClass().equals(IOException.class) || 
					e.getClass().equals(NumberFormatException.class) || 
					e.getClass().equals(NullPointerException.class)) 
				throw new Exception("Erreur lors de la lecture du fichier");
		}
	
		
		
		/**
		 * Test longueur de chaine
		 */
		int taille_final = this.tailleChaine + tailleListe;
		if(tailleListe < 0 || taille_final > SIZELIMIT) throw new SizeLimitExceededException("Erreur SuiteChaine(String chemin, String operateur, int val1, " +
			"int val2, int tailleListe, boolean vide) : La taille de la liste doit être compris entre 0 et 10.");

		if(vide && this.tailleChaine != 0){
			throw new IllegalArgumentException("Erreur SuiteChaine(String chemin, String operateur, int val1, " +
					"int val2, int tailleListe, boolean vide) : vide = "+ vide +" -- La chaine utilisée n'est pas vide\n");	
		}
		
				
		this.fichier = chemin;
		this.vide = vide;
		this.calculator.setOp(operateur);	
		this.startCurseur= BEGININDEX + this.tailleChaine;
		
		/**
		 * Ajout des deux premières valeurs dans la suite
		 */
		if(tailleListe >= 1) {
			this.val1=val1;
			add(new Elem(val1));
		}
		if(tailleListe >= 2){
			this.val2=val2;
			add(new Elem(val2));
		}		
				
		/**
		 * Complète la liste si les deux valeurs ne suffisent pas à compléter la taille. Les éléments qui complètent la suite sont
		 * calculé à partir des deux derniers éléments de la liste
		 */
		while(this.tailleChaine < taille_final){
			curser = getAt(this.tailleChaine-2);
			newVal = calculator.calcul(curser.getValue(), curser.getNext().getValue());
			add(new Elem(newVal));
		}
		
		writeSuiteChaineToFile();
	}
	
	/*****************************************************************************************
	 * Methode à modifier pour le TP3 (à voir avec les tests et le comportement attendu)
	 *****************************************************************************************/
	@Override
	public void add(Elem elememt) throws Exception{
		this.list.add(elememt);
		this.tailleChaine++;
	}

	@Override
	public void removeAt(int position) throws Exception {
		this.list.removeAt(position);
		
		this.val1 = getAt(BEGININDEX).getValue();
		this.val2 = getAt(BEGININDEX+1).getValue();
		
		this.tailleChaine--;
	}

	@Override
	public void removeItem(int element) throws Exception {
		this.list.removeItem(element);
		
		this.val1 = getAt(BEGININDEX).getValue();
		this.val2 = getAt(BEGININDEX+1).getValue();
		
		this.tailleChaine--;
	}

	
	@Override
	public void setAt(int element, int position) throws Exception {
		this.list.setAt(element, position);
	}

	@Override
	public Elem getAt(int position) throws Exception {
		return this.list.getAt(position);
	}

	@Override
	public int getSize() {
		return tailleChaine;
	}

	@Override
	public void reset() throws Exception {
		this.list.reset();
		this.startCurseur = 0;
		this.tailleChaine = 0;
		this.vide = true;
		this.val1 = 0;
		this.val2 = 0;
		this.fichier = null;
	}

	/**
	 * On vérifie le calcul de chaque élément à partir du troisième et cela, en utilisant les deux éléments précédents et
	 * l'opérateur indiqué par l'attribut de la chaine.
	 * Rappel:l'opérateur est un singleton, on peut y accéder de n'importe où dans le programme.  
	 */
	@Override
	public boolean isValide() throws Exception {
		int val1, val2, res, index;
		boolean flag = true;
		Elem curser = null;
		
		if (this.tailleChaine < 3){
			return true;
		}

		index = 2;
		do{
			curser = getAt(index-2);
			val1 = curser.getValue();
			val2 = curser.getNext().getValue();
			res = curser.getNext().getNext().getValue();
			
			if(res != calculator.calcul(val1, val2)){
				flag = false;
			}
			index++;
		} while( flag && index < this.tailleChaine);
		
		return flag;
	}
	
	@Override
	public void chargerChaine(String fichier){
		try {
			if(this.fichier!=null) writeSuiteChaineToFile();
			
			readSuiteChaineFromFile(fichier);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void sauvgarderChaine() {
		try {
			writeSuiteChaineToFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * String = fichier + " : " + noeuds + (", ");
	 */
	public String toString() {
		String str=this.fichier + " : ";
		
		str+=this.list.toString();
		return str;	
	}
	
	
	/**
	 * Ecrit la liste dans le fichier en mettant à jour les paramêtres
	 * @throws Exception
	 */
	private void writeSuiteChaineToFile()throws Exception{
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter(this.fichier,false));
			w.write(this.val1+"\n");
			w.write(this.val2+"\n");
			w.write(this.calculator.getOp().name()+"\n");
			w.write(this.startCurseur+"\n");
			w.write(this.getSize()+"\n");
			w.write(this.toString());
			w.close();
		}
        catch(FileNotFoundException ex) {
        	throw new FileNotFoundException("Erreur writeSuiteChaineToFile : Impossible d'ouvrir le fichier " + this.fichier + "\n");            
        }
        catch(IOException ex) {
        	throw new IOException("Erreur writeSuiteChaineToFile : Erreur d'écriture dans " + this.fichier + "\n");                 
        }
		catch(NullPointerException ex) {
			throw new NullPointerException("Erreur writeSuiteChaineToFile : Impossible d'ouvrir le fichier " + this.fichier + "\n");           
        }
	}
	
	/**
	 * Lit une suite chainée contenue dans un fichier et la charge en mémoire
	 * retourne true si la suite a été correctement chargée
	 * Si la liste est vide ou corompue ne fait aucun changement et retourne faux
	 * 
	 * @param chemin Chemin vers le fichier contenant la chaine
	 */
	private boolean readSuiteChaineFromFile(String chemin) throws Exception {
		String contenu;
		String line = null;
		
		try {
            RandomAccessFile fileReader = new RandomAccessFile(chemin, "r");
            
            /**
             * On passe toutes les lignes de sauvegarde inutile
             */
            for(int i = 0 ; i < 5 ; i++){
	            line=fileReader.readLine();
            }
            
            if((line=fileReader.readLine())!=null){
        		contenu=line;        
        	}
        	else{
        		fileReader.close();
        		return false;
        	}
            
            fileReader.close();
            
            setList(contenu);
            this.fichier= chemin;
            
        }
        catch(FileNotFoundException ex) {
        	throw new Exception("Erreur readSuiteChaineFromFile : Impossible d'ouvrir le fichier " + chemin + "\n");            
        }
        catch(IOException ex) {
        	throw new Exception("Erreur readSuiteChaineFromFile : Erreur de lecture " + chemin + "\n");                 
        }
		catch(NumberFormatException e){
			throw new Exception("Erreur readSuiteChaineFromFile : Fichier corrompu, ligne : "+line);
		}
		catch(NullPointerException ex) {
			throw new Exception("Erreur readSuiteChaineFromFile : Impossible d'ouvrir le fichier " + this.fichier + "\n");           
        }
		
		return true;
	}
	
	/**
	 * Affecte à la suite chainée les éléments contenu dans contenu
	 * @param contenu String contenant les informations sur les noeuds de la chaine
	 * @throws Exception
	 */
	private void setList(String contenu) throws Exception{
		String split[]=contenu.split(": ");
		String noeuds[]=split[1].split(", ");
		Elem nd1;

		this.reset();
		
		
		for(int i=BEGININDEX; i<noeuds.length+BEGININDEX; i++){
			nd1 = new Elem(Integer.parseInt(noeuds[i]));
			this.add(nd1);
		}
	}
	
	/*private boolean isFileEmpty(String chemin) throws Exception{
		boolean isEmpty=true;
        FileReader fileReader = new FileReader(chemin);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        if(bufferedReader.readLine()!=null){
        	isEmpty=false;
        }	
        bufferedReader.close();
        
        return isEmpty;
	}*/
}

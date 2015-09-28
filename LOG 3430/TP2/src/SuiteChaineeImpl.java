import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
		int nbCalcul = this.tailleChaine + tailleListe;
		int newVal = 0;
		Elem curser = null;
		
		this.list = new MyListImpl();
		this.fichier = chemin;
		this.vide = vide;
		
		
		if(this.vide){
			if(((MyListImpl)this.list).getInit() != null || this.tailleChaine != 0){
				throw new Exception("Erreur SuiteChaine(String chemin, String operateur, int val1, " +
			"int val2, int tailleListe, boolean vide) : vide = "+ vide +" -- La chaine utilis�e n'est pas vide\n");
			}
		}
		
		if(nbCalcul > SIZELIMIT){
			throw new Exception("Erreur SuiteChaine(String chemin, String operateur, int val1, " +
		"int val2, int tailleListe, boolean vide) : La taille indiqu� n'est pas valide. La taille actuelle de liste ajout� � " +
			"tailleListe : "+tailleListe+" sera sup�rieur � 10 \n");
		}
				
		if(calculator == null) this.calculator = new CalculatorImpl();
		this.calculator.setOp(operateur);	
		this.val1=val1;
		this.val2=val2;
		this.startCurseur=this.getSize()+BEGININDEX;
		
		/**
		 * Ajout des deux premi�res valeurs dans la suite
		 */
		add(new Elem(val1));
		add(new Elem(val2));
		
		/**
		 * Compl�te la liste si les deux valeurs ne suffisent pas � compl�ter la taille. Les �l�ments qui compl�tent la suite sont
		 * calcul� � partir des deux derniers �l�ments de la liste
		 */
		while(this.tailleChaine < nbCalcul){
			curser = getAt(this.tailleChaine-2);
			newVal = calculator.calcul(curser.getValue(), curser.getNext().getValue());
			add(new Elem(newVal));
		}
		
		writeSuiteChaineToFile();
	}
	
	/*****************************************************************************************
	 * Methode � modifier pour le TP3 (� voir avec les tests et le comportement attendu)
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
	 * On v�rifie le calcul de chaque �l�ment � partir du troisi�me et cela, en utilisant les deux �l�ments pr�c�dents et
	 * l'op�rateur indiqu� par l'attribut de la chaine.
	 * Rappel:l'op�rateur est un singleton, on peut y acc�der de n'importe o� dans le programme.  
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
		if(this.getSize()!=0)
		{
			Elem tmp;
			try {
				tmp = this.list.getAt(BEGININDEX);
				
				while (tmp!=null) {
					str += tmp.getValue() + ", ";
					tmp = tmp.getNext();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			str = str.substring(0, str.length()-2);
		}
		return str;	
	}
	
	
	/**
	 * Ecrit la liste dans le fichier en mettant � jour les param�tres
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
        	throw new Exception("Erreur writeSuiteChaineToFile : Impossible d'ouvrir le fichier " + this.fichier + "\n");            
        }
        catch(IOException ex) {
        	throw new Exception("Erreur writeSuiteChaineToFile : Erreur d'�criture dans " + this.fichier + "\n");                 
        }
		catch(NullPointerException ex) {
			throw new Exception("Erreur writeSuiteChaineToFile : Impossible d'ouvrir le fichier " + this.fichier + "\n");           
        }
	}
	
	/**
	 * Lit une suite chain�e contenue dans un fichier et la charge en m�moire
	 * retourne true si la suite a �t� correctement charg�e
	 * Si la liste est vide ou corompue ne fait aucun changement et retourne faux
	 * 
	 * @param chemin Chemin vers le fichier contenant la chaine
	 */
	private boolean readSuiteChaineFromFile(String chemin) throws Exception {
		String contenu;
		String operateur;
		String line = null;
		
		try {
            FileReader fileReader = new FileReader(chemin);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            /**
             * Trouver si le fichier est vide ou pas.
             */
            if((line=bufferedReader.readLine())!=null){
            	operateur=line;
            	if(calculator.isValide(operateur)!=true){
            		bufferedReader.close();
            		return false;
            	}
        	}
        	else{
        		bufferedReader.close();
        		return false;
        	}
            
            if((line=bufferedReader.readLine())!=null){
        		contenu=line;        
        	}
        	else{
        		bufferedReader.close();
        		return false;
        	}
            
            bufferedReader.close();
            
            setList(contenu); 
            this.fichier=chemin;
            this.calculator.setOp(operateur);;
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
	 * Affecte � la suite chain�e les �l�ments contenu dans contenu
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

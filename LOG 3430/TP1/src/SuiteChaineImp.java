import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Implémentation de l'interface SuiteChaine.
 * @see SuiteChaine
 * @version 0.5
 *
 */
public class SuiteChaineImp implements SuiteChaine {
	
	private static final int BEGININDEX = 0; 

	private Noeud init;
	private Noeud curseur;
	private String fichier;
	private int tailleChaine;
	private String operateur;
	
	/**
	 * Constructeur vide. Initialise attribut de la façon suiavnte : <br>
	 *    - {@link #init} = null<br>
	 *    - {@link #curseur} = value<br>
	 *    - {@link #fichier} = null<br>
	 *    - {@link #tailleChaine} = 0<br>
	 *    - {@link #operateur} = ""<br>
	 *    
	 */
	public SuiteChaineImp() {
		super();
		this.init = null;
		this.curseur = null;
		this.fichier = null;
		this.tailleChaine = 0;
		this.operateur = "";
	}
	
	
	/**
	 * Constructeur complet. Initialise attribut de la façon suivante : <br>
	 *    - {@link #init} = init<br>
	 *    - {@link #curseur} = init<br>
	 *    - {@link #fichier} = fichier<br>
	 *    - {@link #operateur} = operateur<br>
	 *    - {@link #tailleChaine} = tailleChaine<br>
	 *    
	 * @param init
	 * @param fichier
	 * @param operateur
	 * @param tailleChaine
	 */
	public SuiteChaineImp(Noeud init, String fichier, String operateur, int tailleChaine) {
		super();
		this.init = init;
		this.curseur = init;
		this.fichier = fichier;
		this.operateur = operateur;
		this.tailleChaine = tailleChaine;
	}

	@Override
	public SuiteChaineImp suiteChaine(String chemin, String operateur, int val1,
			int val2, int tailleListe, boolean vide) throws Exception {
		int nbCalcul = this.tailleChaine + tailleListe;
		int newVal = 0;
				
		if(vide){
			if(this.init != null || this.tailleChaine != 0){
				throw new Exception("Erreur SuiteChaine(String chemin, String operateur, int val1, " +
			"int val2, int tailleListe, boolean vide) : vide = "+ vide +" -- La chaine utilisée n'est pas vide\n");
			}
		}
		
		if(nbCalcul > 10){
			throw new Exception("Erreur SuiteChaine(String chemin, String operateur, int val1, " +
		"int val2, int tailleListe, boolean vide) : La taille indiqué n'est pas valide. La taille actuelle de liste ajouté à " +
			"tailleListe : "+tailleListe+" sera supérieur à 10 \n");
		}
		
		this.fichier = chemin;
		this.operateur = operateur;	
		
		/**
		 * Ajout des deux premières valeurs dans la suite
		 */
		this.add(new NoeudImp(val1));
		this.add(new NoeudImp(val2));
		
		/**
		 * Complète la liste si les deux valeurs ne suffisent pas à compléter la taille. Les éléments qui complètent la suite sont
		 * calculé à partir des deux derniers éléments de la liste
		 */
		while(this.tailleChaine < nbCalcul){
			this.getAt(this.tailleChaine-2);
			newVal = Operateur.getOperateur().calcul(this.operateur, 
					((NoeudImp)curseur).getValue(), 
					((NoeudImp)curseur).getNext().getValue()
					);
			this.add(new NoeudImp(newVal));
		}
	
		return this;
	}
	
	public Noeud getInit() {
		return init;
	}

	public void setInit(Noeud init) {
		this.init = init;
	}

	public Noeud getCurseur() {
		return curseur;
	}

	public void setCurseur(Noeud curseur) {
		this.curseur = curseur;
	}

	public String getFichier() {
		return fichier;
	}

	public void setFichier(String fichier) {
		this.fichier = fichier;
	}

	public String getOperateur() {
		return operateur;
	}

	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}

	
	/**
	 * L'attribut {@link NoeudImp#index} et {@link NoeudImp#next} du noeud
	 * peuvent être modfiés pour appartenir à la chaine.
	 */
	@Override
	public void add(Noeud elememt) {
		try {
			/**
			 * Vérifier si la chaine est vide avant de cherche un élément de la chaine 
			 */
			if(tailleChaine != 0)
				curseur = getAt(BEGININDEX + getSize()-1);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		((NoeudImp)elememt).setIndex(BEGININDEX + getSize());
		((NoeudImp)elememt).setNext(null);
		
		/**
		 * Vérification si la chaine est vide
		 */
		if(tailleChaine == 0){
			init = ((NoeudImp)elememt);
		} else {
			((NoeudImp)curseur).setNext(elememt);
		}
		tailleChaine++;
	}

	@Override
	public void removeAt(int position) {
		
		if(position == BEGININDEX){
			try {
				curseur = getAt(position);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			
			init = ((NoeudImp)curseur).getNext();
			curseur=init;
		} else {
			try {
				curseur = getAt(position-1);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			
			((NoeudImp)curseur).setNext(((NoeudImp)curseur).getNext().getNext());
			curseur = ((NoeudImp)curseur).getNext();
		}
		
		/**
		 * Mise a jour des index en partant du noeud modifié
		 */
		while(curseur!=null){
			((NoeudImp)curseur).setIndex(((NoeudImp)curseur).getIndex()-1);
			curseur = ((NoeudImp)curseur).getNext();
		}
		
		tailleChaine--;
	}

	@Override
	public void removeItem(Noeud element) {
		int id = ((NoeudImp)element).getIndex();
		removeAt(id);
	}

	
	@Override
	public void setAt(int element, int position) throws Exception {
		try {
			curseur = getAt(position);
		} catch (Exception e) {
			throw new Exception("Erreur setAt() : Appel getAt\n" + e.getMessage());
		}
		
		((NoeudImp)this.curseur).setValue(element);
	}

	@Override
	public Noeud getAt(int position) throws Exception {
		if(position < BEGININDEX || position >= getSize() + BEGININDEX){
			throw new Exception("Erreur getAt() : "+ position +" -- Position de chaine invalide\n");
		}
		
		this.curseur = init;
		while(position != ((NoeudImp)this.curseur).getIndex()){
			this.curseur = ((NoeudImp)this.curseur).getNext();
		}
		return curseur;
	}

	@Override
	public int getSize() {
		return tailleChaine;
	}

	@Override
	public void reset()throws Exception {
		init = null;
		curseur = null;
		tailleChaine = 0;
	}

	/**
	 * On vérifie le calcul de chaque élément à partir du troisième et cela, en utilisant les deux éléments précédents et
	 * l'opérateur indiqué par l'attribut de la chaine.
	 * Rappel:l'opérateur est un singleton, on peut y accéder de n'importe où dans le programme.  
	 */
	@Override
	public boolean isValide() {
		int val1, val2, res, index;
		boolean flag = true;
		
		if (tailleChaine < 3){
			return true;
		}
		try {
			index = 2;
			do{
				this.curseur = this.getAt(index-2);
				val1 = ((NoeudImp)curseur).getValue();
				val2 = ((NoeudImp)(this.curseur = ((NoeudImp)curseur).getNext())).getValue();
				res = ((NoeudImp)(this.curseur = ((NoeudImp)curseur).getNext())).getValue();
				
				if(res != Operateur.getOperateur().calcul(this.operateur, val1, val2)){
					flag = false;
				}
				index++;
			} while( flag && index < this.tailleChaine);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
	public String toString(){
		String str=this.fichier + " : ";
		if(this.getSize()!=0)
		{
			NoeudImp tmp = ((NoeudImp)init);
			
			while (tmp!=null) {
				str += tmp.getValue() + ", ";
				tmp = ((NoeudImp)tmp.getNext());
			}
			
			str = str.substring(0, str.length()-2);
		}
		return str;	
	}
	
	
	/**
	 * Ecrit la liste dans le fichier en mettant à jour les paramêtres
	 * @throws Exception
	 */
	private void writeSuiteChaineToFile()throws Exception{
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter(this.fichier,false));
			w.write(this.operateur+"\n");
			w.write(this.toString());
			
			w.close();
		}
        catch(FileNotFoundException ex) {
        	throw new Exception("Erreur writeSuiteChaineToFile : Impossible d'ouvrir le fichier " + this.fichier + "\n");            
        }
        catch(IOException ex) {
        	throw new Exception("Erreur writeSuiteChaineToFile : Erreur d'écriture dans " + this.fichier + "\n");                 
        }
		catch(NullPointerException ex) {
			throw new Exception("Erreur writeSuiteChaineToFile : Impossible d'ouvrir le fichier " + this.fichier + "\n");           
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
		String contenu, operateur;
		String line = null;
		
		try {
            FileReader fileReader = new FileReader(chemin);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            /**
             * Trouver si le fichier est vide ou pas.
             */
            if((line=bufferedReader.readLine())!=null){
            	operateur=line;
            	if(Operateur.getOperateur().isValide(operateur)!=true){
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
            this.operateur=operateur;
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
		Noeud nd1;

		this.reset();
		
		for(int i=BEGININDEX; i<noeuds.length+BEGININDEX; i++){
			nd1 = new NoeudImp(Integer.parseInt(noeuds[i]));
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

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SuiteChaineImp implements SuiteChaine {
	
	private static final int BEGININDEX = 0; 

	private Noeud init;
	private Noeud curseur;
	private String fichier;
	private int tailleChaine;
	private String operateur;
	
	public SuiteChaineImp() {
		super();
		this.init = null;
		this.curseur = null;
		this.fichier = null;
		this.tailleChaine = 0;
	}
	
	public SuiteChaineImp(Noeud init, String fichier, String operateur, int tailleChaine) {
		super();
		this.init = init;
		this.curseur = init;
		this.fichier = fichier;
		this.operateur = operateur;
		this.tailleChaine = tailleChaine;
	}

	@Override
	/**
	 * A TESTER PARCE QU' IL FAUT QUE J'AILLE EN COURS LA!
	 */
	public String suiteChaine(String chemin, String operateur, int val1,
			int val2, int tailleListe, boolean vide) throws Exception {
		
		int nbCalcul = 0;
		int startIndex=this.getSize()+BEGININDEX;
		boolean isEmpty=false;
		try{
			isEmpty=isFileEmpty(chemin);
			if(!readSuiteChaineFromFile(chemin)){
				System.out.println("Erreur Lors de la lecture du fichier. Aucune donnée chargée");
			}
			
		}
		catch(Exception e){
			throw new Exception("Erreur SuiteChaine(...) : \n\t"+e.getMessage());
					
		}
		
		if(vide){
			if(this.init != null || this.tailleChaine != 0||isEmpty==false){
				throw new Exception("Erreur SuiteChaine(String chemin, String operateur, int val1, " +
			"int val2, int tailleListe, boolean vide) : vide = "+ vide +" -- La chaine utilisée n'est pas vide\n");
			}
		}
		//updating chaine
		this.fichier = chemin;
		this.operateur = operateur;
		nbCalcul = this.tailleChaine + tailleListe;
		
		this.add(new NoeudImp(val1));
		this.add(new NoeudImp(val2));
		
		int newVal = 0;
		while(this.tailleChaine < nbCalcul){
			this.getAt(this.tailleChaine-2);
			newVal = Operateur.getOperateur().calcul(this.operateur, 
					((NoeudImp)curseur).getValue(), 
					((NoeudImp)curseur).getNext().getValue()
					);
			this.add(new NoeudImp(newVal));
		}
		writeSuiteChaineToFile(val1,val2,startIndex);
		return null;
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

	

	@Override
	public void add(Noeud elememt) {
		try {
			//vérifier si la chaine est vide avant de cherche un élément de la chaine 
			if(tailleChaine != 0)
				curseur = getAt(BEGININDEX + getSize()-1);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		((NoeudImp)elememt).setIndex(BEGININDEX + getSize());
		((NoeudImp)elememt).setNext(null);
		
		// vérification si la chaine est vide
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
		 * Mise a jour des index
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
		//deleting file
		BufferedWriter w = new BufferedWriter(new FileWriter(this.fichier,false));
		w.close();
	}

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
				//System.out.println(""+res);
			} while( flag && index < this.tailleChaine);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public String toString(){
		//String str = fichier + " : ";
		String str="";
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
	 * @param val1
	 * @param val2
	 * @param operateur
	 * @param curseur valeur du curseur au début de la dernière opération
	 * @throws Exception
	 */
	private void writeSuiteChaineToFile(int val1, int val2, int curseur)throws Exception{
		BufferedWriter w = new BufferedWriter(new FileWriter(this.fichier,false));
		w.write(val1+"\n");
		w.write(val2+"\n");
		w.write(this.operateur+"\n");
		w.write(curseur+"\n");
		w.write(this.getSize()+"\n");
		w.write(this.toString());

		
		w.close();
	}
	/**
	 * Lit une suite chainée contenue dans un fichier et la charge en mémoire
	 * retourne true si la suite a été correctement chargée
	 * Si la liste est vide ou corompue ne fait aucun changement et retourne faux
	 */
	private boolean readSuiteChaineFromFile(String chemin) throws Exception {
		int val1,val2, index, taille;
		String operateur, contenu;
		String line = null;
		boolean okay=true;
		try {
            FileReader fileReader = new FileReader(chemin);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //Trouver si le fichier est vide ou pas .
            if((line=bufferedReader.readLine())!=null){            	
            	val1=Integer.parseInt(line);
            	 //System.out.println("Test val1: " + val1);
            }
            else{
            	bufferedReader.close();
            	return false;
            }
            
            if((line=bufferedReader.readLine())!=null){
        		val2=Integer.parseInt(line);
        		 //System.out.println("Test val2: "+val2);
        	}
        	else{
        		bufferedReader.close();
        		return false;
        	}
            
            if((line=bufferedReader.readLine())!=null){
            	operateur=line;
            	if(Operateur.getOperateur().isValide(operateur)!=true){
            		bufferedReader.close();
            		return false;
            	}
            	// System.out.println("Test op: "+operateur);
        	}
        	else{
        		bufferedReader.close();
        		return false;
        	}
            
            if((line=bufferedReader.readLine())!=null){
        		index=Integer.parseInt(line);  
        		// System.out.println("Test index: "+index);
        	}
        	else{
        		bufferedReader.close();
        		return false;
        	}
            
            if((line=bufferedReader.readLine())!=null){
        		taille=Integer.parseInt(line);  
        		// System.out.println("Test taille:"+taille);
        	}
        	else{
        		bufferedReader.close();
        		return false;
        	}
            if((line=bufferedReader.readLine())!=null){
        		contenu=line;        
        		//System.out.println("Test contenu:"+contenu);
        	}
        	else{
        		bufferedReader.close();
        		return false;
        	}
            bufferedReader.close();    
            //System.out.println("Test val1: " + val1+" val2: "+val2+" op: "+operateur+" index: "+index+" taille: "+taille);
            setList(contenu);
            this.fichier=chemin;
                
        }
        catch(FileNotFoundException ex) {
        	throw new Exception("Erreur readSuiteChaineFromFile:Impossible d'ouvrir le fichier" + chemin + "\n");            
        }
        catch(IOException ex) {
        	throw new Exception("Erreur readSuiteChaineFromFile:Erreur de Lecture" + chemin + "\n");                 
        }
		catch(NumberFormatException e){
			throw new Exception("Erreur readSuiteChaineFromFile: Fichier corrompu, ligne :"+line);
		}
		return okay;
	}
	
	/**
	 * Affecte à la suite chainée les éléments contenu dans contenu
	 * @param contenu
	 * @throws Exception
	 */
	private void setList(String contenu) throws Exception{
		String array[]=contenu.split(", ");
		Noeud nd1;
		//clears list
		this.reset();
		
		
		for(int i=0+BEGININDEX; i<array.length+BEGININDEX;i++){
			nd1 = new NoeudImp(Integer.parseInt(array[i]));
			this.add(nd1);
		}
	}
	
	private boolean isFileEmpty(String chemin) throws Exception{
		boolean isEmpty=true;
        FileReader fileReader = new FileReader(chemin);

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        //Trouver si le fichier est vide ou pas .
        if(bufferedReader.readLine()!=null){
        	isEmpty=false;
        }	
        bufferedReader.close();
        return isEmpty;
	}
}


public class ChaineImp implements Chaine {
	
	private static final int BEGININDEX = 0; 

	private Noeud init;
	private Noeud curseur;
	private String fichier;
	private int tailleChaine;
	private String operateur;
	
	public ChaineImp() {
		super();
		this.init = null;
		this.curseur = null;
		this.fichier = null;
		this.tailleChaine = 0;
	}
	
	public ChaineImp(Noeud init, String fichier, String operateur, int tailleChaine) {
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
	public Chaine suiteChaine(String chemin, String operateur, int val1,
			int val2, int tailleListe, boolean vide) throws Exception {
		int nbCalcul = 0;
		
		if(vide){
			if(this.init != null || this.tailleChaine != 0){
				throw new Exception("Erreur SuiteChaine(String chemin, String operateur, int val1, " +
			"int val2, int tailleListe, boolean vide) : vide = "+ vide +" -- La chaine utilisée n'est pas vide\n");
			}
		}
		
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
		/*for(int pos = position + BEGININDEX ; pos > BEGININDEX ; pos--){
			
		}*/
				
		return curseur;
	}

	@Override
	public int getSize() {
		return tailleChaine;
	}

	@Override
	public void reset() {
		init = null;
		curseur = null;
		tailleChaine = 0;
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
		String str = fichier + " : ";
		NoeudImp tmp = ((NoeudImp)init);
		
		while (tmp!=null) {
			str += tmp.getValue() + ", ";
			tmp = ((NoeudImp)tmp.getNext());
		}
		
		str = str.substring(0, str.length()-2);
		
		return str;	
	}
}

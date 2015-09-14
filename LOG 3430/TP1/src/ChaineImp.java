
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
	
	public ChaineImp(Noeud init, Noeud curseur, String fichier, String operateur, int tailleChaine) {
		super();
		this.init = init;
		this.curseur = curseur;
		this.fichier = fichier;
		this.operateur = operateur;
		this.tailleChaine = tailleChaine;
	}

	@Override
	public Chaine SuiteChaine(String chemin, String operateur, int val1,
			int val2, int tailleListe, boolean vide) {
		
		Noeud nd2 = new NoeudImp(getSize(), val2, null);
		Noeud nd1 = new NoeudImp(getSize(), val1, nd2);
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
			curseur = getAt(BEGININDEX + getSize()-1);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		((NoeudImp)elememt).setIndex(BEGININDEX + getSize());
		((NoeudImp)elememt).setNext(null);
		
		((NoeudImp)curseur).setNext(elememt);
		curseur = ((NoeudImp)curseur).getNext();
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
		
		curseur = init;
		for(int pos = position + BEGININDEX ; pos > BEGININDEX ; pos--){
			this.curseur = ((NoeudImp)this.curseur).getNext();
		}
				
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
		// TODO Auto-generated method stub
		return false;
	}
	
	/*public void printList(){
		
	}*/
	
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

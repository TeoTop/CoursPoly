
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
		//parcours la liste et verifie que les valeurs des noeuds sont le resultat de l'operation
		
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
	
	public int add(int a, int b){
		int res=a;
		
		if(b>0){
			while(b-- !=0){
				res++;
			}
		}
		else if(b<0){
			while(b++ !=0){
				res--;
			}
		}
		return res;
	}
	
	public int substract(int a, int b){
		return add(a,-b);
		
	}
	
	
	/**
	 * Methode pour multiplier deux entiers (Par Théo)
	 */
	public int multiply(int a, int b){
		
		int res=0;
		if(b>0){
			while(b-- != 0){
				res=add(res,a);
			}
		}
		else if(b<0){
			while(b++ != 0){
				res=substract(res,a);
			}
		}
		return res;
		
	}
	
	/**
	 * On doit retourner le résultat d'une division entière. On ne gère pas le reste. (Par Théo)
	 */
	public int divide(int a, int b) throws Exception
	{
		int res=0;
		
		if(b==0){
			throw new Exception("Erreur divide() : a="+a+" b="+b+" -- division nulle impossible\n");
		}
		
		if(a==b){
			res = 1;
		} else if(a==-b){
			res = -1;
		} else if(a<0){
			if(b<0){
				while(a<=b){
					res++;
					a=substract(a, b);
				}
			}
			if(b>0){
				while(a<=-b){
					res--;
					a=add(a, b);
				}
			}
		} else if(a>0){
			if(b>0){
				while(a>=b){
					res++;
					a=substract(a, b);
				}
			}
			if(b<0){
				while(a>=-b){
					res--;
					a=add(a, b);
				}
			}
		}
		
		return res;
	}
	
	/* Méthode multiply et divided by Timothée
	 * 
	public int multiply(int a, int b){//NOT WORKING
		
		int res=a;
		if(b>0){
			while(b-- >1){
				res=add(res,a);
			}
		}
		else if(b<0){
			while(b++ <=0){
				res=substract(res,a);
			}
		}
		else{
			res=0;
		}
		return res;
		
	}
	
	public int divide(int a, int b) throws Exception
	{//NOT WORKING
		int res=0,test=0,a0=1,b0=1,N=0,n=1,next=1,sign=1;
		if(b==0){
			throw new Exception("Erreur divide() : a="+a+" b="+b+" -- division nulle impossible\n");
		}
		if((a<0)&&(b<0)){
			a=-a;
			b=-b;
		}
		else if((a>0)&&(b<0)){
			b=-b;
			sign=-1;
		}
		else if((a<0)&&(b>0)){
			a=-a;
			sign=-1;
		}
		if(b>a){
			return 0;
		}
		if(b==1){
			return multiply(a,sign);
		}
		if(a==0){
			return 0;
		}
		//voir https://fr.wikipedia.org/wiki/Division_euclidienne#M.C3.A9thode_d.C3.A9cimale
		
		//recherche plus petite puissqnce de  10
		res=a;
		test=b;
		do{
			test=multiply(test,2);
			a0=multiply(a0,2);
			b0=multiply(b0,2);
			N++;
			
		}while(test<=a);
		
		if(a>=4){
			a0=substract(a0,4);
		}
		else{
			a0=1;
		}
		
		System.out.println("N 1 :"+N);
		System.out.println("a0 1 :"+a0);
		System.out.println("b0 1 :"+b0);
		
		while((n<N)&&(b0-a0!=1)){
			next=1;
			while(multiply(2,next)!=(a0+b0)){
				next++;
			}
			if(multiply(add(a0,b0),b)<=multiply(a,2)){
				a0=next;	
			}
			else{
				b0=next;
			}
			n++;
			System.out.println("a0 "+n+" :"+a0);
			System.out.println("b0 "+n+" :"+b0);
		}
		
		
		return multiply(a0,sign);
	}*/
}

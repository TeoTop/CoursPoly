/**
 * 
 * @author teoto_000
 * Singleton + ENUM finir la structure
 */
public class Operateur {

	private String op = null; 
	
	private Operateur(String op){
		this.op = op;
	}
	
	private static Operateur OPERATEUR = null;
	
	public static Operateur getOperateur(String op){
		if(OPERATEUR == null) OPERATEUR = new Operateur(op);
		return OPERATEUR;
	}
	
	public int calcul(int a, int b){
		int res = 0;
		
		switch(OPERATEUR.op){
		case "add":
			res = OPERATEUR.add(a, b);
			break;
		case "sub":
			res = OPERATEUR.substract(a, b);
			break;
		case "mul":
			res = OPERATEUR.multiply(a, b);
			break;
		case "div":
			try {
				res = OPERATEUR.divide(a, b);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		
		return res;
	}
	
	private int add(int a, int b){
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

	private int substract(int a, int b){
		return add(a,-b);
		
	}

	/**
	 * Methode pour multiplier deux entiers (Par Théo)
	 */
	private int multiply(int a, int b){
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
	private int divide(int a, int b) throws Exception
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

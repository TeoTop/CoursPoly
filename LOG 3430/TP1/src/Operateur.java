/**
 * Singleton operateur pour effectuer des calcules (+, -, * et /). Permet de n'avoir qu'une instance d'Operateur 
 * pour toute l'application et qui sera utilisée par toute les chaines pour effectuer les calculs. Evite le duplication 
 * inutile d'une instance fonctionnelle.
 * Singleton opérateur
 * @version 0.5
 */
public class Operateur {
	
	/**
	 * Constructeur privé pour appliquer le pattern singleton
	 */
	private Operateur(){}
	
	/**
	 * Instance unique et privée de la classe opérateur (pattern singleton).
	 */
	private static Operateur OPERATEUR = null;
	
	public static Operateur getOperateur(){
		if(OPERATEUR == null) OPERATEUR = new Operateur();
		return OPERATEUR;
	}
	public boolean isValide(String op)
	{
		if(op.equals("add") || op.equals("sub") || op.equals("mul") ||op.equals("div")){
			return true;
		}
		else{
			return false;
		}
	}
	public int calcul(String op, int a, int b){
		int res = 0;
		
		switch(op){
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
	
	/**
	 * Addition entre a et b (O(b))
	 * @param a Premier terme de l'opération
	 * @param b Second terme de l'opération
	 * @return Résultat de l'addition
	 */
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

	/**
	 * Soustraction de a par b (O(b))
	 * @param a Premier terme de l'opération
	 * @param b Second terme de l'opération
	 * @return Résultat de la soustraction
	 */
	private int substract(int a, int b){
		return add(a,-b);
		
	}

	/**
	 * Multiplication entre a et b (O(a*b))
	 * @param a Premier terme de l'opération
	 * @param b Second terme de l'opération
	 * @return Résultat de la multiplication
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
				res=add(res,-a);
			}
		}
		return res;
	}

	/**
	 * Division de a par b (O(b)).
	 * @param a Premier terme de l'opération
	 * @param b Second terme de l'opération
	 * @return Résultat de la division
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
					a=add(a, -b);
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
					a=add(a, -b);
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
}

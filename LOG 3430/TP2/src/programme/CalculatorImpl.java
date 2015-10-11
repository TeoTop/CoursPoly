package programme;

public class CalculatorImpl implements Calculator {

	enum Operator {
	    add, sub, mul, div
	}
	
	private Operator op;
	
	
	CalculatorImpl() {
		this.op = Operator.add;
	}
	
	
	CalculatorImpl(Operator op) {
		this.op = op;
	}
	
	public Operator getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = Operator.valueOf(op);
	}

	public boolean isValide(String operator)
	{
		for (Operator o : Operator.values()) {
	        if (o.name().equals(operator)) {
	            return true;
	        }
	    }

	    return false;
	}
	
	
	public int calcul(int a, int b) throws Exception{
		int res = 0;
		
		switch(this.op){
		case add:
			res = add(a, b);
			break;
		case sub:
			res = substract(a, b);
			break;
		case mul:
			res = multiply(a, b);
			break;
		case div:
			res = divide(a, b);
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
	private int add(int a, int b) throws Exception{
		int res=a;
		
		if(b>0){
			while(b-- !=0){
				if(res < Integer.MAX_VALUE && res > Integer.MIN_VALUE){
					res++;
			    } else {
			    	throw new ArithmeticException("add(int a, int b) : a ou b est trop grand" +
			    			" pour effectuer le calcule");
			    }
			}
		}
		else if(b<0){
			while(b++ !=0){
				if(res < Integer.MAX_VALUE && res > Integer.MIN_VALUE){
					res--;
			    } else {
			    	throw new ArithmeticException("add(int a, int b) : a ou b est trop grand" +
			    			" pour effectuer le calcule. Diminuer a ou b ou la taille de chaine.");
			    }
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
	private int substract(int a, int b) throws Exception{
		return add(a,-b);
		
	}

	/**
	 * Multiplication entre a et b (O(a*b))
	 * @param a Premier terme de l'opération
	 * @param b Second terme de l'opération
	 * @return Résultat de la multiplication
	 */
	private int multiply(int a, int b) throws Exception{
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
			throw new ArithmeticException("Erreur divide() : a="+a+" b="+b+" -- division par zero impossible\n");
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

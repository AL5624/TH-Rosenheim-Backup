package de.bigdev;

public class U01Folgen {
	
	static int a(int n) {
		if(n==1){
			return 2;
		} else if(n>1) {
			return a(n-1)+3;
		} else {
			throw new IllegalArgumentException(">1!!!!");
		}
	}

	static double  c(int n){
		// (-1)^n 2n
		if(n%2==0){
			return 2*n;
		} else {
			return -2*n;
		}
	}
	
	static double f(int n){
		if(n==1){
			return 1;
		} else if(n==2){
			return 2;
		} else {
			return f(n-1)*f(n-2);
		}
	}
	
	
	public static void main(String[] args) {

		for (int n = 1; n < 10; n++) {
			System.out.print(a(n) + " ");
		}
		
		for (int i = 1; i <= 10; i++) {
			System.out.print(f(i) + " ");
		}
		
		
		
		

	}

}

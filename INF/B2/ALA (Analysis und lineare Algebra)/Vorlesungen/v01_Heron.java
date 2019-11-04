public class v01_Heron {
	
	/**
	 * Rekursive Heron-Folge als Funktion zur Berechnung von sqrt(2)
	 */
	static double a(int n){
		if (n==0){
			return 2;
		} else if (n > 0) {
			return 0.5*(a(n-1) + 2.0 / a(n-1));
		} else {
			// Falls n negativ ist, wird eine Exception geworfen
			throw new IllegalArgumentException("n=" + n + " muss positiv sein! :-(");
		}
	}
	
	/**
	 * Rekursive Heron-Folge als Funktion zur Berechnung von sqrt(x)
	 * 
	 * @param x
	 * @return
	 */
	static double a(int n, double x){
		if (n==0){
			return 2;
		} else if (n > 0) {
			return 0.5*(a(n-1, x) + x / a(n-1, x));
		} else {
			// Falls n negativ ist, wird eine Exception geworfen
			throw new IllegalArgumentException("n=" + n + " muss positiv sein! :-(");
		}
	}
	
	/**
	 * sqrt Funktion über 5 Iterationen des Heron-Verfahrens
	 * 
	 * @param x
	 * @return
	 */
	static double sqrt(double x) {
		return a(5, x);
	}

	public static void main(String[] args) {

		System.out.println("Heron-Verfahren zur Berechnung der Wurzel 2 über 5 Iterationen");
		System.out.println("==============================================================");
		
		double sqrt = Math.sqrt(2);
		
		for (int n = 0; n < 5; n++) {
			System.out.println("a_" + n + "          = " + a(n));
			System.out.println("Math.sqrt(2) = " + sqrt);
			System.out.println("Fehler       = " + (a(n)-sqrt) + "\n");
		}
		
		System.out.println("Berechnung Wurzel 10");
		System.out.println("====================");
		
		System.out.println("sqrt(10)      = " + sqrt(10));
		System.out.println("Math.sqrt(10) = " + Math.sqrt(10));
		System.out.println("Fehler        = " + (sqrt(10)-Math.sqrt(10)) + "\n");
		
		
		System.out.println("Fehlerhafter Aufruf");
		System.out.println("===================");
		a(-1);
	}
}

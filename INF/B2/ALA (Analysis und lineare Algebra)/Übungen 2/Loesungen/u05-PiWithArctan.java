package arctan;

public class PiWithArctan {

	public static double powerOfMinusOne(int i) {
		// i&1 liefert 0-te bit von i!
		return (i & 1) == 0 ? 1.0 : -1.0;
	}

	public static void main(String[] args) {

		double error = Double.MAX_VALUE;
		int k = 0;

		while (error > 0.001) {
			int n = 2 * k + 1;

			// Berechne 4 * (1 - 1/3 + 1/5 - 1/7 + ...)
			double piApproximated = 0.0;

			for (int i = 0; i <= k; i++) {
				piApproximated += powerOfMinusOne(i) * 1.0 / (2 * i + 1);
			}

			piApproximated *= 4;
			
			error = Math.abs(Math.PI - piApproximated);

			System.out.println("n=" + n + ": piApprox = " + piApproximated + " vs. PI = " + Math.PI + ", Fehler = "
					+ error);
			
			k++;
		}
	}

}

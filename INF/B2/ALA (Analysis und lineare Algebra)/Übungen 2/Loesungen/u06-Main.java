package de.bigdev;

public class Main {

	public static void main(String[] args) {
		Complex z = new Complex(-2, -Math.sqrt(6));
		
		System.out.println(z + " hat Länge " + z.length() + " und Winkel " + z.angle(new Complex(1.0, 0.0)));
		System.out.println(z.toPolarFormString());
	}
}

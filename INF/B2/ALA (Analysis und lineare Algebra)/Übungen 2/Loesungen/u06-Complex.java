package de.bigdev;

import complex.Vector;

public class Complex extends Vector {

	public Complex(double x, double y) {
		super(x, y);
	}

	/**
	 * komplexe Multiplikation
	 */
	public Complex mult(Complex z) {
		return new Complex(this.getX() * z.getX() - this.getY() * z.getY(),
				this.getX() * z.getY() + this.getY() * z.getX());
	}

	/**
	 * Konjugation
	 */
	public Complex conjugate() {
		return new Complex(getX(), -getY());
	}

	/**
	 * Inversen-Bildung:
	 */
	public Complex inverse() {
		double length = this.length();
		Complex conjugate = this.conjugate();
		Vector vector = conjugate.scalarMult(1 / (length * length));
		return new Complex(vector.getX(), vector.getY());
	}

	/**
	 * Division
	 */
	public Complex div(Complex z) {
		return this.mult(z.inverse());
	}

	/**
	 * Summe
	 */
	public Complex add(Complex z) {
		Vector sum = super.add(z);
		return new Complex(sum.getX(), sum.getY());
	}

	/**
	 * Polarform
	 */
	public String toPolarFormString() {
		double angle = this.angle(new Complex(1.0, 0.0));

		if (this.getY() < 0) {
			angle = 360 - angle;
		}

		return round(this.length()) + " * e^{i*" + angle / 180 + "*pi}";
	}

	@Override
	public String toString() {

		return round(getX()) + " + i*" + round(getY());
	}

	private double round(double x) {
		return Math.round(x * 1000) / 1000.0;
	}

	public static void main(String[] args) {
		// Aufgabe 29 a)
		Complex five = new Complex(5, 0);
		Complex leftFrac = five.div(new Complex(-4, 3));
		Complex rightFrac = (new Complex(13, 1)).div(new Complex(10, -5));
		Complex ergebnis = leftFrac.add(rightFrac);
		System.out.println(ergebnis);
		System.out.println(ergebnis.toPolarFormString());
	}

}

package de.bigdev;

public class Vector {
	 	private double x;
	    private double y;
	 
	    public Vector(double x, double y) {
	        super();
	        this.x = x;
	        this.y = y;
	    }
	     
	    /**
	     * Addition of two vectors
	     *
	     * @param v
	     * @return the sum with v
	     */
	    public Vector add(Vector v) {
	        return new Vector(x + v.x, y + v.y);
	    }
	     
	    /**
	     * Scalar Mulitplication with a constant
	     *
	     * @param c
	     * @return the scalar multiplication with c
	     */
	    public Vector scalarMult(double c) {
	        return new Vector(c * x, c * y);
	    }
	 
	    /**
	     * Length of two vectors
	     * 
	     * @return length
	     */
	    public double length() {
	        return Math.sqrt(x * x + y * y);
	    }
	 
	    /**
	     * Scalar Product of two vectors
	     * 
	     * @param v
	     * @return the scalar product with v
	     */
	    public double scalarProd(Vector v) {
	        return this.x * v.getX() + this.y * v.getY();
	    }
	 
	    /**
	     * Angle between two vectors
	     * 
	     * @param v
	     * @return the angle with v in degrees
	     */
	    public double angle(Vector v) {
	        return Math.acos(this.scalarProd(v) / (this.length() * v.length()))
	                * 180.0 / Math.PI;
	    }
	 
	    public double getX() { return x; }
	    public double getY() { return y; }
	 
	    @Override
	    public String toString() {
	        return "[" + x + ", " + y + "]";
	    }
	 
	    /**
	     * for testing only. should be factored out into another class... or use
	     * JUnit!
	     * 
	     * @param args
	     */
	    public static void main(String[] args) {
	        Vector v = new Vector(1, 1);
	        Vector w = new Vector(-1, -1);
	 
	        System.out.println("Vector algebra");
	        System.out.println("==============");
	        System.out.println("addition      : " + v + " + " + w + " = " + v.add(w));
	        System.out.println("scalar multip.: 2 * " + v + " = " + v.scalarMult(2));
	        System.out.println("length        : ||" +v + "|| = " + v.length());
	        System.out.println("scalar product: " + v + " * " + w + " = " + v.scalarProd(w));
	        System.out.println("angle         : angle(" + v + ", " + w
	          + ") = " + Math.round(v.angle(w)) + "°");
	    }
}

package de.th_rosenheim.ad.lecture07B;

public class DirectedEdge {
    private final int v;
    private final int w;
    private final double weight;

    // initialize a directed edge from vertex v to vertex w with the given weight
    public DirectedEdge(int v, int w, double weight) {
        if (v < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
        if (w < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    // returns the source vertex of the directed edge
    public int from() {
        return v;
    }


    // returns the dest vertex of the directed edge
    public int to() {
        return w;
    }

    // returns the weight of the directed edge
    public double weight() {
        return weight;
    }


    // returns a string representation of the directed edge
    public String toString() {
        return v + "->" + w + " " + String.format("%5.2f", weight);
    }


}



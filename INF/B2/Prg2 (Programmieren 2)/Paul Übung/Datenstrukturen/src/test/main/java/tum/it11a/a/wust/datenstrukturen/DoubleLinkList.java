package tum.it11a.a.wust.datenstrukturen;

public class DoubleLinkList {
    private int count = 0;
    private double[] data = new double[10];

    public DoubleLinkList() {

    }

    public void setData(double... pData) {
        this.count = pData.length;
    }

    public void add(double value) {

    }

    public void addFirst(double value) {

    }

    public void addLast(double value) {

    }

    public void remove(int index) {

    }

    public String toString() {
        String str = "";

        for(int i=0; i<this.count; i++) {
            if(str.length() > 0) {
                str = str + ", ";
            }

            str += this.data[i];
        }

        return "[" + str + "]";
    }
}

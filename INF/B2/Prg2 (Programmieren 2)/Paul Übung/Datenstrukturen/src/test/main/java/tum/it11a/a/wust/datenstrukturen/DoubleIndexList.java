package tum.it11a.a.wust.datenstrukturen;

public class DoubleIndexList {
    private int count = 0;
    private double[] data;

    public void setData(double... pData) {
        this.data = pData;
        this.count = pData.length;
    }

    public void add(double value) {
        if(this.count < this.data.length) {
            this.data[this.count] = value;
        } else {
            double[] tempData = new double[this.data.length + 1];


            for (int i = 0; i < this.data.length; i++) {
                tempData[i] = this.data[i];
            }

            tempData[this.data.length] = value;
            this.data = tempData;
        }
        this.count++;
    }

    public void addFirst(double value) {
        if(this.count < this.data.length) {
            for (int i = this.count; i > 0; i--) {
                this.data[i] = this.data[i-1];
            }

            this.data[0] = value;
        } else {
            double[] tempData = new double[this.data.length + 1];


            for (int i = 0; i < this.data.length; i++) {
                tempData[i + 1] = this.data[i];
            }

            tempData[0] = value;
            this.data = tempData;
        }
        this.count++;
    }

    public void addLast(double value) {
        this.add(value);
    }

    public void remove(int index) {
        for (int i = index; i < this.count-1; i++) {
            this.data[i] = this.data[i+1];
        }

        this.data = this.array_pop(this.data);

        this.count--;
    }

    public String toString() {
        if(this.data == null || this.data.length < 1) return "[]";
        String str = "";

        for(int i=0; i<this.count; i++) {
            if(str.length() > 0) {
                str += ", ";
            }

           // str += (str.length() > 0 ? ", " : "" ) + this.data[i];

            str += this.data[i];
        }

        return "[" + str + "]";
    }

    public double[] array_pop(double[] topop) {
        double[] temp = new double[topop.length-1];

        for (int i = 0; i < temp.length; i++) {
            temp[i] = topop[i];
        }

        return temp;
    }

    public void add(int index, Double v) {
        if(index >= this.data.length) return;
        this.data[index] = v;
    }

    public void removeValue(Double v) {
        for(int i = 0; i < this.data.length; i++) {
            if(this.data[i] == v) this.remove(i);
        }
    }

    public DoubleIndexList[] sort() {
        DoubleIndexList[] arrDils = new DoubleIndexList[15];
        return arrDils;
    }

    public void clear() {
        this.data = new double[0];
    }

    public Double get(int index) {
        if(index >= this.data.length) return null;
        Double result = this.data[index];
        return result;
    }

    public void set(int index, Double v) {
        if(index >= this.data.length) return;
        this.data[index] = v;
    }

    public boolean contains(Double v) {
        for(int i=0; i<this.data.length; i++) {
            if(this.data[i] == v) return true;
        }
        return false;
    }
}

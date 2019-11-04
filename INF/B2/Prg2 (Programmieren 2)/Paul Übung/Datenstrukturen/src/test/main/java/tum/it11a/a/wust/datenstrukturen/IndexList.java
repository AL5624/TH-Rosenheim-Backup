package tum.it11a.a.wust.datenstrukturen;

public class IndexList<E extends Comparable<E>> {
    private int count = 0;
    private E[] data;

    public void setData(E... pData) {
        this.data = pData;
        this.count = pData.length;
    }

    public void add(E value) {
        if(this.count < this.data.length) {
            this.data[this.count] = value;
        } else {
            E[] tempData = (E[]) (new Comparable[this.data.length + 1]);


            for (int i = 0; i < this.data.length; i++) {
                tempData[i] = this.data[i];
            }

            tempData[this.data.length] = value;
            this.data = tempData;
        }
        this.count++;
    }

    public void addFirst(E value) {
        if(this.count < this.data.length) {
            for (int i = this.count; i > 0; i--) {
                this.data[i] = this.data[i-1];
            }

            this.data[0] = value;
        } else {
            E[] tempData = (E[]) (new Comparable[this.data.length + 1]);


            for (int i = 0; i < this.data.length; i++) {
                tempData[i + 1] = this.data[i];
            }

            tempData[0] = value;
            this.data = tempData;
        }
        this.count++;
    }

    public void addLast(E value) {
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

    public E[] array_pop(E[] topop) {
        E[] temp = (E[]) (new Comparable[topop.length-1]);

        for (int i = 0; i < temp.length; i++) {
            temp[i] = topop[i];
        }

        return temp;
    }

    public void add(int index, E v) {
        if(index >= this.data.length) return;
        this.data[index] = v;
    }

    public void removeValue(E v) {
        for(int i = 0; i < this.data.length; i++) {
            if(this.data[i].equals(v)) this.remove(i);
        }
    }

    public void clear() {
        this.data = (E[]) (new Comparable[0]);
    }

    public E get(int index) {
        if(index >= this.data.length) return null;
        E result = this.data[index];
        return result;
    }

    public void set(int index, E v) {
        if(index >= this.data.length) return;
        this.data[index] = v;
    }

    public boolean contains(E v) {
        for(int i=0; i<this.data.length; i++) {
            if(this.data[i].equals(v)) return true;
        }
        return false;
    }
}

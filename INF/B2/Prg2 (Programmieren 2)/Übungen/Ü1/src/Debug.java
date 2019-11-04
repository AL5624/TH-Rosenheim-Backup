public class Debug {
    public static void main(String[] args) {
        int input[] = {3, 4, 48, 0};
        int a = input[2] - input[1];
        int b = input[1] * input[2];
        int c = b / a;
        int d = b % a;
        b = ++c * a++ + d--;
        int[] output = new int [4];
        output [2] = b;

        boolean bb;
        int aa = 7, cc = 22, dd;
        dd = (cc / aa) *2;
        bb = ((cc % aa) <= (cc / aa)) && (dd == 6);


    }


}

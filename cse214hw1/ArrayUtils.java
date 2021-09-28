package cse214hw1;

public class ArrayUtils {

    public static void rotate(int[] a, int r) {
        int temp = a[0];
        int temp2 = a[0];
        int counter = 0;
        for (int i = 0;i<a.length;i++) {
            temp2 = a[(counter + r) % a.length];
            a[(counter + r) % a.length] = temp;
            counter = (counter + r) % a.length;
            temp = temp2;
        }
    }
    public static void rotate(char[] a, int r) {
        char temp = a[0];
        char temp2 = a[0];
        int counter = 0;
        for (int i = 0;i<a.length;i++) {
            temp2 = a[(counter + r) % a.length];
            a[(counter + r) % a.length] = temp;
            counter = (counter + r) % a.length;
            temp = temp2;
        }
    }
    public static int[] merge(int[] a, int[] b) {
        int[] c = new int[a.length+b.length];
        for (int i = 0;i<a.length;i++) {
            c[i] = a[i];
        }
        for (int i = a.length;i<b.length+a.length;i++) {
            c[i] = b[i-a.length];
        }
        return c;
    }
}

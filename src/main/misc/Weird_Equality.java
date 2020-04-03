package misc;

import java.util.Arrays;
import java.util.Scanner;

public class Weird_Equality {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        int[] A = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] B = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(equals(A, B) ? "YES" : "NO");
    }

    private static boolean equals(int[] p, int[] q) {
        if (Arrays.hashCode(p) == Arrays.hashCode(q)) {
            return true;
        } else if (p.length % 2 == 0) {
            int[] A1 = split(p, true);
            int[] A2 = split(p, false);
            int[] B1 = split(q, true);
            int[] B2 = split(q, false);
            boolean a1EqB1 = equals(A1, B1);
            boolean a2EqB2 = equals(A2, B2);
            boolean a1EqB2 = equals(A1, B2);
            boolean a2EqB1 = equals(A2, B1);

            return (a1EqB1 && a2EqB2) ||
                    (a1EqB1 && a1EqB2) ||
                    (a2EqB2 && a2EqB1);
        } else {
            return false;
        }
    }

    private static int[] split(int[] p, boolean isFirstHalf) {
        int len = p.length / 2;
        int[] q = new int[len];
        if (isFirstHalf) {
            System.arraycopy(p, 0, q, 0, len);
        } else {
            System.arraycopy(p, len, q, 0, p.length / 2);
        }
        return q;
    }

}

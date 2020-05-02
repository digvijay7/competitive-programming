import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {

        int[] radix = new int[201];
        int notificationsCount = 0;
        for (int i = 0; i < expenditure.length; i++) {
            if (i >= d) {
                double median = getMedian(radix, d);
                if (Double.compare(expenditure[i], 2.0 * median) >= 0) {
                    notificationsCount++;
                }
                removeElem(radix, expenditure[i - d]);
            }
            setElem(radix, expenditure[i]);
        }
        return notificationsCount;
    }

    static void setElem(int[] radix, int elem) {
        radix[elem]++;
    }

    static void removeElem(int[] radix, int elem) {
        radix[elem]--;
    }

    static double getMedian(int[] radix, int d) {

        if (d % 2 == 0) {
            int m1 = getMedian(radix, d, d / 2);
            int m2 = getMedian(radix, d, (d-1) / 2);
            return (m1 + m2) / 2.0;
        } else {
            return (double)getMedian(radix, d, d / 2);
        }
    }

    static int getMedian(int[] radix, int d, int c) {
        int elementCount = 0;
        for (int i = 0; i < radix.length; i++) {
            elementCount += radix[i];
            if (elementCount >= (c + 1)) {
                return i;
            }
        }
        return -1;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}


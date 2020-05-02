import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the maximumToys function below.
    static int maximumToys(int[] prices, int k) {
        quickSort(prices, 0, prices.length - 1);
        System.out.println(Arrays.toString(prices));
        //Arrays.sort(prices);
        int left = k;
        int i = 0;
        while(i < prices.length && ((left - prices[i]) >= 0)) {
            left -= prices[i];
            i++;
        }
        return i;
    }

    static void quickSort(int[] prices, int start, int end) {
        if (start < end) {
            int pi = partition(prices, start, end, end);
            quickSort(prices, start, pi - 1);
            quickSort(prices, pi + 1, end);
        }
    }

    static int partition(int[] prices, int start, int end, int pivotIndex) {

        swap(prices, pivotIndex, end);
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (prices[j] < prices[end]) {
                i++;
                swap(prices, i, j);
            }
        }
        swap(prices, i + 1, end);
        return i + 1;
    }

    static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] prices = new int[n];

        String[] pricesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int pricesItem = Integer.parseInt(pricesItems[i]);
            prices[i] = pricesItem;
        }

        int result = maximumToys(prices, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * 1000000
 * 2566393
 * 24 -> 12, 8, 6
 * 12 -> 13
 * 8 -> 4, 2, 2
 * 4 -> 4
 * 2 -> 2
 * 2 -> 2
 * 8 -> 8
 * 6 -> 3, 2, 1
 * @author digvijay.singh
 */
class COINS {

   private static final int MAX_SIZE = 1000000;
   private static int DP_COMPUTED_INDEX = -1;

   public static void main(String[] args) throws java.lang.Exception {

      Scanner reader = new Scanner(System.in);
      Print print = new Print();
      int n;
      long[] array = new long[MAX_SIZE];

      while (reader.hasNextInt()) {
         n = reader.nextInt();
         print.println(recurrOrDp(n, array));
      }
      print.close();
      reader.close();
   }

   private static long recurrOrDp(int n, long[] array) {

      if (n <= MAX_SIZE) {
         return dp(n, array);
      }
      return Math.max(n, recurrOrDp(n / 2, array) + recurrOrDp(n / 3, array) + recurrOrDp(n / 4, array));
   }

   private static long dp(int n, long[] array) {

      if (n <= 1) {
         //if n <=1 directly convert to american dollars
         return n;
      }
      array[0] = 0;
      array[1] = 1;
      for (int i = Math.max(DP_COMPUTED_INDEX, 2); i <= (n / 2); i++) {
         //printArray(array, n / 2);
         array[i] = Math.max(i, array[i / 2] + array[i / 3] + array[i / 4]);
      }
      DP_COMPUTED_INDEX = n/2;
      return Math.max(n, array[n / 2] + array[n / 3] + array[n / 4]);
   }
   private static void printArray(long[] array, int size) {

      for (int i = 0; i < size; i++) {
         System.out.print(array[i]+",");
      }
      System.out.println();
   }

   static class Print {
      private final BufferedWriter bw;

      public Print() {

         this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
      }

      public void print(Object object) throws IOException {

         bw.append("" + object);
      }

      public void println(Object object) throws IOException {

         print(object);
         bw.append("\n");
      }

      public void close() throws IOException {

         bw.close();
      }
   }

}

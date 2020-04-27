import java.io.*;
import java.util.*;

public class BYTESM2 {

   private static final int[][] ARRAY = new int[100][100];
   private static final int[][] DP = new int[2][100];

   public static void main(String[] args) throws java.lang.Exception {

      FastReader reader = new FastReader();
      int T = reader.nextInt();
      Print print = new Print();
      while (T-- > 0) {
         int h = reader.nextInt();
         int w = reader.nextInt();
         for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
               ARRAY[i][j] = reader.nextInt();
            }
         }
         //print.println(sum(h, w));
         print.println(dp(h, w));

      }
      print.close();
   }

   private static int dp(int h, int w) {

      for (int i = 0; i < w; i++) {
         DP[0][i] = ARRAY[0][i];
      }

      int straight, left, right, dp_last_row_index, dp_row_index;
      for (int i = 1; i < h; i++) {
         for (int j = 0; j < w; j++) {
            dp_last_row_index = (i - 1) % 2;
            dp_row_index = i % 2;
            straight = DP[dp_last_row_index][j];
            left = j - 1 < 0 ? 0 : DP[dp_last_row_index][j - 1];
            right = j + 1 >= w ? 0 : DP[dp_last_row_index][j + 1];
            DP[dp_row_index][j] = ARRAY[i][j] + Integer.max(straight, Integer.max(left, right));
         }
      }
      int max = 0;
      dp_row_index = (h - 1)%2;
      for (int i = 0; i < w; i++) {
         if (DP[dp_row_index][i] > max) {
            max = DP[dp_row_index][i];
         }
      }
      return max;
   }

   private static int sum(int h, int w) {

      List<Integer> sums = new ArrayList<>(w);
      for (int i = 0; i < w; i++) {
         sums.add(recurr(0, i, h, w));
      }
      return sums.stream().mapToInt(i -> i).max().orElse(0);
   }

   private static int recurr(int row, int column, int h, int w) {

      if (row >= h) {
         return 0;
      } else if (column >= w) {
         return 0;
      } else if (column < 0) {
         return 0;
      }

      int straight = recurr(row + 1, column, h, w);
      int left = recurr(row + 1, column - 1, h, w);
      int right = recurr(row + 1, column + 1, h, w);
      return ARRAY[row][column] + Integer.max(straight, Integer.max(left, right));
   }

   static class FastReader {
      BufferedReader br;
      StringTokenizer st;

      public FastReader() {

         br = new BufferedReader(new InputStreamReader(System.in));
      }

      String next() {

         while (st == null || !st.hasMoreElements()) {
            try {
               st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
         return st.nextToken();
      }

      int nextInt() {

         return Integer.parseInt(next());
      }

      long nextLong() {

         return Long.parseLong(next());
      }

      double nextDouble() {

         return Double.parseDouble(next());
      }

      String nextLine() {

         String str = "";
         try {
            str = br.readLine();
         } catch (IOException e) {
            e.printStackTrace();
         }
         return str;
      }
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

import java.io.*;
import java.util.*;

public class EDIST {

   private static final int MAX_STR_LENGTH = 3000;
   private static int[][] DP = new int[MAX_STR_LENGTH][MAX_STR_LENGTH];

   public static void main(String[] args) throws java.lang.Exception {

      FastReader reader = new FastReader();
      Print print = new Print();
      int T = reader.nextInt();
      while (T-- > 0) {
         String A = reader.nextLine();
         String B = reader.nextLine();
         //print.println(editDistance(A, B, A.length() - 1, B.length() - 1));
         print.println(editDistanceDP(A, B));

      }
      print.close();
   }

   /**
    * @param m current index pos of String A
    * @param n current index pos of String B
    */
   private static int editDistance(String A, String B, int m, int n) {

      if (m < 0 && n < 0) {
         // both of them finished simultaneously
         return 0;
      } else if (m < 0 || n < 0) {
         // either A finished xor B finished
         // insert diff of characters(Validate)
         return Math.abs(m - n);
      }

      if (A.charAt(m) == B.charAt(n)) {
         return editDistance(A, B, m - 1, n - 1);
      } else {
         int deleteACharacter = editDistance(A, B, m - 1, n) + 1;
         int replaceACharacterWithBCharacter = editDistance(A, B, m - 1, n - 1) + 1;
         int insertCharacterIntoA = editDistance(A, B, m, n - 1) + 1;
         return Math.min(deleteACharacter, Math.min(replaceACharacterWithBCharacter, insertCharacterIntoA));
      }
   }

   private static int editDistanceDP(String A, String B) {
      //printDP(A.length(), B.length());
      for (int n = 0; n <= B.length(); n++) {
         for (int m = 0; m <= A.length(); m++) {
            //System.out.println("m " + m  + " n " + n);
            if ((m == 0)) {
               DP[n][m] = n;
            } else if ((n == 0)) {
               DP[n][m] = m;
            } else if (A.charAt(m - 1) == B.charAt(n - 1)) {
               DP[n][m] = DP[n-1][m-1];
            } else {
               // Get minimum from (n-1, m-1), (n-1, m), (n, m-1) and add 1 to it
               DP[n][m] = 1 + min(DP[n - 1][m - 1], DP[n - 1][m], DP[n][m - 1]);
            }
            //printDP(A.length(), B.length());
         }
      }
      return DP[B.length()][A.length()];
   }

   private static int min(int a, int b, int c) {

      return Math.min(a, Math.min(b, c));
   }

   private static void printDP(int m, int n) {

      System.out.println("----------");
      for (int i = n - 1; i >= 0; i--) {
         for (int j = 0; j < m; j++) {
            System.out.print(DP[i][j] + " ");
         }
         System.out.println();
      }
      System.out.println("----------");
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class AIBOHP {

   private static int[][] DP = new int[6101][6101];

   // aabb -> [bb]aabb 2
   // abc -> [cb]abc 2
   // cabc -> c[b]abc 1
   public static void main(String[] args) throws java.lang.Exception {

      FastReader reader = new FastReader();
      int t = reader.nextInt();
      Print print = new Print();
      while(t-- > 0){
         String S = reader.nextLine();
         print.println(costPallin(S, 0, S.length() - 1));
         print.println(costPallinDP2(S));

      }
      print.close();
   }

   private static int costPallin(String s, int l, int r) {

      if (l >= r) {
         return 0;
      } else if (s.charAt(l) == s.charAt(r)) {
         return costPallin(s, l + 1, r - 1);
      } else {
         return 1 + Math.min(costPallin(s, l + 1, r), costPallin(s, l, r - 1));
      }
   }

   private static int costPallinDP(String s) {

      for (int i = 0; i <= s.length(); i++) {
         for (int j = s.length(); j >= i - 1; j--) {
            System.out.println("i " + i + " j " + j);
            if (j == -1){
               // do nothing
            } else if (i == 0 && j == s.length()) {
               DP[i][j] = 0;
            } else if (i == 0) {
               DP[i][j] = j == 0 ? DP[i][j + 1] : DP[i][j + 1] + 1;
            } else if (j == s.length()) {
               DP[i][j] = i == s.length() ? DP[i - 1][j] : DP[i - 1][j] + 1;
            } else if (s.charAt(i - 1) == s.charAt(j)) {
               DP[i][j] = DP[i-1][j+1];
            } else {
               DP[i][j] = 1 + Math.min(DP[i - 1][j], DP[i][j + 1]);
            }
            printDP(s.length() + 1, s.length() + 1);
         }
      }
      int min = Integer.MAX_VALUE;
      int i = 1;
      int j = 0;
      while (i <= s.length() && j <= s.length()) {
         if(DP[i][j] < min) {
            min = DP[i][j];
         }
         i++;
         j++;
      }
      return min;
   }

   private static int costPallinDP2(String str){
      for(int i=0;i<str.length();i++)
         DP[i][i] = 0;

      //Find all length till the length of string
      for(int i=2;i<=str.length();i++) {

         //Now traverse all i length substring in string
         for(int j=0;j<str.length()-i+1;j++) {
            System.out.println("i " + i + " j " + j);
            //Find end of current string
            int e = j + i - 1;

            //if char are equal and length is 2 we need 0 operations
            if(str.charAt(e) == str.charAt(j) && i == 2)
               DP[j][e] = 0;
               //else if length is greater than 2 operations are equal to inside subproblem
            else if(str.charAt(e) == str.charAt(j))
               DP[j][e] = DP[j+1][e-1];
            else
               //else if char are not equal either we can insert at last or start
               //so it will minimum of these two operations
               DP[j][e] = 1 + Math.min(DP[j+1][e],DP[j][e-1]);
            printDP(str.length() + 1, str.length() + 1);
         }

      }
      return DP[0][str.length()-1];
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

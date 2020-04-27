import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author digvijay.singh
 */
public class ONP {

   public static void main(String[] args) throws java.lang.Exception {

      FastReader reader = new FastReader();
      Print print = new Print();
      int t = reader.nextInt();

      while (t > 0) {
         String expression = reader.next();
         Stack<Character> stack = new Stack<Character>();
         for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
               // do nothing
            } else if (expression.charAt(i) == ')') {
               print.print(stack.pop());
            } else if (Character.getType(expression.charAt(i)) == Character.LOWERCASE_LETTER) {
               print.print(expression.charAt(i));
            } else {
               stack.push(expression.charAt(i));
            }
         }
         print.println("");
         t--;
      }
      print.close();
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


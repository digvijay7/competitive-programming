import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class STPAR {

   private static int[] TRUCKS = new int[1000];
   private static final String NO = "no";
   private static final String YES = "yes";

   public static void main(String[] args) throws java.lang.Exception {

      FastReader reader = new FastReader();
      int n = reader.nextInt();
      Print print = new Print();
      while (n != 0) {
         for (int i = 0; i < n; i++) {
            TRUCKS[i] = reader.nextInt();
         }
         print.println(orderingPossible(n));
         n = reader.nextInt();
      }
      print.close();
   }

   private static String orderingPossible(int n) {

      LinkedList<Integer> queue = new LinkedList<>();
      Stack<Integer> stack = new Stack<>();
      int i = 0;
      int current = 1;
      while (current != n) {
         //System.out.println("Queue: " + queue + " Stack: " + stack);
         if (!stack.empty() && current == stack.peek()) {
            queue.add(stack.pop());
            current++;
         } else if (current == TRUCKS[i]) {
            queue.add(current);
            current++;
            i++;
         } else {

            if (stack.empty()) {
               stack.push(TRUCKS[i]);
               i++;
            } else if (stack.peek() < TRUCKS[i]) {
               return NO;
            } else {
               stack.push(TRUCKS[i]);
               i++;
            }
         }
      }
      return YES;
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

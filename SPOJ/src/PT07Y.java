import java.io.*;
import java.util.*;

public class PT07Y {

   private static final int MAX_SIZE = 10001;
   private static List<List<Integer>> GRAPH = new ArrayList<>(MAX_SIZE);

   public static void main(String[] args) throws java.lang.Exception {

      FastReader reader = new FastReader();
      for (int i = 0; i < MAX_SIZE; i++) {
         GRAPH.add(new LinkedList<>());
      }
      int N = reader.nextInt();
      int M = reader.nextInt();
      int root = -1;
      for (int i = 0; i < M; i++) {
         int u = reader.nextInt();
         int v = reader.nextInt();
         GRAPH.get(u).add(v);
         GRAPH.get(v).add(u);
         root = u;
      }
      boolean isTree = isTree(N, M, root);
      Print print = new Print();
      String output = isTree ? "YES" : "NO";
      print.println(output);
      print.close();
   }

   private static boolean isTree(int N, int M, int root) {

      if (N == 1) {
         return true;
      } else if (N - 1 != M) {
         return false;
      }
      Set<Integer> visited = new HashSet<>(N);
      connected(root, visited);
      return visited.size() == N;
   }

   private static void connected(int root, Set<Integer> visited) {

      visited.add(root);
      for (Integer child : GRAPH.get(root)) {
         if (!visited.contains(child)) {
            connected(child, visited);
         }
      }
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

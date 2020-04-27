import java.io.*;
import java.util.*;

public class PT07Z {

   private static final int MAX_SIZE = 10001;
   private static final int MIN_LENGTH_OF_LONGEST_PATH = 0;
   private static final int MIN_HEIGHT = 0;
   static List<List<Integer>> GRAPH = new ArrayList<>(MAX_SIZE);

   public static void main(String[] args) throws java.lang.Exception {

      FastReader reader = new FastReader();
      for (int i = 0; i < MAX_SIZE; i++) {
         GRAPH.add(new LinkedList<>());
      }
      int N = reader.nextInt();
      int root = -1;
      for (int i = 0; i < N - 1; i++) {
         int x = reader.nextInt();
         int y = reader.nextInt();
         GRAPH.get(x).add(y);
         GRAPH.get(y).add(x);
         if (root == -1 && GRAPH.get(x).size() >= 2) {
            root = x;
         } else if (root == -1 && GRAPH.get(y).size() >= 2) {
            root = y;
         }
      }

      Print print = new Print();
      if (N == 1) {
         print.println(0);
      } else if (N == 2) {
         print.println(1);
      } else {
         Pair output = longestPathLength(root, new HashSet<>(MAX_SIZE));
         print.println(output.max);
      }
      print.close();
   }

   static Pair longestPathLength(int root, Set<Integer> visited){

      //System.out.println(">> At root: " + root);
      visited.add(root);
      LinkedList<Pair> outputs2 = new LinkedList<>();
      for (Integer child: GRAPH.get(root)) {
         if (!visited.contains(child)) {
            //System.out.println(">> Visiting root: " + root + " child: " + child);
            Pair out = longestPathLength(child, visited);
            outputs2.add(out);
            //System.out.println(">> Visiting root: " + root + " child: " + child + " output: " + out);
         }
      }
      //System.out.println(">> Child outputs: " + outputs2);
      if (outputs2.isEmpty()) { // leaf node
         return new Pair(MIN_LENGTH_OF_LONGEST_PATH, MIN_HEIGHT);
      } else if (outputs2.size() == 1) {
         Pair output = outputs2.poll();
         return new Pair(output.max, output.maxHeight + 1);
      } else {

         int outputsMax = MIN_LENGTH_OF_LONGEST_PATH;
         int firstMaxHeight = MIN_HEIGHT;
         int secondMaxHeight= MIN_HEIGHT;
         for (Pair output : outputs2) {
            if (output.max > outputsMax) {
               outputsMax = output.max;
            }
            if (firstMaxHeight < output.maxHeight) {
               secondMaxHeight = firstMaxHeight;// shift down the max value
               firstMaxHeight = output.maxHeight;
            } else if (secondMaxHeight < output.maxHeight) { // output.maxHeight is not greater than first but maybe the second one
               secondMaxHeight = output.maxHeight;
            }
         }
         int nodeHeight = firstMaxHeight + 1;
         //System.out.println("Visiting root: " + root + " maxChildHeight1: " + maxHeight + " maxChildHeight2: " + secondMaxHeight);
         return new Pair(Integer.max(outputsMax, firstMaxHeight + secondMaxHeight + 2), nodeHeight);
      }
   }

   static class Pair {

      int max;
      int maxHeight;

      public Pair(int max, int maxHeight) {

         this.max = max;
         this.maxHeight = maxHeight;
      }

      @Override
      public String toString() {

         return "Pair{" + "max=" + max + ", maxHeight=" + maxHeight + '}';
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

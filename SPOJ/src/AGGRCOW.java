import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.Function;

/**
 * @author digvijay.singh
 */
public class AGGRCOW {

   public static void main(String[] args) throws java.lang.Exception {

      FastReader reader = new FastReader();
      int t = reader.nextInt();
      int[] barn = new int[100000];
      Print print = new Print();
      while (t > 0) {
         int N = reader.nextInt();
         int C = reader.nextInt();
         for (int i = 0; i < N; i++) {
            barn[i] = reader.nextInt();
         }
         Arrays.sort(barn, 0, N);
         //print.println(Arrays.toString(barn));
         int maxMinDistance = BinarySearch
               .binarySearch(0, 1000000000, dist -> assignmentPossible(dist, barn, N, C));
         print.println(maxMinDistance);
         t--;
      }
      print.close();
   }

   private static Integer assignmentPossible(int dist, int[] barn, int N, int C) {

      int prevIndex = 0;
      int count = 1;
      for (int i = 1; i < N; i++) {
         if ((barn[i] - barn[prevIndex]) >= dist) {
            prevIndex = i;
            count++;
         }
      }
      return count >= C ? 1 : 0;
   }

   static class BinarySearch {

      // Compare returns 1 if assignment is possible and 0 if not possible
      // If assignment is possible then look between mid to right to find a better solution
      // Else look between left to right to find any solution
      public static int binarySearch(int left, int right, Function<Integer, Integer> compare) {

         int lastSolution = -1;
         while (left < right) {
            int mid = (left + right) / 2;
            //System.out.println(">>>>> mid" + mid);
            if (compare.apply(mid) == 1) {
               left = mid + 1;
               lastSolution = mid;
            } else {
               right = mid;
            }
         }
         return lastSolution;
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

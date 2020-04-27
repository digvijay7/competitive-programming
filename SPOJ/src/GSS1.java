import java.io.*;
import java.util.*;

class GSS1 {

   static final int NP = Integer.MIN_VALUE;
   static final int ARR_SIZE = 200000;
   private static int[] initSum = new int[ARR_SIZE];
   private static int[] initPfxSum = new int[ARR_SIZE];
   private static int[] initSfxSum = new int[ARR_SIZE];
   private static int[] initMax = new int[ARR_SIZE];
   private static int[] sum = new int[ARR_SIZE];
   private static int[] max = new int[ARR_SIZE];
   private static int[] pfxSum = new int[ARR_SIZE];
   private static int[] sfxSum = new int[ARR_SIZE];

   public static void main(String[] args) throws java.lang.Exception {

      FastReader reader = new FastReader();
      int N = reader.nextInt();
      int[] input = new int[50000];
      for (int i = 0; i < N; i++) {
         input[i] = reader.nextInt();
      }
      build(input, N, 0, 0, N - 1);
      Print print = new Print();
      int M = reader.nextInt(), x, y;
      while (M > 0) {
         x = reader.nextInt();
         y = reader.nextInt();
         print.println(query(N, x - 1, y - 1));
         M--;
      }
      print.close();
   }

   private static void build(int[] input, int N, int node, int start, int end) {

      if (start == end) {
         initSum[node] = initMax[node] = initPfxSum[node] = initSfxSum[node] = input[start];
      } else {
         int mid = (start + end) / 2;
         int left = 2 * node + 1;
         int right = 2 * node + 2;
         build(input, N, left, start, mid);
         build(input, N, right, mid + 1, end);

         initSum[node] = initSum[left] + initSum[right];
         initPfxSum[node] = max(initPfxSum[left], initSum[left] + initPfxSum[right]);
         initSfxSum[node] = max(initSfxSum[right], initSum[right] + initSfxSum[left]);
         initMax[node] = max(initMax[left], initMax[right], initPfxSum[right] + initSfxSum[left]);
      }
   }

   private static int query(int N, int l, int r) {

      query(l, r, N, 0, 0, N - 1);
      return max[0];
   }

   private static void query(int l, int r, int N, int node, int start, int end) {

      /*System.out.println(String.format("%d %d %d %d %d", l, r, node, start, end));*/
      if (l > end || r < start) {
         max[node] = pfxSum[node] = sfxSum[node] = sum[node] = NP;
      } else {

         if (l <= start && end <= r) {

            max[node] = initMax[node];
            pfxSum[node] = initPfxSum[node];
            sfxSum[node] = initSfxSum[node];
            sum[node] = initSum[node];

         } else {

            int mid = (start + end) / 2;
            int left = 2 * node + 1;
            int right = 2 * node + 2;
            query(l, r, N, left, start, mid);
            query(l, r, N, right, mid + 1, end);
            int pfxSumVal = max(pfxSum[left], sum(sum[left], pfxSum[right]));
            int sfxSumVal = max(sfxSum[right], sum(sum[right], sfxSum[left]));
            max[node] = max(max[left], max[right], sum(sfxSum[left], pfxSum[right]));
            sum[node] = sum(sum[left], sum[right]);
            pfxSum[node] = pfxSumVal;
            sfxSum[node] = sfxSumVal;
         }
      }
   }

   private static int max(int... elems) {

      int max = Integer.MIN_VALUE;
      for (int i = 0; i < elems.length; i++) {
         max = elems[i] >= max ? elems[i] : max;
      }
      return max;
   }

   private static int sum(int a, int b) {

      long sum = (long) a + (long) b;
      if (sum < Integer.MIN_VALUE) {
         return NP;
      }
      return (int) sum;
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

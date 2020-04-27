import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class DQUERY {

   public static void main(String[] args) throws java.lang.Exception {

      FastReader reader = new FastReader();
      int n = reader.nextInt();
      int[] input = new int[n];
      for (int i = 0; i < n; i++) {
         input[i] = reader.nextInt();
      }
      SegTreeNode root = createSegTree(input);
      int q = reader.nextInt();
      Print print = new Print();
      for (int i = 0; i < q; i++) {
         int x = reader.nextInt();
         int y = reader.nextInt();
         print.println(dCount(root, x - 1, y - 1));
      }
      print.close();
   }

   private static SegTreeNode createSegTree(int[] input) {

      return build(input, 0, input.length - 1);
   }

   private static SegTreeNode build(int[] input, int start, int end) {

      if (start == end) {
         return new SegTreeNode(Collections.singletonList(input[start]), null, null, start, end);
      } else {
         int mid = (start + end) / 2;
         SegTreeNode left = build(input, start, mid);
         SegTreeNode right = build(input, mid + 1, end);
         List<Integer> merged = merge(left.elems, right.elems);
         return new SegTreeNode(merged, left, right, start, end);
      }
   }

   private static List<Integer> merge(List<Integer> leftInput, List<Integer> rightInput) {

      List<Integer> output = new ArrayList<>(leftInput.size() + rightInput.size());
      int i = 0, j = 0, prev = Integer.MIN_VALUE;
      while (i < leftInput.size() && j < rightInput.size()) {
         if (leftInput.get(i) <= rightInput.get(j)) {
            if (leftInput.get(i) != prev) {
               output.add(leftInput.get(i));
               prev = leftInput.get(i);
            }
            i++;
         } else {
            if (rightInput.get(j) != prev) {
               output.add(rightInput.get(j));
               prev = rightInput.get(j);
            }
            j++;
         }
      }
      while (i < leftInput.size()) {
         if (leftInput.get(i) != prev) {
            output.add(leftInput.get(i));
            prev = leftInput.get(i);
         }
         i++;
      }
      while (j < rightInput.size()) {
         if (rightInput.get(j) != prev) {
            output.add(rightInput.get(j));
            prev = rightInput.get(j);
         }
         j++;
      }
      //System.out.println("[MERGE] Left: " + leftInput + " Right: " + rightInput + " Output: " + output);
      return output;
   }

   static class SegTreeNode {

      private List<Integer> elems;
      private SegTreeNode left, right;
      private int start, end;

      public SegTreeNode(List<Integer> elems, SegTreeNode left, SegTreeNode right, int start, int end) {

         this.elems = elems;
         this.left = left;
         this.right = right;
         this.start = start;
         this.end = end;
      }
   }

   private static int dCount(SegTreeNode root, int l, int r) {

      return query(root, l, r).size();
   }

   private static List<Integer> query(SegTreeNode root, int l, int r) {

      if (root == null) {

         return Collections.emptyList();

      } else if (root.start > r || l > root.end) {

         return Collections.emptyList();

      } else if (l <= root.start && root.end <= r) {

         return root.elems;

      } else {

         List<Integer> left = query(root.left, l, r);
         List<Integer> right = query(root.right, l, r);
         return merge(left, right);
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

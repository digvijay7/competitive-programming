import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 *
 * Sample input:
11
8118
123
11
7
9
323111
123356
0
56442
56471
199

 Sample output:
 8228
 131
 22
 8
 11
 323323
 124421
 1
 56465
 56565
 202
 * @author digvijay.singh
 */
public class PALIN {


   public static void main(String[] args) throws java.lang.Exception {

      FastReader reader = new FastReader();
      Print printer = new Print();
      long t = reader.nextLong();
      char[] number = new char[1000001];
      while (t > 0) {
         String strNum = reader.nextLine();
         int numSize = strNum.length() + 1; // Added one for padding
         int padding = 1;
         strNum.getChars(0, strNum.length(), number, 1);
         number[0] = '0';
         int i = numSize - 1, j;
         // We add one by default to handle the case number is already a pallindrome
         if (addOneAtIndex(number, numSize - 1) == 0) {
            padding--;
         }
         while (i >= (numSize + 1) / 2) {
            j = numSize - i - 1 + padding;
            //System.out.println(">>>>> start i " + i);
            if (number[i] == number[j]) {
               // then do nothing
               i--;
            } else if (number[i] < number[j]) {
               number[i] = number[j];
               i--;
            } else if (number[i] > number[j]) {
               int indexUpdated = addOneAtIndex(number, i - 1);
               if (indexUpdated == 0) {
                  padding--;
               }
               number[i] = number[j];
               if (indexUpdated < (numSize + 1) / 2) {
                  i = numSize - indexUpdated - 1 + padding;
               } else {
                  i--;
               }
               //i = numSize - 1 ; // Start processing from the beginning
            }
            //print(printer, number, numSize - 1, padding);
            //System.out.println(">>>>> end i " + i);
         }
         print(printer, number, numSize - 1, padding);
         t--;
      }
      printer.close();
   }

   static void print(Print pinter, char[] number, int from, int to) throws IOException {

      int delta = from - to > 0 ? -1 : 1;
      for (int i = from; i != to + delta; i = i + delta) {
         pinter.print(number[i]);
      }
      pinter.println("");
   }

   static int addOneAtIndex(char[] number, int index) {
      int ret;
      if (number[index] == '9') {
         number[index] = '0';
         ret = addOneAtIndex(number, index - 1);
      } else if (index == 0) {
         number[index] = '1';
         ret = index;
      } else {
         number[index]++;
         ret = index;
      }
      return ret;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

   void godzillaWin() {
      System.out.println("Godzilla");
   }

   void mechaGodzillaWin() {
      System.out.println("MechaGodzilla");
   }

   void draw() {
      System.out.println("uncertain");
   }

   private static void readExtra(Scanner s, int times) {
      while (times > 0){
         s.nextInt();
         times--;
      }
   }

   public static void main(String[] args) throws IOException {

      Main a = new Main();
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      //Scanner s = new Scanner(System.in);
      int T = Integer.valueOf(in.readLine());
      while (T > 0) {
         in.readLine();//discard line
         //s.nextLine();
         String[] ngNm = in.readLine().split("\\s+");
         int ng = Integer.valueOf(ngNm[0]);
         int nm = Integer.valueOf(ngNm[1]);
         if (ng == 0 && nm == 0) {
            a.draw();
         } else if (ng == 0 && nm > 0) {
            a.mechaGodzillaWin();
            in.readLine();
         } else if (ng > 0 && nm == 0){
            a.godzillaWin();
            in.readLine();
         } else {

            String[] ngArmy = in.readLine().split("\\s+");
            int maxNg = Integer.valueOf(ngArmy[0]);
            for (int i = 1; i < ngArmy.length && i < ng; i++) {
               maxNg = Math.max(Integer.valueOf(ngArmy[i]), maxNg);
            }
            String[] nmArmy = in.readLine().split("\\s+");
            int maxNm = Integer.valueOf(nmArmy[0]);
            for (int i = 1; i < nmArmy.length && i < nm; i++) {
               maxNm = Math.max(Integer.valueOf(nmArmy[i]), maxNm);
            }
            if (maxNg >= maxNm) {
               a.godzillaWin();
            } else {
               a.mechaGodzillaWin();
            }
         }
         T--;
      }
   }
}

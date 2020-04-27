import java.util.Scanner;
public class Tricount {
   public static long[] c=new long[1000001];
   public static long t(int n) {long m=n;long a=0;while(m>0){a+=c[n]!=0?c[n]:(m*(m+1)/2)+(((m/2+(m%2))*((m-1)%2+m-1))/2);m--;n--;}return a;}
   public static void main(String[] args) throws Exception {
      Scanner s=new Scanner(System.in);
      int T=s.nextInt();
      while(T>0){System.out.println(t(s.nextInt()));T--;}
   }
}

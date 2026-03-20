public class RabinKarp {

   public final static int d = 256;

   public static boolean search(String pattern, String txt) {
       int q = 101;

       int m = pattern.length();
       int n = txt.length();

       if (m > n) return false;

       int p = 0;
       int t = 0;
       int h = 1;

       for (int i = 0; i < m - 1; i++)
           h = (h * d) % q;

       for (int i = 0; i < m; i++) {
           p = (d * p + pattern.charAt(i)) % q;
           t = (d * t + txt.charAt(i)) % q;
       }

       for (int i = 0; i <= n - m; i++) {

           if (p == t) {
               boolean match = true;

               for (int j = 0; j < m; j++) {
                   if (txt.charAt(i + j) != pattern.charAt(j)) {
                       match = false;
                       break;
                   }
               }

               if (match) return true;
           }

           if (i < n - m) {
               t = (d * (t - txt.charAt(i) * h) + txt.charAt(i + m)) % q;
               if (t < 0) t += q;
           }
       }

       return false;
   }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlagiarismChecker {

   public static void main(String[] args) {

       try {
           File originalFile = new File("src/main/resources/poem.txt");
           File plagFile = new File("src/main/resources/cheat.txt");

           Scanner originalScanner = new Scanner(originalFile);
           Scanner plagScanner = new Scanner(plagFile);

           ArrayList<String> originalLines = new ArrayList<>();
           ArrayList<String> plagLines = new ArrayList<>();

           // READ + CLEAN original file
           while (originalScanner.hasNextLine()) {
               String line = originalScanner.nextLine()
                       .toLowerCase()
                       .replaceAll("[^a-z ]", "")
                       .trim();

               if (!line.isEmpty()) {
                   originalLines.add(line);
               }
           }

           // READ + CLEAN plagiarized file
           while (plagScanner.hasNextLine()) {
               String line = plagScanner.nextLine()
                       .toLowerCase()
                       .replaceAll("[^a-z ]", "")
                       .trim();

               if (!line.isEmpty()) {
                   plagLines.add(line);
               }
           }

           originalScanner.close();
           plagScanner.close();

           // CHECK FOR MATCHES
           for (String pattern : plagLines) {

               for (int i = 0; i < originalLines.size(); i++) {
                   String text = originalLines.get(i);

                   if (rabinKarp(pattern, text)) {
                       System.out.println("MATCH FOUND");
                       System.out.println("Pattern: " + pattern);
                       System.out.println("Line: " + (i + 1));
                       System.out.println("------------------");
                   }
               }
           }

       } catch (FileNotFoundException e) {
           System.out.println("Error: File not found.");
       }
   }

   // ✅ Rabin-Karp inside same file
   public static boolean rabinKarp(String pattern, String text) {
       int d = 256;
       int q = 101;

       int m = pattern.length();
       int n = text.length();

       if (m > n) return false;

       int p = 0, t = 0, h = 1;

       for (int i = 0; i < m - 1; i++) {
           h = (h * d) % q;
       }

       for (int i = 0; i < m; i++) {
           p = (d * p + pattern.charAt(i)) % q;
           t = (d * t + text.charAt(i)) % q;
       }

       for (int i = 0; i <= n - m; i++) {

           if (p == t) {
               boolean match = true;

               for (int j = 0; j < m; j++) {
                   if (text.charAt(i + j) != pattern.charAt(j)) {
                       match = false;
                       break;
                   }
               }

               if (match) return true;
           }

           if (i < n - m) {
               t = (d * (t - text.charAt(i) * h) + text.charAt(i + m)) % q;
               if (t < 0) t += q;
           }
       }

       return false;
   }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlagiarismChecker {

   public static void main(String[] args) {

       try {
        // creates the file objects for original and plagiarized texts
           File originalFile = new File("src/main/resources/poem.txt");
           File plagFile = new File("src/main/resources/cheat.txt");

        // creates scanners to read the created files
           Scanner originalScanner = new Scanner(originalFile);
           Scanner plagScanner = new Scanner(plagFile);

        // lists are created to store lines from each file
           ArrayList<String> originalLines = new ArrayList<>();
           ArrayList<String> plagLines = new ArrayList<>();

           // read and clean original file
           while (originalScanner.hasNextLine()) {
               String line = originalScanner.nextLine()
                       .toLowerCase()                   // ignores capitalizations
                       .replaceAll("[^a-z ]", "")       // ignores punctuations
                       .trim();                         // removes extra spaces
                // only add non-empty lines
               if (!line.isEmpty()) {
                   originalLines.add(line);
               }
           }

           // read and clean plagiarized file
           while (plagScanner.hasNextLine()) {
               String line = plagScanner.nextLine()
                       .toLowerCase()
                       .replaceAll("[^a-z ]", "")
                       .trim();

               if (!line.isEmpty()) {
                   plagLines.add(line);
               }
           }
            // closes the scanners
           originalScanner.close();
           plagScanner.close();

           // compares each line in plagiarized file to each original line
           for (String pattern : plagLines) {

               for (int i = 0; i < originalLines.size(); i++) {
                   String text = originalLines.get(i);
                // uses rabin-karp method to check for patterns in texts
                   if (rabinKarp(pattern, text)) {
                       System.out.println("MATCH FOUND");
                       System.out.println("Pattern: " + pattern);
                       System.out.println("Line: " + (i + 1));
                       System.out.println("------------------");
                   }
               }
           }

       } catch (FileNotFoundException e) {
        // handles possibility if file isn't found
           System.out.println("Error: File not found.");
       }
   }

   // Rabin-Karp algorithm
   // returns true if pattern is found in text
   public static boolean rabinKarp(String pattern, String text) {
       int d = 256; // number of characters
       int q = 101; // prime number for hashtag

       int m = pattern.length();
       int n = text.length();

       if (m > n) return false; // if pattern is longer than the text, returns false

       int p = 0, t = 0, h = 1; // value for pattern, text, and removing leading character

       for (int i = 0; i < m - 1; i++) {
           h = (h * d) % q;
       }

       for (int i = 0; i < m; i++) { // calculate hash value for pattern and text
           p = (d * p + pattern.charAt(i)) % q;
           t = (d * t + text.charAt(i)) % q;
       }
        // check pattern across text
       for (int i = 0; i <= n - m; i++) {
        // find the match if present
           if (p == t) {
               boolean match = true;

               for (int j = 0; j < m; j++) {
                   if (text.charAt(i + j) != pattern.charAt(j)) {
                       match = false;
                       break;
                   }
               }
                // if full match found, then returns true
               if (match) return true;
           }
                // calculate next window
           if (i < n - m) {
               t = (d * (t - text.charAt(i) * h) + text.charAt(i + m)) % q;
               if (t < 0) t += q; // hash value must be positive
           }
       }

       return false; // false is returned if no match is found
   }
}

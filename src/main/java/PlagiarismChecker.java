import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlagiarismChecker {

  public static void main(String[] args) {

      try {
          File originalFile = new File("poem.txt");
          File plagFile = new File("cheat.txt");

          Scanner originalScanner = new Scanner(originalFile);
          Scanner plagScanner = new Scanner(plagFile);

          ArrayList<String> originalLines = new ArrayList<>();
          ArrayList<String> plagLines = new ArrayList<>();

          // read original
          while (originalScanner.hasNextLine()) {
              originalLines.add(originalScanner.nextLine().toLowerCase().trim());
          }

          // read plagiarized
          while (plagScanner.hasNextLine()) {
              plagLines.add(plagScanner.nextLine().toLowerCase().trim());
          }

          originalScanner.close();
          plagScanner.close();

          // 🔥 THIS LOOP WAS MISSING
          for (int i = 0; i < plagLines.size(); i++) {
              String pattern = plagLines.get(i);

              for (int j = 0; j < originalLines.size(); j++) {
                  String text = originalLines.get(j);

                  if (pattern.length() > 5 && RabinKarp.search(pattern, text)) {
                      System.out.println("Plagiarism detected!");
                      System.out.println("Pattern: " + pattern);
                      System.out.println("Found on line: " + (j + 1));
                      System.out.println("------------------");
                  }
              }
          }

      } catch (FileNotFoundException e) {
          System.out.println("Error: File not found.");
      }
  }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PlagiarismChecker {
    
    public static void main(String[] args) {
        try {
            File originalFile = new File("poem.txt");
            File plagarizedFile = new File("cheat.txt");

            Scanner originalScanner = new Scanner(originalFile);
            Scanner plagScanner = new Scanner(plagarizedFile);

            List<String> originalLines = new ArrayList<>();
            List<String> plagLines = new ArrayList<>();

            while (originalScanner.hasNextLine()) {
                originalLines.add(originalScanner.nextLine());
            }

            while (plagScanner.hasNextLine()) {
                plagLines.add(plagScanner.nextLine());
            }

            originalScanner.close();
            plagScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
        }
    }
}
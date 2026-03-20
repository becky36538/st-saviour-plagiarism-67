import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Detector {
    public static void main(String[] args) {
        try {
            
            File cheat = new File("cheat.txt");
            Scanner scanner = new Scanner(cheat);

            // contains access to file
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }

            scanner.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
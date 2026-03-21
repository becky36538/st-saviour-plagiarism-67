import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Detector {
    public static void main(String[] args) {
        try {
            // creates file object that directs to cheat.txt
            File cheat = new File("cheat.txt");
            
            // creates scanner to read file
            Scanner scanner = new Scanner(cheat);
            
            // loop each line in file
            while (scanner.hasNextLine()) {
                // each line is printed in scanner
                System.out.println(scanner.nextLine());
            }
            // closes the scanner
            scanner.close();
            
        } catch (FileNotFoundException e) {
            // this message is shown if file is not found 
            System.out.println("File not found");
        }
    }
}
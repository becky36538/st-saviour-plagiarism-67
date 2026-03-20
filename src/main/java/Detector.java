import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Detector {
    public static void main(String[] args) {
        File cheat = new File("./resources/cheat.txt");
        try {
            Scanner scanner = new Scanner(cheat);

            // contains access to file
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
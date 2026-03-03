public class Detector {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(poem);

            // contains access to file
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } finally (

        )
    }
}
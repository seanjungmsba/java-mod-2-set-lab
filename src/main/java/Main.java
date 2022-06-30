import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            boolean processing = true;
            while (processing) {
                Runner.selectOptions(sc);
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
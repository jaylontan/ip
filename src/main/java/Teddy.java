import java.util.Scanner;

public class Teddy {
    public static void main(String[] args) {
        String seperator = "_".repeat(60);
        System.out.println(seperator);
        System.out.println("Hello! I'm Teddy\nWhat can I do for you?");
        System.out.println(seperator);
        Scanner sc = new Scanner(System.in);
        String response = sc.nextLine();
        System.out.println(response);
    }
}

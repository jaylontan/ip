import java.util.Scanner;
import java.util.ArrayList;

public class Teddy {
    public static void main(String[] args) {
        // chatbot greeting
        String seperator = "_".repeat(60);
        System.out.println(seperator + "\nHello! I'm Teddy\nWhat can I do for you?\n" + seperator);

        // creating list
        ArrayList<String> list = new ArrayList<>();

        // reading input from user
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(seperator + "\nBye! Hope to see you again soon!\n" + seperator);
                break;
            }

            System.out.println(seperator + "\n" + "added: " + input + "\n" + seperator);
            list.add(input);
        }
    }
}

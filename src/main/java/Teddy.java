import java.util.Scanner;
import java.util.ArrayList;

public class Teddy {
    public static void main(String[] args) {
        // chatbot greeting
        String seperator = "_".repeat(60);
        System.out.println(seperator + "\nHello! I'm Teddy\nWhat can I do for you?\n" + seperator);

        // creating list
        ArrayList<Task> list = new ArrayList<>();

        // reading input from user
        Scanner sc = new Scanner(System.in);
        int count = 1;

        while (true) {
            String input = sc.nextLine();
            String[] parts = input.split(" ");
            String command = parts[0];
            if (command.equalsIgnoreCase("bye")) {
                System.out.println(seperator + "\nBye! Hope to see you again soon!\n" + seperator);
                break;
            } else if (command.equalsIgnoreCase("list")) { // list out all items
                System.out.println(seperator);
                for (Task task : list) {
                    System.out.println(task.getIndex() + ". " + task.toString());
                }
                System.out.println(seperator);
            } else if (command.equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(parts[1]);
                list.get(index - 1).mark();
                System.out.println(seperator + "\nNice! I've marked this task as done:\n   " +
                        list.get(index - 1).toString() + "\n" + seperator);
            } else if (command.equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(parts[1]);
                list.get(index - 1).unmark();
                System.out.println(seperator + "\nOK, I've marked this task as not done yet:\n   " +
                        list.get(index - 1).toString() + "\n" + seperator);
            } else { // add task to list
                Task task = new Task(input, count);
                System.out.println(seperator + "\nadded: " + task.toString() + "\n" + seperator);
                list.add(task);
                count++;
            }
        }
    }
}

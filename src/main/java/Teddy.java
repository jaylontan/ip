import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Teddy {

    private static final String SEPERATOR =  "_".repeat(60);
    private static List<Task> list = new ArrayList<>();
    private static int count  = 1;

    public static void main(String[] args) {
        // chatbot greeting
        System.out.println(SEPERATOR + "\nHello! I'm Teddy\nWhat can I do for you?\n" + SEPERATOR);

        // reading input from user
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            String[] parts = input.split(" ");
            String command = parts[0];
            if (command.equalsIgnoreCase("bye")) { // exit command
                System.out.println(SEPERATOR + "\nBye! Hope to see you again soon!\n" + SEPERATOR);
                break;
            } else if (command.equalsIgnoreCase("list")) { // list out all items
                listTasks();
            } else if (command.equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(parts[1]);
                markAsDone(index);
            } else if (command.equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(parts[1]);
                unmark(index);
            } else { // add task to list
                addTask(input, count);
                count++;
            }
        }
    }

    public static void listTasks() {
        System.out.println(SEPERATOR);
        for (Task task : list) {
            System.out.println(task.getIndex() + ". " + task);
        }
        System.out.println(SEPERATOR);
    }

    public static void markAsDone(int index) {
        list.get(index - 1).mark();
        System.out.println(SEPERATOR + "\nNice! I've marked this task as done:\n   " +
                list.get(index - 1).toString() + "\n" + SEPERATOR);
    }

    public static void unmark(int index) {
        list.get(index - 1).unmark();
        System.out.println(SEPERATOR + "\nOK, I've marked this task as not done yet:\n   " +
                list.get(index - 1).toString() + "\n" + SEPERATOR);
    }

    public static void addTask(String input, int count) {
        Task task = new Task(input, count);
        System.out.println(SEPERATOR + "\nadded: " + task + "\n" + SEPERATOR);
        list.add(task);
    }
}

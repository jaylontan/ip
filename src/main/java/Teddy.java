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
            String[] parts = input.split(" ", 2);
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
            } else if (command.equalsIgnoreCase("todo")) {
                String task = parts[1];
                addTodo(task, count);
                count++;
            } else if (command.equalsIgnoreCase("deadline")) {
                addDeadline(parts[1], count);
                count++;
            } else if (command.equalsIgnoreCase("event")) {
                addEvent(parts[1], count);
                count++;
            } else { // add task to list
                addTask(input, count);
                count++;
            }
        }
    }

    public static void listTasks() {
        System.out.println(SEPERATOR + "\nHere are the tasks in your list:");
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

    public static void addTodo(String input, int count) {
        Todo todo = new Todo(input, count);
        list.add(todo);
        if (list.size() > 1) {
            System.out.println(SEPERATOR + "\nGot it, I've added this task:\n  " + todo
                    + "\nNow you have " + list.size() + " tasks in the list.\n" + SEPERATOR);
        } else {
            System.out.println(SEPERATOR + "\nGot it, I've added this task:\n  " + todo
                    + "\nNow you have " + list.size() + " task in the list.\n" + SEPERATOR);
        }
    }

    public static void addDeadline(String input, int count) {
        String[] split = input.split("/");
        String task = split[0].trim();
        String time = split[1].split(" ", 2)[1];
        Deadline deadline = new Deadline(task, count, time);
        list.add(deadline);
        if (list.size() > 1) {
            System.out.println(SEPERATOR + "\nGot it, I've added this task:\n  " + deadline
                    + "\nNow you have " + list.size() + " tasks in the list.\n" + SEPERATOR);
        } else {
            System.out.println(SEPERATOR + "\nGot it, I've added this task:\n  " + deadline
                    + "\nNow you have " + list.size() + " task in the list.\n" + SEPERATOR);
        }
    }

    public static void addEvent(String input, int count) {
        String[] split = input.split("/");
        String task = split[0].trim();
        String start = split[1].trim().split(" ", 2)[1];
        String end = split[2].trim().split(" ", 2)[1];
        Event event = new Event(task, count, start, end);
        list.add(event);
        if (list.size() > 1) {
            System.out.println(SEPERATOR + "\nGot it, I've added this task:\n  " + event
                    + "\nNow you have " + list.size() + " tasks in the list.\n" + SEPERATOR);
        } else {
            System.out.println(SEPERATOR + "\nGot it, I've added this task:\n  " + event
                    + "\nNow you have " + list.size() + " task in the list.\n" + SEPERATOR);
        }
    }
}

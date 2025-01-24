import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Teddy {

    private static final String SEPERATOR =  "_".repeat(60);
    private static final String FILE_PATH = "./data/teddy.txt";
    private static final String DIRECTORY_PATH = "./data";
    private static final List<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        // chatbot greeting
        System.out.println(SEPERATOR + "\nHello! I'm Teddy\nWhat can I do for you?\n" + SEPERATOR);

        // reading input from user
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String input = sc.nextLine();
                String[] parts = input.split(" ", 2);
                Command command = Command.fromString(parts[0]);

                switch (command) {
                    case BYE:
                        System.out.println(SEPERATOR + "\nBye! Hope to see you again soon!\n" + SEPERATOR);
                        return;
                    case LIST:
                        listTasks();
                        break;
                    case MARK:
                        markAsDone(parts);
                        break;
                    case UNMARK:
                        unmark(parts);
                        break;
                    case TODO:
                        addTodo(parts, list.size() + 1);
                        break;
                    case DEADLINE:
                        addDeadline(parts, list.size() + 1);
                        break;
                    case EVENT:
                        addEvent(parts[1], list.size() + 1);
                        break;
                    case DELETE:
                        deleteTask(parts);
                        break;
                    default:
                        throw new TeddyException("I don't understand the command: " + parts[0]);
                }
            } catch (TeddyException e) {
                System.out.println(SEPERATOR + "\n" + e.getMessage() + "\n" + SEPERATOR);
            } catch (Exception e) {
                System.out.println(SEPERATOR + "\nSomething went wrong. Please try again.\n" + SEPERATOR);
            }
        }
    }

    public static void listTasks() {
        System.out.println(SEPERATOR + "\nHere are the tasks in your list:");
        int count = 1;
        for (Task task : list) {
            System.out.println(count + ". " + task);
            count++;
        }
        System.out.println(SEPERATOR);
    }

    public static void markAsDone(String[] parts) throws TeddyException {
        if (parts.length <= 1 || parts[1].isBlank()) {
            throw new TeddyException("Please specify the task number to mark as done.");
        }
        try {
            int index = Integer.parseInt(parts[1]);
            list.get(index - 1).mark();
            System.out.println(SEPERATOR + "\nNice! I've marked this task as done:\n   " +
                    list.get(index - 1).toString() + "\n" + SEPERATOR);
        } catch (IndexOutOfBoundsException e) {
            throw new TeddyException("Task number " + parts[1] + " does not exist.");
        } catch (NumberFormatException e) {
            throw new TeddyException("Task number must be a valid integer.");
        }
    }

    public static void unmark(String[] parts) throws TeddyException {
        if (parts.length <= 1 || parts[1].isBlank()) {
            throw new TeddyException("Please specify the task number to marked as not done.");
        }
        try {
            int index = Integer.parseInt(parts[1]);
            list.get(index - 1).unmark();
            System.out.println(SEPERATOR + "\nOK, I've marked this task as not done yet:\n   " +
                    list.get(index - 1).toString() + "\n" + SEPERATOR);
        } catch (IndexOutOfBoundsException e) {
            throw new TeddyException("Task number " + parts[1] + " does not exist.");
        } catch (NumberFormatException e) {
            throw new TeddyException("Task number must be a valid integer.");
        }
    }

    public static void addTodo(String[] parts, int count) throws TeddyException{
        if (parts.length <= 1 || parts[1].isBlank()) {
            throw new TeddyException("The description of a todo cannot be empty.");
        } else {
            Todo todo = new Todo(parts[1]);
            list.add(todo);
            System.out.println(SEPERATOR + "\nGot it, I've added this task:\n  " + todo
                    + "\nNow you have " + list.size() + (list.size() > 1 ? " tasks" : " task") + " in the list.\n" + SEPERATOR);
        }
    }

    public static void addDeadline(String[] parts, int count) throws TeddyException {
        if (parts.length <= 1 || parts[1].isBlank()) {
            throw new TeddyException("The description of a deadline cannot be empty.");
        }
        if (!parts[1].contains("/by")) {
            throw new TeddyException("A deadline must have a '/by' followed by the time (e.g., 'deadline task /by time').");
        }
        String[] split = parts[1].split("/by", 2);
        if (split.length < 2 || split[1].isBlank()) {
            throw new TeddyException("The time for a deadline cannot be empty.");
        }
        Deadline deadline = new Deadline(split[0].trim(), split[1].trim());
        list.add(deadline);
        System.out.println(SEPERATOR + "\nGot it, I've added this task:\n  " + deadline
                + "\nNow you have " + list.size() + (list.size() > 1 ? " tasks" : " task") + " in the list.\n" + SEPERATOR);
    }


    public static void addEvent(String input, int count) throws TeddyException {
        if (input == null || input.isBlank()) {
            throw new TeddyException("The description of an event cannot be empty.");
        }
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new TeddyException("An event must have '/from' and '/to' followed by the start and end times.");
        }
        String[] split = input.split("/");
        if (split.length < 3) {
            throw new TeddyException("The start and end times for an event cannot be empty.");
        }
        String task = split[0].trim();
        String start = split[1].split(" ", 2)[1].trim();
        String end = split[2].split(" ", 2)[1].trim();
        Event event = new Event(task, start, end);
        list.add(event);
        System.out.println(SEPERATOR + "\nGot it, I've added this task:\n  " + event
                + "\nNow you have " + list.size() + (list.size() > 1 ? " tasks" : " task") + " in the list.\n" + SEPERATOR);
    }

    public static void deleteTask(String[] parts) throws TeddyException {
        try {
            if (parts.length <= 1 || parts[1].isBlank()) { // check if index is provided
                throw new TeddyException("Please specify the task number to delete.");
            }
            int index = Integer.parseInt(parts[1]);
            if (index < 1 || index > list.size()) {
                throw new TeddyException("Task number " + index + " does not exist in the list.");
            }
            System.out.println(SEPERATOR + "\nNoted. I've removed this task:\n  " + list.get(index - 1));
            list.remove(index - 1);
            System.out.println("Now you have " + list.size() + (list.size() == 1 ? " task" : " tasks") + " in the list.\n" + SEPERATOR);
        } catch (NumberFormatException e) { // handle non integer input
            throw new TeddyException("Task number must be a valid integer.");
        } catch (IndexOutOfBoundsException e) { //handle index out of range errors
            throw new TeddyException("Task number is out of range. Please try again.");
        }
    }


}

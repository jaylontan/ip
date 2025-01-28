package teddy;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;
    private final Storage storage;

    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        this.storage = storage;
    }

    public void addTodo(String[] parts) throws TeddyException{
        if (parts.length <= 1 || parts[1].isBlank()) {
            throw new TeddyException("The description of a todo cannot be empty.");
        } else {
            Todo todo = new Todo(parts[1]);
            tasks.add(todo);

            try {
                this.storage.writeToFile(todo.toString());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

            System.out.println("Got it, I've added this task:\n  " + todo
                    + "\nNow you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.");
        }
    }

    public void addDeadline(String[] parts) throws TeddyException {
        Deadline deadline = getDeadline(parts);
        tasks.add(deadline);

        try {
            storage.writeToFile(deadline.toString());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        System.out.println("Got it, I've added this task:\n  " + deadline
                + "\nNow you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.");
    }

    private Deadline getDeadline(String[] parts) throws TeddyException {
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
        return new Deadline(split[0].trim(), split[1].trim());
    }

    public void addEvent(String input) throws TeddyException {
        if (input == null || input.isBlank()) {
            throw new TeddyException("The description of an event cannot be empty.");
        }
        Event event = getEvent(input);
        tasks.add(event);

        try {
            storage.writeToFile(event.toString());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        System.out.println("Got it, I've added this task:\n  " + event
                + "\nNow you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.");
    }

    private Event getEvent(String input) throws TeddyException {
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
        return new Event(task, start, end);
    }

    public void deleteTask(String[] parts) throws TeddyException {
        try {
            if (parts.length <= 1 || parts[1].isBlank()) { // check if index is provided
                throw new TeddyException("Please specify the task number to delete.");
            }
            int index = Integer.parseInt(parts[1]);
            if (index < 1 || index > tasks.size()) {
                throw new TeddyException("Task number " + index + " does not exist in the list.");
            }
            System.out.println("Noted. I've removed this task:\n  " + tasks.get(index - 1));
            tasks.remove(index - 1);
            System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
        } catch (NumberFormatException e) { // handle non integer input
            throw new TeddyException("Task number must be a valid integer.");
        } catch (IndexOutOfBoundsException e) { //handle index out of range errors
            throw new TeddyException("Task number is out of range. Please try again.");
        }
    }

    public void markTask(String[] parts) throws TeddyException {
        if (parts.length <= 1 || parts[1].isBlank()) {
            throw new TeddyException("Please specify the task number to mark as done.");
        }
        try {
            int index = Integer.parseInt(parts[1]);
            tasks.get(index - 1).mark();
            System.out.println("Nice! I've marked this task as done:\n   " +
                    tasks.get(index - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            throw new TeddyException("Task number " + parts[1] + " does not exist.");
        } catch (NumberFormatException e) {
            throw new TeddyException("Task number must be a valid integer.");
        }
    }

    public void unmarkTask(String[] parts) throws TeddyException {
        if (parts.length <= 1 || parts[1].isBlank()) {
            throw new TeddyException("Please specify the task number to marked as not done.");
        }
        try {
            int index = Integer.parseInt(parts[1]);
            tasks.get(index - 1).unmark();
            System.out.println("OK, I've marked this task as not done yet:\n   " +
                    tasks.get(index - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            throw new TeddyException("Task number " + parts[1] + " does not exist.");
        } catch (NumberFormatException e) {
            throw new TeddyException("Task number must be a valid integer.");
        }
    }

    public void printTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasks.get(i));
        }
    }
}

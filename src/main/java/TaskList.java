import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    public void markTask(int index) {
        this.tasks.get(index).mark();
    }

    public void unmarkTask(int index) {
        this.tasks.get(index).unmark();
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public void printTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasks.get(i));
        }
    }
}

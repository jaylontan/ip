package teddy;

public class Task {

    private final String task;
    private boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    // Mark the task as done
    public void mark() {
        this.done = true;
    }

    // Mark the task as not done
    public void unmark() {
        this.done = false;
    }

    // Get the type of task
    public String getTask() {
        return this.task;
    }

    // Check if task is marked as done
    public boolean isDone() {
        return this.done;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }
}

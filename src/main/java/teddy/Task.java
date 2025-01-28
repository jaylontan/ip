package teddy;

public class Task {

    private final String task;
    private boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public String getTask() {
        return this.task;
    }

    public boolean isDone() {
        return this.done;
    }

    public String toFileFormat() {
        return "T | " + (done ? "1" : "0") + " | " + task;
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

public class Task {

    private String task;
    private int index;
    private boolean done;

    public Task(String task, int index) {
        this.task = task;
        this.index = index;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public int getIndex() {
        return this.index;
    }

    public String toString() {
        if (this.done) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }
}

public class Task {

    private String task;
    private int index;
    private boolean done;

    public Task(String task, int index) {
        this.task = task;
        this.index = index;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public String toString() {
        if (this.done) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }
}

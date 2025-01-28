package teddy;

public class Todo extends Task {

    public Todo(String task) {
        super(task);
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getTask();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

public class Todo extends Task {

    public Todo(String task, int index) {
        super(task, index);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}

public class Deadline extends Task {

    private final String time;

    public Deadline(String input, String time) {
        super(input);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getTask() + " | " + time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time + ")";
    }
}

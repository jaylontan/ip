public class Deadline extends Task {

    private String time;

    public Deadline(String input, int index, String time) {
        super(input, index);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time + ")";
    }
}

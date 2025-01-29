package teddy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private final String time;

    public Deadline(String input, String time) {
        super(input);
        this.time = time;
    }

    @Override
    public String toString() {
        try {
            return "[D]" + super.toString() + " (by: "
                    + LocalDate.parse(this.time).format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        } catch (DateTimeParseException e) {
            return "[D]" + super.toString() + " (by: " + this.time + ")";
        }
    }
}

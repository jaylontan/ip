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
        StringBuilder sb = new StringBuilder();
        sb.append("[D]");
        sb.append(super.toString());
        sb.append(" (by: ");

        try {
            String by = LocalDate.parse(this.time).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

            sb.append(by);
            sb.append(")");

            return sb.toString();
        } catch (DateTimeParseException e) {
            sb.append(this.time);
            sb.append(")");

            return sb.toString();
        }
    }
}

package teddy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private final String start;
    private final String end;

    public Event(String input, String start, String end) {
        super(input);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[E]");
        sb.append(super.toString());
        sb.append(" (from: ");

        try {
            String startTime = LocalDate.parse(this.start).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            String endTime = LocalDate.parse(this.end).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

            sb.append(startTime);
            sb.append(" to: ");
            sb.append(")");

            return sb.toString();
        } catch (DateTimeParseException e) {
            sb.append(" (from: ");
            sb.append(start);
            sb.append(" to: ");
            sb.append(end);
            sb.append(")");

            return sb.toString();
        }
    }
}

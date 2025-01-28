public class Parser {
    public static String[] splitInput(String input) {
        String[] parts = input.split(" ", 2);
        return parts;
    }

    public static Command parseCommand(String[] parts) throws TeddyException {
        return Command.fromString(parts[0]);
    }

}

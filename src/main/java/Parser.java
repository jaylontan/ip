public class Parser {
    public static String[] splitInput(String input) {
        return input.split(" ", 2);
    }

    public static Command parseCommand(String[] parts) throws TeddyException {
        return Command.fromString(parts[0]);
    }

}

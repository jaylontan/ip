package teddy;

public class Teddy {

    private final Ui ui;
    private final TaskList tasks;
    private static final String FILE_PATH = "./data/teddy.txt";

    public Teddy() {
        this.ui = new Ui();
        Storage storage = new Storage(FILE_PATH);
        this.tasks = new TaskList(storage);
    }

    public void run() {
        ui.welcome();

        while (true) {
            try {
                String input = ui.readCommand();
                String[] parts = Parser.splitInput(input);
                Command command = Parser.parseCommand(parts);

                switch (command) {
                    case BYE:
                        ui.goodbye();
                        return;
                    case LIST:
                        ui.setSEPERATOR();
                        tasks.printTasks();
                        ui.setSEPERATOR();
                        break;
                    case MARK:
                        ui.setSEPERATOR();
                        tasks.markTask(parts);
                        ui.setSEPERATOR();
                        break;
                    case UNMARK:
                        ui.setSEPERATOR();
                        tasks.unmarkTask(parts);
                        ui.setSEPERATOR();
                        break;
                    case TODO:
                        ui.setSEPERATOR();
                        tasks.addTodo(parts);
                        ui.setSEPERATOR();
                        break;
                    case DEADLINE:
                        ui.setSEPERATOR();
                        tasks.addDeadline(parts);
                        ui.setSEPERATOR();
                        break;
                    case EVENT:
                        ui.setSEPERATOR();
                        tasks.addEvent(parts[1]);
                        ui.setSEPERATOR();
                        break;
                    case DELETE:
                        ui.setSEPERATOR();
                        tasks.deleteTask(parts);
                        ui.setSEPERATOR();
                        break;
                    case FIND:
                        ui.setSEPERATOR();
                        tasks.find(parts[1]);
                        ui.setSEPERATOR();
                        break;
                    default:
                        throw new TeddyException("I don't understand the command: " + parts[0]);
                }
            } catch (TeddyException e) {
                ui.setSEPERATOR();
                ui.error("\n" + e.getMessage() + "\n");
                ui.setSEPERATOR();
            } catch (Exception e) {
                ui.setSEPERATOR();
                ui.error("\nSomething went wrong. Please try again.\n");
                ui.setSEPERATOR();
            }
        }
    }

    public static void main(String[] args) {
        new Teddy().run();
    }
}

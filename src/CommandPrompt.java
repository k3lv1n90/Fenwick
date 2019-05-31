
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Fenwick Software Technical Task
 *
 * @author Kel-PC
 */
public class CommandPrompt {

    FileUtility fl = new FileUtility();

    /**
     * Constructor for CommandPrompt Class
     *
     * @param arguments the arguments needed to run the application
     */
    public CommandPrompt(String[] arguments) {
        switch (checkInputs(arguments)) {
            case RECORD:
                performRecord(arguments);
                break;
            case SUMMARY:
                performSummary(arguments);
                break;
            case HELP:
                performHelp();
                break;
            default:
                performHelp();
        }
    }

    /**
     * Method to check the input and return the correct enum to run
     *
     * @param inputs the arguments that will be checked to see what to run
     *
     * @return InputType Enum to know what method to run
     */
    private InputType checkInputs(String[] inputs) {
        if (Arrays.toString(inputs).contains(InputType.RECORD.label)) {
            return InputType.RECORD;
        } else if (Arrays.toString(inputs).contains(InputType.SUMMARY.label)) {
            return InputType.SUMMARY;
        } else {
            return InputType.HELP;
        }
    }

    /**
     * Generic method to provide instructions for users to know how to use the
     * application
     *
     */
    private void performHelp() {
        System.out.println("");
        System.out.println("+--------------+--------------+");
        System.out.println("Help Section");
        System.out.println("Please note that there are only two commands to this program: record & summary");
        System.out.println("Eg: record filename.txt 2 3");
        System.out.println("This command will record the values 2 and 3 into the database filename.txt that can be read");
        System.out.println("Eg: summary filename.txt");
        System.out.println("This command will display a summary of the database filename.txt");
        System.out.println("+--------------+--------------+");
        System.out.println("");
    }

    /**
     * Method to check the input, write it into file
     *
     * @param records the arguments needed to add/write to file
     *
     */
    private void performRecord(String[] records) {

        if (records.length < 3) {
            performHelp();
            return;
        }
        String fileName = records[1];

        ArrayList<String> recording = new ArrayList<>();

        for (int i = 2; i < records.length; i++) {
            recording.add(records[i]);
        }

        if (fl.writeToFile(recording, fileName)) {
            System.out.println("Records successfully saved");
        } else {
            System.out.println("Error saving file.");
        }

    }

    /**
     * Method to check the input and return the correct enum to run
     *
     * @param summary the arguments allow the file to read the filename
     *
     */
    private void performSummary(String[] summary) {
        if (summary.length < 2) {
            System.out.println("Missing Parameters. Please try again");
            performHelp();
            return;
        }
        String fileName = summary[1];
        fl.readFromFile(fileName);
    }

}

/**
 * Enum type to know what to use and returns the text based on what we need to
 * perform
 *
 */
enum InputType {
    RECORD("record"), SUMMARY("summary"), HELP("help");

    public final String label;

    private InputType(String label) {
        this.label = label;
    }
}

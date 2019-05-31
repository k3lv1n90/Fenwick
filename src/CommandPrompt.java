
import java.util.ArrayList;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Fenwick Software Technical Task
 * @author Kel-PC
 */
public class CommandPrompt {
    
    public CommandPrompt(String[] arguments) {
       switch (checkInputs(arguments)) {
           case RECORD:
               performRecord(arguments);
               break;
           case SUMMARY:
               //perform Summary;
               break;
           case HELP:
               //perform HELP
               break;
           default:
               //perform help'
       }
    }
    
    private static InputType checkInputs (String[] inputs) {
        if(Arrays.toString(inputs).contains(InputType.RECORD.label)) {
            return InputType.RECORD;
        } else if (Arrays.toString(inputs).contains(InputType.SUMMARY.label)) {
            return InputType.SUMMARY;
        } else {
            return InputType.HELP;
        }
    }
    
    private static void performHelp() {
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
    
    
    private static void performRecord(String[] records) {
        
        if (records.length < 3) {
            performHelp();
            return;
        }
        String fileName = records[1];
        ArrayList<String> recording = new ArrayList<>();
        
        for (int i = 2; i < records.length; i++) {
            recording.add(records[i]);
        }
        
        if (FileUtility.writeToFile(recording, fileName)) {
            System.out.println("Records successfully saved");
        } else {
            System.out.println("Error saving file.");
        }
        
        
    }
    
    private static void performSummary(String[] summary) {
       if(summary.length < 2) {
           System.out.println("Missing Parameters. Please try again");
           performHelp();
           return;
       }
       String fileName = summary[1];
       FileUtility.readFromFile(fileName);
    }
    
}

    


enum InputType {
    RECORD("record"), SUMMARY("summary"), HELP("help");
    
    public final String label;
    
    private InputType(String label) {
        this.label = label;
    }
}
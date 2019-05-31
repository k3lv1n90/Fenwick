
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
    
    private String[] arguments;

    public CommandPrompt(String[] arguments) {
       this.arguments = arguments;
       switch (checkInputs(arguments)) {
           case RECORD:
               //performRecording;
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
}




enum InputType {
    RECORD("record"), SUMMARY("summary"), HELP("help");
    
    public final String label;
    
    private InputType(String label) {
        this.label = label;
    }
}
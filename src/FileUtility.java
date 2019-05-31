
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Kel-PC
 */

public class FileUtility {
    
    public static Integer parseInt (String input) {
        Integer i = 0;
        while(true) {
            try {
                i = Integer.parseInt(input);
                break;
            } catch (Exception e) {
                System.out.println("Not a valid Integer");
            }
        }
        return i;
    }
    
    public static Boolean writeToFile(ArrayList<String> inputs, String fileName) {
        try {
            PrintWriter writeln = new PrintWriter(new FileWriter(fileName, true));
            for (String s : inputs) {
                writeln.println(s);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static void readFromFile(String fileName) {
        ArrayList<String> txtArray = new ArrayList<>();
        try {
            BufferedReader readLn = new BufferedReader(new FileReader(fileName));
            
            String readIn = readLn.readLine();
            while (readIn != null) {
                txtArray.add(readIn);
                readIn = readLn.readLine();
            }  
        } catch (FileNotFoundException fnf) {
            System.out.println("File not found. Please try again with proper filename");
            return;
        } catch (Exception e) {
            System.out.println("Error Parsing file");
            return;
        } 
        
        if (txtArray.isEmpty()) {
            System.out.println("File does not contain any record");
            return;
        }
        
        printSummary(txtArray);
    }
    
    private static void printSummary(ArrayList<String> txtInput) {
        
//        DecimalFormat df = new DecimalFormat("####.0");
//        String format = df.format()
        System.out.println("+--------------+-------+");
        System.out.println("| # of Entries | " + txtInput.size() + "\t|");
        System.out.println("| Min. Value   | " + minValue(txtInput) + "\t|");
        System.out.println("| Max. Value   | " + maxValue(txtInput) + "\t|");
        System.out.println("| Avg. Value   | " + format(txtInput) + "\t|");
        System.out.println("+--------------+--------+");

    }
    
}

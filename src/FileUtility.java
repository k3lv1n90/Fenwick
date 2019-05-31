
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
        

        System.out.println("+--------------+-------+");
        System.out.println("| # of Entries | " + txtInput.size() + "\t|");
        System.out.println("| Min. Value   | " + minValue(txtInput) + "\t|");
        System.out.println("| Max. Value   | " + maxValue(txtInput) + "\t|");
        System.out.println("| Avg. Value   | " + getAverage(txtInput) + "\t|");
        System.out.println("+--------------+--------+");

    }
    
    private static double minValue(ArrayList<String> txtInput) {
        double min = 999999999;
        int errors = 0;
        for (String s : txtInput) {
            try {
                double num = Double.parseDouble(s);
                if (num < min) {
                    min = num;
                }
            } catch (Exception e) {
                errors++;
            }
        }
        if (errors > 0) {
            System.out.println("There were " + errors + " errors in the file and were ignored.");
        }        
        return min;
    }
    
    private static double maxValue(ArrayList<String> txtInput) {
        double max = 0;
        for (String s: txtInput) {
            try {
                double num = Double.parseDouble(s);
                if (num > max) {
                    max = num;
                }
            } catch (Exception e) {
                // if min had any errors, it would be the same errors here. no point writing it out twice.
            }
        }
        return max;
    }
    
    private static String getAverage(ArrayList<String> txtInput) {
        double sum = 0;
        int count = 0;
        for (String s: txtInput) {
            try {
                double num = Double.parseDouble(s);
                sum += num;
                count ++;
            } catch (Exception e) {
               // if min had any errors, it would be the same errors here. no point writing it out twice. 
            }
        }
        
        DecimalFormat df = new DecimalFormat("####.0");
        String format = df.format(sum/count);
        
        return format;
    }
}



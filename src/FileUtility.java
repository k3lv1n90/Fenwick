
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

    /**
     * Constructor for FileUtitily.
     */
    public FileUtility() {
    }

    /**
     * Method write to file
     *
     * @param inputs the arguments that will be needed to read and write the
     * file
     * @param filename the filename to know what to save the file as
     *
     * @return Boolean to understand if the process of writing failed/pass
     */
    public Boolean writeToFile(ArrayList<String> inputs, String fileName) {
        System.out.println(inputs);
        try {
            PrintWriter writeln = new PrintWriter(new FileWriter(fileName, true));
            for (String s : inputs) {
                writeln.println(s);
            }
            writeln.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method to read from file
     *
     * @param fileName the arguments to know what filename to read from
     *
     */
    public void readFromFile(String fileName) {
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

    /**
     * Method print out on console the summary version of the text file
     *
     * @param txtInput the array containing all the details from the text file
     *
     */
    private void printSummary(ArrayList<String> txtInput) {

        double minVal = minValue(txtInput);
        double maxVal = maxValue(txtInput);
        String avgVal = getAverage(txtInput);

        System.out.println("+--------------+-------+");
        System.out.println("| # of Entries | " + txtInput.size() + "\t|");
        System.out.println("| Min. Value   | " + minVal + "\t|");
        System.out.println("| Max. Value   | " + maxVal + "\t|");
        System.out.println("| Avg. Value   | " + avgVal + "\t|");
        System.out.println("+--------------+--------+");

    }

    /**
     * Method print out on console the min value of the text file
     *
     * @param txtInput the array containing all the details from the text file
     *
     * @return double the minimum value
     */
    private double minValue(ArrayList<String> txtInput) {
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

    /**
     * Method print out on console the max value of the text file
     *
     * @param txtInput the array containing all the details from the text file
     *
     * @return double the maximum value
     */
    private double maxValue(ArrayList<String> txtInput) {
        double max = 0;
        for (String s : txtInput) {
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

    /**
     * Method print out on console the average value of the text file
     *
     * @param txtInput the array containing all the details from the text file
     *
     * @return double the average value
     */
    private String getAverage(ArrayList<String> txtInput) {
        double sum = 0;
        int count = 0;
        for (String s : txtInput) {
            try {
                double num = Double.parseDouble(s);
                sum += num;
                count++;
            } catch (Exception e) {
                // if min had any errors, it would be the same errors here. no point writing it out twice. 
            }
        }

        DecimalFormat df = new DecimalFormat("####.0");
        String format = df.format(sum / count);

        return format;
    }
}

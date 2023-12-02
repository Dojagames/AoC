import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day5b {

    public static ArrayList<String> getInput(String path) {
        ArrayList<String> content = new ArrayList<>();
        FileReader fr;
        try {
            fr = new FileReader(path);
        } catch (FileNotFoundException e) {
            System.err.println("Error occurred opening the file...");
            return content;
        }
        BufferedReader br = new BufferedReader(fr);
        String s = "";
        try {
            while ((s = br.readLine()) != null) {
                content.add(s);
            }
            br.close();
        } catch (IOException e) {
            System.err.println("Error occured reading data...");
            return content;
        }
        return content;
    }

    public static void main(String[] args) {
        ArrayList<String> input = getInput("Day3/input3.txt");
        int sum = 0;
        String first = "";
        int firstBegin = 0;
        int firstEnd = 0;
        String second = "";
        int secondBegin = 0;
        int secondEnd = 0;
        for (String line : input) {
            first = line.split(",")[0];
            second = line.split(",")[1];
            firstBegin = Integer.parseInt(first.split("-")[0]);
            firstEnd = Integer.parseInt(first.split("-")[1]);
            secondBegin = Integer.parseInt(second.split("-")[0]);
            secondEnd = Integer.parseInt(second.split("-")[1]);
            if ((firstBegin <= secondEnd) && (firstEnd >= secondBegin)) {
                sum++;
            }
        }
        System.out.println(sum);
    }
}
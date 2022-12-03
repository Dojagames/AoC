import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day1 {

    public static ArrayList<String> getInput(String path) {
        ArrayList<String> content = new ArrayList<>();
        FileReader fr;
        try {
            fr = new FileReader(path);
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred opening the file...");
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
            System.err.println("Error occured reading html...");
            return content;
        }
        return content;
    }

    public static void main(String[] args) {
        ArrayList<String> input = getInput("Day1/input1.txt");
        int num = 0;
        int biggest = 0;
        for (String line : input) {
            if (line.length() > 1) {
                num += Integer.parseInt(line);
            } else {
                if (num > biggest) {
                    biggest = num;
                }
                num = 0;
            }
        }
        System.out.println(biggest);
    }
}
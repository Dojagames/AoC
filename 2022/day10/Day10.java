import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day10 {

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
        ArrayList<String> input = getInput("Day10/input10.txt");
        int sum = 0;
        int cycles = 0;
        int x = 1;
        ArrayList<Integer> cycleList = new ArrayList<>();
        for (String line : input) {

            if (line.split("\\s+")[0].equals("addx")) {
                cycleList.add(x);
                cycles++;
                cycleList.add(x);
                cycles++;
                x += Integer.parseInt(line.split("\\s+")[1]);
                System.out.println(x);

            } else {
                cycleList.add(x);
                cycles++;
            }
        }

        for (int i = 20; i <= 220; i += 40) {
            sum += cycleList.get(i - 1) * i;
        }

        System.out.println(sum);
    }
}
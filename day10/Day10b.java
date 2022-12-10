import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day10b {

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
        int x = 1;
        ArrayList<Integer> cycleList = new ArrayList<>();
        for (String line : input) {

            if (line.split("\\s+")[0].equals("addx")) {
                cycleList.add(x);
                cycleList.add(x);
                x += Integer.parseInt(line.split("\\s+")[1]);
                System.out.println(x);

            } else {
                cycleList.add(x);
            }
        }

        char[][] screen = new char[6][40];
        int cycles = 0;
        for (int px = 0; px < screen.length; px++) {
            for (int py = 0; py < screen[0].length; py++) {
                screen[px][py] = (cycleList.get(cycles) >= py - 1 && cycleList.get(cycles) <= py + 1) ? '#'
                        : '.';
                cycles++;
                System.out.print(screen[px][py]);
            }
            System.out.println();
        }
    }
}
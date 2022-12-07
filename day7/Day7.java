import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Day7 {

    static int sum = 0;

    static ArrayList<String> input;
    static Iterator<String> commands;

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
        input = getInput("Day7/input7.txt");
        commands = input.iterator();
        calcSize();
        System.out.println(sum);
    }

    public static int calcSize() {
        int size = 0;
        String command = "";
        while (commands.hasNext()) {
            command = commands.next();
            if (command.startsWith("$ cd") && !command.endsWith("..")) {
                System.out.println("new directory");
                size += calcSize();
            } else if (Character.isDigit(command.charAt(0))) {
                size += Integer.parseInt(command.split("\\s")[0]);
                System.out.println("adding" + command.split("\\s")[0]);
            } else if (command.equals("$ cd ..")) {
                System.out.println("end of tree" + size);
                if (size <= 100_000) {
                    sum += size;
                    System.out.println("new sum: " + sum);
                }
                return size;
            }
        }
        return size;
    }
}
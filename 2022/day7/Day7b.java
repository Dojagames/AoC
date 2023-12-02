import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Day7b {

    static ArrayList<String> input;
    static Iterator<String> commands;

    static ArrayList<Integer> dirs;

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
        dirs = new ArrayList<>();
        int neededSpace = 30_000_000 - (70_000_000 - calcSize());
        int bestDirectory = 0;
        dirs.sort(null);
        for (int i = dirs.size() - 1; i > 0; i--) {
            if (dirs.get(i) < neededSpace) {
                break;
            }
            bestDirectory = dirs.get(i);
        }
        System.out.println(bestDirectory);
    }

    public static int calcSize() {
        int size = 0;
        int bestDirectory = 300_000;
        String command = "";
        while (commands.hasNext()) {
            command = commands.next();
            if (command.startsWith("$ cd") && !command.endsWith("..")) {
                size += calcSize();
            } else if (Character.isDigit(command.charAt(0))) {
                size += Integer.parseInt(command.split("\\s")[0]);
            } else if (command.equals("$ cd ..")) {
                dirs.add(size);
                return size;
            }
        }
        return size;
    }
}
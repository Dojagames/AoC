import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Day6b {

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
        ArrayList<String> input = getInput("Day6/input6.txt");
        String stream = input.get(0);
        LinkedList<Character> chars = new LinkedList<>(
                Arrays.asList(stream.charAt(0), stream.charAt(1), stream.charAt(2), stream.charAt(3), stream.charAt(4),
                        stream.charAt(5), stream.charAt(6), stream.charAt(7), stream.charAt(8), stream.charAt(9),
                        stream.charAt(10), stream.charAt(11), stream.charAt(12)));
        LinkedList<Character> sorted = new LinkedList<>();
        int index = 0;
        boolean different = false;
        for (int i = 13; i < stream.length(); i++) {
            chars.addLast(stream.charAt(i));
            sorted = new LinkedList<>(chars);
            sorted.sort(null);
            different = true;
            for (int j = 1; j < sorted.size(); j++) {
                if (sorted.get(j) == sorted.get(j - 1)) {
                    different = false;
                    break;
                }
            }
            if (different) {
                index = i + 1;
                break;
            }
            chars.removeFirst();
        }
        System.out.println(index);
    }
}
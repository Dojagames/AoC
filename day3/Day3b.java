import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day3b {

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
        char common = 0;
        for (int i = 0; i < input.size() / 3; i++) {
            for (char c : input.get(i * 3).toCharArray()) {
                if (input.get(i * 3 + 1).contains(c + "") && input.get(i * 3 + 2).contains(c + "")) {
                    common = c;
                    if (Character.isUpperCase(common)) {
                        sum += common - 38;
                    } else {
                        sum += common - 96;
                    }
                    break;
                }
            }
        }
        System.out.println(sum);
    }
}
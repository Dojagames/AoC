import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day2 {

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
        ArrayList<String> input = getInput("Day2/input2.txt");
        int score = 0;
        char opponent = 0;
        char me = 0;
        for (String line : input) {
            opponent = line.charAt(0);
            me = line.charAt(2);

            if (me == 'X') {
                score += 1;
                me = 'A';
            } else if (me == 'Y') {
                score += 2;
                me = 'B';
            } else {
                score += 3;
                me = 'C';
            }

            if (opponent == me) {
                score += 3;
            } else if (opponent == 'A') {
                if (me == 'B') {
                    score += 6;
                }
            } else if (opponent == 'B') {
                if (me == 'C') {
                    score += 6;
                }
            } else {
                if (me == 'A') {
                    score += 6;
                }
            }
        }
        System.out.println(score);
    }
}
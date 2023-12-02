import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day2b {

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
        char win = 0;
        char me = 0;
        for (String line : input) {
            opponent = line.charAt(0);
            win = line.charAt(2);

            if (win == 'X') {
                if (opponent == 'A') {
                    me = 'C';
                } else if (opponent == 'B') {
                    me = 'A';
                } else {
                    me = 'B';
                }
            } else if (win == 'Y') {
                score += 3;
                me = opponent;
            } else {
                score += 6;
                if (opponent == 'A') {
                    me = 'B';
                } else if (opponent == 'B') {
                    me = 'C';
                } else {
                    me = 'A';
                }
            }

            if (me == 'A') {
                score += 1;
            } else if (me == 'B') {
                score += 2;
            } else {
                score += 3;
            }
        }
        System.out.println(score);
    }
}
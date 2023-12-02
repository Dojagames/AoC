import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day5 {

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
        ArrayList<String> input = getInput("Day5/input5.txt");
        List<String> cratesList = input.subList(0, 8);
        List<String> movesList = input.subList(10, input.size());
        LinkedList<LinkedList<Character>> crates = new LinkedList<>();
        for (int x = 0; x < 9 * 4; x += 4) {
            LinkedList<Character> rowCrates = new LinkedList<>();
            for (int y = 0; y < cratesList.size(); y++) {
                if (cratesList.get(y).substring(x, x + 2).contains("[")) {
                    rowCrates.addFirst(cratesList.get(y).substring(x + 1, x + 2).toCharArray()[0]);
                }
            }
            crates.add(rowCrates);
        }
        LinkedList<Character> chars = new LinkedList<>();
        int amount = 0;
        int heap1 = 0;
        int heap2 = 0;
        for (String move : movesList) {

            for (int x = 0; x < 9; x++) {
                for (Character ch : crates.get(x)) {
                    System.out.print(ch);
                }
                System.out.println();
            }

            move = move.trim().replaceAll("\\D", "");
            move = (move.length() == 3) ? "0" + move : move;
            amount = Integer.parseInt(move.substring(0, 2));
            heap1 = Integer.parseInt(move.substring(2, 3));
            heap2 = Integer.parseInt(move.substring(3, 4));
            System.out.println(heap2);
            for (int i = 0; i < amount; i++) {
                chars.addFirst(crates.get(heap1 - 1).getLast());
                crates.get(heap1 - 1).removeLast();
            }
            crates.get(heap2 - 1).addAll(chars);
            chars = new LinkedList<>();
        }
        String result = "";
        for (LinkedList<Character> list : crates) {
            result += list.getLast();
        }

        System.out.println(result);
    }
}
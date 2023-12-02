import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day11 {

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
        ArrayList<String> input = getInput("Day11/input11.txt");
        ArrayList<Monkey> monkeys = new ArrayList<>();
        ArrayList<Integer> startingItems = new ArrayList<>();
        int testNumber = 0;
        int operation = 0;
        int trueMonkey = 0;
        int falseMonkey = 0;
        boolean addMul = true;
        for (String line : input.subList(1, input.size() - 1)) {
            if (line.contains("Monkey")) {
                monkeys.add(new Monkey(new ArrayList<>(startingItems), testNumber, operation, addMul, trueMonkey,
                        falseMonkey));
                startingItems.clear();
            } else if (line.contains("items")) {
                for (String item : line.trim().split(":")[1].split(", ")) {
                    startingItems.add(Integer.parseInt(item.trim()));
                }
            } else if (line.contains("Operation")) {
                if (line.contains("+")) {
                    addMul = true;
                } else {
                    addMul = false;
                }
                if (line.replaceAll("\\D", "") != "") {
                    System.out.println("Hey");
                    operation = Integer.parseInt(line.replaceAll("\\D", ""));
                } else {
                    operation = 0;
                }
            } else if (line.contains("Test")) {
                testNumber = Integer.parseInt(line.replaceAll("\\D", ""));
            } else if (line.contains("true")) {
                trueMonkey = Integer.parseInt(line.replaceAll("\\D", ""));
            } else if (line.contains("false")) {
                falseMonkey = Integer.parseInt(line.replaceAll("\\D", ""));
            }
        }
        monkeys.add(new Monkey(new ArrayList<>(startingItems), testNumber, operation, addMul, trueMonkey, falseMonkey));
        startingItems.clear();

        for (int i = 0; i < 20; i++) {
            for (Monkey monkey : monkeys) {
                ArrayList<Integer[]> throwsTo = monkey.play();
                for (Integer[] throwing : throwsTo) {
                    monkeys.get(throwing[1]).addItem(throwing[0]);
                }
            }
            System.out.println("----------");
        }
        int[] inspectedItems = new int[monkeys.size()];
        for (int i = 0; i < monkeys.size(); i++) {
            inspectedItems[i] = monkeys.get(i).getInspectedItems();
        }
        Arrays.sort(inspectedItems);
        System.out.println(inspectedItems[6] * inspectedItems[7]);
    }
}

class Monkey {
    ArrayList<Integer> items;
    int testNumber, operation, trueMonkey, falseMonkey;
    boolean addMul;
    int inspectedItems = 0;

    public Monkey(ArrayList<Integer> startingItems, int testNumber, int operation, boolean addMul, int trueMonkey,
            int falseMonkey) {
        this.items = startingItems;
        this.testNumber = testNumber;
        this.operation = operation;
        this.addMul = addMul;
        this.trueMonkey = trueMonkey;
        this.falseMonkey = falseMonkey;
        for (int item : items) {
            System.out.println(item);
        }
        System.out.println("");
    }

    public int getInspectedItems() {
        return this.inspectedItems;
    }

    public void addItem(int item) {
        this.items.add(item);
    }

    public ArrayList<Integer[]> play() {
        ArrayList<Integer[]> results = new ArrayList<>();
        int newWorryLevel = 0;
        for (int i = 0; i < items.size(); i++) {
            inspectedItems++;
            if (inspectedItems < 1)
                System.out.println("item " + items.get(i) + " inspected!");
            if (addMul) {
                newWorryLevel = (int) ((items.get(i) + operation) / 3);
                results.add(new Integer[] { newWorryLevel,
                        (newWorryLevel % testNumber == 0) ? trueMonkey : falseMonkey });
            } else if (!addMul && operation > 0) {
                newWorryLevel = (int) ((items.get(i) * operation) / 3);
                results.add(new Integer[] { newWorryLevel,
                        (newWorryLevel % testNumber == 0) ? trueMonkey : falseMonkey });
            } else if (!addMul && operation == 0) {
                newWorryLevel = (int) (Math.pow(items.get(i), 2) / 3);
                results.add(new Integer[] { newWorryLevel,
                        (newWorryLevel % testNumber == 0) ? trueMonkey : falseMonkey });
            }
        }
        items.clear();
        return results;
    }
}
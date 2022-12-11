import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day11b {

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
        ArrayList<Monkey2> monkeys = new ArrayList<>();
        ArrayList<Long> startingItems = new ArrayList<>();
        int testNumber = 0;
        int operation = 0;
        int trueMonkey = 0;
        int falseMonkey = 0;
        boolean addMul = true;
        for (String line : input.subList(1, input.size() - 1)) {
            if (line.contains("Monkey")) {
                monkeys.add(new Monkey2(new ArrayList<>(startingItems), testNumber, operation, addMul, trueMonkey,
                        falseMonkey));
                startingItems.clear();
            } else if (line.contains("items")) {
                for (String item : line.trim().split(":")[1].split(", ")) {
                    startingItems.add((long) Integer.parseInt(item.trim()));
                }
            } else if (line.contains("Operation")) {
                if (line.contains("+")) {
                    addMul = true;
                } else {
                    addMul = false;
                }
                if (line.replaceAll("\\D", "") != "") {
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
        monkeys.add(new Monkey2(new ArrayList<>(startingItems), testNumber, operation, addMul, trueMonkey, falseMonkey));
        startingItems.clear();

        for (int i = 0; i < 10_000; i++) {
            for (Monkey2 monkey : monkeys) {
                ArrayList<Long[]> throwsTo = monkey.play();
                for (Long[] throwing : throwsTo) {
                    monkeys.get(Math.toIntExact(throwing[1])).addItem(throwing[0]);
                }
            }
        }
        int[] inspectedItems = new int[monkeys.size()];
        for (int i = 0; i < monkeys.size(); i++) {
            inspectedItems[i] = monkeys.get(i).getInspectedItems();
        }
        Arrays.sort(inspectedItems);
        long result = (long) inspectedItems[6] * (long) inspectedItems[7];
        System.out.println(result);
    }
}

class Monkey2 {
    ArrayList<Long> items;
    int testNumber, operation, trueMonkey, falseMonkey;
    boolean addMul;
    int inspectedItems = 0;

    final long DIVISOR_PRODUCT = 2*3*5*7*11*13*17*19;

    public Monkey2(ArrayList<Long> startingItems, int testNumber, int operation, boolean addMul, int trueMonkey,
            int falseMonkey) {
        this.items = startingItems;
        this.testNumber = testNumber;
        this.operation = operation;
        this.addMul = addMul;
        this.trueMonkey = trueMonkey;
        this.falseMonkey = falseMonkey;
        for (long item : items) {
            System.out.println(item);
        }
    }

    public int getInspectedItems() {
        return this.inspectedItems;
    }

    public void addItem(long item) {
        this.items.add(item);
    }

    public ArrayList<Long[]> play() {
        ArrayList<Long[]> results = new ArrayList<>();
        long newWorryLevel = 0;
        for (int i = 0; i < items.size(); i++) {
            inspectedItems++;
            if (addMul) {
                newWorryLevel = items.get(i) + operation;
                newWorryLevel %= DIVISOR_PRODUCT;
                results.add(new Long[] { newWorryLevel,
                        (newWorryLevel % testNumber == 0) ? (long) trueMonkey : falseMonkey });
            } else if (!addMul && operation > 0) {
                newWorryLevel = items.get(i) * operation;
                newWorryLevel %= DIVISOR_PRODUCT;
                results.add(new Long[] { newWorryLevel,
                        (newWorryLevel % testNumber == 0) ? (long) trueMonkey : falseMonkey });
            } else if (!addMul && operation == 0) {
                newWorryLevel = (long) Math.pow(items.get(i), 2);
                newWorryLevel %= DIVISOR_PRODUCT;
                results.add(new Long[] { newWorryLevel,
                        (newWorryLevel % testNumber == 0) ? (long) trueMonkey : falseMonkey });
            }
        }
        items.clear();
        return results;
    }
}
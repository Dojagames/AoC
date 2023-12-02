import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day1b {

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
        ArrayList<String> input = getInput("Day1/input1.txt");
        int[] nums = new int[input.size()];
        int buffer = 0;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).length() > 1) {
                buffer += Integer.parseInt(input.get(i));
            } else {
                nums[i] = buffer;
                buffer = 0;
            }
        }
        Arrays.sort(nums);
        System.out.println(nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3]);
    }
}
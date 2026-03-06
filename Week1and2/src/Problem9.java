import java.util.*;

public class Problem9 {

    public static void findTwoSum(int[] arr, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : arr) {

            int complement = target - num;

            if (map.containsKey(complement)) {
                System.out.println("Pair found: " +
                        complement + " + " + num);
            }

            map.put(num, 1);
        }
    }

    public static void main(String[] args) {

        int[] transactions = {500, 300, 200};

        findTwoSum(transactions, 500);
    }
}
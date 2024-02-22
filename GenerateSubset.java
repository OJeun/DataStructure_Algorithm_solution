import java.util.ArrayList;
import java.util.List;

public class GenerateSubset {
    public static void main(final String[] args) {
        int[] arr = {1, 2, 3};
        List<List<Integer>> result = generateSubsets(arr);
        for(List<Integer> list : result) {
            System.out.println(list);
        }
    }

    private static List<List<Integer>> generateSubsets(final int[] arr) {
        return generateSubsetsHelper(arr, arr.length - 1);
    }

    private static List<List<Integer>> generateSubsetsHelper(final int[] arr, final int index) {
        if (index < 0) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> emptyList = new ArrayList<>();
            result.add(emptyList);
            return result;
        } else {
           int lastElement = arr[index];
            List<List<Integer>> subSets = generateSubsetsHelper(arr, index - 1);
            List<List<Integer>> elementAddedToSubsets = new ArrayList<>();
            for (List<Integer> list : subSets) {
                List<Integer> clonedList = new ArrayList<>(list);
                clonedList.add(lastElement);
                elementAddedToSubsets.add(clonedList);
            }
            subSets.addAll(elementAddedToSubsets);
            return subSets;
        }
    }
}

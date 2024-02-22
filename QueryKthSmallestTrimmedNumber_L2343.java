import java.util.Arrays;

class Solution {
    public static int[] smallestTrimmedNumbers(final String[] nums, final int[][] queries) {
        int lengthOfNums = nums.length;


        int[] rightMostNDigits = getKth(queries);
        int[] trimIndices = getTrimIndex(queries);
        int numOfIndices = trimIndices.length;
        int[] result = new int[numOfIndices];
        for (int i = 0; i < numOfIndices; i++) {
            int[] trimmedNums;
            int[] originalTrimmed;
            int k = rightMostNDigits[i];
            int trimIndex = trimIndices[i];
            trimmedNums = afterTrimmed(nums, trimIndex);
            originalTrimmed = afterTrimmed(nums, trimIndex);
            int resultNum = kthSmallest(trimmedNums, 0, lengthOfNums - 1, k);
            for (int j = 0; j < trimmedNums.length; j++) {
                if (originalTrimmed[j] == resultNum) {
                    result[i] = j;
                }
            }
        }
        return result;
    }

    private static int[] afterTrimmed(final String[] nums, final int lastNDigits) {
        int numsLength = nums.length;
        int[] result = new int[numsLength];
        for (int i = 0; i < numsLength; i++) {
            String strNum = nums[i];
            int lengthOfStr = strNum.length();
            String numStr = strNum.substring(lengthOfStr - lastNDigits);
            result[i] = Integer.parseInt(numStr);
        }
        return result;
    }

    private static int[] getKth(final int[][] queries) {
        int lengthOfQueries = queries.length;
        int[] result = new int[lengthOfQueries];
        for (int i = 0; i < lengthOfQueries; i++) {
            result[i] = queries[i][0];
        }
        return result;
    }

    private static int[] getTrimIndex(final int[][] queries) {
        int lengthOfQueries = queries.length;
        int[] result = new int[lengthOfQueries];
        for (int i = 0; i < lengthOfQueries; i++) {
            result[i] = queries[i][1];
        }
        return result;
    }

    private static int partition(final int[] arr, final int low, final int high) {
        int pivot = arr[high];
        int pivotLoc = low;

        for (int i = low; i <= high; i++) {
            if (arr[i] < pivot) {
                int temp = arr[i];
                arr[i] = arr[pivotLoc];
                arr[pivotLoc] = temp;
                pivotLoc++;
            }
        }
        int temp = arr[pivotLoc];
        arr[pivotLoc] = arr[high];
        arr[high] = temp;
        return pivotLoc;
    }

    private static int kthSmallest(final int[] arr, final int low, final int high, final int k) {
        int partition = partition(arr, low, high);

        if (partition == k - 1) {
            return arr[partition];
        } else if (k - 1 < partition) {
            return kthSmallest(arr, low, partition - 1, k);
        } else {
            return kthSmallest(arr, partition + 1, high, k);
        }
    }

    public static void main(String[] args) {
        String[] testArr = {"24", "37", "96", "04"};
        int[][] testQueries = {{2, 1}, {2, 2}};
        System.out.println(Arrays.toString(smallestTrimmedNumbers(testArr, testQueries)));
    }
}

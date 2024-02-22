public class MaximumSubarray_L53 {
    // T(n) = 2T(n/2) + F(n)
    // F(n) = O(n)
    // Time complex = O(nlogn)
    public static void main(String[] args) {
        int[] testArray = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maximumSubarray(testArray));
    }

    private static int maximumSubarray(final int[] arr) {
        return maximumSubarrayHelper(arr, 0, arr.length - 1);
    }

    private static int maximumSubarrayHelper(final int[] array, final int start, final int end) {
        if (start >= end) {
            return array[start];
        } else {
            int mid = (start + end) / 2;

            int leftHalfSubArraySum = maximumSubarrayHelper(array, start, mid - 1);
            int rightHalfSubArraySum = maximumSubarrayHelper(array, mid + 1, end);


            int midSubArraySum = crossMaxSumSubArray(array, start, mid, end);

            return Math.max(Math.max(leftHalfSubArraySum, rightHalfSubArraySum), midSubArraySum);
        }
    }

    private static int crossMaxSumSubArray(final int[] array, final int start, final int mid, final int end) {
        int maxLeftSum = Integer.MIN_VALUE;
        int leftSum = 0;
        for (int i = mid; i >= start; i--) {
            leftSum += array[i];
            maxLeftSum = Math.max(leftSum, maxLeftSum);
        }

        int maxRightSum = Integer.MIN_VALUE;
        int rightSum = 0;
        for (int i = mid + 1; i <= end; i++) {
            rightSum += array[i];
            maxRightSum = Math.max(rightSum, maxRightSum);
        }

        return Math.max(maxRightSum + maxLeftSum, Math.max(maxLeftSum, maxRightSum));
    }
}

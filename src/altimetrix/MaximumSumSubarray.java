package altimetrix;

import java.util.Arrays;

public class MaximumSumSubarray {
    public static void main(String[] args) {
        int[] input = {-3, 1, -8, 4, -1, 2, 1, -5, 5};

        int[] maxSumSubarray = findMaxSumSubarray(input);

        System.out.println("Input array: " + Arrays.toString(input));
        System.out.println("Maximum sum subarray: " + Arrays.toString(maxSumSubarray));
        System.out.println("Maximum sum: " + calculateSum(maxSumSubarray));
    }

    private static int[] findMaxSumSubarray(int[] nums) {
        int start = 0, end = 0;
        int currentStart = 0;
        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (currentSum + nums[i] < nums[i]) {
                currentSum = nums[i];
                currentStart = i;
            } else {
                currentSum += nums[i];
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = currentStart;
                end = i;
            }
        }

        // Copy the maximum sum subarray
        int[] result = Arrays.copyOfRange(nums, start, end + 1);

        return result;
    }

    private static int calculateSum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }
}

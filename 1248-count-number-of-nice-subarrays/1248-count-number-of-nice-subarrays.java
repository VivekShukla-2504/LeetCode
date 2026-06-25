class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }
    private int atMost(int[] nums, int k) {
        int head, tail = 0, sum = 0, result = 0;
        for (head = 0; head < nums.length; head++) {
            sum += nums[head]%2;
            while (sum > k && tail <= head) {
                sum -= nums[tail]%2;
                tail++;
            }
            result += head - tail + 1;
        }
        return result;
  
    }
}
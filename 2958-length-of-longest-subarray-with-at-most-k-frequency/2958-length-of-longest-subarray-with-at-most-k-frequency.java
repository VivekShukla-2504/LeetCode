class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {
            countMap.put(nums[right], countMap.getOrDefault(nums[right], 0) + 1);

            while (countMap.get(nums[right]) > k) {
                countMap.put(nums[left], countMap.get(nums[left]) - 1);
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
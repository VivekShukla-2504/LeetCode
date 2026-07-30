class Solution {
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;

        int[][] dp = new int[n][1001];
        int maxLength = 2;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int diff = nums[i] - nums[j] + 500;
                
                dp[i][diff] = (dp[j][diff] == 0) ? 2 : dp[j][diff] + 1;
                
                maxLength = Math.max(maxLength, dp[i][diff]);
            }
        }

        return maxLength;
    }
}
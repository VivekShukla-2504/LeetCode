class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[][] memo = new int[n][n + 1];
        
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        
        return helper(0, 1, piles, suffixSum, memo);
    }
    
    private int helper(int i, int M, int[] piles, int[] suffixSum, int[][] memo) {
        int n = piles.length;
        
        if (i + 2 * M >= n) {
            return suffixSum[i];
        }
        
        if (memo[i][M] != 0) {
            return memo[i][M];
        }
        
        int minOpponentScore = Integer.MAX_VALUE;
        
        for (int X = 1; X <= 2 * M; X++) {
            int nextM = Math.max(M, X);
            minOpponentScore = Math.min(minOpponentScore, helper(i + X, nextM, piles, suffixSum, memo));
        }
        
        memo[i][M] = suffixSum[i] - minOpponentScore;
        return memo[i][M];
    }
}
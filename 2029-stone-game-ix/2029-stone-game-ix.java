class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] cnt = new int[3];
        for (int stone : stones) {
            cnt[stone % 3]++;
        }
        
        // If count of 0-remainder stones is even
        if (cnt[0] % 2 == 0) {
            return cnt[1] >= 1 && cnt[2] >= 1;
        }
        
        // If count of 0-remainder stones is odd
        return Math.abs(cnt[1] - cnt[2]) > 2;
    }
}
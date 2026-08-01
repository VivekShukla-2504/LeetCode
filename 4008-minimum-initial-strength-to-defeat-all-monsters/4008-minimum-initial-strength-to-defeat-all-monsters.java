class Solution {
    public long minInitialStrength(int[] monsters, int[][] boosts) {
        int n = monsters.length;
        
        long[] diff = new long[n + 1];
        for (int[] boost : boosts) {
            int l = boost[0];
            int r = boost[1];
            int v = boost[2];
            diff[l] += v;
            if (r + 1 < n) {
                diff[r + 1] -= v;
            }
        }
        
        long[] bonus = new long[n];
        long currentBonus = 0;
        long totalMonsterSum = 0;
        for (int i = 0; i < n; i++) {
            currentBonus += diff[i];
            bonus[i] = currentBonus;
            totalMonsterSum += monsters[i];
        }
        
        long low = 0;
        long high = totalMonsterSum;
        long ans = high;
        
        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            if (canDefeat(mid, monsters, bonus)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return ans;
    }
    
    private boolean canDefeat(long initialStrength, int[] monsters, long[] bonus) {
        long curr = initialStrength;
        for (int i = 0; i < monsters.length; i++) {
            if (curr + bonus[i] < monsters[i]) {
                return false;
            }
            curr = Math.max(0L, curr - monsters[i]);
        }
        return true;
    }
}
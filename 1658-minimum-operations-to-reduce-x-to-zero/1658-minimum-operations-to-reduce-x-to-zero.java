class Solution {
    public int minOperations(int[] nums, int x) {
      
        int k = -x, n = nums.length;
        for (int a : nums) k += a;
        if (k < 0) return -1;
        if (k == 0) return n;

        int best = -1, i = 0, s = 0;
        for (int j = 0; j < n; j++) {
            s += nums[j];
            while (s > k)
                s -= nums[i++];

            if (s == k)
                best = Math.max(best, j - i + 1);
        }

        return best < 0 ? -1 : n - best;
    }
    
   
}
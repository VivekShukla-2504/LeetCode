class Solution {
    public List<List<Integer>> subsets(int[] nums) {
         int n = nums.length;
    List<List<Integer>> res = new ArrayList<>();
    for (long mask = 0; mask < (1L << n); mask++) {
        List<Integer> cur = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (((mask >> i) & 1L) == 1L) cur.add(nums[i]);
        res.add(cur);
    }
    return res;
    }
}
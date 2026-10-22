class Solution {
    public int singleNumber(int[] nums) {
        int xr = 0;
    for (int x : nums) xr ^= x;
    return xr;
    }
}
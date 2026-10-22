class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> f = new HashMap<>();
    for (int x : nums) f.merge(x, 1, Integer::sum);
    for (var e : f.entrySet()) if (e.getValue() == 1) return e.getKey();
    return -1;
    }
}
class Solution {
    public int[] singleNumber(int[] nums) {
        Set<Integer> s = new HashSet<>();
    for (int x : nums) if (!s.remove(x)) s.add(x);
    Iterator<Integer> it = s.iterator();
    return new int[]{it.next(), it.next()};
    }
}
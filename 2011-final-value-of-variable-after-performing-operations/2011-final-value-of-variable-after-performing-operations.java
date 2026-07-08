class Solution {
    public int finalValueAfterOperations(String[] operations) {
        return Arrays.stream(operations).mapToInt(x->x.contains("+")?1:-1).sum();
    }
}
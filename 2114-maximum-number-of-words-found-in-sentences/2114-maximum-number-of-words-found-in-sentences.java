class Solution {
    public int mostWordsFound(String[] sentences) {
        return Arrays.stream(sentences).mapToInt(x->x.split(" ").length).max().getAsInt();
    }
}
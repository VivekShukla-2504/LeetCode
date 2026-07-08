class Solution {
    public String truncateSentence(String s, int k) {
       return String.join(" ",Arrays.stream(s.split(" ")).limit(k).toList()); 
    }
}

class Solution {
    public int minimumPushes(String word) {
      
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        Arrays.sort(freq);

        int totalPushes = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[25 - i] == 0) break; 
            
            int pushMultiplier = (i / 8) + 1;
            totalPushes += freq[25 - i] * pushMultiplier;
        }

        return totalPushes;
    }
}
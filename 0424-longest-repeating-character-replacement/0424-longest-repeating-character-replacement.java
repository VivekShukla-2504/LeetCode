class Solution {
    public int characterReplacement(String s, int k) {
     
        HashMap<Character,Integer> map = new HashMap<>(26);
        //int[] freq = new int[26];
        int left = 0;
        int ans = 0;
        int maxFreq = 0;
        for(int right = 0; right < s.length(); right++) {
           char ch = s.charAt(right);
            map.put(ch,map.getOrDefault(ch,0)+1);
           //freq[ch - 'A']++;
            maxFreq = Math.max(maxFreq, map.get(ch));
            while ((right - left + 1) - maxFreq > k) 
            {
               map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
               left++;
            }

            
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
}
    }

class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String ans = "";
        int minLen = n + 1;
        
        int count = 0;
        int left = 0;
        
        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                count++;
            }
            
            // Shrink window from the left while maintaining at least k ones
            while (count == k) {
                // Shrink leading zeros to ensure the shortest valid substring starting from 'left'
                while (s.charAt(left) == '0') {
                    left++;
                }
                
                int len = right - left + 1;
                String sub = s.substring(left, right + 1);
                
                if (len < minLen) {
                    minLen = len;
                    ans = sub;
                } else if (len == minLen && sub.compareTo(ans) < 0) {
                    ans = sub;
                }
                
                // Move left past the first '1' to find the next valid window
                count--;
                left++;
            }
        }
        
        return ans;
    }
}
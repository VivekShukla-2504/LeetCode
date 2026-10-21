class Solution {
    public int minOperations(String s) {
        int n = s.length();
        
        // 0 operations: already sorted
        boolean sorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;
        
        // Impossible for length 2 if not sorted
        if (n == 2) return -1;
        
        char minChar = s.charAt(0);
        char maxChar = s.charAt(0);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c < minChar) minChar = c;
            if (c > maxChar) maxChar = c;
        }
        
        // 1 operation: minChar at index 0 or maxChar at index n - 1
        if (s.charAt(0) == minChar || s.charAt(n - 1) == maxChar) {
            return 1;
        }
        
        // 3 operations check: minChar does not appear in any proper prefix, 
        // and maxChar does not appear in any proper suffix.
        boolean minInPrefix = false;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == minChar) {
                minInPrefix = true;
                break;
            }
        }
        
        boolean maxInSuffix = false;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == maxChar) {
                maxInSuffix = true;
                break;
            }
        }
        
        if (!minInPrefix && !maxInSuffix) {
            return 3;
        }
        
        // Otherwise, 2 operations are always enough
        return 2;
    }
}
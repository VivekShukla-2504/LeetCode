class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] last = new int[m + 1];
        last[m] = n;

        int ptr = n - 1;
        for (int j = m - 1; j >= 0; j--) {
            while (ptr >= 0 && word1.charAt(ptr) != word2.charAt(j)) {
                ptr--;
            }
            last[j] = ptr;
            if (ptr >= 0) ptr--;
        }

        int[] result = new int[m];
        boolean usedMismatch = false;
        int j = 0;

        for (int i = 0; i < n && j < m; i++) {
            boolean isMatch = word1.charAt(i) == word2.charAt(j);

            if (isMatch) {
                result[j++] = i;
            } else if (!usedMismatch && last[j + 1] > i) {
                usedMismatch = true;
                result[j++] = i;
            }
        }

        return j == m ? result : new int[0];
    }
}
import java.util.*;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int oddCount = 0;
        int oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                oddChar = i;
            }
        }

        if (oddCount > 1) {
            return "";
        }

        int m = n / 2;
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        // Try matching a prefix of length i of the first half (from m down to 0)
        for (int i = m; i >= 0; i--) {
            int[] currentHalf = halfCount.clone();
            boolean possiblePrefix = true;
            for (int k = 0; k < i; k++) {
                int ch = target.charAt(k) - 'a';
                if (currentHalf[ch] > 0) {
                    currentHalf[ch]--;
                } else {
                    possiblePrefix = false;
                    break;
                }
            }
            if (!possiblePrefix) continue;

            // Case 1: i == m
            // Match the entire first half of target and see if the resulting palindrome is > target
            if (i == m) {
                // If n is odd, try middle character >= target[m]
                int startMid = (n % 2 != 0) ? target.charAt(m) - 'a' : -1;
                
                if (n % 2 != 0) {
                    // Try the exact oddChar first if it's >= target[m]
                    if (oddChar >= startMid) {
                        String res = buildResult(target, i, -1, oddChar, currentHalf, n);
                        if (res != null) return res;
                    }
                } else {
                    // Even length: exact match of first half mirrored
                    String res = buildResult(target, i, -1, -1, currentHalf, n);
                    if (res != null) return res;
                }
            }

            // Case 2: i < m
            // Pick a character larger than target[i] at position i
            if (i < m) {
                int startChar = target.charAt(i) - 'a' + 1;
                for (int c = startChar; c < 26; c++) {
                    if (currentHalf[c] > 0) {
                        int[] nextHalf = currentHalf.clone();
                        nextHalf[c]--;
                        String res = buildResult(target, i, c, oddChar, nextHalf, n);
                        if (res != null) return res;
                    }
                }
            }
        }

        return "";
    }

    private String buildResult(String target, int i, int charAtI, int oddChar, int[] remainingHalf, int n) {
        StringBuilder firstHalf = new StringBuilder();
        firstHalf.append(target, 0, i);
        if (charAtI != -1) {
            firstHalf.append((char) ('a' + charAtI));
        }

        for (int c = 0; c < 26; c++) {
            while (remainingHalf[c] > 0) {
                firstHalf.append((char) ('a' + c));
                remainingHalf[c]--;
            }
        }

        StringBuilder full = new StringBuilder(firstHalf);
        if (n % 2 != 0) {
            full.append((char) ('a' + oddChar));
        }
        
        StringBuilder rightHalf = new StringBuilder(firstHalf).reverse();
        full.append(rightHalf);

        String res = full.toString();
        return res.compareTo(target) > 0 ? res : null;
    }
}
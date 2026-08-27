class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Try matching prefix length L of target from n down to 0
        for (int L = n; L >= 0; L--) {
            int[] tempCount = count.clone();
            boolean possible = true;

            // Step 1: Match target[0...L-1]
            for (int i = 0; i < L; i++) {
                char tChar = target.charAt(i);
                if (tempCount[tChar - 'a'] > 0) {
                    tempCount[tChar - 'a']--;
                } else {
                    possible = false;
                    break;
                }
            }

            if (!possible) continue;

            // Step 2: Pick a character strictly greater than target[L]
            if (L < n) {
                int targetCharIdx = target.charAt(L) - 'a';
                int nextCharIdx = -1;

                for (int c = targetCharIdx + 1; c < 26; c++) {
                    if (tempCount[c] > 0) {
                        nextCharIdx = c;
                        break;
                    }
                }

                if (nextCharIdx != -1) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(target, 0, L);
                    sb.append((char) ('a' + nextCharIdx));
                    tempCount[nextCharIdx]--;

                    // Step 3: Append remaining characters in ascending order
                    for (int c = 0; c < 26; c++) {
                        while (tempCount[c] > 0) {
                            sb.append((char) ('a' + c));
                            tempCount[c]--;
                        }
                    }
                    return sb.toString();
                }
            }
        }

        return "";
    }
}
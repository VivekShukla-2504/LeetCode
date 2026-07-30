class Solution {
    public int numberOfCombinations(String num) {
        int n = num.length();
        char[] s = num.toCharArray();
        if (s[0] == '0') return 0;

        final int MOD = 1_000_000_007;

        int[][] lcp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s[i] == s[j]) {
                    lcp[i][j] = lcp[i + 1][j + 1] + 1;
                }
            }
        }

        int[][] sum = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                long dpVal;
                if (s[i - j] == '0') {
                    dpVal = 0;
                } else if (i - j == 0) {
                    dpVal = 1;
                } else {
                    int prevLen = j - 1;
                    int cap = Math.min(prevLen, i - j);
                    long val = sum[i - j][cap]; 

                    int i2j = i - 2 * j;
                    if (i2j >= 0) {
                        int p = i2j;      
                        int q = i - j;    
                        int len = lcp[p][q];
                        boolean le;
                        if (len >= j) {
                            le = true; 
                        } else {
                            le = s[p + len] <= s[q + len];
                        }
                        if (le) {
                            long dpPrevEqual = (sum[i - j][j] - sum[i - j][j - 1] + MOD) % MOD;
                            val = (val + dpPrevEqual) % MOD;
                        }
                    }
                    dpVal = val;
                }
                sum[i][j] = (int) ((sum[i][j - 1] + dpVal) % MOD);
            }
        }

        return sum[n][n];
    }
}
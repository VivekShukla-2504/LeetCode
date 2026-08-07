import java.util.Arrays;

class Solution {
    private static final int[][] dp = new int[60][60];

    static {
        for (int i = 0; i < 60; i++) {
            Arrays.fill(dp[i], 1000);
        }
        dp[0][0] = 0;
        for (int u = 0; u < 60; u++) {
            for (int v = 0; v < 60; v++) {
                if (u == 0 && v == 0) continue;
                int res = 1000;
                res = Math.min(res, 1 + dp[Math.max(0, u - 3)][v]); // '8'
                res = Math.min(res, 1 + dp[u][Math.max(0, v - 2)]); // '9'
                res = Math.min(res, 1 + dp[Math.max(0, u - 1)][Math.max(0, v - 1)]); // '6'
                res = Math.min(res, 1 + dp[Math.max(0, u - 2)][v]); // '4'
                res = Math.min(res, 1 + dp[u][Math.max(0, v - 1)]); // '3'
                res = Math.min(res, 1 + dp[Math.max(0, u - 1)][v]); // '2'
                dp[u][v] = res;
            }
        }
    }

    public String smallestNumber(String num, long t) {
        int c2 = 0, c3 = 0, c5 = 0, c7 = 0;
        long tempT = t;
        while (tempT % 2 == 0) { c2++; tempT /= 2; }
        while (tempT % 3 == 0) { c3++; tempT /= 3; }
        while (tempT % 5 == 0) { c5++; tempT /= 5; }
        while (tempT % 7 == 0) { c7++; tempT /= 7; }
        if (tempT > 1) return "-1";

        int n = num.length();
        int zeroIdx = num.indexOf('0');
        int limit = (zeroIdx == -1) ? n : zeroIdx;

        int[] req2 = new int[limit + 1];
        int[] req3 = new int[limit + 1];
        int[] req5 = new int[limit + 1];
        int[] req7 = new int[limit + 1];

        req2[0] = c2; req3[0] = c3; req5[0] = c5; req7[0] = c7;

        for (int i = 0; i < limit; i++) {
            int d = num.charAt(i) - '0';
            req2[i + 1] = Math.max(0, req2[i] - factorCount(d, 2));
            req3[i + 1] = Math.max(0, req3[i] - factorCount(d, 3));
            req5[i + 1] = Math.max(0, req5[i] - factorCount(d, 5));
            req7[i + 1] = Math.max(0, req7[i] - factorCount(d, 7));
        }

        if (zeroIdx == -1 && req2[n] == 0 && req3[n] == 0 && req5[n] == 0 && req7[n] == 0) {
            return num;
        }

        for (int i = limit; i >= 0; i--) {
            int startDigit = (i < n) ? (num.charAt(i) - '0' + 1) : 1;

            for (int d = startDigit; d <= 9; d++) {
                int r2 = Math.max(0, req2[i] - factorCount(d, 2));
                int r3 = Math.max(0, req3[i] - factorCount(d, 3));
                int r5 = Math.max(0, req5[i] - factorCount(d, 5));
                int r7 = Math.max(0, req7[i] - factorCount(d, 7));

                int remLen = n - 1 - i;
                if (minDigitsNeeded(r2, r3, r5, r7) <= remLen) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i);
                    sb.append(d);
                    sb.append(fillSmallestSuffix(remLen, r2, r3, r5, r7));
                    return sb.toString();
                }
            }
        }

        int targetLen = Math.max(n + 1, minDigitsNeeded(c2, c3, c5, c7));
        return fillSmallestSuffix(targetLen, c2, c3, c5, c7);
    }

    private int factorCount(int d, int prime) {
        int count = 0;
        while (d > 0 && d % prime == 0) {
            count++;
            d /= prime;
        }
        return count;
    }

    private int minDigitsNeeded(int c2, int c3, int c5, int c7) {
        return dp[c2][c3] + c5 + c7;
    }

    private String fillSmallestSuffix(int len, int r2, int r3, int r5, int r7) {
        StringBuilder sb = new StringBuilder();
        for (int pos = 0; pos < len; pos++) {
            int remLen = len - 1 - pos;
            for (int d = 1; d <= 9; d++) {
                int nextR2 = Math.max(0, r2 - factorCount(d, 2));
                int nextR3 = Math.max(0, r3 - factorCount(d, 3));
                int nextR5 = Math.max(0, r5 - factorCount(d, 5));
                int nextR7 = Math.max(0, r7 - factorCount(d, 7));

                if (minDigitsNeeded(nextR2, nextR3, nextR5, nextR7) <= remLen) {
                    sb.append(d);
                    r2 = nextR2;
                    r3 = nextR3;
                    r5 = nextR5;
                    r7 = nextR7;
                    break;
                }
            }
        }
        return sb.toString();
    }
}
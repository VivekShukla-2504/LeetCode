class Solution {
    static final long LIMIT = 1_000_001L;

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) freq[ch - 'a']++;

        char middle = 0;
        int[] half = new int[26];
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) middle = (char) ('a' + i);
            half[i] = freq[i] / 2;
        }

        int n = 0;
        for (int x : half) n += x;

        int[] primes = sieve(n);               

        if (k > countWays(half, n, primes, k)) return "";

        StringBuilder firstHalf = new StringBuilder();
        long[] numPow    = new long[primes.length];
        long[] denomBase = new long[primes.length];
        int len = n;

        while (len > 0) {
            int m = 0;
            while (m < primes.length && primes[m] <= len) m++;

            for (int pi = 0; pi < m; pi++) {
                int p = primes[pi];
                numPow[pi] = factPower(len - 1, p);
                long d = 0;
                for (int c = 0; c < 26; c++)
                    if (half[c] > 0) d += factPower(half[c], p);
                denomBase[pi] = d;
            }

            boolean placed = false;
            for (int c = 0; c < 26 && !placed; c++) {
                if (half[c] == 0) continue;

                long ways = 1;
                for (int pi = 0; pi < m; pi++) {
                    int p = primes[pi];
                    long vp = valuation(half[c], p);
                    long power = numPow[pi] - denomBase[pi] + vp;
                    while (power-- > 0) {
                        ways *= p;
                        if (ways >= LIMIT) { ways = LIMIT; break; }
                    }
                    if (ways >= LIMIT) break;
                }

                if (k > ways) {
                    k -= ways;
                } else {
                    half[c]--;
                    firstHalf.append((char) ('a' + c));
                    placed = true;
                }
            }

            if (!placed) return "";
            len--;
        }

        StringBuilder ans = new StringBuilder(firstHalf);
        if (middle != 0) ans.append(middle);
        ans.append(firstHalf.reverse());
        return ans.toString();
    }

    private long countWays(int[] freq, int n, int[] primes, long limit) {
        long ans = 1;
        for (int p : primes) {
            if (p > n) break;
            long power = factPower(n, p);
            for (int x : freq) power -= factPower(x, p);
            while (power-- > 0) {
                ans *= p;
                if (ans >= limit) return limit;
            }
        }
        return ans;
    }

    private long factPower(int n, int p) {
        long c = 0;
        while (n > 0) { n /= p; c += n; }
        return c;
    }

    private long valuation(int x, int p) {
        long c = 0;
        while (x % p == 0) { x /= p; c++; }
        return c;
    }

    private int[] sieve(int n) {
        if (n < 2) return new int[0];
        boolean[] comp = new boolean[n + 1];
        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (!comp[i]) {
                cnt++;
                for (long j = (long) i * i; j <= n; j += i) comp[(int) j] = true;
            }
        }
        int[] primes = new int[cnt];
        int idx = 0;
        for (int i = 2; i <= n; i++) if (!comp[i]) primes[idx++] = i;
        return primes;
    }
}


class Solution {

    static final long LIMIT = 1_000_001L;

    public String smallestPalindrome(String s, int k) {

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }


        char middle = 0;
        int[] half = new int[26];

        for (int i = 0; i < 26; i++) {

            if ((freq[i] & 1) == 1)
                middle = (char) ('a' + i);

            half[i] = freq[i] / 2;
        }


        int n = 0;

        for (int x : half)
            n += x;


        int[] primes = sieve(n);
        int pCount = primes.length;


        int[][] factPow = new int[n + 1][pCount];


        for (int i = 1; i <= n; i++) {

            for (int j = 0; j < pCount; j++) {

                int p = primes[j];

                factPow[i][j] = factPow[i - 1][j];

                int x = i;

                while (x % p == 0) {
                    factPow[i][j]++;
                    x /= p;
                }
            }
        }


        long[] denom = new long[pCount];


        for (int i = 0; i < 26; i++) {

            if (half[i] == 0)
                continue;

            for (int j = 0; j < pCount; j++) {
                denom[j] += factPow[half[i]][j];
            }
        }


        long total = countWays(
                n,
                factPow,
                denom,
                primes,
                k
        );


        if (total < k)
            return "";


        StringBuilder firstHalf = new StringBuilder();

        int len = n;


        while (len > 0) {

            boolean placed = false;


            for (int c = 0; c < 26; c++) {

                if (half[c] == 0)
                    continue;


                int old = half[c];


                half[c]--;


                for (int j = 0; j < pCount; j++) {

                    denom[j] -= factPow[old][j] - factPow[old - 1][j];

                }


                long ways = countWays(
                        len - 1,
                        factPow,
                        denom,
                        primes,
                        k
                );


                if (k > ways) {

                    k -= ways;


                    half[c]++;

                    for (int j = 0; j < pCount; j++) {

                        denom[j] += factPow[old][j] - factPow[old - 1][j];

                    }

                } 
                else {

                    firstHalf.append((char) ('a' + c));
                    placed = true;
                    break;

                }
            }


            if (!placed)
                return "";


            len--;
        }


        StringBuilder ans = new StringBuilder(firstHalf);

        if (middle != 0)
            ans.append(middle);

        ans.append(firstHalf.reverse());


        return ans.toString();
    }



    private long countWays(
            int n,
            int[][] factPow,
            long[] denom,
            int[] primes,
            int limit
    ) {

        long ans = 1;


        for (int j = 0; j < primes.length; j++) {

            long power = factPow[n][j] - denom[j];


            while (power-- > 0) {

                ans *= primes[j];


                if (ans >= limit)
                    return LIMIT;
            }
        }


        return ans;
    }



    private int[] sieve(int n) {

        if (n < 2)
            return new int[0];


        boolean[] composite = new boolean[n + 1];

        ArrayList<Integer> list = new ArrayList<>();


        for (int i = 2; i <= n; i++) {

            if (!composite[i]) {

                list.add(i);


                if ((long)i * i <= n) {

                    for (long j = (long)i * i; j <= n; j += i) {
                        composite[(int)j] = true;
                    }
                }
            }
        }


        int[] primes = new int[list.size()];


        for (int i = 0; i < primes.length; i++) {
            primes[i] = list.get(i);
        }


        return primes;
    }
}
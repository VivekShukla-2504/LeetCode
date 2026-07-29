class Solution {

    static final long LIMIT = 1000001;

    public String smallestPalindrome(String s, int k) {

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder left = new StringBuilder();
        char middle = 0;

        for (int i = 0; i < 26; i++) {

            if (freq[i] % 2 == 1) {
                middle = (char) ('a' + i);
            }

            for (int j = 0; j < freq[i] / 2; j++) {
                left.append((char) ('a' + i));
            }
        }


        int[] halfFreq = new int[26];

        for (char c : left.toString().toCharArray()) {
            halfFreq[c - 'a']++;
        }


        int totalWays = (int) countWays(halfFreq, k);

        if (k > totalWays) {
            return "";
        }


        StringBuilder firstHalf = new StringBuilder();

        int len = left.length();

        while (len > 0) {

            boolean placed = false;

            for (int i = 0; i < 26; i++) {

                if (halfFreq[i] == 0)
                    continue;


                halfFreq[i]--;

                long ways = countWays(halfFreq, k);


                if (k > ways) {

                    k -= ways;
                    halfFreq[i]++;

                } else {

                    firstHalf.append((char) ('a' + i));
                    placed = true;
                    break;
                }
            }


            if (!placed)
                return "";


            len--;
        }


        StringBuilder ans = new StringBuilder();

        ans.append(firstHalf);

        if (middle != 0)
            ans.append(middle);

        ans.append(firstHalf.reverse());


        return ans.toString();
    }



private long countWays(int[] freq, int limit) {

    int n = 0;

    for (int x : freq)
        n += x;


    int[] primes = generatePrimes(n);

    long ans = 1;


    for (int p : primes) {

        int power = factPower(n, p);

        for (int x : freq) {
            power -= factPower(x, p);
        }


        while (power-- > 0) {

            ans *= p;

            if (ans >= limit)
                return limit;
        }
    }

    return ans;
}


private int factPower(int n, int p) {

    int count = 0;

    while (n > 0) {

        n /= p;
        count += n;
    }

    return count;
}


private int[] generatePrimes(int n) {

    boolean[] isPrime = new boolean[n + 1];

    Arrays.fill(isPrime, true);

    if (n >= 0)
        isPrime[0] = false;

    if (n >= 1)
        isPrime[1] = false;


    for (int i = 2; i * i <= n; i++) {

        if (isPrime[i]) {

            for (int j = i * i; j <= n; j += i) {
                isPrime[j] = false;
            }
        }
    }


    ArrayList<Integer> list = new ArrayList<>();

    for (int i = 2; i <= n; i++) {

        if (isPrime[i])
            list.add(i);
    }


    int[] primes = new int[list.size()];

    for (int i = 0; i < list.size(); i++)
        primes[i] = list.get(i);


    return primes;
}
}
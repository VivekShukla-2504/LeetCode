
class Solution {
    public int countPrimes(int n) {
        if (n <= 2) return 0;

        boolean[] comp = new boolean[n];

        for (int p = 2; p * p < n; p++) {
            if (!comp[p]) {
                for (int j = p * p; j < n; j += p) {
                    comp[j] = true;
                }
            }
        }

        int count = 0;

        for (int i = 2; i < n; i++) {
            if (!comp[i]) {
                count++;
            }
        }

        return count;
    }
}

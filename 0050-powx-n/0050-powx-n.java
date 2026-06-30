class Solution {
    public double myPow(double x, int n) {

        long power = n; // important

        if (power < 0)
            power = -power;

        double ans = 1;

        while (power > 0) {

            if ((power & 1) == 1) {   // odd
                ans *= x;
            }

            x *= x;
            power /= 2;
        }

        if (n < 0)
            return 1 / ans;

        return ans;
    }
}
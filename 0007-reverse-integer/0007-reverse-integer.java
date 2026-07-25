class Solution {
    public int reverse(int x) {
        long result = 0; // use long to safely detect overflow before casting

        while (x != 0) {
            int digit = x % 10;
            x /= 10;
            result = result * 10 + digit;

            // check overflow against int range
            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                return 0;
            }
        }

        return (int) result;
    }
}
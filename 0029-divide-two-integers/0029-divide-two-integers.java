class Solution {
    public int divide(int dividend, int divisor) {
     if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
    if (dividend == divisor) return 1;
    boolean neg = (dividend < 0) != (divisor < 0);
    long a = Math.abs((long) dividend), b = Math.abs((long) divisor), q = 0;
    while (a >= b) {
        long t = b, m = 1;
        while ((t << 1) <= a) { t <<= 1; m <<= 1; }
        a -= t; q += m;
    }
    return neg ? (int) -q : (int) q;
    }
}
class Solution {
    public int minBitFlips(int start, int goal) {
         int x = start ^ goal, c = 0;
    while (x != 0) { x &= (x - 1); c++; }
    return c;
    }
}
class Solution {
public:
    bool checkDivisibility(int n) {
         int x = 0;
        int y = 1;
        int ori = n;
        if (n <= 0) {
            return false;
        }
        while (n > 0) {
            x = x + (n % 10);
            y = y * (n % 10);
            n = n / 10;
        }

        return ori % (x + y) == 0;
    }
};
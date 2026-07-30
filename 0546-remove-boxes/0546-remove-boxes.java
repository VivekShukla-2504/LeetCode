class Solution {
    private int[][][] memo;

    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        memo = new int[n][n][n];
        return calculatePoints(boxes, 0, n - 1, 0);
    }

    private int calculatePoints(int[] boxes, int i, int j, int k) {
        if (i > j) {
            return 0;
        }

        if (memo[i][j][k] > 0) {
            return memo[i][j][k];
        }

        int originalI = i;
        int originalK = k;
        while (i + 1 <= j && boxes[i] == boxes[i + 1]) {
            i++;
            k++;
        }
        int maxPoints = (k + 1) * (k + 1) + calculatePoints(boxes, i + 1, j, 0);

        for (int m = i + 1; m <= j; m++) {
            if (boxes[m] == boxes[i]) {
                int currentPoints = calculatePoints(boxes, i + 1, m - 1, 0) 
                                  + calculatePoints(boxes, m, j, k + 1);
                maxPoints = Math.max(maxPoints, currentPoints);
            }
        }

        memo[originalI][j][originalK] = maxPoints;
        return maxPoints;
    }
}
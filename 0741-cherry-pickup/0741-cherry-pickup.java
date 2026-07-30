import java.util.Arrays;

class Solution {
    private int[][][] memo;
    private int n;

    public int cherryPickup(int[][] grid) {
        n = grid.length;
        
        // Quick check: If starting or destination cells are blocked
        if (grid[0][0] == -1 || grid[n - 1][n - 1] == -1) {
            return 0;
        }

        // Initialize memoization array with Integer.MIN_VALUE / 2 (unvisited)
        memo = new int[n][n][n];
        for (int[][] matrix : memo) {
            for (int[] row : matrix) {
                Arrays.fill(row, Integer.MIN_VALUE);
            }
        }

        int result = dp(grid, 0, 0, 0);
        return Math.max(0, result);
    }

    private int dp(int[][] grid, int r1, int c1, int r2) {
        int c2 = r1 + c1 - r2;

        // Boundary checks or hitting a thorn (-1)
        if (r1 >= n || c1 >= n || r2 >= n || c2 >= n || grid[r1][c1] == -1 || grid[r2][c2] == -1) {
            return -1000000; // Small negative number representing invalid path
        }

        // Reached destination (n-1, n-1)
        if (r1 == n - 1 && c1 == n - 1) {
            return grid[r1][c1];
        }

        // Return memoized result if already computed
        if (memo[r1][c1][r2] != Integer.MIN_VALUE) {
            return memo[r1][c1][r2];
        }

        // Cherries collected at the current step
        int cherries = 0;
        if (r1 == r2 && c1 == c2) {
            cherries += grid[r1][c1]; // Both at same cell: pick once
        } else {
            cherries += grid[r1][c1] + grid[r2][c2]; // Different cells: pick both
        }

        // Explore all 4 possible move combinations for person 1 and person 2
        int nextMax = Math.max(
            Math.max(dp(grid, r1 + 1, c1, r2 + 1), dp(grid, r1 + 1, c1, r2)),
            Math.max(dp(grid, r1, c1 + 1, r2 + 1), dp(grid, r1, c1 + 1, r2))
        );

        cherries += nextMax;

        memo[r1][c1][r2] = cherries;
        return cherries;
    }
}
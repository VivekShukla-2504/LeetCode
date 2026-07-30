
class Solution {
    private Integer[][][] memo;

    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        memo = new Integer[n][n][n];
        return Math.max(0, dp(grid, 0, 0, 0, n));
    }

    private int dp(int[][] grid, int r1, int c1, int r2, int n) {
        int c2 = r1 + c1 - r2;

        // Out of bounds or thorn (-1) check
        if (r1 >= n || c1 >= n || r2 >= n || c2 >= n || 
            grid[r1][c1] == -1 || grid[r2][c2] == -1) {
            return -1000000; // Return a large negative number for invalid paths
        }

        // Reached destination (n-1, n-1)
        if (r1 == n - 1 && c1 == n - 1) {
            return grid[r1][c1];
        }

        // Return memoized result if available
        if (memo[r1][c1][r2] != null) {
            return memo[r1][c1][r2];
        }

        // Cherries collected at the current step
        int cherries = 0;
        if (r1 == r2 && c1 == c2) {
            cherries += grid[r1][c1]; // Pick up cherry only once if both are on the same cell
        } else {
            cherries += grid[r1][c1] + grid[r2][c2];
        }

        // Explore 4 possible moves for Person 1 and Person 2:
        // 1. Person 1 Down, Person 2 Down
        // 2. Person 1 Down, Person 2 Right
        // 3. Person 1 Right, Person 2 Down
        // 4. Person 1 Right, Person 2 Right
        int maxNext = Math.max(
            Math.max(dp(grid, r1 + 1, c1, r2 + 1, n), dp(grid, r1 + 1, c1, r2, n)),
            Math.max(dp(grid, r1, c1 + 1, r2 + 1, n), dp(grid, r1, c1 + 1, r2, n))
        );

        cherries += maxNext;
        return memo[r1][c1][r2] = cherries;
    }
}
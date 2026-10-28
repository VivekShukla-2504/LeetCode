class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] queens = new int[n];
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n];
        boolean[] diag2 = new boolean[2 * n];
        
        dfs(0, n, queens, cols, diag1, diag2, res);
        return res;
    }

    private void dfs(int row, int n, int[] queens, boolean[] cols, boolean[] diag1, boolean[] diag2, List<List<String>> res) {
        if (row == n) {
            List<String> board = new ArrayList<>();
            for (int r = 0; r < n; r++) {
                char[] line = new char[n];
                Arrays.fill(line, '.');
                line[queens[r]] = 'Q';
                board.add(new String(line));
            }
            res.add(board);
            return;
        }

        for (int c = 0; c < n; c++) {
            int d1 = row - c + n;
            int d2 = row + c;
            if (!cols[c] && !diag1[d1] && !diag2[d2]) {
                queens[row] = c;
                cols[c] = diag1[d1] = diag2[d2] = true;
                dfs(row + 1, n, queens, cols, diag1, diag2, res);
                cols[c] = diag1[d1] = diag2[d2] = false;
            }
        }
    }
}
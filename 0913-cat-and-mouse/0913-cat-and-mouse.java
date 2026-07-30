
class Solution {
    private static final int MOUSE_TURN = 1;
    private static final int CAT_TURN = 2;

    private static final int DRAW = 0;
    private static final int MOUSE_WIN = 1;
    private static final int CAT_WIN = 2;

    public int catMouseGame(int[][] graph) {
        int n = graph.length;

        // color[m][c][t] stores the outcome of the state (m, c, t)
        // 0: DRAW (default), 1: MOUSE_WIN, 2: CAT_WIN
        int[][][] color = new int[n][n][3];

        // degree[m][c][t] stores the number of available moves for the current player
        int[][][] degree = new int[n][n][3];

        // Precompute degrees for all states
        for (int m = 0; m < n; m++) {
            for (int c = 1; c < n; c++) {
                degree[m][c][MOUSE_TURN] = graph[m].length;
                degree[m][c][CAT_TURN] = graph[c].length;

                // Cat cannot move to Node 0 (Hole)
                for (int node : graph[c]) {
                    if (node == 0) {
                        degree[m][c][CAT_TURN]--;
                        break;
                    }
                }
            }
        }

        // Queue for BFS backward propagation
        // Array format: {mousePos, catPos, turn, result}
        Queue<int[]> queue = new ArrayDeque<>();

        // 1. Initialize Terminal States
        for (int i = 1; i < n; i++) {
            for (int t = 1; t <= 2; t++) {
                // Mouse reached hole -> Mouse Wins
                color[0][i][t] = MOUSE_WIN;
                queue.offer(new int[]{0, i, t, MOUSE_WIN});

                // Cat caught Mouse -> Cat Wins
                color[i][i][t] = CAT_WIN;
                queue.offer(new int[]{i, i, t, CAT_WIN});
            }
        }

        // 2. Backward BFS Propagation
        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int m = state[0];
            int c = state[1];
            int t = state[2];
            int res = state[3];

            // If we reached the initial state, return the calculated result early
            if (m == 1 && c == 2 && t == MOUSE_TURN) {
                return res;
            }

            // Find all predecessor states (states that could lead into the current state)
            int prevTurn = (t == MOUSE_TURN) ? CAT_TURN : MOUSE_TURN;

            if (prevTurn == MOUSE_TURN) {
                for (int prevM : graph[m]) {
                    if (color[prevM][c][MOUSE_TURN] != DRAW) continue;

                    // If Mouse can move to a state where Mouse Wins
                    if (res == MOUSE_WIN) {
                        color[prevM][c][MOUSE_TURN] = MOUSE_WIN;
                        queue.offer(new int[]{prevM, c, MOUSE_TURN, MOUSE_WIN});
                    } else {
                        // Res == CAT_WIN: Decrement available moves for Mouse
                        degree[prevM][c][MOUSE_TURN]--;
                        if (degree[prevM][c][MOUSE_TURN] == 0) {
                            color[prevM][c][MOUSE_TURN] = CAT_WIN;
                            queue.offer(new int[]{prevM, c, MOUSE_TURN, CAT_WIN});
                        }
                    }
                }
            } else { // prevTurn == CAT_TURN
                for (int prevC : graph[c]) {
                    if (prevC == 0 || color[m][prevC][CAT_TURN] != DRAW) continue;

                    // If Cat can move to a state where Cat Wins
                    if (res == CAT_WIN) {
                        color[m][prevC][CAT_TURN] = CAT_WIN;
                        queue.offer(new int[]{m, prevC, CAT_TURN, CAT_WIN});
                    } else {
                        // Res == MOUSE_WIN: Decrement available moves for Cat
                        degree[m][prevC][CAT_TURN]--;
                        if (degree[m][prevC][CAT_TURN] == 0) {
                            color[m][prevC][CAT_TURN] = MOUSE_WIN;
                            queue.offer(new int[]{m, prevC, CAT_TURN, MOUSE_WIN});
                        }
                    }
                }
            }
        }

        return color[1][2][MOUSE_TURN];
    }
}

class Solution {
    private static final int MOUSE_TURN = 1;
    private static final int CAT_TURN = 2;

    private static final int DRAW = 0;
    private static final int MOUSE_WIN = 1;
    private static final int CAT_WIN = 2;

    public int catMouseGame(int[][] graph) {
        int n = graph.length;

        int[][][] color = new int[n][n][3];

        int[][][] degree = new int[n][n][3];

        for (int m = 0; m < n; m++) {
            for (int c = 1; c < n; c++) {
                degree[m][c][MOUSE_TURN] = graph[m].length;
                degree[m][c][CAT_TURN] = graph[c].length;

                for (int node : graph[c]) {
                    if (node == 0) {
                        degree[m][c][CAT_TURN]--;
                        break;
                    }
                }
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 1; i < n; i++) {
            for (int t = 1; t <= 2; t++) {
                color[0][i][t] = MOUSE_WIN;
                queue.offer(new int[]{0, i, t, MOUSE_WIN});

                color[i][i][t] = CAT_WIN;
                queue.offer(new int[]{i, i, t, CAT_WIN});
            }
        }

        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int m = state[0];
            int c = state[1];
            int t = state[2];
            int res = state[3];

            if (m == 1 && c == 2 && t == MOUSE_TURN) {
                return res;
            }

            int prevTurn = (t == MOUSE_TURN) ? CAT_TURN : MOUSE_TURN;

            if (prevTurn == MOUSE_TURN) {
                for (int prevM : graph[m]) {
                    if (color[prevM][c][MOUSE_TURN] != DRAW) continue;

                    if (res == MOUSE_WIN) {
                        color[prevM][c][MOUSE_TURN] = MOUSE_WIN;
                        queue.offer(new int[]{prevM, c, MOUSE_TURN, MOUSE_WIN});
                    } else {
                        degree[prevM][c][MOUSE_TURN]--;
                        if (degree[prevM][c][MOUSE_TURN] == 0) {
                            color[prevM][c][MOUSE_TURN] = CAT_WIN;
                            queue.offer(new int[]{prevM, c, MOUSE_TURN, CAT_WIN});
                        }
                    }
                }
            } else {
                for (int prevC : graph[c]) {
                    if (prevC == 0 || color[m][prevC][CAT_TURN] != DRAW) continue;

                    if (res == CAT_WIN) {
                        color[m][prevC][CAT_TURN] = CAT_WIN;
                        queue.offer(new int[]{m, prevC, CAT_TURN, CAT_WIN});
                    } else {
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
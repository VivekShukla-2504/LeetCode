class Solution {

    private static final int DRAW = 0;
    private static final int MOUSE = 1;
    private static final int CAT = 2;

    public int catMouseGame(int[][] graph) {
        int n = graph.length;

        int[][][] color = new int[n][n][2];
        int[][][] degree = new int[n][n][2];

        for (int m = 0; m < n; m++) {
            for (int c = 0; c < n; c++) {
                degree[m][c][0] = graph[m].length;
                degree[m][c][1] = graph[c].length;

                for (int next : graph[c]) {
                    if (next == 0) {
                        degree[m][c][1]--;
                    }
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();

        for (int c = 1; c < n; c++) {
            color[0][c][0] = MOUSE;
            color[0][c][1] = MOUSE;
            queue.offer(new int[]{0, c, 0, MOUSE});
            queue.offer(new int[]{0, c, 1, MOUSE});
        }

        for (int i = 1; i < n; i++) {
            color[i][i][0] = CAT;
            color[i][i][1] = CAT;
            queue.offer(new int[]{i, i, 0, CAT});
            queue.offer(new int[]{i, i, 1, CAT});
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int m = cur[0];
            int c = cur[1];
            int turn = cur[2];
            int result = cur[3];

            for (int[] parent : parents(graph, m, c, turn)) {
                int pm = parent[0];
                int pc = parent[1];
                int pTurn = parent[2];

                if (color[pm][pc][pTurn] != DRAW) continue;

                if ((pTurn == 0 && result == MOUSE) ||
                    (pTurn == 1 && result == CAT)) {

                    color[pm][pc][pTurn] = result;
                    queue.offer(new int[]{pm, pc, pTurn, result});
                } else {
                    degree[pm][pc][pTurn]--;
                    if (degree[pm][pc][pTurn] == 0) {
                        int lose = (pTurn == 0) ? CAT : MOUSE;
                        color[pm][pc][pTurn] = lose;
                        queue.offer(new int[]{pm, pc, pTurn, lose});
                    }
                }
            }
        }

        return color[1][2][0];
    }

    private List<int[]> parents(int[][] graph, int mouse, int cat, int turn) {
        List<int[]> res = new ArrayList<>();

        if (turn == 0) {
            for (int prevCat : graph[cat]) {
                if (prevCat == 0) continue;
                res.add(new int[]{mouse, prevCat, 1});
            }
        } else {
            for (int prevMouse : graph[mouse]) {
                res.add(new int[]{prevMouse, cat, 0});
            }
        }

        return res;
    }
}
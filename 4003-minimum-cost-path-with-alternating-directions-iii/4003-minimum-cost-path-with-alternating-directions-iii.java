import java.util.*;

class Solution {
    static class State implements Comparable<State> {
        long dist;
        long node;

        State(long dist, long node) {
            this.dist = dist;
            this.node = node;
        }

        @Override
        public int compareTo(State other) {
            return Long.compare(this.dist, other.dist);
        }
    }

    public long minCost(int m, int n, int[][] penalty) {

        long totalNodes = (long) m * n * 2;
        long[] dist = new long[(int) totalNodes];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<State> pq = new PriorityQueue<>();

        long startCost = entrance(0, 0);
        dist[(int) id(0, 0, 1, n)] = startCost;
        pq.offer(new State(startCost, id(0, 0, 1, n)));

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            long d = cur.dist;
            long u = cur.node;

            if (d > dist[(int) u]) continue;

            int p = (int) (u % 2);
            long cell = u / 2;

            int j = (int) (cell % n);
            int i = (int) (cell / n);

            // Flip parity at current cell
            {
                long cost = penalty[i][j];
                int np = 1 - p;

                long v = id(i, j, np, n);

                if (dist[(int) v] > d + cost) {
                    dist[(int) v] = d + cost;
                    pq.offer(new State(dist[(int) v], v));
                }
            }

            // Move to neighbors
            for (int dir = 0; dir < 4; dir++) {

                int ni = i + dx[dir];
                int nj = j + dy[dir];

                if (ni < 0 || ni >= m || nj < 0 || nj >= n)
                    continue;

                boolean isRightOrDown = (dir == 0 || dir == 2);
                boolean matches = (p == 1) ? isRightOrDown : !isRightOrDown;

                long cost = entrance(ni, nj);

                if (!matches)
                    cost += penalty[i][j];

                int np = 1 - p;

                long v = id(ni, nj, np, n);

                if (dist[(int) v] > d + cost) {
                    dist[(int) v] = d + cost;
                    pq.offer(new State(dist[(int) v], v));
                }
            }
        }

        return Math.min(
                dist[(int) id(m - 1, n - 1, 0, n)],
                dist[(int) id(m - 1, n - 1, 1, n)]);
    }

    private long entrance(long i, long j) {
        return (i + 1) * (j + 1);
    }

    private long id(int i, int j, int p, int n) {
        return (((long) i * n + j) * 2 + p);
    }
}
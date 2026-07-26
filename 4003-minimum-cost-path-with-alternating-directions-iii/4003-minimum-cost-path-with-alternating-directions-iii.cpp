class Solution {
public:
    long long minCost(int m, int n, vector<vector<int>>& penalty) {
        auto entrance = [](long long i, long long j) -> long long {
            return (i + 1) * (j + 1);
        };

        auto id = [&](int i, int j, int p) -> long long {
            return ((long long)i * n + j) * 2 + p;
        };

        long long totalNodes = (long long)m * n * 2;
        vector<long long> dist(totalNodes, LLONG_MAX);

        priority_queue<pair<long long, long long>,
                       vector<pair<long long, long long>>,
                       greater<pair<long long, long long>>> pq;

        long long startCost = entrance(0, 0);
        dist[id(0, 0, 1)] = startCost;   
        pq.push({startCost, id(0, 0, 1)});

        int dx[4] = {0, 0, 1, -1};
        int dy[4] = {1, -1, 0, 0};
        

        while (!pq.empty()) {
            auto top = pq.top(); pq.pop();
            long long d = top.first;
            long long u = top.second;
            if (d > dist[u]) continue;

            int p = (int)(u % 2);
            long long node = u / 2;
            int j = (int)(node % n);
            int i = (int)(node / n);

           
            {
                long long cost = penalty[i][j];
                int np = 1 - p;
                long long v = id(i, j, np);
                if (dist[v] > d + cost) {
                    dist[v] = d + cost;
                    pq.push({dist[v], v});
                }
            }

            
            for (int dir = 0; dir < 4; dir++) {
                int ni = i + dx[dir];
                int nj = j + dy[dir];
                if (ni < 0 || ni >= m || nj < 0 || nj >= n) continue;

                bool isRightOrDown = (dir == 0 || dir == 2);
                bool matches = (p == 1) ? isRightOrDown : !isRightOrDown;

                long long cost = entrance(ni, nj);
                if (!matches) cost += penalty[i][j];

                int np = 1 - p;
                long long v = id(ni, nj, np);
                if (dist[v] > d + cost) {
                    dist[v] = d + cost;
                    pq.push({dist[v], v});
                }
            }
        }

        long long ans = min(dist[id(m - 1, n - 1, 0)], dist[id(m - 1, n - 1, 1)]);
        return ans;
    }
};
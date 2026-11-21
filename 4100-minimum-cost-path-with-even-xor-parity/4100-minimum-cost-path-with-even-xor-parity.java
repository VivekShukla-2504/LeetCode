class Solution {
    public int minActivationCost(int[][] edges, int[] values) {
        int n = values.length;
        int [] w = new int[n];
        for(int i = 0;i<n;i++)
        {
            w[i] = Integer.bitCount(values[i]) &1;
        }
        long ans = Long.MAX_VALUE;

        for(int[]e:edges)
            {
                int u = e[0],v = e[1], c = e[2];
                if(w[u] == w[v])
                {
                    ans = Math.min(ans,c);
                }
            }
        int [] min1 = new int [n];
        int [] min2 = new int[n];
        Arrays.fill(min1, Integer.MAX_VALUE);
        
        Arrays.fill(min2, Integer.MAX_VALUE);
        for(int []e:edges)
            {
                int u = e[0],v =e[1],c = e[2];
                if(w[u] == 0&& w[v] == 1)
                {
                    update(min1, min2, u, c);
                }
                if(w[v] == 0 && w[u] == 1)
                {
                    update(min1, min2, v,c);
                }
            }
        for(int i =0;i<n;i++)
        {
            if(w[i] == 0 && min2[i]!= Integer.MAX_VALUE)
            {
                ans = Math.min(ans,(long) min1[i] + min2[i]);
            }
        }
        return ans == Long.MAX_VALUE? -1 :(int ) ans;
    }
    private void update(int[] min1, int[] min2, int u, int c)
    {
        if(c<min1[u])
        {
            min2[u] = min1[u];
            min1[u] = c;
        }
        else if (c<min2[u])
        {
            min2[u] = c;
        }
    }
}
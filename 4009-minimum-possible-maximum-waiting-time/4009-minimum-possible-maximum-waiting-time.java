import java.util.*;

class Solution {
    // Memoization table using a HashMap with encoded Long key
    private Map<Long, int[]> memo;

    public int minMaxWaitingTime(int[] demand, int[] fuel) {
        memo = new HashMap<>();
        
        // Result array format: [maxCarsServed, minMaxWaitTime]
        int[] res = dfs(0, fuel[0], fuel[1], 0, 0, demand);
        
        // If no cars can be served, return -1
        return res[0] == 0 ? -1 : res[1];
    }

    /**
     * DFS Function to evaluate assignment choices.
     * 
     * @param i  Current car index
     * @param f0 Remaining fuel in dispenser 0
     * @param f1 Remaining fuel in dispenser 1
     * @param w0 Time remaining until dispenser 0 becomes free
     * @param w1 Time remaining until dispenser 1 becomes free
     * @return int[] array: {maxCarsServed, minMaxWaitTime}
     */
    private int[] dfs(int i, int f0, int f1, int w0, int w1, int[] demand) {
       
        if (i == demand.length) {
            return new int[]{0, 0};
        }

        long stateKey = encodeState(i, f0, f1, w0, w1);
        if (memo.containsKey(stateKey)) {
            return memo.get(stateKey);
        }

        int d = demand[i];
        int bestCars = 0;
        int minWait = Integer.MAX_VALUE;

        if (f0 >= d) {
            int currentWait0 = w0;
            
            int delta0 = currentWait0;
            
            int nextW0 = currentWait0 + d - delta0; 
            int nextW1 = Math.max(0, w1 - delta0);

            int[] res0 = dfs(i + 1, f0 - d, f1, nextW0, nextW1, demand);
            int carsServed0 = 1 + res0[0];
            int maxWait0 = Math.max(currentWait0, res0[1]);

            if (carsServed0 > bestCars) {
                bestCars = carsServed0;
                minWait = maxWait0;
            } else if (carsServed0 == bestCars) {
                minWait = Math.min(minWait, maxWait0);
            }
        }

        if (f1 >= d) {
            
            int currentWait1 = w1;
            
            int delta1 = currentWait1;
            
            int nextW0 = Math.max(0, w0 - delta1);
            int nextW1 = currentWait1 + d - delta1;

            int[] res1 = dfs(i + 1, f0, f1 - d, nextW0, nextW1, demand);
            int carsServed1 = 1 + res1[0];
            int maxWait1 = Math.max(currentWait1, res1[1]);

            if (carsServed1 > bestCars) {
                bestCars = carsServed1;
                minWait = maxWait1;
            } else if (carsServed1 == bestCars) {
                minWait = Math.min(minWait, maxWait1);
            }
        }

        if (bestCars == 0) {
            minWait = 0;
        }

        int[] result = new int[]{bestCars, minWait};
        memo.put(stateKey, result);
        return result;
    }

    private long encodeState(int i, int f0, int f1, int w0, int w1) {
        long key = i;
        key = (key * 51) + f0;
        key = (key * 51) + f1;
        key = (key * 101) + w0;
        key = (key * 101) + w1;
        return key;
    }
}

class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        // Max-heap to store available fuel amounts from visited gas stations
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        long currentFuel = startFuel; // Use long to prevent potential integer overflow
        int stops = 0;
        int i = 0;
        int n = stations.length;

        // Drive until we reach or pass the target position
        while (currentFuel < target) {
            // Add fuel capacity of all reachable stations to our max-heap
            while (i < n && stations[i][0] <= currentFuel) {
                maxHeap.add(stations[i][1]);
                i++;
            }

            // If no reachable station offers fuel and we haven't reached the target, we fail
            if (maxHeap.isEmpty()) {
                return -1;
            }

            // Greedily refuel with the station offering the maximum fuel
            currentFuel += maxHeap.poll();
            stops++;
        }

        return stops;
    }
}
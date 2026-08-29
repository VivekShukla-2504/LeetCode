import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        // 1. Pair each value with its original index
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i]; // value
            pairs[i][1] = i;       // original index
        }

        // Sort pairs by value
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));

        // 2. Group into connected components
        List<List<int[]>> components = new ArrayList<>();
        List<int[]> currentGroup = new ArrayList<>();
        currentGroup.add(pairs[0]);

        for (int i = 1; i < n; i++) {
            // If difference between adjacent sorted values <= limit, they are in the same group
            if (pairs[i][0] - pairs[i - 1][0] <= limit) {
                currentGroup.add(pairs[i]);
            } else {
                components.add(currentGroup);
                currentGroup = new ArrayList<>();
                currentGroup.add(pairs[i]);
            }
        }
        components.add(currentGroup);

        // 3. Reconstruct the array
        int[] result = new int[n];

        for (List<int[]> group : components) {
            // Extract and sort original indices in ascending order
            int groupSize = group.size();
            int[] indices = new int[groupSize];
            for (int i = 0; i < groupSize; i++) {
                indices[i] = group.get(i)[1];
            }
            Arrays.sort(indices);

            // Assign sorted values to sorted indices
            for (int i = 0; i < groupSize; i++) {
                int val = group.get(i)[0];
                int idx = indices[i];
                result[idx] = val;
            }
        }

        return result;
    }
}
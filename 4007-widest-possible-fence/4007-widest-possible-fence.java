import java.util.*;

class Solution {
    public int maximumWidth(int[] planks) {
        int n = planks.length;
        if (n <= 1) return n;

        Arrays.sort(planks);

        int maxAns = 1;

        Map<Long, Integer> freq = new HashMap<>();
        for (int p : planks) {
            long val = p;
            int count = freq.getOrDefault(val, 0) + 1;
            freq.put(val, count);
            if (count > maxAns) {
                maxAns = count;
            }
        }

        Map<Long, Integer> pairCounts = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long sum = (long) planks[i] + planks[j];
                pairCounts.put(sum, pairCounts.getOrDefault(sum, 0) + 1);
            }
        }

        for (Map.Entry<Long, Integer> entry : pairCounts.entrySet()) {
            long H = entry.getKey();
            int totalPairs = entry.getValue();
            int singles = freq.getOrDefault(H, 0);

            if (totalPairs + singles > maxAns) {
                int disjointPairs = getDisjointPairs(planks, H);
                maxAns = Math.max(maxAns, singles + disjointPairs);
            }
        }

        return maxAns;
    }

    private int getDisjointPairs(int[] sortedPlanks, long H) {
        int left = 0;
        int right = binarySearchRight(sortedPlanks, H - 1);
        int pairs = 0;

        while (left < right) {
            long sum = (long) sortedPlanks[left] + sortedPlanks[right];
            if (sum == H) {
                pairs++;
                left++;
                right--;
            } else if (sum < H) {
                left++;
            } else {
                right--;
            }
        }
        return pairs;
    }

    private int binarySearchRight(int[] arr, long maxVal) {
        int low = 0, high = arr.length - 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= maxVal) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
}
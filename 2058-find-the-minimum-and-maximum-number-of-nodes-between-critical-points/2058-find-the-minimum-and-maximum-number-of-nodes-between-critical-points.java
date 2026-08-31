/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        // A list with fewer than 3 nodes cannot have any critical points
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int firstCriticalIndex = -1;
        int lastCriticalIndex = -1;
        int minDistance = Integer.MAX_VALUE;

        ListNode prev = head;
        ListNode curr = head.next;
        int currentIndex = 1;

        while (curr.next != null) {
            ListNode next = curr.next;

            // Check if current node is a local maxima or minima
            boolean isLocalMaxima = curr.val > prev.val && curr.val > next.val;
            boolean isLocalMinima = curr.val < prev.val && curr.val < next.val;

            if (isLocalMaxima || isLocalMinima) {
                if (firstCriticalIndex == -1) {
                    firstCriticalIndex = currentIndex;
                } else {
                    // Update minDistance between adjacent critical points
                    minDistance = Math.min(minDistance, currentIndex - lastCriticalIndex);
                }
                lastCriticalIndex = currentIndex;
            }

            prev = curr;
            curr = curr.next;
            currentIndex++;
        }

        // Return [-1, -1] if fewer than two critical points are found
        if (firstCriticalIndex == lastCriticalIndex) {
            return new int[]{-1, -1};
        }

        int maxDistance = lastCriticalIndex - firstCriticalIndex;
        return new int[]{minDistance, maxDistance};
    }
}
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public static int sumNumbers(TreeNode node) {
    return dfs(node, 0);
}

private static int dfs(TreeNode node, int currentSum) {
    if (node == null) return 0;
    
    currentSum = currentSum * 10 + node.val;
    
    // Leaf node — return the completed number
    if (node.left == null && node.right == null) {
        return currentSum;
    }
    
    return dfs(node.left, currentSum) + dfs(node.right, currentSum);
}
}
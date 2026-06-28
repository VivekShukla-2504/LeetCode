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
    private int count = 0;
    private int res = 0;

    public int kthSmallest(TreeNode root, int k) {
        Inorder(root, k);
        return res;
    }
    private void Inorder(TreeNode node , int k )
    {
        if (node == null)
            return;
    
        Inorder(node.left,k);

        count++;
        if(count == k)
        {
            res = node.val;
            return;
        }
        
        Inorder(node.right,k);
    }
}
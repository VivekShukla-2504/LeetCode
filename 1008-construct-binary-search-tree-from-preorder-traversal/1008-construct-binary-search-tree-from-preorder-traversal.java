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
    int i =0;
    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorder(preorder,Integer.MAX_VALUE);
    }
    public TreeNode bstFromPreorder(int[] pre,int bound)
    {
        if( i==pre.length || pre[i]>bound)
         return null;
        TreeNode root = new  TreeNode (pre[i++]);
        root.left = bstFromPreorder( pre,root.val);
        root.right = bstFromPreorder(pre,bound);
        return root;

    }
}
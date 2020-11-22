package DFSAndBFS;

import lombok.AllArgsConstructor;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/11/18 & 1.0
 * 递归、广度优先遍历
 * 二叉树的最大深度-104
 * 思路：遍历根节点的左右子树的深度并选择最大的,最后+1
 */
public class MaximumDepthOfBinaryTree_Recursion {
    @AllArgsConstructor
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }

    /**
     * 递归
     * @param root
     * @return
     * 时间复杂度：O(n) n-二叉树的节点数，每个节点只被遍历到一次
     * 空间复杂度：O(n) n-树的深度
     */
    public int maxDepth(TreeNode root) {
        //特判
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
//        注：这里可以直接简化成一行代码
//        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        MaximumDepthOfBinaryTree_Recursion maximumDepthOfBinaryTree = new MaximumDepthOfBinaryTree_Recursion();
        System.out.println(maximumDepthOfBinaryTree.maxDepth(treeNode));
    }
}

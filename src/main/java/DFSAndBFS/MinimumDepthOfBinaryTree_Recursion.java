package DFSAndBFS;

import lombok.AllArgsConstructor;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/11/18 & 1.0
 * 递归、广度优先搜索
 * 二叉树的最小深度-111
 */
public class MinimumDepthOfBinaryTree_Recursion {
    @AllArgsConstructor
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 递归实现
     * @param root
     * @return
     * 思路：可以求出左右子树的深度，选择最小的，再+1。
     *       但是注意：和取最大值不同，如果这里每次都取最小，那如果遇到某一子树为空的情况，取最小后，每次都只会是0
     *       将可能出现的情况进行分类：左右子树都为空、左右子树有一个为空、左右子树都不为空。
     * 时间复杂度：O(n) n--树中所有节点的个数，每个节点只被遍历一遍
     * 空间复杂度：O(n) n--树的深度
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //如果左右两子树都为空，说明是叶子节点，就返回1
        //当前分支遍历完毕，深度可加一，代表的是叶子节点自身的深度
        if (root.left == null && root.right == null) {
            return 1;
        }
        //如果左孩子和右孩子其中一个为空，那么需要返回比较大的那个孩子的深度
        //连边分别遍历
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        //当要执行下面的if语句的时候，整棵树的所有节点都已被遍历过了。
        //如果当前节点有左、右子树中有一个null的话,说明其中一个必定为0
        if (root.left == null || root.right == null) {
            return leftDepth + rightDepth + 1;
        }
        //最后，如果左右孩子都不为空，返回最小深度再+1（加一代表根节点自身具有的深度）即可。
        return Math.min(leftDepth, rightDepth) + 1;
    }

    /**
     * 递归-优化代码
     * 国际网站上的最优解
     * @param root
     * @return
     * 对于上面的方法中的3个if()在这里凝结成一行语句:
     * 1）左子节点和右子节点都为空的情况，
     *    这里算在(leftDepth == 0 || rightDepth == 0) ? leftDepth + rightDepth + 1 里面，
     *    结果都是返回 1，因为叶子节点的左子树深度和右子树深度均为 0。
     * 2）左子节点或右子节点为空的情况，
     *    也算在(leftDepth == 0 || rightDepth == 0) ? leftDepth + rightDepth + 1 里。
     * 3）左右子节点均不为空，那就取两者的最小值再 +1 即可。
     */
    public int minDepthBetter(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = minDepthBetter(root.left);
        int rightDepth = minDepthBetter(root.right);
        //如果left和right都为0，返回1；
        //如果left和right只有一个为0，说明他只有一个子节点，只需要返回它另一个子节点的最小深度+1即可；
        //如果left和right都不为0，说明他有两个子节点，只需要返回最小深度的+1即可。
        return (leftDepth == 0 || rightDepth == 0) ? leftDepth + rightDepth + 1 : Math.min(leftDepth, rightDepth) + 1 ;
    }
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(2);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(5, new TreeNode(3), new TreeNode(4));
        MinimumDepthOfBinaryTree_Recursion minimumDepthOfBinaryTree = new MinimumDepthOfBinaryTree_Recursion();
        System.out.println(minimumDepthOfBinaryTree.minDepthBetter(treeNode));
    }
}

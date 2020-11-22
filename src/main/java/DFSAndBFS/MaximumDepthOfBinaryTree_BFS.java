package DFSAndBFS;

import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/11/18 & 1.0
 * 递归、广度优先遍历
 * 二叉树的最大深度-104
 */
public class MaximumDepthOfBinaryTree_BFS {
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
     * 广度优先遍历
     * BFS
     * 队列中只保存当前层的所有节点
     * @param root
     * @return
     * 复杂度分析：
     * 时间复杂度：O(n)，其中 n 为二叉树的节点个数。每个节点只会被访问一次。
     * 空间复杂度：此方法空间的消耗取决于队列存储的元素数量，
     *             其在最坏情况下会达到 O(n)。
     */
    public int maxDepth(TreeNode root) {
        int depth = 0;
        //特判
        if (root == null) {
            return depth;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode pollNode = queue.poll();
                if (pollNode.left != null) {
                    queue.add(pollNode.left);
                }
                if (pollNode.right != null) {
                    queue.add(pollNode.right);
                }
            }
            depth++;
        }
        return depth;
    }
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        MaximumDepthOfBinaryTree_BFS maximumDepthOfBinaryTree = new MaximumDepthOfBinaryTree_BFS();
        System.out.println(maximumDepthOfBinaryTree.maxDepth(treeNode));
    }
}

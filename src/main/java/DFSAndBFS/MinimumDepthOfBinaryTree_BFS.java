package DFSAndBFS;

import lombok.AllArgsConstructor;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/11/18 & 1.0
 * 递归、广度优先搜索
 * 二叉树的最小深度-111
 */
public class MinimumDepthOfBinaryTree_BFS {
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
     * 广度优先搜索-BFS
     * 思路及解法：
     * 可以想到使用广度优先搜索的方法，遍历整棵树。
     * 当我们找到一个叶子节点时，直接返回这个叶子节点的深度。
     * 广度优先搜索的性质保证了最先搜索到的叶子节点的深度一定最小。
     * @param root
     * @return
     * 时间复杂度：O(n) n-树中节点的数量
     * 空间复杂度：O(n) n-队列中元素的个数
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        //层数计数器
        int level = 0;
        while (!deque.isEmpty()) {
            level++;
            int depth = deque.size();
            while (depth-- > 0) {
                TreeNode pollNode = deque.poll();
                //如果当前节点的左右子树都为空，直接返回它所在的层数即可
                if (pollNode.left == null && pollNode.right == null) {
                    return level;
                }
                if (pollNode.left !=null) {
                    deque.add(pollNode.left);
                }
                if (pollNode.right != null) {
                    deque.add(pollNode.right);
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(2);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(5, new TreeNode(3), new TreeNode(4));
        MinimumDepthOfBinaryTree_BFS minimumDepthOfBinaryTree = new MinimumDepthOfBinaryTree_BFS();
        System.out.println(minimumDepthOfBinaryTree.minDepth(treeNode));
    }
}

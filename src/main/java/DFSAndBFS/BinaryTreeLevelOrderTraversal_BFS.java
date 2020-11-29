package DFSAndBFS;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/11/23 & 1.
 * 二叉树的层序遍历-102
 * 思路：
 *  解法一：BFS-广度优先遍历（借助队列）
 *   二叉树的层序遍历和N叉树的层序遍历类似，都可用队列解决
 *   同样要注意：如果root一开始就是空的话，需要返回[]，而不是null。
 *   时间复杂度：O(n), n-节点个数，内个点进队出队各一次
 *   空间复杂度：O(n), n-树中节点的个数
 */
public class BinaryTreeLevelOrderTraversal_BFS {
    @AllArgsConstructor
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode (int val) {
            this.val = val;
        }
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return new ArrayList<>(res);
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            LinkedList<Integer> list = new LinkedList<>();
            while (size-- > 0) {
                TreeNode treeNodeRemove = deque.removeFirst();
                list.add(treeNodeRemove.val);
                if (treeNodeRemove.left != null) {
                    deque.add(treeNodeRemove.left);
                }
                if (treeNodeRemove.right != null) {
                    deque.add(treeNodeRemove.right);
                }
            }
            res.add(list);
        }
        return res;
    }
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        BinaryTreeLevelOrderTraversal_BFS binaryTreeLevelOrderTraversal = new BinaryTreeLevelOrderTraversal_BFS();
        List<List<Integer>> lists = binaryTreeLevelOrderTraversal.levelOrder(treeNode);
        System.out.println(lists);
    }
}

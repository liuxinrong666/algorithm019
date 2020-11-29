package DFSAndBFS;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/11/23 & 1.0
 * 二叉树的层序遍历-102
 * 递归实现
 */
public class BinaryTreeLevelOrderTraversal_Recurision {
    @AllArgsConstructor
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode (int val) {
            this.val = val;
        }
    }

    /**
     * 递归实现
     * 将同层节点存入中
     * @param root
     * @return
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    private List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        levelOrderHelper(root, 0);
        return lists;
    }

    private void levelOrderHelper(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (lists.size() <= level) {
            lists.add(new ArrayList<>());
        }
        lists.get(level).add(root.val);
        levelOrderHelper(root.left, level + 1);
        levelOrderHelper(root.right, level + 1);
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        BinaryTreeLevelOrderTraversal_Recurision binaryTreeLevelOrderTraversal = new BinaryTreeLevelOrderTraversal_Recurision();
        List<List<Integer>> lists = binaryTreeLevelOrderTraversal.levelOrder(treeNode);
        System.out.println(lists);
    }
}

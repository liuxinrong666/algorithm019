package diGui;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/11/17 & 1.0
 * 递归
 * 翻转二叉树-266
 * 从下往上换
 */
public class InvertBinaryTree {
    @AllArgsConstructor
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 递归法(国际站上的最优解)：
     * @param root
     * @return
     * 复杂度分析：
     * 时间复杂度：O(N)，其中 N 为二叉树节点的数目。
     *      会遍历二叉树中的每一个节点，
     *      对每个节点而言，在常数时间内交换其两棵子树。
     * 空间复杂度：O(N)。使用的空间由递归栈的深度决定，
     *      它等于当前节点在二叉树中的高度。
     *      在平均情况下，二叉树的高度与节点个数为对数关系，
     *      即 O(logN)。而在最坏情况下，树形成链状，空间复杂度为 O(N)。
     */
    public TreeNode invertTree(TreeNode root) {
        //特判
        if (root == null) {
            return null;
        }
        TreeNode leftNode = invertTree(root.left);
        TreeNode rightNode = invertTree(root.right);
        root.left = rightNode;
        root.right = leftNode;
        return root;
    }

    /**
     * 先序遍历结果树--用来打印结果
     * @param root
     * @return
     */
    public List<Integer> preorderTravel(TreeNode root) {
        ArrayList<Integer> integers = new ArrayList<>();
        preorder(root,integers);
        return integers;
    }
    private void preorder(TreeNode root, ArrayList<Integer> integers) {
        if (root == null) {
            return;
        }
        integers.add(root.val);
        preorder(root.left, integers);
        preorder(root.right, integers);
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(4);
        treeNode.left = new TreeNode(2,new TreeNode(1),new TreeNode(3));
        treeNode.right = new TreeNode(7,new TreeNode(6),new TreeNode(9));
        InvertBinaryTree invertBinaryTree = new InvertBinaryTree();
        TreeNode treeResult = invertBinaryTree.invertTree(treeNode);

        //将结果树打印出来--采用二叉树的先序遍历
        //注意：二叉树翻转后，树本身的结构就已经改变了，下面两个输出的结果是相同的。
        System.out.println(invertBinaryTree.preorderTravel(treeNode));
        System.out.println(invertBinaryTree.preorderTravel(treeResult));
    }
}

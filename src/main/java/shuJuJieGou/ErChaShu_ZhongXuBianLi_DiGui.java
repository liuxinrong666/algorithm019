package shuJuJieGou;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/10/22 & 1.0
 *
 * 二叉树的中序遍历-94
 * 解法（一）递归
 */

public class ErChaShu_ZhongXuBianLi_DiGui {
    /**
     * 自定义数据类
     */
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    static public List<Integer> inorderTraversal(TreeNode root) {
        //定义一个动态数组--用来存放遍历过的节点顺序
        List<Integer> resArray = new ArrayList<>();
        //自定义方法--inorder(当前的树，遍历完的节点)
        inorder(root, resArray);
        return resArray;
    }

    /**
     * 根据当前根节点进行中序遍历
     * @param root  当前的树
     * @param resArray  遍历完的节点
     */
    static public void inorder(TreeNode root, List<Integer> resArray) {
        //不停地遍历下去，直到不再存在根节点（所有的节点都遍历完了）
        if (root == null) {
            return;
        }
        //从左子树开始遍历
        inorder(root.left,resArray);
        //接着遍历根节点
        resArray.add(root.val);
        //最后遍历右子树
        inorder(root.right,resArray);
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode();
        treeNode.val = 1;
        treeNode.left=null;
        treeNode.right=new TreeNode(2,new TreeNode(3),null);
        List<Integer> inorder = inorderTraversal(treeNode);
        System.out.println(inorder);
    }
    /**
     * 复杂度分析:
     *
     * 时间复杂度：O(n)，其中 nn 为二叉树节点的个数。二叉树的遍历中每个节点会被访问一次且只会被访问一次。
     * 空间复杂度：O(n)。空间复杂度取决于递归的栈深度，而栈深度在二叉树为一条链的情况下会达到 O(n)的级别。
     */
}

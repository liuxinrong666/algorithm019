package shuJuJieGou;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/10/27 & 1.0
 * 二叉树的先序遍历-递归方法
 */
public class ErChaShu_XianXuBianLi_DiGui {
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){ this.val=val;}
    }
    public  static List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        preorder(root,arrayList);
        return arrayList;
    }
    private static void preorder(TreeNode root, ArrayList<Integer> arrayList) {
        if(root==null){
            return;
        }
        arrayList.add(root.val);
        preorder(root.left, arrayList);
        preorder(root.right, arrayList);
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode();
        treeNode.val=1;
        treeNode.left=null;
        treeNode.right = new TreeNode(2, new TreeNode(3), null);
        List<Integer> integers = preorderTraversal(treeNode);
        System.out.println(integers);
    }
}

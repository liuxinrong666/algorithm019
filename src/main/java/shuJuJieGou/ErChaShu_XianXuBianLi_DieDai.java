package shuJuJieGou;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/10/27 & 1.0
 * 二叉树先序遍历-迭代实现
 */
public class ErChaShu_XianXuBianLi_DieDai {
    public static List<Integer> preorderTraversal(ErChaShu_XianXuBianLi_DiGui.TreeNode root) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        //创建栈
        Deque<ErChaShu_XianXuBianLi_DiGui.TreeNode> deque = new LinkedList<>();
        //当还有结点未遍历到或者栈内还存有数据时
        while (root!=null||!deque.isEmpty()){
            //当前根结点还有左子树/右子树时
            while (root!=null){
                //因为是直接从根结点开始遍历，所以直接将根结点加入到访问元组中
                arrayList.add(root.val);
                //入栈
                deque.push(root);
                //不断地往下找当前结点的左子树，直到叶子结点
                root = root.left;
            }
            //出栈-后进先出
            ErChaShu_XianXuBianLi_DiGui.TreeNode treeNode = deque.pop();
            //然后对于出栈后的树再遍历其右子树
            root=treeNode.right;
        }
        return arrayList;
    }
    public static void main(String[] args) {
        ErChaShu_XianXuBianLi_DiGui.TreeNode treeNode = new ErChaShu_XianXuBianLi_DiGui.TreeNode();
        treeNode.val=1;
        treeNode.left=null;
        treeNode.right = new ErChaShu_XianXuBianLi_DiGui.TreeNode(2, new ErChaShu_XianXuBianLi_DiGui.TreeNode(3), null);
        List<Integer> preorderList = preorderTraversal(treeNode);
        System.out.println(preorderList);
    }
}

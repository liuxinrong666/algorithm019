package shuJuJieGou;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/10/22 & 1.0
 * 二叉树的中序遍历-94
 * 解法（二）迭代
 *
 * 思路与算法:
 * 递归方法也可以用迭代的方式实现，两种方式是等价的，
 * 区别在于递归的时候隐式地维护了一个栈，而在迭代的时候需要显式地将这个栈模拟出来，
 * 其他都相同，具体实现可以看下面的代码：
 */
public class ErChaShu_ZhongXuBianLi_DieDai {
    static public List<Integer> inorderTraversal(ErChaShu_ZhongXuBianLi_DiGui.TreeNode root){
        //创建动态数组--来存储遍历完的结点
        ArrayList<Integer> resArray = new ArrayList<>();
        //创建栈
        /**
         * LinkedList插入和删除操作更加高效，随机访问速度慢；
         *           可以作为栈、队列、双端队列数据结构使用；
         * Deque接口-这里Deque作为栈使用（先进后出）
         */
        Deque<ErChaShu_ZhongXuBianLi_DiGui.TreeNode> deque = new LinkedList<>();
        //还有结点未被排序或者栈内还存有节点
        while (root != null || !deque.isEmpty() ) {
            //当此时结点还存有左子树或右子树时
            while (root!=null){
                //当前树中的节点入栈
                deque.push(root);
                //不断找当前根结点下的左子树，直到叶子结点
                root=root.left;
            }
            //出栈-后进先出
            root = deque.pop();
            //将出栈后的根节点，加入遍历完的元组中
            resArray.add(root.val);
            //最后遍历右子树
            root=root.right;
        }
        //返回遍历顺序
        return resArray;
    }

    public static void main(String[] args) {
        ErChaShu_ZhongXuBianLi_DiGui.TreeNode treeNode = new ErChaShu_ZhongXuBianLi_DiGui.TreeNode();
        treeNode.val=1;
        treeNode.left=null;
        treeNode.right=new ErChaShu_ZhongXuBianLi_DiGui.TreeNode(2,new ErChaShu_ZhongXuBianLi_DiGui.TreeNode(3),null);
        inorderTraversal(treeNode);
    }
    /**
     * 复杂度分析:
     *
     * 时间复杂度：O(n)，其中 nn 为二叉树节点的个数。二叉树的遍历中每个节点会被访问一次且只会被访问一次。
     * 空间复杂度：O(n)。空间复杂度取决于栈深度，而栈深度在二叉树为一条链的情况下会达到 O(n)的级别。
     */
}

package DFSAndBFS;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/10/28 & 1.0
 * 验证二叉搜索树-98
 *
 * 结合中序遍历的思想
 * 采用迭代的思想
 */
public class Shu_YanZhengErChaSouSuoShu_DieDai {
    /** 代表前一节点 */
    static long pre = -Long.MAX_VALUE;
    public static boolean isValidBST(Shu_YanZhengErChaSouSuoShu_DiGui.TreeNode root) {
        if(root == null){
            return true;
        }
        //创建栈
        Deque<Shu_YanZhengErChaSouSuoShu_DiGui.TreeNode> deque = new LinkedList<>();
        while (root!=null || !deque.isEmpty()){
            //当节点还存有左子树或右子树时
            while (root!=null){
                deque.push(root);
                //一直找当前节点的左子树
                root = root.left;
            }
            //出栈-后进先出-深度高到低
            Shu_YanZhengErChaSouSuoShu_DiGui.TreeNode treeNode = deque.pop();
            //如果前一个节点大于后一个节点
            if(pre >= treeNode.val){
                return false;
            }
            //pre需要被赋予新值
            pre = treeNode.val;
            //接着遍历右子树-同理于左子树
            root = treeNode.right;
        }
        return true;
    }
    public static void main(String[] args) {
        Shu_YanZhengErChaSouSuoShu_DiGui.TreeNode treeNode = new Shu_YanZhengErChaSouSuoShu_DiGui.TreeNode();
        treeNode.val=5;
        treeNode.left=new Shu_YanZhengErChaSouSuoShu_DiGui.TreeNode(1);
        treeNode.right=new Shu_YanZhengErChaSouSuoShu_DiGui.TreeNode(7,new Shu_YanZhengErChaSouSuoShu_DiGui.TreeNode(3),new Shu_YanZhengErChaSouSuoShu_DiGui.TreeNode(6));
        boolean validBST = isValidBST(treeNode);
        System.out.println(validBST);
    }
}

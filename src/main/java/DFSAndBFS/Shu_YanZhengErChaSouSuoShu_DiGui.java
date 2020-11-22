package DFSAndBFS;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/10/28 & 1.0
 * 验证二叉搜索树-98
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 递归方法完成：
 * 每个树的左子节点要小于根节点，右子节点要大于根节点
 * 从上往下，也就是说由最终的根节点到叶子节点这个顺序进行递归比较
 * 根据当前节点的变化，不断改变合理的大小值范围
 */
public class Shu_YanZhengErChaSouSuoShu_DiGui {
    @NoArgsConstructor
    @AllArgsConstructor
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){ val=x;}
    }

    /**
     * 递归实现
     * 【国际站上票数最高解】
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        //不断检测当前节点的值是否在合理范围内
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode();
        treeNode.val=5;
        treeNode.left=new TreeNode(1);
        treeNode.right=new TreeNode(7,new TreeNode(3),new TreeNode(6));
        Shu_YanZhengErChaSouSuoShu_DiGui yanZhengErChaSouSuoShuDiGui = new Shu_YanZhengErChaSouSuoShu_DiGui();
        System.out.println(yanZhengErChaSouSuoShuDiGui.isValidBST(treeNode));
    }
}

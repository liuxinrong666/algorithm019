package diGui;

import lombok.AllArgsConstructor;

import java.util.HashMap;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/11/19 & 1.0
 * 递归
 * 从前序和中序遍历构造二叉树-105
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    @AllArgsConstructor
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 思路：
     *  先序遍历--[根节点，[左子树]，[右子树]]
     *  中序遍历--[[左子树]，根节点，[右子树]]
     *  根据先序遍历的第一个节点能够找到中序遍历中的根节点的位置（因为忽略重复元素的情况），
     *  也就能够知道其左子树、右子树中元素的个数
     *  接着对找到的左右子树进行递归处理即可。
     *  注：在中序遍历结果中查找根节点的步骤可接用HashMap，就省去for循环的一次次遍历了
     * @param preorder
     * @param inorder
     * @return
     * 时间复杂度：O(n) n--树中节点的个数
     * 空间复杂度：O(n) 返回的答案需要O(n)的空间，创建的HashMap需要O(n)的空间，
     *                  以及额外的O(h)--递归时栈的空间
     *                  n > h，所以为O(n)
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //创建HashMap存储中序遍历，方便找root节点
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            hashMap.put(inorder[i], i);
        }
        //注意：这里不要写preorder.length-1,不然会超界
        return builderTreeHelper(preorder, 0, preorder.length, 0, inorder.length, hashMap);
    }
    private TreeNode builderTreeHelper(int[] preorder, int p_start, int p_end, int i_start, int i_end, HashMap hashMap) {
        //叶子节点
        if (p_start == p_end) {
            return null;
        }
        //根据先序遍历结果得知根节点
        int root_val = preorder[p_start];
        //创建树并将根节点存入
        TreeNode treeNode = new TreeNode(root_val);
        //在中序遍历中找到根节点的位置
        int i_root_index = (int) hashMap.get(root_val);
        //左子树中节点的个数,并存入树中
        int leftNum = i_root_index - i_start;
        //通过递归不断获取左右子树
        treeNode.left = builderTreeHelper(preorder, p_start + 1, leftNum + p_start + 1, i_start, i_root_index, hashMap);
        treeNode.right = builderTreeHelper(preorder, p_start + leftNum + 1, p_end, i_root_index + 1, i_end, hashMap);
        return treeNode;
    }

    public static void main(String[] args) {
        ConstructBinaryTreeFromPreorderAndInorderTraversal constructBinaryTree = new ConstructBinaryTreeFromPreorderAndInorderTraversal();
        constructBinaryTree.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
    }
}

package diGui;

import lombok.AllArgsConstructor;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/11/19 & 1.0
 * 递归
 * 二叉树的最近公共祖先-236
 * 思路：
 *   最近公共祖先（深度最大）：对于给定的两个数 p,q
 *   1.如果p，q是root的直系左右子节点，那么root就是两者的最近公共祖先
 *   2.p, q可能是root子树的子节点，且那个子树不是p,q的最近公共祖先，那么root即为所求
 *   3.root.val = p.val 且q是root的子节点 或
 *     root.val = q.val 且p是root的子节点。
 *     说明：拿本题的测试树来说，
 *           如果 p=2,q=4,那么在检测到 2 这个节点的时候，因为5的左子树中没有 4 。
 *           就直接返回 5 的右子树了，5这个节点的递归也就结束了。
 *           除此之外，接着继续遍历 3 的右子树，
 *           发现里面没有 4 ，因此直接返回之前 5 返回的右子树，对应的根节点就是 2。
 *    也就是说，从根节点开始，不断地分左右子树遍历，
 *    找到和p / q值相等的根节点，然后返回他所在的分支，
 *    否则就返回 Null，
 *    之后也不断地对返回的结果进行处理，仅保留不为Null的分支。
 */
public class LowestCommonAncestorOfAncestorOfBinaryTree {
    @AllArgsConstructor
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //每轮递归停止条件：过叶子节点 / 找到和p/q值相等的根节点
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode treeNodeLeft = lowestCommonAncestor(root.left, p, q);
        TreeNode treeNodeRight = lowestCommonAncestor(root.right, p, q);
        //如果左右子树都不包含p或q，那么就返回null。
        if (treeNodeLeft == null && treeNodeRight == null) {
            return null;
        }
        //如果仅是左子树或右子树中不包含p或q的话，那就返回另一子树。
        if (treeNodeLeft == null) {
            return treeNodeRight;
        }
        if (treeNodeRight == null) {
            return treeNodeLeft;
        }
        //如果两个子树中都包含p或q，那么该root节点即为所求。
        return root;
//        对于后面三个if判断，可以整合成一条语句：
//        return treeNodeLeft == null ? treeNodeRight : treeNodeRight == null ? treeNodeLeft : root;
    }
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(2, new TreeNode(7), new TreeNode(4));
        treeNode.left = new TreeNode(5, new TreeNode(6), treeNode2);
        treeNode.right = new TreeNode(1, new TreeNode(0), new TreeNode(8));
        LowestCommonAncestorOfAncestorOfBinaryTree lowestCommonAncestorOfAncestor = new LowestCommonAncestorOfAncestorOfBinaryTree();
        TreeNode treeNodeResult = lowestCommonAncestorOfAncestor.lowestCommonAncestor(treeNode, treeNode2, new TreeNode(4));
        System.out.println("最近公共祖先为：" + treeNodeResult.val);
    }
}

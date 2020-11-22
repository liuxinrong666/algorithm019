package DFSAndBFS;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/11/18 & 1.0
 * 递归、DFS深度搜索 (国际站上的最优解)
 * 二叉树的序列化和反序列化-297
 * 思路：
 *  先将输入的树转换成String类型（这里我通过二叉树的先序遍历实现），
 *  然后再将转换好的String再转换成TreeNode的数据形式。
 *  null会用特殊符号进行表示。
 *  时间复杂度：O(n) n-树中节点的个数
 *  空间复杂度: O(n) n-队列中元素的数量
 */
public class
SerializeAndDeserializeBinaryTree_Recursion {
    @AllArgsConstructor
    @NoArgsConstructor
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 二叉树的序列化
     * 先序遍历(递归实现)：根-》左-》右
     * Null用特殊字符"N"来表示，
     * 节点之间用","来分割。
     * @param root
     * @return
     */
    private static final String SPLITER = ",";
    private static final String NN = "N";
    public String serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        preOrder(root, stringBuilder);
        return stringBuilder.toString();
    }
    private void preOrder(TreeNode root, StringBuilder stringBuilder) {
        if (root == null) {
            //注：StringBuilder()的话会自动将int转换成String，不需要String.valueOf()
            stringBuilder.append(NN + SPLITER);
        } else {
            stringBuilder.append(root.val).append(SPLITER);
            preOrder(root.left, stringBuilder);
            preOrder(root.right, stringBuilder);
        }
    }

    /**
     * 二叉树的反序列化
     * @param data
     * @return
     */
    public TreeNode deserialize(String data) {
        Deque<String> deque = new LinkedList<>();
        //按照传入的字符串的顺序，将其添加到队列中
        deque.addAll(Arrays.asList(data.split(SPLITER)));
        return buildTree(deque);
    }
    private TreeNode buildTree(Deque<String> deque) {
        //出队列-队头开始移出
        String removeNode = deque.remove();
        if (removeNode.equals(NN)) {
            return null;
        }
        TreeNode treeNode = new TreeNode(Integer.valueOf(removeNode));
        //因为队列中元素顺序是按先序遍历，
        // 所以只要再按照根-->左子节点-->右子节点的顺序再将元素添加进去即可。
        treeNode.left = buildTree(deque);
        treeNode.right = buildTree(deque);
        return treeNode;
    }
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3, new TreeNode(4), new TreeNode(5));
        SerializeAndDeserializeBinaryTree_Recursion serializeAndDeserializeBinaryTree = new SerializeAndDeserializeBinaryTree_Recursion();
        String serialize = serializeAndDeserializeBinaryTree.serialize(treeNode);
        serializeAndDeserializeBinaryTree.deserialize(serialize);
    }
}

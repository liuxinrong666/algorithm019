package DFSAndBFS;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/11/26 & 1.0
 * 在每个树行中找最大值-515
 * 思路：
 *  层序遍历、一边遍历一边比大小
 */
public class FindLargestValueInEachTreeRow {
    @AllArgsConstructor
    @NoArgsConstructor
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 法一：BFS
     * @param root
     * @return
     * 时间复杂度：O(n) n-树的节点总数
     * 空间复杂度：O(logn) 就是树的深度，也就是层数
     */
    public List<Integer> largestValues2(TreeNode root) {
        ArrayList<Integer> maxs = new ArrayList<>();
        //注：这里的root == null 并不是指传入的root对象的val为空，
        //    而是这个root对象本身就是null的
        if (root == null) {
            return maxs;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        //注意：一定要在最开始对root进行判空，否则后面的pollNode.val会报空指针异常
        //      因为.isEmpty()只能判断出参数中的元素是否为空，而deque是加上了root的，那么虽然root值为null,但是此时的deque已经不是null了
        //      所以此时的deque对于isEmpty()来说，并不为Null
        while (!deque.isEmpty()) {
            int size = deque.size();
            int max = Integer.MIN_VALUE;
            while (size-- > 0) {
                TreeNode pollNode = deque.poll();
                max = Math.max(max, pollNode.val);
                if (pollNode.left != null) {
                    deque.add(pollNode.left);
                }
                if (pollNode.right != null) {
                    deque.add(pollNode.right);
                }
            }
            maxs.add(max);
        }
        return maxs;
    }

    /**
     * 法二：DFS（比BFS执行的用时少）
     * 同层节点对应相应的层数
     * @param root
     * @return
     * 时间复杂度：O(n) n-节点的个数
     * 空间复杂度：O(logn),最糟糕情况为O(n) ，就是链表的形式
     */
    public List<Integer> largestValues(TreeNode root) {
        ArrayList<Integer> maxs = new ArrayList<>();
        largestValuesHelper(root, 0, maxs);
        return maxs;
    }

    /**
     * 自定义递归函数
     * @param root
     * @param level
     * @param maxs
     */
    private void largestValuesHelper(TreeNode root, int level, ArrayList<Integer> maxs) {
        if (root == null) {
            return;
        }
        if (maxs.size() == level) {
            maxs.add(level,root.val);
        }
        int max = Math.max(maxs.get(level), root.val);
        maxs.set(level, max);
        largestValuesHelper(root.left, level + 1, maxs);
        largestValuesHelper(root.right, level + 1, maxs);
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(3, new TreeNode(5), new TreeNode(3));
        treeNode.right = new TreeNode(2, null, new TreeNode(9));
        FindLargestValueInEachTreeRow findLargestValueInEachTreeRow = new FindLargestValueInEachTreeRow();
        List<Integer> list = findLargestValueInEachTreeRow.largestValues(treeNode);
        System.out.println(list);
        TreeNode treeNodeNull = new TreeNode();
        FindLargestValueInEachTreeRow findLargestValueInEachTreeRowNull = new FindLargestValueInEachTreeRow();
        List<Integer> listNull = findLargestValueInEachTreeRowNull.largestValues(treeNodeNull);
        System.out.println(listNull);
    }
}

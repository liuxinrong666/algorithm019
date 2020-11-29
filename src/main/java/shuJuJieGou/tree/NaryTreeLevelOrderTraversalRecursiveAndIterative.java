package shuJuJieGou.tree;

import lombok.Getter;

import java.util.*;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/11/14 & 1.0
 * N叉树的层序遍历-429
 * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 * 递归和队列两种解法
 * 思考：一层一层的遍历,先遍历根节点再遍历其子节点
 *       注意：队列适合广度优先遍历-BFS；栈适合深度优先遍历-DFS。
 *             递归运行时使用堆栈，跟适合深度优先搜索。
 *             这里使用递归是要确定节点所在的位置，
 *             然后哪一层的节点对应加到哪一层的列表中即可。
 */
public class NaryTreeLevelOrderTraversalRecursiveAndIterative {
    @Getter
    static class Node{
        public int val;
        public List<Node> children;
        public Node(int val){
            this.val = val;
        }
        public void addChildren(Node node) {
            if (children == null) {
                children = new ArrayList<>();
            }
            children.add(node);
        }
    }
    /**
     * 法一：队列-Queue 实现
     * 保证每一层的元素存入队列之后，不出队列，再存入下一层
     * @param root
     * @return
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public List<List<Integer>> levelOrder2(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        //特判
        if (root == null) {
            return result;
        }
        //定义队列
        Queue<Node> queue = new LinkedList<>();
        //将root根节点加入到当前的队列中
        queue.add(root);
        while (!queue.isEmpty()) {
            //定于元组，存储每层的所有节点
            List<Integer> level = new ArrayList<>();
            //当前层的节点数
            int size = queue.size();
            //遍历当前层的所有节点
            for (int i = 0; i < size; i++) {
                //出队
                Node node = queue.poll();
                //将出队的当前层的节点都加入到存储当前层节点的元组中
                level.add(node.val);
                if (node.children != null) {
                    //.addAll()：将指定集合中的所有元素添加到此集合中。
                    //将当前节点的节点一次性地加入到队列中
                    queue.addAll(node.children);
                }
            }
            result.add(level);
        }
        return result;
    }

    /**
     * 法二：递归实现
     * @param root
     * @return
     * 复杂度分析：
     * 时间复杂度：O(n)。n 指的是节点的数量
     * 空间复杂度：正常情况 O(logn)，最坏情况 O(n)。运行时在堆栈上的空间。
     */
    private List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> levelOrder(Node root) {
        level(root, 0);
        return result;
    }

    /**
     * 自定义递归函数
     * @param root 当前所处的根节点
     * @param i 当前所处的层数
     */
    private void level(Node root, int i) {
        if (root == null) {
            return;
        }
        if (result.size() <= i) {
            //新增加一个空数组
            result.add(new ArrayList<>());
        }
        //将当前节点的根节点值加入到对应层数的数组中
        result.get(i).add(root.val);
        if (root.children != null) {
            //接着递归遍历其子节点
            for (Node child : root.children) {
                level(child, i + 1);
            }
        }
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        node.addChildren(new Node(3));
        node.addChildren(new Node(2));
        node.addChildren(new Node(4));
        List<Node> children = node.getChildren();
        children.get(0).addChildren(new Node(5));
        children.get(0).addChildren(new Node(6));
        NaryTreeLevelOrderTraversalRecursiveAndIterative levelOrderTraversal = new NaryTreeLevelOrderTraversalRecursiveAndIterative();
        System.out.println(levelOrderTraversal.levelOrder(node));
    }
}

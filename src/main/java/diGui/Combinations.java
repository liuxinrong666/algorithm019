package diGui;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/11/19 & 1.0
 * 递归
 * 组合-77
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 */
public class Combinations {
    /**
     * 递归、回溯
     * @param n
     * @param k
     * @return
     * 自己分析的时间空间复杂度：
     *     时间复杂度：O(k^n)
     *     空间复杂度：O(nk) ：
     *         递归所需 O(k) ,存储返回结果所需 O(nk)
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        //特判
        if (k <= 0 || n < k) {
            return res;
        }
        //创建路径（选第一个数）
        Deque<Integer> path = new ArrayDeque<>();
        // 从 1 开始是题目的设定
        dfs(n, k, 1, path, res);
        return res;
    }

    private void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {
        // 递归终止条件是：path 的长度等于 k
        if (path.size() == k) {
            //如果单个path满了的话，就将其存入结果集合中
            res.add(new ArrayList<>(path));
            return;
        }
        // 如果path没满
        // 遍历可能的搜索起点
        //注：搜索起点的上界 + 接下来要选择的元素个数 - 1 = n
        //    搜索起点的上界 = n - (k - path.size()) + 1
        //    所以，剪枝过程就是：把 i <= n 改成 i <= n - (k - path.size()) + 1
        for (int i = begin; i <= n - (k - path.size()) + 1; i++) {
            // 向路径变量里添加一个数
            path.addLast(i);
            // 下一轮搜索，设置的搜索起点要加 1，因为组合数里不允许出现重复的元素
            dfs(n, k, i + 1, path, res);
            // 重点理解这里：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
            path.removeLast();
        }
    }
    public static void main(String[] args) {
        Combinations combinations = new Combinations();
        System.out.println(combinations.combine(4, 2));
    }
}

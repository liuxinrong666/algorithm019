package fenZhiAndHuiSu;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/11/22 & 1.0
 * 子集-78
 * 思路：将其想象成一棵n叉树，通过DFS遍历这颗n叉树，它所走过的所有路径都是子集的一部分。
 * 注意，可以有空集合的情况,所以在递归函数中，一开始可以直接add空集合。
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        subsetsHelper(nums, new ArrayList<>(), 0, res);
        return res;
    }

    private void subsetsHelper(int[] nums, List<Integer> deque, int index, List<List<Integer>> res) {
        //走过的所有路径都是子集的一部分，都要加入到结果集合中
        res.add(new ArrayList<>(deque));
        for (int i = index; i < nums.length; i++) {
            deque.add(nums[i]);
            subsetsHelper(nums, deque, i + 1, res);
            deque.remove(deque.size() - 1);
        }
    }

    public static void main(String[] args) {
        Subsets subset = new Subsets();
        List<List<Integer>> subsets1 = subset.subsets(new int[]{1, 2, 3});
        System.out.println(subsets1);
    }
}

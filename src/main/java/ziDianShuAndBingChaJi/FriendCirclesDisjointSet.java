package ziDianShuAndBingChaJi;

import java.util.Arrays;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/12/22 & 1.0
 * 并查集
 * 朋友圈-547
 * 每棵树代表一个集合，树中的每个节点代表一个元素。
 * 思路：
 *   使用一个大小为 N 的 parent 数组进行图的遍历，
 *   每个节点都遍历所有相邻点，并让相邻点指向它，
 *   并设置成一个由 parent 节点决定的单独组。
 *   这个过程被称为 union。
 *   这样每个组都有一个唯一的 parent 节点，这些节点的父亲为 -1。
 *   对于每对新节点，找寻他们的父亲。
 *   如果父亲节点一样，那么什么都不做他们已经是一个组里。
 *   如果父亲不同，说明他们仍然需要合并。通过他们的父亲合并让他们在一个组里。
 *   最后，找到组的个数，也就是根节点的个数。这些节点的 parent 应为 -1。
 * 时间复杂度：O(n^3) 访问整个矩阵一次、并查集操作需要最坏O(n)的时间
 * 空间复杂度：O(n) parent大小为n
 */
public class FriendCirclesDisjointSet {
    public int findCircleNum(int[][] M) {
        //parent[i] 记录节点 i 的父节点；
        // 特殊的，当 i 是根节点时，parent[i] 的值为 -1。
        // 初始时，每个节点都构成了一棵树，即每个节点都是一个根节点，
        int[] parent = new int[M.length];
        //用 -1作为值 初始化parent数组中所有的父节点
        Arrays.fill(parent, -1);
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                //如果矩阵当前位置上的元素等于1 并且 不是对角线元素的话
                if (M[i][j] == 1 && i != j) {
                    //合并
                    union(parent, i, j);
                }
            }
        }
        //统计父节点的个数
        int count = 0;
        for (int i = 0; i < parent.length; i++) {
            //如果是父节点
            if (parent[i] == -1) {
                count++;
            }
        }
        return count;
    }

    /**
     * 合并父节点
     * @param parent
     * @param x 横坐标
     * @param y 纵坐标
     *  通过 find 函数找到 x，y 的根节点 xset, yset。
     */
    private void union(int[] parent, int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        // 如果两个根节点不相同，则将 x 的父节点xset设为 yset,实现合并。如果相同，则无需任何操作。
        if (xset != yset) {
            parent[xset] = yset;
        }
    }

    /**
     * 查询父节点
     * 通过 x 的父节点，父节点的父节点 ... ，一直找到根节点并返回其ID。
     * @param parent
     * @param i
     * @return
     */
    int find(int parent[], int i) {
        //如果是根节点就返回其ID
        if (parent[i] == -1) {
            return i;
        }
        //再寻找当前节点parent[i]的父节点
        return find(parent, parent[i]);
    }

    public static void main(String[] args) {
        FriendCirclesDisjointSet friendCirclesDisjointSet = new FriendCirclesDisjointSet();
        int res= friendCirclesDisjointSet.findCircleNum(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}});
        System.out.println(res);
    }
}

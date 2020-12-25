package ziDianShuAndBingChaJi;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/12/22 & 1.0
 * DFS
 * 朋友圈-547
 * 计算有多少个朋友圈
 * 思路： M矩阵为方形的，朋友圈中各个人两两互相均为好友
 *        朋友圈的个数最多不超过M.length
 *        visited就相当于记录行与列是否又被遍历过，
 *               行和列只要有一个被遍历过，另外一个也就可以忽略了，因为[1][2]和[2][1]所表达的意思相同。
 * 时间复杂度：O(n^2) 整个矩阵都要被遍历到
 * 空间复杂度：O(n) visited的大小 =》M.length
 */
public class FriendCirclesDFS {
    public int findCircleNum(int[][] M) {
        //创建visited来记录是否被浏览
        int[] visited = new int[M.length];
        //记录朋友圈的数量
        int count = 0;
        //遍历每一个一维数组
        for (int i = 0; i < M.length; i++) {
            //如果当前的行没有被浏览过
            if (visited[i] == 0) {
                //针对当前的一维数组进行DFS
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    /**
     * DFS
     * @param M
     * @param visited
     * @param i 当前所要遍历的一维数组的位置
     */
    private void dfs(int[][] M, int[] visited, int i) {
        //遍历当前一维数组中的所有元素
        for (int j = 0; j < M.length; j++) {
            //如果此人在朋友圈中 并且 该列还未浏览过
            if (M[i][j] == 1 && visited[j] == 0) {
                //先将其做上标记防止重复浏览
                visited[j] =1;
                dfs(M, visited, j);
            }
        }
    }

    public static void main(String[] args) {
        FriendCirclesDFS friendCircles = new FriendCirclesDFS();
        int res = friendCircles.findCircleNum(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}});
        System.out.println(res);
    }
}

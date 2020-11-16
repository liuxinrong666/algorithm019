package GuangDuYouXianSouSuoAndTu;

import java.util.*;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/10/21 & 1.0
 *
 * 最小高度树-310
 */

public class ZuiXiaoGaoDuShu310 {
    /**
     *
     * @param n 节点的个数
     * @param edges 含有所有边上的一对点--这里就是：[[,],[,],[,],[,]]
     * @return 返回最小高度树：
     *         每一个叶节点到根节点 最大的距离 就是当前根节点的数的高度 ，
     *         然后取出使得高度最小的根。
     */
    static public List<Integer> findMinHeightTrees(int n, int [][] edges) {
        List<Integer> res = new ArrayList<>();
        /*如果只有一个节点，那么他就是最小高度树*/
        if (n == 1) {
            res.add(0);
            return res;
        }
        /*
        * 建立各个节点的出度表，
        * 顶点的出边条数称为该顶点的出度
        */
        int[] degree = new int[n];
        /*建立图关系，在每个节点的list中存储相连的点*/
        List<List<Integer>> map = new ArrayList<>();
        for (int i=0;i<n;i++) {
            map.add(new ArrayList<>());
        }
        System.out.println("map1: "+Arrays.toString(map.toArray()));
        for(int[] edge:edges){
            /*出度++*/
            degree[edge[0]]++;
            /*在degree的edge[0]的位置上的元素值加一*/
            System.out.println("degree[edge[0]]++: "+ Arrays.toString(degree));
            /*出度++*/
            degree[edge[1]]++;
            System.out.println("degree[edge[1]]++: "+ Arrays.toString(degree));
            /*添加相邻节点*/
            map.get(edge[0]).add(edge[1]);
            /*给二维数组-map的edge[0]位置的元组中添加元素edge[1]*/
            System.out.println("map.get(edge[0]).add(edge[1]): "+map);
            map.get(edge[1]).add(edge[0]);
            System.out.println("map.get(edge[1]).add(edge[0]): "+map);
            /*这样，degree和map中的值就对应上了：
            * degree：[1, 3, 1, 1]
            * map: [[1], [0, 2, 3], [1], [1]]
            * 也就是说，各个节点代表的数字值可表示成degree数组中的位置，节点出现次数可作为该位置上的值；
            * 在二维数组map中，是先将各个节点的数字值表示成位置，然后再将此节点连接的那个节点数值作为元素加入对应位置的元组中。
            * 解释：
            * EG：degree中的3代表--1节点出现3次
            *     对应的map中的[0,2,3]代表--和1节点形成边得节点有：0，2，3
            */
        }
//--------------------------- 开始剥洋葱（外-->内）-------------------------------------------------
        /*建立队列*/
        Queue<Integer> queue = new LinkedList<>();
        /*把所有出度（这里就可以理解成出现次数）为1的节点-也就是叶子节点入队（先进先出）*/
        for(int i=0;i<n;i++){
            if(degree[i]==1) {
                queue.offer(i);
            }
        }
        System.out.println("开始剥洋葱（外-->内）-------------------------------------");
        System.out.println("叶子节点有-queue："+queue);
        /*循环条件是经典的不空判断*/
        while(!queue.isEmpty()){
            System.out.println("while循环--单层节点不空------------------------");
            /*注意：我们每层循环都要new一个新的结果集合，这样最后保存的就是最终的最小高度树了*/
            res = new ArrayList<>();
            /*最外层的节点的数量*/
            int size = queue.size();
            System.out.println("最外层节点的数量为-size："+size);
            for (int i=0;i<size;i++){
                /*逐个得出叶子节点--叶子节点逐个出队*/
                int cur = queue.poll();
                System.out.println("cur: "+cur);
                System.out.println("queue: "+queue);
                /*把当前的的叶子节点加入到结果集*/
                res.add(cur);
                System.out.println("输出当前的结果集-res: "+res);
                List<Integer> neighbors = map.get(cur);
                System.out.println("与此节点连接的节点-neighbors："+neighbors);
                /*这里就是经典的bfs了，把当前节点的相邻节点都拿出来，
                把他们的出度都减去1，因为最初的叶子节点已经不存在了，
                所以，他的相邻节点们就可能变成叶子节点
                 */
                /*
                 BFS:宽度优先搜索算法（又称广度优先搜索）是最简便的图的搜索算法之一，
                 这一算法也是很多重要的图的算法的原型。
                 */
                System.out.println("--------------------------");
                for (int neighbor:neighbors){
                    /*neighbors中的各个节点的出度-1*/
                    degree[neighbor]--;
                    if (degree[neighbor]==1){
                        /*如果是叶子节点我们就入队*/
                        System.out.println("新入队的叶子节点："+neighbor);
                        queue.offer(neighbor);
                        System.out.println("目前的叶子节点有："+queue);
                    }
                }
            }
        }
        /*返回最后一次保存的list*/
        return  res;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] egdes={{0,3},{1,3},{2, 3},{4,3},{5,4}};
        List<Integer> heightTrees = findMinHeightTrees(n, egdes);
        System.out.println("**********************");
        System.out.println("最小高度树为："+heightTrees);
    }
}

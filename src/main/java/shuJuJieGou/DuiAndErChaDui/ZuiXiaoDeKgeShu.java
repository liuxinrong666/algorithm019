package shuJuJieGou.DuiAndErChaDui;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/11/15 & 1.0
 * 堆
 * 最小的K个数-40
 * 思路：
 * 1）.sort()之后取前k个数:
 *   可先将传入的数组进行整体顺序的翻转，然后再取出后面k个元素的值即可。
 *   注意：数组被创建之后，对于未被赋值的元素默认值是0。
 *        这里不该将其也打印出来，所以在长度的设置上要注意:
 *         不能取输入数组的长度，而是要取k。
 * 2）堆：
 *   本题是求前 K 小，因此用一个容量为 K 的大根堆。
 *   每次 poll 出最大的数，那堆中保留的就是前 K 小
 * 3）快排
 */
public class ZuiXiaoDeKgeShu {
    /**
     * 法二：堆
     * @param arr
     * @param k
     * @return
     * 复杂度分析：
     * 时间复杂度：O(nlogk)，其中 n 是数组 arr 的长度。
     *     由于大根堆实时维护前 k 小值，所以插入删除都是 O(logk) 的时间复杂度，
     *     最坏情况下数组里 n 个数都会插入，所以一共需要 O(nlogk) 的时间复杂度。
     * 空间复杂度：O(k)，因为大根堆里最多 k 个数。
     *  注意：不是小根堆！小根堆需要把全部的元素都入堆，
     *       时间复杂度就变成了O(NlogN)，而不是 O(NlogK)
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] res = new int[k];
        //特判
        if (k == 0) {
            return res;
        }
        //定义优先级队列（这里模拟一个堆）
        //默认的是小根堆，这里需要修改比较器
        PriorityQueue<Integer> queue = new PriorityQueue<>((num1, num2) -> num2 - num1);
        //当前堆的大小小于k,加元素到堆中
        for (int i = 0; i < k; i++) {
            //加k个元素到队头，作为初始堆
            queue.offer(arr[i]);
        }
        //注意：这里i是从k开始的，因为前k个元素已经在堆中了。
        for (int i=k; i<arr.length; i++) {
            //判断队顶元素是否小于遍历到的当前元素
            if (queue.peek() > arr[i]) {
                //将队顶元素弹出
                queue.poll();
                //新的队顶元素加入
                queue.offer(arr[i]);
            }
        }
        for (int i = 0; i < k; i++) {
            //再将队内元素加入到数组中
            res[i] = queue.poll();
        }
        return res;
    }
    /**
     * 法三：.sort排序
     * @param arr
     * @param k
     * @return
     * 时间复杂度：O(nlogn)
     *     .sort()-O(nlogn),for循环-O(k)
     * 空间复杂度：O(logn),排序所需的额外的空间复杂度
     */
    public int[] getLeastNumbers3(int[] arr, int k) {
        int[] res = new int[k];
        //特判
        if (k == 0) {
            return res;
        }
        //先排序(默认是升序)
        Arrays.sort(arr);
        for (int i = 0; i <k ; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static void main(String[] args) {
        ZuiXiaoDeKgeShu zuiXiaoDeKgeShu = new ZuiXiaoDeKgeShu();
        int[] leastNumber = zuiXiaoDeKgeShu.getLeastNumbers(new int[]{0,0,1,2,4,2,2,3,1,4}, 8);
        System.out.println(Arrays.toString(leastNumber));
    }
}

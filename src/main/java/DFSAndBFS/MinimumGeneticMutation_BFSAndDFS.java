package DFSAndBFS;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/11/24 & 1.0
 * 实现最小基因变化-433
 * BFS、DFS
 * 注意：这里要求求出最少的步数，但是每次也就能改变一个基因，
 *       因此，如果在改变一个碱基之后，基因库中有相同基因的话，就记录他，下次从他开始再接着改变基因即可。
 */
public class MinimumGeneticMutation_BFSAndDFS {
    /**
     * 法一：BFS
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation_BFS(String start, String end, String[] bank) {
        HashSet<String> bank_library = new HashSet<>();
        //将bank-基因库中的所有可能基因存入集合（不可重复）中
        for (String library : bank) {
            bank_library.add(library);
        }
        char[] banks = {'A', 'C', 'G', 'T'};
        LinkedList<String> dequeue = new LinkedList<>();
        //将起始的基因字符串加入到另一个队列中
        dequeue.offer(start);
        int count = 0;
        //监督集合-用来排除基因出现重复操作的情况
        HashSet<String> visited = new HashSet<>();
        while (!dequeue.isEmpty()) {
            int size = dequeue.size();
            while (size-- > 0) {
                //头部弹出基因元素
                String poll = dequeue.poll();
                //如果当前的字符串变化正好是目标值就返回count的值
                if (poll.equals(end)) {
                    return count;
                }
                //如果不能和end基因匹配上，就将当前基因字母进行逐个的修改
                //对于基因中的每个基因字母，都要用banks中的各个字母替换一遍，模拟换基因的步骤
                char[] curr = poll.toCharArray();
                for (int i = 0; i < curr.length; i++) {
                    //原来的基因元素
                    char old = curr[i];
                    for (char b : banks) {
                        //将banks里面的A,B,C,G,T分别赋值给curr里面的字符
                        curr[i] = b;
                        String newString = new String(curr);
                        //当前的字符串是否被访问过，被访问过说明陷入了循环，不可能变成目标值。
                        //当前的字符串是否在基因库，如果在，就记录这个新的字符串被访问过，
                        //                                  并且把这个新字符串加入到队列中
                        if (!visited.contains(newString) && bank_library.contains(newString)) {
                            visited.add(newString);
                            //存入队列中，有while()循环控制，会接着遍历dequeue中的序列，
                            // 也就是从第一步换基因之后的基因开始，继续换基因
                            dequeue.offer(newString);
                        }
                    }
                    //将当前位置上的基因字母还原成之前的样子，
                    curr[i] = old;
                }
            }
            //并不是有一个dequeue中的基因弹出队列，count就+1，
            // 必须将dequeue中的可能基因情况都更改考虑结束,步骤才会加一
            count++;
        }
        return -1;
    }

    /**
     * 方法二：DFS (比BFS执行用时少)
     * 递归实现，找子问题：start基因是会改变的，步数会改变
     * 入果当前的start基因不等于end基因，那就进入碱基交换环节--
     *  因此遍历库中基因， 如果当前start基因和库中的某个基因有多于1处基因是不同的，
     *                          那就终止当前循环，进行下一个库基因。
     */
    private int count = Integer.MAX_VALUE;
    public int MinMutation_DFS(String start, String end, String[] bank) {
        HashSet<String> vistied = new HashSet<>();
        dfs(0, start, end, bank, vistied);
        return count == Integer.MAX_VALUE ? -1 : count;
    }

    private void dfs(int minStepCount, String start, String end, String[] bank, HashSet<String> vistied) {
        if (start.equals(end)) {
            count = Math.min(count, minStepCount);
            return;
        }
        //i代表的是bank基因库中第几个基因-第(i+1)个
        for (int i = 0; i < bank.length; i++) {
            int diff = 0;
            //j 指的是当前基因中的第几位碱基-第(j+1)个
            for (int j = 0; j < 8; j++) {
                //如果基因库中当前的基因和start基因有多于一个碱基是不同的
                if (bank[i].charAt(j) != start.charAt(j)) {
                    diff++;
                }
                if (diff > 1) {
                    break;
                }
            }
            //如果两者有且仅有一处碱基不同，并且当前基因还没有被访问过
            if (diff == 1 && !vistied.contains(bank[i])) {
                vistied.add(bank[i]);
                //最少步数+1，start基因换成刚才基因库中的基因
                dfs(minStepCount + 1, bank[i], end, bank, vistied);
                //注意递归调用之后，visited还需出队操作
                vistied.remove(bank[i]);
            }
        }
    }

    public static void main(String[] args) {
        MinimumGeneticMutation_BFSAndDFS minimumGeneticMutation_bfsAndDFS = new MinimumGeneticMutation_BFSAndDFS();
        int res = minimumGeneticMutation_bfsAndDFS
                .MinMutation_DFS("AAAACCCC", "CCCCCCCC" ,new String[]{"AAAACCCA","AAACCCCA","AACCCCCA","AACCCCCC","ACCCCCCC","CCCCCCCC","AAACCCCC","AACCCCCC"});
        System.out.println(res);
    }
}

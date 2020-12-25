package twoEndedBFS;

import java.util.*;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/12/24 & 1.0
 * 法一：BFS（可能会执行超时）
 * 单词接龙-127
 * 注意： 转换序列的长度的话，需要将第一个起始单词也算上
 *       字典中不存在重复的单词，但是没说起始单词没有可能在字典中，像这里用Set存储的话可以自动去重。
 *       除此之外，还要注意字典是否为空、字典中是否存在目标单词的特殊情况。
 * 解题思路：
 *     和“最小基因变化”的BFS思路类似。
 *     主要区别：这道题要试的字母为 小写26个字母，且最后返回的是序列的长度（得额外加１）
 *     这道题，每次仅能改变一个字母，而每个位置上可以出现26种不同的情况，如果改变后的单词在单词字典(Set)中能够匹配上，则代表这步变化可行。
 *     每改变一次，下一个起始单词就可能会随之改变，也可能会有多个候选的起始单词，它们存于queue中（因为队列的进出的顺序一致）。
 *     可以通过创建visited集合来记录已经考虑过的情况，避免重复、队列顺序错乱。
 *     需要不断地判断当前queue中是否含有单词（也就是当前层是否遍历完），也就体现出BFS的思想了。
 * 时间复杂度：O(N * C)  N是单词字典中单词的个数，C是每个单词的长度（题目规定相同），
 *                    存入Hash表中，时间复杂度为O(N * C);
 *                    最糟糕的，每层会有N种情况，就相当于queue中可能会同时存有N个单词，作为最外层的while循环，复杂度为O(N),
 *                    除此之外，还需要在单词字典中找到可以匹配的单词-双重for循环，时间复杂度为O(26 * C),
 *                    也就是说，三层for循环的话，时间复杂度为:O(N * C),这里的26作为常数可不予表示。
 * 空间复杂度：O(N * C)
 *            Set存储单词字典：O(N * C)、queue：O(N * C)、visited：O(N * C)
 */
public class WordLadderBFSTwo {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 第 1 步：先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        //Hash的查询时间复杂度为O(1)，list查询的时间复杂度为O(n)
        Set<String> wordSet = new HashSet<>(wordList);
        //注意特况
        if (wordList.size() == 0 || !wordList.contains(endWord)) {
            return 0;
        }

        //第 2 步：图的广度优先遍历，必须使用队列（先进先出）和 表示是否访问过的 visited 哈希表
        Queue<String> queue = new LinkedList<>();
        //将当前起始单词加入到队列中
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();

        // 第 3 步：开始广度优先遍历，包含起点，因此初始化的时候步数为 1
        int step = 1;
        //当队列中还存单词的时候
        while (!queue.isEmpty()) {
            //currentSize代表着每一步(每一step)可以有的选择数(分叉)
            int currentSize = queue.size();
            while (currentSize-- > 0) {
                //依次遍历当前队列中的单词-出队
                String currentWord = queue.poll();
                char[] charArray = currentWord.toCharArray();
                //遍历每一个当前起始单词中的字母
                for (int i = 0; i < charArray.length; i++) {
                    //先保存然后恢复-先保存当前遍历的字母
                    char originChar = charArray[i];
                    //用26个字母分别替换 当前位置上的字母
                    for (char k = 'a'; k <= 'z'; k++) {
                        if (k == originChar) {
                            continue;
                        }
                        charArray[i] = k;
                        String nextWord = String.valueOf(charArray);
                        //如果单词列表中存在修改一个字母后的单词
                        if (wordList.contains(nextWord)) {
                            //如果他直接就等于目标单词的话，就返回step + 1，替换结束
                            if (nextWord.equals(endWord)) {
                                return step + 1;
                            }
                            if (!visited.contains(nextWord)) {
                                //就将其存入队列中作为下一个起始的单词（队尾进入）
                                queue.add(nextWord);
                                // 注意：添加到队列以后，必须马上标记为已经访问
                                visited.add(nextWord);
                            }
                        }
                    }
                    //修改之后如果成为目标单词或者成为下一个起始单词失败，则恢复
                    charArray[i] = originChar;
                }
            }
            //当前的层数走完，步骤数加一
            step++;
        }
        return 0;
    }

    public static void main(String[] args) {
        WordLadderBFSTwo wordLadderBFSTwo = new WordLadderBFSTwo();
        int res1 = wordLadderBFSTwo.ladderLength("hit", "cog",
                Arrays.asList(new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        int res2 = wordLadderBFSTwo.ladderLength("hit", "cog",
                Arrays.asList(new String[]{"hot", "dot", "dog", "lot", "log"}));
        System.out.println(res1);
        System.out.println(res2);
    }
}

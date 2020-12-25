package twoEndedBFS;

import java.util.*;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/12/23 & 1.0
 *  法一：BFS（可能会执行超时）
 *  拆成两个方法
 */
public class WordLadderBFS {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 第 1 步：先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        Set<String> wordSet = new HashSet<>(wordList);
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
                // 如果 currentWord 能够修改 1 个字符与 endWord 相同，则返回 step + 1，这个加一代表的是endWord本身
                if (changeWordEveryOneLetter(currentWord, endWord, queue, visited, wordList)) {
                    return step + 1;
                }
            }
            step++;
        }
        return 0;
    }
    /**
     * 尝试对 currentWord 修改每一个字符，看看是不是能与 endWord 匹配
     * @param currentWord  出队的单词
     * @param endWord   目标单词
     * @param queue     起始单词
     * @param visited   已被访问过的单词
     * @param wordList   已知的单词集合
     * @return
     */
    private boolean changeWordEveryOneLetter(String currentWord, String endWord, Queue<String> queue, Set<String> visited, List<String> wordList) {
        char[] charArray = currentWord.toCharArray();
        //遍历每一个当前起始单词中的字母
        for (int i = 0; i < endWord.length(); i++) {
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
                    //如果他直接就等于目标单词的话，那就直接返回true，替换结束
                    if (nextWord.equals(endWord)) {
                        return true;
                    }
                    //如果被修改后的单词没有被访问过
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
        return false;
    }

    public static void main(String[] args) {
        WordLadderBFS wordLadderBFS = new WordLadderBFS();
        int num = wordLadderBFS.ladderLength("hit", "cog",
                Arrays.asList(new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        int num2 = wordLadderBFS.ladderLength("hit", "cog",
                Arrays.asList(new String[]{"hot", "dot", "dog", "lot", "log"}));
        System.out.println(num);
        System.out.println(num2);
    }
}

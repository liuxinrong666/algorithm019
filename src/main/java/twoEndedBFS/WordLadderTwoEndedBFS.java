package twoEndedBFS;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/12/24 & 1.0
 * 双向BFS
 * 单词接龙-127
 * 思路：双向BFS的思路和单向BFS实现的类似，
 *       就是 这里用 HashSet类型（无放入顺序）的beginVisited和endVisited 以及 用来存储中间产生的下一层节点的nextLevelVisited
 *       这三个集合共同来实现之前单向的queue队列的功能。
 *       对于每层节点的更换 由原来的出队、入队 变为 集合的替换（nextLevelVisited 代替 beginVisited）
 * 时间复杂度：
 *
 * 空间复杂度：
 *       Set存储单词字典：O(N * C)、visited：O(N * C)
 */
public class WordLadderTwoEndedBFS {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 第 1 步：先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        //Hash的查询时间复杂度为O(1)，list查询的时间复杂度为O(n)
        Set<String> wordSet = new HashSet<>(wordList);
        //特况
        if (wordList.size() == 0 || !wordList.contains(endWord)) {
            return 0;
        }

        //2.已经访问过的 word 添加到 visited 哈希表里
        Set<String> visited = new HashSet<>();
        //分别用左边和右边扩散的哈希表代替单向 BFS 里的队列，它们在双向 BFS 的过程中交替使用
        Set<String> beginVisited = new HashSet<>();
        beginVisited.add(beginWord);
        Set<String> endVisited = new HashSet<>();
        endVisited.add(endWord);

        // 第 3 步：执行双向 BFS，左右交替扩散的步数之和为所求
        int step = 1;
        while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
            // 优先选择小的哈希表进行扩散，考虑到的情况更少
            if (beginVisited.size() > endVisited.size()) {
                Set<String> tmp = beginVisited;
                beginVisited = endVisited;
                endVisited = tmp;
            }
            // 逻辑到这里，保证 beginVisited 是相对较小的集合，
            // nextLevelVisited 在扩散完成以后，会成为新的 beginVisited
            Set<String> nextLevelVisited = new HashSet<>();
            for (String word : beginVisited) {
                char[] charArray = word.toCharArray();
                for (int i = 0; i < word.length(); i++) {
                    char originChar = charArray[i];
                    //注意取等！
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originChar) {
                            continue;
                        }
                        charArray[i] = c;
                        String nextWord = String.valueOf(charArray);
                        if (wordSet.contains(nextWord)) {
                            if (endVisited.contains(nextWord)) {
                                return step + 1;
                            }
                            if (!visited.contains(nextWord)) {
                                //nextVisited来实现单向queue存储同层节点的作用
                                nextLevelVisited.add(nextWord);
                                visited.add(nextWord);
                            }
                        }
                    }
                    //恢复
                    charArray[i] = originChar;
                }
            }
            beginVisited = nextLevelVisited;
            //每层的遍历结束后，步骤加一
            step++;
        }
        return 0;
    }
    public static void main(String[] args) {
        WordLadderTwoEndedBFS wordLadderTwoEndedBFS = new WordLadderTwoEndedBFS();
        int res1 = wordLadderTwoEndedBFS.ladderLength("hit", "cog",
                Arrays.asList(new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        int res2 = wordLadderTwoEndedBFS.ladderLength("hit", "cog",
                Arrays.asList(new String[]{"hot", "dot", "dog", "lot", "log"}));
        System.out.println(res1);
        System.out.println(res2);
    }
}

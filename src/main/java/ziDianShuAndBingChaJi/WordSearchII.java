package ziDianShuAndBingChaJi;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/12/21 & 1.0
 * 字典树Trie-单词搜索-212
 * Trie + DFS（有回溯）
 *  时间复杂度：O(M(4⋅3^(L−1))，其中 M 是二维网格中的单元格数，L 是单词的最大长度。（详情见官方解释）
 *  空间复杂度：O（N）
 *       在最坏的情况下，如果单词之间没有前缀重叠，
 *       则 Trie 将拥有与所有单词的字母一样多的节点。
 *       也可以选择在 Trie 中保留单词的副本。
 *       因此，我们可能需要 2N 的空间用于 Trie。
 **/
public class WordSearchII {
    private final TrieNode root = new TrieNode();
    private static class TrieNode {
        private TrieNode[] children;
        private String word;
        public TrieNode() {
            word = null;
            children = new TrieNode[26];
        }
    }
    /**
     * 将words加载到Tries中，然后对board做DFS
     * words = ["oath","pea","eat","rain"] --》Trie
     * board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"]] --》DFS
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            insert(word);
        }
        //二维数组中，一维数组的个数为n
        int n = board.length;
        if (n == 0) {
            return res;
        }
        //每个一维数组的长度为m(均相同)
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            //每一个一维数组都会调用一次dfs()
            for (int j = 0; j < m; j++) {
                //利用DFS方法查找
                existRecursive(board, i, j, root, res);
            }
        }
        return res;
    }
    /**
     * DFS 深度遍历
     * @param board  传入的二维数组
     * @param i
     * @param j
     * @param root 当前所在的节点
     * @param res  结果集合
     */
    private void existRecursive(char[][] board, int i, int j, TrieNode root, List<String> res) {
        //终止条件-1  board或visited中有一个遍历完毕
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
            return;
        }
        //终止条件-2  当前节点遍历过或者遍历的字母在前缀树中不存在。
        if (board[i][j] == '$' || root.children[board[i][j] - 'a'] == null) {
            return;
        }
        //当前层逻辑
        //再继续顺着往下
        root = root.children[board[i][j] - 'a'];
        //如果当前单词字母还未遍历完毕（未到结尾），则继续加入到答案中
        if (root.word != null) {
            res.add(root.word);
            //将当前单词设置为null，防止重复加入
            root.word = null;
        }
        //将遍历过的节点值换成'$'作为区分
        board[i][j] = '$';
        char tmp = board[i][j];
        //递归下探-上下左右去遍历
        existRecursive(board, i - 1, j, root, res);
        existRecursive(board, i + 1, j, root, res);
        existRecursive(board, i, j - 1, root, res);
        existRecursive(board, i, j + 1, root, res);
        //回溯-如果道路走不通可以退回去
        board[i][j] = tmp;
    }
    /**
     * 插入Trie
     * @param word
     */
    private void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        //当前节点结束，存入当前单词(就是最后一个节点不是用是否为End标记而是采用整个单词来进行标记)
        node.word = word;
    }
    public static void main(String[] args) {
        WordSearchII wordSearchII = new WordSearchII();
        List<String> words = wordSearchII.findWords(new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}},
                new String[]{"oath", "pea", "eat", "rain"});
        System.out.println(words);
    }
}

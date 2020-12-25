package ziDianShuAndBingChaJi;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/12/20 & 1.0
 * 字典树
 * 实现Trie（前缀树）-208
 */
public class ImplementTriePrefixTreeHashMap {
    /**
     * 法一：
     * 维护一个Map<Character, TrieNode> next的map，思路和数组的类似
     * 这个和数组的方法相比，有一个好处就是：测试的数据可以不全要求是小写的字母
     */
    class TrieNode {
        //定义next为当前节点所能链接到的所有节点
        Map<Character, TrieNode> next = new HashMap<>();
        boolean isEnd = false;
    }
    TrieNode root = new TrieNode();
    public ImplementTriePrefixTreeHashMap() {
    }
    /**
     * 插入传入的单词到前缀树中
     * @param word
     * 时间复杂度：O(m) m为传入的word的长度，要么检查要么创建节点。
     * 空间复杂度：O(m) 最坏的的情况下，新键入的键和Trie中的键没有公共的前缀
     */
    public void insert(String word) {
        //一开始前缀树初始化出来的，仅是一个根节点
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            //如果当前字符c不在next中
            if (!curr.next.containsKey(c)) {
                //生成一个新的节点，并将当前的节点指向新生成的节点
                TrieNode tmp = new TrieNode();
                curr.next.put(c, tmp);
                curr = tmp;
            } else {
                //如果当前字符c在next中，则继续进行匹配
                //沿着链接移动到树的下一个子层，算法继续搜索下一个键字符
                curr = curr.next.get(c);
            }
        }
        //在单词被遍历结束时，给当前所在的节点设置isEnd的标记，表示以当前字符结束可以形成一个字母
        curr.isEnd = true;
    }
    /**
     * 返回单词是否在前缀树内(必须有完全一样的单词树匹配上才行)
     * @param word
     * @return
     * 时间复杂度：O(m) m为传入word的长度，算法的每一步均搜索下一个键字符，最坏的情况下需要 m次操作。
     * 空间复杂度：O(1) 仅查找
     */
    public boolean search(String word) {
        TrieNode curr = root;
        //遍历字母的所有字符
        for (char c : word.toCharArray()) {
            //如果当前字符所在的节点不存在，返回false，说明单词还没搜完，树已经断了
            if (!curr.next.containsKey(c)) {
                return false;
            }
            //不断将遍历到的节点赋值给当前节点curr
            curr = curr.next.get(c);
        }
        //注意：search和startsWith的区别,需要判断当前节点是否是isEnd，如果是的话，证明是搜索到了，
        //      但是startsWith则不需要满足此条件,只要含有即可
        return curr.isEnd;
    }
    /**
     * 返回前缀树是否以传入的单词开头
     * @param prefix
     * @return
     * 时间复杂度：O(m) m为传入word的长度，算法的每一步均搜索下一个键字符，最坏的情况下需要 m次操作。
     * 空间复杂度：O(1) 仅查找
     */
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (char c : prefix.toCharArray()) {
            if (!curr.next.containsKey(c)) {
                return false;
            }
            curr = curr.next.get(c);
        }
        return true;
    }
    public static void main(String[] args) {
        ImplementTriePrefixTreeHashMap implementTriePrefixTree = new ImplementTriePrefixTreeHashMap();
        implementTriePrefixTree.insert("implementTriePrefixTree");
        implementTriePrefixTree.insert("implementTriePrefixTreess");
        boolean b = implementTriePrefixTree.search("implementTriePrefixTree");
        boolean bb = implementTriePrefixTree.search("implementTriePrefixTreess");
        boolean bbb = implementTriePrefixTree.search("implement");
        System.out.println(b);
        System.out.println(bb);
        System.out.println(bbb);
        boolean bbbb = implementTriePrefixTree.startsWith("implementTriePrefixTrees");
        System.out.println(bbbb);
    }
}

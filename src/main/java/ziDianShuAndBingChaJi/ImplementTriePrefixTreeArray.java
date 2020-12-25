package ziDianShuAndBingChaJi;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/12/21 & 1.0
 * 字典树
 * 实现Trie（前缀树）-208
 */
public class ImplementTriePrefixTreeArray {
    /**
     * 法二：
     * 数组的形式解决
     * 位置-当前的字符c-'a'
     * 时间复杂度、空间复杂度的分析和HashMap的相同
     * 缺点就是需要注意测试数据仅为小写字母的限制条件
     */
    ImplementTriePrefixTreeArray[] next;
    boolean isEnd;
    public ImplementTriePrefixTreeArray() {
        next = new ImplementTriePrefixTreeArray[26];
        isEnd = false;
    }
    /**
     * 插入
     * @param word
     */
    public void insert(String word) {
        ImplementTriePrefixTreeArray curr = this;
        for (char c : word.toCharArray()) {
            if (curr.next[c - 'a'] == null) {
                curr.next[c - 'a'] = new ImplementTriePrefixTreeArray();
            }
            curr = curr.next[c - 'a'];
        }
        curr.isEnd = true;
    }
    /**
     * 查询传入的字符串是否存在于前缀树中
     * @param word
     * @return
     */
    public boolean search(String word) {
        ImplementTriePrefixTreeArray curr = this;
        for (char c : word.toCharArray()) {
            if (curr.next[c - 'a'] == null) {
                return false;
            }
            curr = curr.next[c - 'a'];
        }
        return curr.isEnd;
    }
    /**
     * 查询当前的Trie是否以传入的单词为前缀
     * @param word
     * @return
     */
    public boolean startsWith(String word) {
        ImplementTriePrefixTreeArray curr = this;
        for (char c : word.toCharArray()) {
            if (curr.next[c - 'a'] == null) {
                return false;
            }
            curr = curr.next[c - 'a'];
        }
        return true;
    }
    public static void main(String[] args) {
        //注意：测试的数据必须均为小写的字母！否则会出现字符位置越界，因为数组的位置号范围是[0-25]
        ImplementTriePrefixTreeArray implementTriePrefixTreeArray = new ImplementTriePrefixTreeArray();
        implementTriePrefixTreeArray.insert("triearraysss");
        boolean b1 = implementTriePrefixTreeArray.search("triearraysss");
        boolean b2 = implementTriePrefixTreeArray.search("triearray");
        System.out.println(b1);
        System.out.println(b2);
        boolean b3 = implementTriePrefixTreeArray.startsWith("triearray");
        System.out.println(b3);
    }
}

package Hash;

import java.util.*;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/11/11 & 1.0
 * Hash
 * 字母异位词分组-49
 *
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 * 思考：
 * 1）审题：这道题和“有效的字母异位词”思想类似
 *          注意：如果没有两个字符串是异位字符串，也要输出那个单独的。
 * 2）解法：
 * （1）Hash(.sort()排序)：如果两个字符串中的字母是异位关系的话，.sort()之后，就是相同的。
 *            所以可以先对传入的字符串进行排序，将他们不断地与Hash中的Key进行匹配，
 *            如果有相同的Key，就加入到对应的value中；
 *            否则就新加入对应的Key，value就是一个空的ArrayList,并且再将Key加入到value中。
 * （2）Hash(数组统计子字符串中，字母的个数)：
 *            将统计好的数组作为Key，存储到HashMap中，相同结果的子字符串放在一起作为value。
 */
public class ZiMuYiWeiCiFenZu {
    /**
     * 法一 :Hash（数组记录单个字符串中各个字母出现的个数）
     * @param strs
     * @return
     * 时间复杂度：O(nk) n代表传入字符串的个数, k代表各个字符串的长度
     * 空间复杂度：O(nk) 存储在 hashMap 中的全部信息内容。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        //特判
        if (strs == null) {
            return null;
        }
        //创建Hash
        HashMap<String, ArrayList> hashMap = new HashMap<>();
        for (String s : strs) {
            //创建数组--记录字串中各种小写字母出现的个数
            char[] ints = new char[26];
            for (char c : s.toCharArray()) {
                ints[c - 'a']++;
            }
            String string = String.valueOf(ints);
            if (!hashMap.containsKey(string)) {
                hashMap.put(string,new ArrayList());
            }
            hashMap.get(string).add(s);
        }
        return new ArrayList(hashMap.values());
    }
    /**
     * 法二 --Hash（.sort()排序）
     * @param strs
     * @return
     * 复杂度分析:
     * 时间复杂度：O(NKlogK)，其中 N 是 strs 的长度，而 K 是 strs 中字符串的最大长度。
     *   当我们遍历每个字符串时，外部循环具有的复杂度为 O(N)。然后，我们在 O(KlogK) 的时间内对每个字符串排序。
     * 空间复杂度：O(NK)，排序存储在 hashMap 中的全部信息内容。
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        //特判
        if (strs == null) {
            return null;
        }
        //创建哈希表
        HashMap<String, List> hashMap = new HashMap<>();
        for (String s : strs) {
            //注意：因为Arrays.sort()不能接收String类型的参数，因此需要将其换成char[]
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String key = String.valueOf(c);
            if (!hashMap.containsKey(key)) {
                hashMap.put(key, new ArrayList());
            }
            hashMap.get(key).add(s);
        }
        return new ArrayList(hashMap.values());
    }
    public static void main(String[] args) {
        ZiMuYiWeiCiFenZu ziMuYiWeiCiFenZu = new ZiMuYiWeiCiFenZu();
        System.out.println(ziMuYiWeiCiFenZu.groupAnagrams(new String[]{"eattttt", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(ziMuYiWeiCiFenZu.groupAnagrams(new String[]{}));
    }
}

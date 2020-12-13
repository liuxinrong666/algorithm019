package dongTaiGuiHua;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/12/12 & 1.0
 * 动态规划(DP)
 * 最长公共子序列-1143
 * 子问题划分：
 * 1）如果S序列和T的序列的最后一位相同，
 *    那么最大子序列长度 就是{s1,s2,...,si-1}和{t1,t2,...,tj-1}的最大子序列长度+1
 * 2）否则，分为两种情况再取max：
 *   （1）{s1,s2,...,si}和{t1,t2,...,tj-1}的最大子序列长度
 *   （2）{s1,s2,...,si-1}和{t1,t2,...,tj}的最大子序列长度
 *    然后再取两种情况的最大值即可。
 */
public class LongestCommonSubsequence {
    /**
     * 法一：动态规划
     * 注意：起始条件-两字符串的大小，是否为空
     * @param text1
     * @param text2
     * @return
     * 时间复杂度为：O(mn) 双重for循环,为两个数组的长度之积
     * 空间复杂度为：O(mn) 形成m*n的矩阵
     */
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text1.length() <= 0 || text2 == null || text2.length() <= 0) {
            return 0;
        }
        int length1 = text1.length();
        int length2 = text2.length();
        int[][] dp = new int[length1 + 1][length2 + 1];
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                //结尾相同
                if (chars1[i - 1] == chars2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    //结尾不同
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[length1][length2];
    }
    public static void main(String[] args) {
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence();
        int res = longestCommonSubsequence.longestCommonSubsequence("ace","abcde");
        System.out.println(res);
    }
}

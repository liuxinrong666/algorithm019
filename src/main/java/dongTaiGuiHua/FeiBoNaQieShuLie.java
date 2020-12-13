package dongTaiGuiHua;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/12/12 & 1.0
 * 动态规划
 * 斐波那契额数列-剑指Offer 10-I
 * 注意: 数字的溢出情况
 */
public class FeiBoNaQieShuLie {
    /**
     * 法一：递归(会超时)
     * @param n
     * @return
     * 时间复杂度：形成二叉树，时间复杂度为O(2^n)
     * 空间复杂度：树的深度，O(n)
     */
    public int fib3(int n) {
        return n < 2 ? n : fib3((n - 1) % 1000000007) + fib3((n - 2) % 1000000007);
    }

    /**
     * 法二：动态规划 + 取余数
     * 由(x + y)⊙p = (x⊙p + y⊙p)⊙p
     * 可推出 f(n)⊙p=[f(n−1)⊙p+f(n−2)⊙p]⊙p
     * 从而可以在循环过程中每次计算 sum = (a + b) ⊙ 1000000007
     * @param n
     * @return
     * 时间复杂速度：O(n) 计算 f(n) 需循环 n 次，每轮循环内计算操作使用 O(1)。
     * 空间复杂度：O(n) 几个标志变量使用常数大小的额外空间。
     */
    public int fib2(int n) {
        int a = 0, b = 1, sum;
        //自底向上，for循环，减少重复计算
        for (int i = 0; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }
    public static void main(String[] args) {
        FeiBoNaQieShuLie feiBoNaQieShuLie = new FeiBoNaQieShuLie();
        int fib = feiBoNaQieShuLie.fib2(10);
        int fib1 = feiBoNaQieShuLie.fib3(10);
        System.out.println(fib);
        System.out.println(fib1);
    }
}

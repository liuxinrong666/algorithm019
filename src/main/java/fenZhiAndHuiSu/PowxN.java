package fenZhiAndHuiSu;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/11/22 & 1.0
 * 计算x的n次幂函数-50
 * 思路：
 *   n的范围：[-2^31,2^31-1]
 *            n可正可负，如果是负数，就先将其转换成正数后，再用 1 去除以结果即可，但是需要注意以下
 *            注意：如果n取最小值-2^31的话，取反之后会超出最大范围，
 *                   因此可在操作之前将n从int 类型转换成long类型。
 *   在用分治法求n次幂的时候，注意 奇数偶数两种情况组要分别考虑
 *   解法：递归+分治（快速幂）
 */
public class PowxN {
    /**
     * 递归+分治
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? myPowHelper(x, N) : 1.0 / myPowHelper(x, -N);
    }

    /**
     * 自定义递归函数，用来求N次幂
     * @param x
     * @param N
     */
    private double myPowHelper(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double v = myPowHelper(x, N / 2);
        return N % 2 == 0 ? v * v : v * v * x;
    }

    public static void main(String[] args) {
        PowxN powxN = new PowxN();
        double res = powxN.myPow(2.00000, -2);
        System.out.println(res);
    }
}

package tanXinSuanFa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Author : ASUS and xinrong
 * @Version : 2020/10/20 & 1.0
 */
public class FenFaBingGan455 {
    /**
     * g[i]:每个孩子的胃口值；s[i]:每块饼干的尺寸
     * @param g
     * @param s
     * @return
     *
     * 自写程序- 排序+双重for循环 --（LeeCode上提交之后显示“超出时间限制”）
     * 双重for循环的时间复杂度为 O(孩子数*饼干数)
     *  所以，for循环实在是浪费时间（尤其是多重for循环）
     * 常规排序排序的时间复杂度为 O(nlogn) n-元素的个数
     */
    static public int findContentChildren1(int[] g, int[] s) {
        //先将传入的两个数组进行排序-都是从小到大排序
        //如果不排序 EG:
         //g:1 4
         //s:5 1
         //这样的话5直接会被1拿走，而4只能和1匹配，
         //事实上应该是1满足1，5满足4
        Arrays.sort(g);
        System.out.println("排序后的胃口数："+Arrays.toString(g));
        Arrays.sort(s);
        System.out.println("排序后的尺寸数："+Arrays.toString(s));
        int n=0;
        //可变数组用来存放已经被满足的孩子
        List<Integer> deleteIndex = new ArrayList<>();
        //for循环
        for(int i=0;i<s.length;i++){
            System.out.println("当前饼干的尺寸为："+s[i]);
            for(int j=0;j<g.length;j++){
                System.out.println("当前孩子的胃口数为："+g[j]);
                if(s[i]>=g[j] && !deleteIndex.contains(j)){
                    n+=1;
                    System.out.println("胃口数为 "+g[j]+" 的孩子被满足了");
                    //下次分饼干的时候就不用考虑这个孩子了
                    deleteIndex.add(j);
                    break; //跳出当前所在的for()循环
                    //continue 是不执行continue下面的语句，接着进行后面的循环
                }
            }
        }
        //注：如果采用foreach循环，是不能去掉已经被满足的孩子的，因此还是采用的for循环。
        return n;
    }

    /**
     * @param g 所有孩子的胃口值
     * @param s 所有饼干的尺寸值
     * @return
     *
     * 优化方法-采用排序+while语句
     */
    static public int findContentChildren2(int[] g, int[] s){
        //特殊情况：孩子或饼干数为0
        if(g.length==0||s.length==0){
            return 0;
        }
        //先排序-- 小-->大
        Arrays.sort(g);
        Arrays.sort(s);
        //遍历的饼干、孩子起始位置
        int bNumber=0;
        int cNumber=0;
        //以饼干并且孩子数不都为0为前提
        //遍历每块饼干，直到找到其能满足的孩子
        //由于孩子和饼干的数组元素都是按照升序排列的，所以不用顾及漏不漏的问题
        while(bNumber<s.length && cNumber<g.length){
            if(s[bNumber]>=g[cNumber]){
                cNumber++;
            }
            bNumber++;
        }
        return cNumber;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("一共有几个孩子？");
        int children = scanner.nextInt();
        int[] g = new int[children];
        System.out.println("请输入每个孩子的胃口数：");
        for(int i=0;i<children;i++){
            g[i] = scanner.nextInt();
        }
        System.out.println("孩子们的胃口数："+ Arrays.toString(g));
        System.out.println("一共有几块饼干？");
        int biscuits = scanner.nextInt();
        int s[] = new int[biscuits];
        System.out.println("请输入每块饼干的尺寸数：");
        for(int i=0;i<s.length;i++){
            s[i] = scanner.nextInt();
        }
        System.out.println("每块饼干的尺寸数："+Arrays.toString(s));
        System.out.println("*************************");
        int number = findContentChildren2(g, s);
        System.out.println("最多能满足 "+number+" 个孩子的胃口");
//------------------------------------ 总结 -----------------------------------------------------------
        //虽然可以定义一个可变数组，这样直接将输入的数字add()进去即可，
        // 但是还是需要用for()循环判断收录的数据个数，而且后面还要根据题意将ArrayList<Integer>[]转换成int[]，
        // 所以还是直接定义成int[]的更方便。

        //不过在此尝试中获得了以下数据类型转换经验：
//        ArrayList<Integer> childrenSize = new ArrayList<>();
//        childrenSize.add(1);
//        //ArrayList<Integer>转换成int[]：.stream().mapToInt(x->x).toArray()
//        int[] a=childrenSize.stream().mapToInt(x->x).toArray();
    }
}

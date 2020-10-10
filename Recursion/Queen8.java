package Recursion;

public class Queen8
{
    static int max = 8;
    static int[] array = new int[max];
    static int count = 0;
    static int count_1 = 0;
    public static void main(String[] args)
    {
        Queen8 q = new Queen8();
        q.check(0);
        System.out.println(count);
        System.out.println(count_1);
    }
    //编写一个方法，放置第n个皇后
    static void check(int n )
    {
        if (n == max)
        {
            print();
            return;
        }
        //依次放入皇后
        for (int i  = 0;i <8;i++)
        {
            //先把当前的皇后n，放到该行的第一列
            array[n] = i;
            //要和前面是否冲突
            if (judge(n))
            {
                //如果不冲突接着放皇后
                check(n+1);//这里会产生回溯
            }
        }
    }
    //查看当我们放置的第n个皇后，就去检测该皇后是否和前面已经拜访的皇后冲突
    /**
     *
     * @param n：表示当前位置。
     * @return
     */
    static boolean judge(int n )
    {
        count_1++;
        for (int i = 0;i<n;i++)
        {
            if (array[i] == array[n] || (n - i) == Math.abs(array[n] - array[i]))
            {
                //在同一列，判断是否在同一列，是和在同意斜线。
                return false;
            }
        }
        return true;
    }
    static void print()
    {
        count++;
        for (int i =0;i<array.length;i++)
        {
            System.out.printf(array[i] + "");
        }
        System.out.println();
    }
}

/*package Search;

public class FibonacciSearch
{
    public static int maxSize = 20;
    public static void main(String[] args)
    {
        int[] arr = {1,8,10,89,1000,1234};

    }
    //需要使用到斐波那契数列
    public static int[] fib()
    {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++)
        {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }
    public static int fibSearch(int[] arr, int key)
    {
        int low = 0;
        int high = arr.length-1;
        int k =0; //表示斐波那契分割数值的下标
        int mid = 0;
        int[] f =fib();
        while (high>f[k] -1)
        {
            k++;
        }
    }
}*/

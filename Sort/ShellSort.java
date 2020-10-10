package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort
{
    public static void main(String[] args)
    {
        int[] arr = new int[80000];
        for (int i =0;i<80000;i++)
        {
            arr[i] = (int) (Math.random()*80000000);
        }
        Date data1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = s.format(data1);
        System.out.println("排序前的时间是="+dateStr);
        shellSort(arr);
        Date data2 = new Date();
        SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1 = s1.format(data2);
        System.out.println("排序前的时间是="+dateStr1);

    }
    //对交换式的希尔排序改为移动
    public static void shellSort(int[] arr)
    {
        for (int gap = arr.length/2;gap >0;gap =gap/2)
        {
            //从gap个元素逐个对其所在的组进行插入排序
            for (int i=gap;i<arr.length;i++)
            {
                int j =i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap])
                {
                    while(j-gap >= 0 && temp < arr[j - gap])
                    {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    //使用逐步推到希尔排序
    public static void shellSOrt(int[] arr)
    {
        int temp = 0;
        for (int gap = arr.length/2;gap >0;gap =gap/2)
        {
            for (int i = gap;i<arr.length;i++)
            {
                for (int j = i-gap;j>=0;j -=gap)
                {
                    if (arr[j] > arr[j +gap])
                    {
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(arr));
        /*System.out.println(Arrays.toString(arr));*/
/*        int temp = 0;
        //希尔排序第一轮排序
        //因为第一轮排序，是将10个数据分成了5组
        for (int i = 5; i < arr.length; i++)
        {

            for (int j = i - 5; j >= 0; j -= 5)
            //当前元素大于加上步长后的那个元素
            {
                if (arr[j] > arr[j + 5])
                {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));

        for (int i = 2; i < arr.length; i++)
        {

            for (int j = i - 2; j >= 0; j -= 2)
            //当前元素大于加上步长后的那个元素
            {
                if (arr[j] > arr[j + 2])
                {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }

            }
        }
        System.out.println(Arrays.toString(arr));
        for (int i = 1; i < arr.length; i++)
        {

            for (int j = i - 1; j >= 0; j -= 1)
            //当前元素大于加上步长后的那个元素
            {
                if (arr[j] > arr[j + 1])
                {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }

            }
        }
        System.out.println(Arrays.toString(arr));*/
    }

}

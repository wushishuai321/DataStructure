package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort
{
    public static void main(String[] args)
    {
/*        int[] arr = {1,9,-1,10,-2};
        bubbleSort(arr);
        System.out.println();
        System.out.println(Arrays.toString(arr));*/
        //创建一个8w的随机数组
        int[] arr = new int[80000];
        for (int i =0;i<80000;i++)
        {
            arr[i] = (int) (Math.random()*8000000);
        }
        System.out.println(Arrays.toString(arr));


        Date data1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = s.format(data1);
        System.out.println("排序前的时间是="+dateStr);

        bubbleSort(arr);

        Date data2 = new Date();
        SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1 = s1.format(data2);
        System.out.println("排序前的时间是="+dateStr1);



    }

    public static void bubbleSort(int[] arr)
    {
        int temp = 0;
        for (int i = 0;i<arr.length;i++)
        {
            for (int j =i;j<arr.length;j++)
            {
                if (arr[j] < arr[i])
                {
                    temp =arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }
}

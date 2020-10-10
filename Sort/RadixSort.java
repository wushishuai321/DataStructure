package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort
{
    public static void main(String[] args)
    {
        int[] arr = new int[80000000];
        for (int i =0;i<80000;i++)
        {
            arr[i] = (int) (Math.random()*80000000);
        }
        Date data1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = s.format(data1);
        System.out.println("排序前的时间是="+dateStr);
        radixSort(arr);
        Date data2 = new Date();
        SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1 = s1.format(data2);
        System.out.println("排序后的时间是="+dateStr1);
        System.out.println(Arrays.toString(arr));

    }

    public static void radixSort(int[] arr)
    {

        //得到数组中最大的数的位数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++)
        {
            if (max < arr[i])
            {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中，实际存放了多少个数据，我们应以一个一维数组来记录每一个通的每次放入数据个数
        int[] bucketElementCount = new int[10];

        for(int j = 0,n = 1; j < maxLength; j++, n *=10)
        {
            for (int i = 0;i <arr.length; i++)
            {
                int digitOfElement = arr[i] / n % 10;
                bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[i];
                bucketElementCount[digitOfElement]++;
            }
            //按照这个桶的顺序放回原来的数组
            int index = 0;
            for (int k = 0; k < bucketElementCount.length; k++)
            {
                if (bucketElementCount[k] != 0)
                {
                    for (int l = 0; l < bucketElementCount[k]; l++)
                    {
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                bucketElementCount[k] = 0;
            }
        }
        //System.out.println("第一次的排序");
/*        System.out.println(Arrays.toString(arr));*/
    }


}

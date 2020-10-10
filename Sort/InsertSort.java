package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSort
{
    public static void main(String[] args)
    {
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

        insertSort(arr);

        Date data2 = new Date();
        SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1 = s1.format(data2);
        System.out.println("排序后的时间是="+dateStr1);


    }
    public static void insertSort(int[] arr)
    {
        for (int i = 1 ;i < arr.length;i++)
        {
            int insertval = arr[i];
            int insertindex = i - 1;
            while (insertindex >= 0 && insertval < arr[insertindex])
            {
                arr[insertindex + 1] = arr[insertindex];
                insertindex--;
            }

            arr[insertindex + 1] = insertval;
/*            System.out.println();
            System.out.println(Arrays.toString(arr));*/
        }
        System.out.println(Arrays.toString(arr));
    }
}

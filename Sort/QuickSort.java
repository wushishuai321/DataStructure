package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort
{
    public static void main(String[] args)
    {
        int[] arr = new int[80000000];
        for (int i =0;i<8000000;i++)
        {
            arr[i] = (int) (Math.random()*8000000);
        }

        Date data1 = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = s.format(data1);
        System.out.println("排序前的时间是="+dateStr);

        quickSort(arr,0, arr.length-1);

        Date data2 = new Date();
        SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1 = s1.format(data2);
        System.out.println("排序后的时间是="+dateStr1);
    }

    public static void quickSort(int[] arr, int left, int right)
    {
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];

        while ( l < r)
        {
            while ( arr[l] < pivot)
            {
                l++;
            }
            while ( arr[r] > pivot)
            {
                r--;
            }
            if (l == r)
            {
                break;
            }
            int temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;
            //如果交换完以后，发现这个arr【l】 == pivot
            if (arr[l] == pivot) r--;
            if (arr[r] == pivot) l++;
/*            System.out.println(Arrays.toString(arr));*/
        }
        //判断如果 l == r，必须l++，r--
        if (l == r)
        {
            l++;
            r--;
        }
        if (left < r)
        {
            quickSort(arr, left, r );
        }
        if (right > l)
        {
            quickSort(arr, l, right);
        }
    }
}

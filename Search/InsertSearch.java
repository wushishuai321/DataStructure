package Search;

import java.util.Arrays;

public class InsertSearch
{
    public static void main(String[] args)
    {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++)
        {
            arr[i] = i + 1;
        }
        for (int ele : arr)
        {
            System.out.printf(ele + ",");
        }
        System.out.println();
        System.out.println(Arrays.toString(arr));

        System.out.println("插值查找得到为：" + 6);
        System.out.println(insertValueSearch(arr, 0, arr.length - 1, 6));
    }

    public static int insertValueSearch(int[] arr, int left, int right, int findval)
    {
        if (left > right || findval < arr[0] || findval > arr[arr.length - 1])
        {
            return -1;
        }
        int mid = left + (right + left) * (findval - arr[left]) / (arr[right] - arr[left]);
        int midval = arr[mid];
        if (findval > midval) return insertValueSearch(arr, mid + 1, right, findval);
        if (findval < midval) return insertValueSearch(arr, left, right - 1, findval);
        if (findval == midval) return mid;
        return -1;
    }
}

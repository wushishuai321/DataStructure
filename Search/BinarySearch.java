package Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearch
{
    public static void main(String[] args)
    {
        int[] arr = {0,1,2,3,4,5,5,6,7,8};
        List<Integer> l = binarySearch2(arr, 0, arr.length-1, 5);
        System.out.println(l);
    }
    //实现有序数组的重复查找，
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findval)
    {
        if (left > right)
        {
            return new ArrayList<Integer>();
        }
        int l = left;
        int r = right;
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findval > midVal )
        {
            return binarySearch2(arr,mid + 1, right, findval);
        }
        if (findval < midVal)
        {
            return binarySearch2(arr, left, mid - 1, findval);
        }
        if (findval == midVal)
        {
            List<Integer> resIndexlist =  new ArrayList<Integer>();
            int temp = mid - 1;
            while (true)
            {
                if (temp < 0 || arr[temp] != findval)
                {
                    break;
                }
                resIndexlist.add(temp);
                temp--;
            }
            resIndexlist.add(mid);
            temp = mid + 1;
            while (true)
            {
                if (temp > arr.length-1 || arr[temp] != findval)
                {
                    break;
                }
                resIndexlist.add(temp);
                temp++;
            }
            return resIndexlist;
        }
        return null;
    }
}

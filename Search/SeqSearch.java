package Search;

public class SeqSearch
{
    public static void main(String[] args)
    {
        int[] arr = {-1,34,9,11,34};
        System.out.println(seqSearch(arr,9));
    }
    public static int seqSearch(int[] arr, int value)
    {
        for (int i =0; i < arr.length; i++)
        {
            if (value == arr[i]) return i;
        }
        return -1;
    }

}

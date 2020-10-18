package Tree;

import java.util.Arrays;

public class HeapSort
{
    public static void main(String[] args)
    {
        //要求将数组进行升序排列,进行大顶堆
        int[] arr = {4, 6, 8, 5, 9,0,-1,-2,3};
        heapSort(arr);
    }

    //编写一个堆排序的方法
    public static void heapSort(int[] arr)
    {
        int temp = 0;
        System.out.println("堆排序");
        //分布完成
/*        adjustHeap(arr ,1, arr.length);
        System.out.println("第一次"+ Arrays.toString(arr));
        adjustHeap(arr,0,arr.length);
        System.out.println("第一次"+Arrays.toString(arr));*/
        //将无序序列构建成一个堆，根据升序降序需求选择大顶堆还是小顶对
        for (int i = arr.length /2 -1; i>=0;i--)
        {
            adjustHeap(arr,i,arr.length);
        }
        System.out.println(Arrays.toString(arr));

        for (int j = arr.length -1; j>0;j--)
        {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
        }
        System.out.println(Arrays.toString(arr));

    }
    //将一个数组（二叉树）,调整成为一个大顶堆

    /**
     * 功能：完成将以i对应的非叶子节点的树   调整成为大顶堆
     * 举例：int[] arr = {4,6,8,5,9};=> i = 1=>adjustHeap =>arr = {4,9,8,5,6}
     * 如果我们再一次调用adjustHeap传入的是 i= 0 =>{4,9,8,5,6}=>{9,6,8,5,4}
     *
     * @param arr:待调整的数组
     * @param i:i表示非叶子节点在数组中的索引
     * @param length：表示对多少个元素进行调整，length是在逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length)
    {
        int temp = arr[i]; //先取出当前元素的值，保存到临时变量
        //开始调整
        //说明
        //1.k = i*2+1是i的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1)
        {
            if (arr[k] < arr[k + 1] && k + 1 < length)
            {
                //说明左子节点的值小于右子节点的值
                k++;
            }
            if (arr[k]> temp)
            {
                arr[i] = arr[k];
                i = k;  //同时指针后移判断，继续循环比较
            }else
            {
                break; //特别注意
            }
        }
        //当for循环结束后，已经将以i为父节点的这个树的最大值，放在了i这个位置最顶上面
        arr[i] = temp; //将temp放到调整过后的位置
    }
}

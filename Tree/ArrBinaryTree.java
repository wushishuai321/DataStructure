package Tree;
//顺序存储二叉树,数组可以传换成树，树也可以转换成数组。
public class ArrBinaryTree
{
    public static void main(String[] args)
    {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        //创建一个
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();
        System.out.println();
        arrayBinaryTree.infixOrder();
        System.out.println();
        arrayBinaryTree.postOrder();
    }
}

//编写一个ArrBinaryTree,实现顺序存储二叉树的遍历
class ArrayBinaryTree
{
    private int[] arr;  //存储数据节点的数组

    public ArrayBinaryTree(int[] arr)
    {
        this.arr = arr;
    }

    //重载preOrder////////////////////////////////////////////////////////方法可以学习
    public void preOrder()
    {
        this.preOrder(0);
    }
    public void infixOrder()
    {
        this.infixOrder(0);
    }
    public void postOrder()
    {
        this.postOrder(0);
    }

    //编写一个方法完成顺序存储,index表示数组的下标
    public void preOrder(int index)
    {
        //如果数组为空，或者arr.length == 0
        if (arr == null || arr.length == 0)
        {
            System.out.println("数组为空不能按照二叉树的前序遍历");
        }
        System.out.print(arr[index]+" ");
        //向左进行递归遍历
        if (index * 2 + 1 < arr.length)
        {
            preOrder(2 * index + 1);
        }
        //向右递归
        if (index * 2 + 2 < arr.length)
        {
            preOrder(2 * index + 2);
        }
    }
    //中序遍历
    public void infixOrder(int index)
    {
        if (arr == null || arr.length == 0)
        {
            System.out.println("数组为空不能按照二叉树进行中序遍历");
            return;
        }
        if (index * 2 + 1 < arr.length)
        {
            infixOrder(index * 2 + 1);
        }
        System.out.print(arr[index]+" ");
        if (index * 2 + 2 < arr.length)
        {
            infixOrder(index * 2 + 2);
        }
    }
    //后序遍历
    public void postOrder(int index)
    {
        if (arr == null || arr.length == 0)
        {
            System.out.println("数组为空不能按照二叉树进行后序遍历");
        }
        if (index * 2 + 1 < arr.length)
        {
            infixOrder(index * 2 + 1);
        }
        if (index * 2 + 2 < arr.length)
        {
            infixOrder(index * 2 + 2);
        }
        System.out.print(arr[index]+" ");
    }
}
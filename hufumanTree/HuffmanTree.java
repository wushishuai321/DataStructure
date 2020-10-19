package hufumanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree
{
    public static void main(String[] args)
    {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        preOrder(createHuffmanTree(arr));

    }

    //创建赫夫曼树的方法
    public static Node createHuffmanTree(int[] arr)
    {
        //第一步为了操作方便
        //1.遍历arr数组
        //2.将arr的每一个元素构建成一个node
        //3.将node放入到ArrayList
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr)
        {
            nodes.add(new Node(value));
        }
        //这个处理过程是一个循环过程

        while (nodes.size() > 1)
        {
            //排序从小到大
            Collections.sort(nodes);
            System.out.println("nodes = " + nodes);
            //取出根节点权值最小的二叉树
            //取出权值最小的节点
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            //构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //从ArrayList中删除掉上面的两个二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将parent加入到nodes当中去
            nodes.add(parent);

            System.out.println("第一次处理后" + nodes);
        }
        //返回的是赫夫曼的root节点
        return nodes.get(0);
    }
    public static void preOrder(Node node)
    {
        System.out.println(node.value);
        if (node.left != null)
        {
            preOrder(node.left);
        }
        if (node.right != null)
        {
            preOrder(node.right);
        }
    }
}
//创建一个节点类
//为了让node这个对象支持排序Collection集合排序
//让node实现compareble接口


class Node implements Comparable<Node>
{
    int value; //节点的权值
    Node left;
    Node right; //指向右子节点
    //洗一个前序遍历


    public Node(int value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "Node{" + "value=" + value + '}';
    }

    @Override
    public int compareTo(Node o)
    {
        //表示从小到大的排序，从大到小-（）。
        return this.value - o.value;
    }
}
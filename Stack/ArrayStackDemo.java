package Stack;

import javax.swing.*;
import java.util.Scanner;

public class ArrayStackDemo
{
    public static void main(String[] args)
    {
        ArrayStack a = new ArrayStack(5);
        String key = "";
        boolean loop =true;
        Scanner s =new Scanner(System.in);

        while(loop)
        {
            System.out.println("show：表示显示栈");
            System.out.println("exit：退出程序");
            System.out.println("push：表示添加数据到栈");
            System.out.println("pop：表示取出数据");
            System.out.println("请输入你的选择:");
            key = s.next();

            switch (key)
            {
                case "show":
                    a.list();
                    break;
                case "push":
                    System.out.println("请输入一个数:");
                    int value = s.nextInt();
                    a.push(value);
                    break;
                case "pop":
                    try
                    {
                        int res = a.pop();
                        System.out.println("出栈的数据时："+res);
                    }catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    s.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("程序退出");

    }
}
//定义一个类，表示栈
class ArrayStack
{
    private int maxSize;
    private int[] stack;      //数组模拟栈，数据就放在改数组中
    private int top = -1;     //top表示栈顶，初始化为-1

    //构造器
    public ArrayStack(int maxSize)
    {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }
    //栈满
    public boolean isFull()
    {
        return top == maxSize-1;
    }
    //栈空
    public boolean isEmpty()
    {
        return top == -1;
    }
    //入栈的操作
    public void push(int value)
    {
        //先判断栈是否满
        if (isFull())
        {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }
    //出栈
    public int pop()
    {
        if (isEmpty())
        {
            throw new RuntimeException("栈空,没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //显示栈的情况，遍历栈,遍历时需要从栈顶显示数据
    public void list()
    {
        if (isEmpty())
        {
            System.out.println("没有数据");
            return;
        }
        for (int i = top;i >= 0;i--)
        {
            System.out.printf("stack[%d] = %d\n",i,stack[i]);
        }
    }

}
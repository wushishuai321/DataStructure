package Stack;

public class Calculator
{
    public static void main(String[] args)
    {
        //完成计算器的功能
        String expression = "20-10*2-20";
        //创建两个栈，数栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        int index = 0;  //index用于扫描表达式的值。
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        String keepnum = "";//用于拼接多位数
        char ch = ' '; // 将每次扫描得到的CHar保存到ch
        //开始用while循环的扫描expression
        while(true)
        {
            //一次得到expression中的每一个字符
            ch = expression.substring(index,index + 1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if (operStack.isOper(ch))
            {
                //如果是运算符,判断当前的符号栈是否为空
                if(!operStack.isEmpty())
                {
                    //如果符号栈有操作符，就进行比较，如果当前的操作符优先级小于或者邓宇栈中的操作符
                    if (operStack.priority(ch) <= operStack.priority(operStack.peekStack()))
                    {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,(char)oper);
                        //把运算的数值存入栈中
                        numStack.push(res);
                        //把当前的操作符加符号栈
                        operStack.push(ch);
                    }else
                    {
                        //如果当前的操作符的优先级大于栈中的优先级
                        operStack.push(ch);
                    }
                }
                else
                {
                    //如果为空直接入栈
                    operStack.push(ch);
                }
            }else
            {
                //当我们发现是数字
               /* numStack.push(ch - 48);*/
                //当处理多位数时，如果是多位数要判断
                //在处理数时，需要向expression的表达式的index后再看一看。
                //我们需要订一个字符串变量
                keepnum += ch;
                //如果ch已经是ex的最后了
                if(index == expression.length() - 1)
                {
                    numStack.push(Integer.parseInt(keepnum));
                }else
                {
                    //判断下一个字符是不是为数字
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0)))
                    {
                        numStack.push(Integer.parseInt(keepnum));
                        //要清空keepnum
                        keepnum = "";
                    }
                }
            }
            //index + 1,并判断是否扫描到expression的最后
            index++;
            if (index >= expression.length())
            {
                break;
            }
        }
        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop相应的数和符号
        while (true)
        {
            //如果符号栈为空，则计算就结束了,数栈中只有一个数字
            if (operStack.isEmpty())
            {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
/*            if(num1 < 0 && oper == '-')
            {
                num2 = -num2;
            }*/
            res = numStack.cal(num1,num2,(char)oper);
            //把运算的数值存入栈中
            numStack.push(res);
        }
        System.out.println("表达式:"+expression+"="+numStack.pop());

    }
}

class ArrayStack2
{
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack2(int maxSize)
    {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }
    //增加一个方法可以返回当前栈顶的值，但是不是真正的pop
    public int peekStack()
    {
        return stack[top];
    }
    public boolean isFull()
    {
        return top == maxSize-1;
    }
    public boolean isEmpty()
    {
        return top == -1;
    }
    public void push(int value)
    {
        if (isFull())
        {
            System.out.println("栈满，不能添加元素");
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
            throw new RuntimeException("栈空没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //遍历栈
    public void list()
    {
        if (isEmpty())
        {
            System.out.println("没有数据");
            return;
        }
        for (int i = top;i>=0;i--)
        {
            System.out.printf("stack[%d] = %d\n",i,stack[i]);
        }
    }
    //扩展功能返回运算符的优先级，优先级是程序员来定的，优先级使用数字表示
    //数字越大，则优先级就越高
    public int priority(int oper)
    {
        if (oper == '*' || oper == '/')
        {
            return 1;
        }else if (oper == '+' || oper == '-')
        {
            return 0;
        }else
        {
            return -1;//假定目前的计算式表达式只有这个加减乘除
        }
    }
    //判断是不是一个运算符
    public boolean isOper(char val)
    {
        return val == '+' ||val == '-' ||val == '/' ||val == '*';
    }
    //计算方法
    public int cal(int num1,int num2,char oper)
    {
        int res = 0; // res存放计算的结果
        switch (oper)
        {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//注意压栈的顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}

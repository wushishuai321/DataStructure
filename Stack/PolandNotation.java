package Stack;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation
{
    public static void main(String[] args)
    {
        //将中缀表达式转换成后缀表达式的方法
        String expression = "1+((2+3)x4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println(infixExpressionList);//[1, +, (, (, 2, +, 3, ), x, 4, ), -, 5]6
        //将得到的中缀表达式转换成后缀表达式对应的List
        List<String> p = parseSuffixExpressionList(infixExpressionList);
        System.out.println(p);
        System.out.println(calculator(p));


        //先定义一个逆波兰表达式
        String suffixExpression = "3 4 + 5 x 6 -";
        //先将suffixExpression放到arraylist中
        List<String> rpnList = getListString(suffixExpression);
        System.out.println(rpnList);

        int res =calculator(rpnList);
        System.out.println(res);
    }
    //将得到的中缀表达式对应的list变化为后缀表达式
    public static List<String> parseSuffixExpressionList(List<String> ls)
    {
        //定义两个栈，
        Stack<String> s1 = new Stack<String>(); //符号栈
        List<String> s2 = new ArrayList<String>(); //存储中间节点的栈,因为s2没有pop还要逆序输出只需要一个List

        //遍历ls
        for (String item: ls)
        {
            //如果是一个数就入栈s2
            if(item.matches("\\d+"))
            {
                s2.add(item);
            }else if (item.equals("("))
            {
                s1.push(item);
            }else if (item.equals(")"))
            {
                while (!s1.peek().equals("("))
                {
                    s2.add(s1.pop());
                }
                s1.pop();//将（弹出
            }else
            {
                //当item的优先级，小于或者邓宇栈顶的s1运算符的优先级，将s1栈定的运算符弹出并加入到s2中
                while (s1.size() != 0 && Operration.getValue(s1.peek()) >= Operration.getValue(item))
                {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        //将s1剩余的运算符加入到s2中
        while (s1.size() != 0)
        {
            s2.add(s1.pop());
        }
        return s2;

    }
    //将中缀表达式，转成对应的List
    public static List<String> toInfixExpressionList(String s)
    {
        //定义一个List存放中缀表达式的内容
        List<String> list = new ArrayList<String>();
        int i = 0;//这个是一个指针，用于遍历中缀表达式的字符串
        String str; // 对多位数的拼接
        char c;//没遍历到一个字符就放入到c
        do
        {
            //如果c是一个非数字，我们就需要加入到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57)
            {
                list.add("" + c);
                i++;
            }else
            {
                //如果是一个数需要考虑多位数的问题
                str = ""; // 先将str变为空
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57)
                {
                    str += c;
                    i++;
                }
                list.add(str);
            }
        }while (i < s.length());
        return list;
    }



    public static List<String> getListString(String suffixExpression)
    {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele: split)
        {
            list.add(ele);
        }
        return list;
    }
    public static int calculator(List<String> ls)
    {
        //创建栈，只需要一个栈
        Stack<String> stack = new Stack<String>();
        for (String item: ls)
        {
            //这里使用一个正则表达式取出数
            if(item.matches("\\d+"))
            {
                //匹配的时多位数
                //入栈
                stack.push(item);
            }else
            {
                //pop弹出两个数
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res;
                if (item.equals("+"))
                {
                    res =num1 + num2;
                }else if (item.equals("-"))
                {
                    res = num1 - num2;
                }else if (item.equals("x"))
                {
                    res = num1 * num2;
                }else if (item.equals("/"))
                {
                    res = num1 / num2;
                }else
                {
                    throw new RuntimeException("运算符有误");
                }
                //吧res入栈
                stack.push(""+ res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类Operation可以返回一个运算符对应的优先级
class Operration
{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int NUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation)
    {
        int result = 0;
        switch (operation)
        {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "x":
                result = NUL;
                break;
            case "/":
                result = DIV;
                break;
 /*           default:
                System.out.println("不存在该运算符");
                break;*/
        }
        return result;
    }
}
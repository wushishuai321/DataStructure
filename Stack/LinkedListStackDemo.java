package Stack;



public class LinkedListStackDemo
{
    public static void main(String[] args)
    {
        StackDemo s = new StackDemo();
        YuanSu s1 = new YuanSu(1);
        YuanSu s2 = new YuanSu(2);
        YuanSu s3 = new YuanSu(3);
        YuanSu s4 = new YuanSu(4);
        s.push(s1);
        s.push(s2);
        s.push(s3);
        s.push(s4);

        s.show();
        System.out.println("pop后的：");
        s.pop();
        s.pop();
        s.show();
    }
}
class StackDemo
{
    private YuanSu head = new YuanSu(0);
    private YuanSu top = null;
    //构造器
    public void push(YuanSu y)
    {
        YuanSu temp = head;
        while (true)
        {
            if(temp.getNext() == null)
            {
                break;
            }
            temp = temp.getNext();
        }
        temp.setNext(y);


    }
    public void pop()
    {
        YuanSu temp = head;
        while(true)
        {
            if(temp.getNext().getNext() == null)
            {
                break;
            }
            temp = temp.getNext();
        }
        System.out.println("pop的值为:"+temp.getNext().getNo());
        temp.setNext(temp.getNext().getNext());

    }
    public void show()
    {
        YuanSu temp = head.getNext();
        while (true)
        {
            if (temp == null)
            {
                return;
            }
            System.out.println(temp.getNo());
            temp =temp.getNext();
        }
    }


}


class YuanSu
{
    private int no;
    private YuanSu next;//指向下一个节点
    public YuanSu(int no)
    {
        this.no = no;
    }
    public int getNo()
    {
        return no;
    }
    public YuanSu getNext()
    {
        return  next;
    }
    public void setNext(YuanSu next)
    {
        this.next = next;
    }
}

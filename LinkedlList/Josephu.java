package LinkedlList;

public class Josephu
{
    public static void main(String[] args)
    {
        //测试一把看看构建环形链表，和遍历是否成功
        CircleSingleLinkedList c =new CircleSingleLinkedList();
        c.addBoy(5);
        c.showBoy();
        c.countBoy(1,2,5);
    }
}
//创建一个环形的单项链表
class CircleSingleLinkedList
{
    private Boy first = new Boy(0);
    //添加小孩节点
    public void addBoy(int nums)
    {
        if (nums < 1)
        {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null; //辅助指针
        for(int i = 1;i<=nums;i++)
        {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if(i == 1)
            {
                first = boy;
               /* first.setNext(first);*///构成环
                curBoy = first;
            }else
            {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }
    //遍历当前环形链表
    public void showBoy()
    {
        //判断链表是否为空
        if (first == null)
        {
            System.out.println("链表为空");
            return;
        }
        //因为first不能动，因此我们需要使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true)
        {
            System.out.printf("小孩的编号为:%d\n",curBoy.getNo());
            if (curBoy.getNext() == first)
            {
                //说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext();//让curboy后移
        }
    }
    //根据用户的输入，计算出圈的一个顺序
    public void countBoy(int startNo,int coutNum,int nums)
    {
        //先对数据进行一个校验
        if (first == null || startNo < 1 || startNo >nums)
        {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建一个辅助指针，帮助完成小孩出圈、
        Boy helper = first;
        while (true)
        {
            if (helper.getNext() == first)
            {
                //说明helper指向了最后一个节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper移动k-1次
        for (int j = 0;j < startNo-1;j++)
        {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数是，当first和helper指针同时移动m-1然后出圈
        //这里是一个循环操作，知道圈中只有一个节点。
        while (true)
        {
            if (helper == first)
            {
                //说明圈中只有一个节点
                break;
            }
            //让first和helper指针同时移动countnums - 1
            for (int i= 0 ;i<coutNum - 1;i++)
            {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这是first指向的节点就是小孩要出圈的节点
            System.out.println("小孩出圈"+first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("最后留在圈中的小孩编号为:"+first.getNo());
    }

}


//定义一个Boy节点
class Boy
{
    private int no;
    private Boy next;//指向下一个节点
    public Boy(int no)
    {
        this.no = no;
    }
    public int getNo()
    {
        return no;
    }
    public void setNo(int no)
    {
        this.no = no;
    }
    public Boy getNext()
    {
        return  next;
    }
    public void setNext(Boy next)
    {
        this.next = next;
    }

}
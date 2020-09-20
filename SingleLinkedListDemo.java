package LinkedlList;

public class SingleLinkedListDemo
{
    //链表的面试题
    //1.统计链表中有效节点的个数
    public static int getLength(HeroNode head)
    {
        if(head.next == null)
        {
            //该链表为空
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while(cur != null)
        {
            length++;
            cur =cur.next;
        }
        return length;
    }
    //2.打印链表的倒数第k个节点
    //（1）编写一个方法接受head节点同时接受一个index：表示的是倒数第index个节点
    //（2）先把链表从头到尾遍历一下，得到链表的总长度
    //（3）得到长度后，我们从链表的第一个开始遍历
    //（4）如果找到了接返回该节点，找不到就返回一个空节点
    public static HeroNode findLastIndexNode(HeroNode head,int index)
    {
        if (head.next == null)
        {
            return null;
        }
        //第一次遍历得到链表的长度
        int length = getLength(head);
        //第二次遍历，找到我们所需要的节点。
        //先做一个index数据的校验。
        if(index > length | index <= 0)
        {
            System.out.println("输入的k值超出了我们的链表长度，不能返回");
        }
        //定义一个辅助变量
        HeroNode temp = head.next;
        for (int i =0;i<length - index;i++)
        {
            temp =temp.next;
        }
        return temp;
    }
    public static void main(String[] args)
    {
        //测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1,"songjian","jishiyu");
        HeroNode hero2 = new HeroNode(2,"wuyong","yuqilin");
        HeroNode hero3 = new HeroNode(3,"wusong","jishi");
        HeroNode hero4 = new HeroNode(4,"songan","jhiyu");
        //创建一个链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
   /*     singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);*/

        //加入按照编号的顺序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        //显示
        singleLinkedList.list();

        //测试修改节点的代码
        System.out.println("修改后的链表状态");
        HeroNode newheroNode = new HeroNode(2,"w","s");
        singleLinkedList.update(newheroNode);
        singleLinkedList.list();
        //删除一个节点
        System.out.println("删除后的链表状态");
        singleLinkedList.del(1);
        singleLinkedList.del(2);
        singleLinkedList.del(4);

        singleLinkedList.list();

        //找到该节点。
        singleLinkedList.find(4);

        System.out.println();
        //打印此时链表的有效节点的长度
        System.out.println(getLength(singleLinkedList.getHead()));
        System.out.println(findLastIndexNode(singleLinkedList.getHead(),1));

    }
}
//定义一个SingleLinkedList来管理我们的英雄
class SingleLinkedList
{
    //先初始化一个头节点，一般来说头节点不能动,不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");
    //因为头部节点为私有变量，只有在类中设定一个方法，放回头部节点。
    public HeroNode getHead()
    {
        return head;
    }
    //添加节点到单向链表
    //思路，当不考虑编号的顺序时
    //1.找到当前的这个链表的最后节点
    //2.将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode)
    {
        //因为head节点不能动，因此我们需要一个辅助变量temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true)
        {
            //找到链表的最后
            if(temp.next == null)
            {
                break;
            }
            //如果没有找到最后
            temp = temp.next;
        }
        //当推出while循环时，temp指向了链表的最后
        //将最后的这个节点next指向新的节点
        temp.next = heroNode;
    }
    //第二种方式添加英雄时，根据排名插入到指定位置
    //(如果有这个排名，则添加失败，并给出提示信息)
    public void addByOrder(HeroNode heroNode)
    {
        //因为头节点不能动，因此我们仍然需要一个辅助指针或者变量来帮助找到添加的位置。
        //因为这是一个单链表，因此我们找到的temp是位于添加的位置前一个节点。
        HeroNode temp = head;
        boolean flag = false;//标识添加的编号是否存在，默认为false
        while (true)
        {
            if (temp.next == null)
            {
                //说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no)
            {
                //位置找到了，就在temp位置的后边，
                break;
            }else if(temp.next.no == heroNode.no)
            {
                //说明我们这个编号已经存在了，就不需要再进行添加了
                flag = true; //说明编号存在。
                break;
            }
            temp = temp.next;
        }
        //判断flag的值
        if (flag)
        {
            //不能添加说明编号存在
            System.out.printf("准备插入的这个英雄的编号%d已经存在了，不能加入了",heroNode.no);
        }else
        {
            //插入到链表中
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }
//修改节点的信息，根据no编号来修改，就是说no的编号不能改
    public void update(HeroNode newHeroNode)
    {
        //判断连边是否为空
        if (head.next == null)
        {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;
        while (true)
        {
            if(temp == null)
            {
                break;//已经遍历完这个链表
            }
            if (temp.no == newHeroNode.no)
            {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到需要修改的节点
        if(flag)
        {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else
        {
            System.out.printf("没有找到编号为%d的这个节点",newHeroNode.no);
        }
    }
    //删除结点
    //思路1.head节点不能动，因此我们需要一个temp辅助接点找到待删除节点的前一个节点
    //2.说明我们在比较是，是temp.next.no和待删除的no比较
    public void del(int no)
    {
        HeroNode temp =head;
        boolean flag = false;
        while (true)
        {
            if (temp.next == null)
            {
                //已经到链表 的最后
                break;
            }
            if(temp.next.no == no)
            {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag)
        {
            temp.next = temp.next.next;

        }else
        {
            System.out.printf("要删除的%d节点不存在\n",no);
        }
    }
    public void find(int no)
    {
        HeroNode temp =head.next;
        boolean flag = false;
        while(true)
        {
            if(temp == null)
            {
                break;
            }
            if (temp.no == no)
            {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag)
        {
            System.out.println("找到了需要的节点"+temp);
        }else
        {
            System.out.println("链表中没有改no");
        }
    }
    //显示链表需要通过遍历
    public void list()
    {
        //先判断链表是否为空
        if (head.next == null )
        {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true)
        {
            //判断是否到链表的最后
            if (temp == null)
            {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将next后移，一定小心
            temp = temp.next;
        }
    }
}
//定义一个HeroNode,每个HeroNode对象就是一个节点
class HeroNode
{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;
    //构造器
    public HeroNode(int hNo,String hName,String hNickname)
    {
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;
    }
    //为了显示方便我们重写一下toString方法。
    public String toString()
    {
        return "HeroNode [no="+no+",name="+name+",nickname="+nickname+"]";
    }
}

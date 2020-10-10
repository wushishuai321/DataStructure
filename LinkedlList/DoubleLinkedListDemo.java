package LinkedlList;

public class DoubleLinkedListDemo
{
    public static void main(String[] args)
    {
        HeroNode2 hero1 = new HeroNode2(1,"songjian","jishiyu");
        HeroNode2 hero2 = new HeroNode2(2,"wuyong","yuqilin");
        HeroNode2 hero3 = new HeroNode2(3,"wusong","jishi");
        HeroNode2 hero4 = new HeroNode2(4,"songan","jhiyu");
        DoubleLinkedList d = new DoubleLinkedList();
        /*d.add(hero1);
        d.add(hero2);
        d.add(hero3);
        d.add(hero4);
        d.list();*/
        //删除节点
/*        d.del(4);
        System.out.println("开始删除节点");
        d.list();
        //修改节点
        System.out.println("开始修改节点");
        d.update(new HeroNode2(3,"w","s"));
        d.list();*/
        //按照顺序添加节点
        d.addByOrder(hero1);
        d.addByOrder(hero3);
        d.addByOrder(hero4);
        d.addByOrder(hero2);
        d.list();
    }
}
//创建一个双向链表
class DoubleLinkedList
{
    private HeroNode2 head = new HeroNode2(0,"","");
    //遍历双向链表
    public void list()
    {
        if (head.next == null)
        {
            System.out.println("此时双向链表为空!");
            return;
        }
        HeroNode2 temp = head.next; /////////////////////
        while(true)
        {
            if (temp == null)
            {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
    //第二种添加方式，按照序列号添加
    public void addByOrder(HeroNode2 heroNode)
    {
        HeroNode2 temp = head;
        HeroNode2 next;
        boolean flag = false;
        while(true)
        {
            if (temp.next == null)
            {
                break;
            }
            if (temp.next.no > heroNode.no)
            {
                break;
            }
            else if (temp.next.no == heroNode.no)
            {
                flag = true;
                break;
            }
            temp =temp.next;
        }
        if (flag)
        {
            System.out.println("编号已经存在");
        }else
        {
            next = temp.next;
            temp.next = heroNode;
            heroNode.pre = temp;
            if(next != null)
            {
                next.pre = heroNode;
                heroNode.next = next;
            }
        }
    }
    //添加链表元素
    public void add(HeroNode2 heraNode)
    {
        HeroNode2 temp = head;
        while(true)
        {
            if(temp.next == null)
            {
                break;
            }
            temp = temp.next;
        }
        temp.next = heraNode;
        heraNode.pre = temp;
    }
    //修改一个节点的元素
    public void update(HeroNode2 heroNode)
    {
        if(head.next == null)
        {
            System.out.println("链表为空，不能修改");
            return;
        }
        HeroNode2 temp = head;
        while(true)
        {
            if (heroNode.no == temp.no)
            {
                temp.name = heroNode.name;
                temp.nickname = heroNode.nickname;
                break;
            }
            temp = temp.next;
            if(temp == null)  System.out.println("未找到");
        }
        return;
    }
    //删除列表的节点
    public void del(int no)
    {
        if(head.next == null)
        {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head;
        while(true)
        {
            if(temp.no == no)
            {
                temp.pre.next = temp.next;
                if(temp.next != null)   temp.next.pre = temp.pre;
                return;
            }
            temp = temp.next;
        }
    }
}

//创建一个双相链表的类
class HeroNode2
{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no,String name,String nickname)
    {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
    public String toString()
    {
        return "HeroNode [no="+no+",name="+name+",nickname="+nickname+"]";
    }
}
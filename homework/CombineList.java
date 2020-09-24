package 数据结构课后作业;

public class CombineList
{
    public static void main(String[] args)
    {
        //创建第一个链表
        HeroNode hero1 = new HeroNode(1,"a","A");
        HeroNode hero2 = new HeroNode(2,"b","B");
        HeroNode hero3 = new HeroNode(4,"c","C");
        SingleLinkedList s1 = new SingleLinkedList();
        s1.add(hero1);
        s1.add(hero2);
        s1.add(hero3);
        System.out.println("这是s1的链表：");
        s1.printList();
        //创建第二个链表
        HeroNode hero4 = new HeroNode(3,"a","A");
        HeroNode hero5 = new HeroNode(5,"b","B");
        HeroNode hero6 = new HeroNode(6,"c","C");
        SingleLinkedList s2 = new SingleLinkedList();
        s2.add(hero4);
        s2.add(hero5);
        s2.add(hero6);
        System.out.println("这是s2的链表:");
        s2.printList();

        System.out.println("合并后的链表为：");
        SingleLinkedList s3 = new SingleLinkedList();
        s3.add(combineList(s1.getHead(),s2.getHead()));
        s3.printList();
    }
    public static HeroNode combineList(HeroNode head1,HeroNode head2)
    {
        if (head1 == null) return head2;
        if (head2 == null) return head1;

        HeroNode head = null;
        if (head1.no <= head2.no)
        {
            head = head1;
            head.next = combineList(head1.next,head2);
        }else
        {
            head = head2;
            head.next =combineList(head1,head2.next);
        }
        return head;
    }
}

class SingleLinkedList
{
    private HeroNode head = new HeroNode(0,"","");
    public HeroNode getHead()
    {
        return head;
    }
    public void add(HeroNode heroNode)
    {
        HeroNode temp = head;
        while(true)
        {
            if(temp.next == null)
            {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }
    public void printList()
    {
        if(head.next == null)
        {
            System.out.println("链表为空，不能打印");
            return;
        }
        HeroNode temp = head.next;
        while (temp != null)
        {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode
{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no,String name,String nickname)
    {
        this.no = no;
        this.name = name;
        this.nickname =nickname;
    }
    public String toString()
    {
        return "HeroNode [no="+no+",name="+name+",nickname="+nickname+"]";
    }
}
package HashTable;



import java.util.Scanner;

public class HashTableDemo
{
    public static void main(String[] args)
    {
        //创建一个哈希表
        HashTable hashTable = new HashTable(7);
        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("exit：退出系统");
            System.out.println("find: 查找雇员");
            System.out.println("delet: 删除雇员");
            key = scanner.next();
            switch (key)
            {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建一个雇员
                    Employ employ = new Employ(id, name);
                    hashTable.add(employ);
                    break;
                case "list":
                    hashTable.lsit();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                case "find":
                    System.out.println("请输入要查找的id");
                    int findid = scanner.nextInt();
                    hashTable.findEmpolyId(findid);
                    break;
                case "delet":
                    System.out.println("请输入要删除的元素id");
                    int deletid = scanner.nextInt();
                    hashTable.deletEmployId(deletid);
                    break;
            }
        }
    }
}

//创建hashTable用来管理多条链表
class HashTable
{
    private EmployLinkedList[] employLinkedListArray;
    private int size; //表示共有多少条链表

    public HashTable(int size)
    {
        //初始化上面
        this.size = size;
        employLinkedListArray = new EmployLinkedList[size];
        /////注意一定要初始化每一条链表//////////////////////////////////////////////////////////
        for (int i = 0; i < size; i++)
        {
            employLinkedListArray[i] = new EmployLinkedList();
        }
    }

    //添加雇员
    public void add(Employ employ)
    {
        //根据员工的id得到该员工因该添加到哪条链表
        int employLinkedListNo = hashFun(employ.id);
        //将employ加入到对应的链表中
        employLinkedListArray[employLinkedListNo].add(employ);
    }

    //遍历所有的链表
    public void lsit()
    {
        //遍历哈希表（数组+链表）
        for (int i = 0; i < size; i++)
        {
            employLinkedListArray[i].list(i);
        }
    }

    //编写一个散列函数,使用一个简单的取模法
    public int hashFun(int id)
    {
        return id % size;
    }

    //根据输入的id查找对应的雇员
    public void findEmpolyId(int id)
    {
        //使用散列函数确定哪一条链表
        int employLinkedNo = hashFun(id);
        Employ employ = employLinkedListArray[employLinkedNo].findEmployId(id);
        if (employ != null)
        {
            System.out.printf("在第%d链表中找到雇员%d", employLinkedNo, id);
        } else
        {
            System.out.println("在哈希表中,没有找到该元素");
        }
    }
    public void deletEmployId(int id)
    {
        int employLinkedno = hashFun(id);
        employLinkedListArray[employLinkedno].deletEmploy(id);
    }
}

class Employ
{
    public int id;
    public String name;
    public Employ next;

    public Employ(int id, String name)
    {
        super(); ///
        this.name = name;
        this.id = id;
    }
}

//创建employlist，表示一条链表
class EmployLinkedList
{
    //头指针指向第一个雇员，我们这个链表的head直接指向第一个雇员
    Employ head;
    //添加员工到链表
    public void add(Employ employ)
    {
        Employ temp = head;
        if (head == null)
        {
            head = employ;
            return;
        }
        while (true)
        {
            if (temp.next == null)
            {
                break;
            }
            temp = temp.next;
        }
        temp.next = employ;
    }
    //遍历链表的雇员信息
    public void list(int id)
    {
        if (head == null)
        {
            System.out.println("当前" + id + "链表为空");
            return;
        }
        System.out.println("第" + id + "链表的信息为:");
        Employ temp = head;
        while (true)
        {
            System.out.printf("    " + "=> id=%d name=%s\t", temp.id, temp.name);

            if (temp.next == null)
            {
                break;
            }
            temp = temp.next; //////
        }
        System.out.println();
    }

    //如果查找到就返回Employ
    public Employ findEmployId(int id)
    {
        if (head == null)
        {
            System.out.println("链表为空");
            return null;
        }
        Employ temp = head;
        while (true)
        {
            if (temp.id == id)
            {
                break;
            }
            if (temp.next == null)
            {
                temp = null;

            }
            temp = temp.next;
        }
        return temp;
    }

    public void deletEmploy(int id)
    {
        Employ temp = new Employ(-1,"");
        temp.next = head;

        boolean flag = false;
        while (true)
        {
            if (temp.next == null)
            {
                //已经到链表 的最后
                break;
            }
            if(temp.next.id == id)
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
            System.out.printf("要删除的%d节点不存在\n",id);
        }
    }
}

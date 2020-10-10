package HashTable;

public class xun
{
    public static void main(String[] args)
    {
        EmployLinkedList linkedlist = new EmployLinkedList();
        Employ e1 = new Employ(0, "w");
        Employ e2 = new Employ(1, "s");
        Employ e3 = new Employ(2, "b");
        linkedlist.add(e1);
        linkedlist.add(e2);
        linkedlist.add(e3);
        linkedlist.list(0);
        linkedlist.deletEmploy(2);
        linkedlist.list(0);
    }
}


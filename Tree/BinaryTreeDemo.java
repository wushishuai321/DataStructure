package Tree;

public class BinaryTreeDemo
{
    public static void main (String[] args)
    {
        BinaryTree1 binarytree = new BinaryTree1();
        //创建需要的节点
        HeroNode root = new HeroNode(1,"shujian");
        HeroNode heroNode2 = new HeroNode(2,"sdhujian");
        HeroNode heroNode3 = new HeroNode(3,"shdadujian");
        HeroNode heroNode4 = new HeroNode(4,"shujian");
        HeroNode heroNode5 = new HeroNode(5,"father");
        //先手动创建二叉树，后面采用递归的方式创建二叉树
        root.setLeft(heroNode2);
        root.setRight(heroNode3);
        heroNode3.setRight(heroNode4);
        heroNode3.setLeft(heroNode5);
        //将root加入树。
        binarytree.setRoot(root);
        //测试前中后序遍历
        System.out.println("前序遍历");
        binarytree.preOrder();
        System.out.println("中序遍历");
        binarytree.infixOrder();
        System.out.println("后序遍历");
        binarytree.postOrder();
        //测试找到节点的功能
        HeroNode searchNode = binarytree.preOrderSearch(15);
        if (searchNode != null)
        {
            System.out.println("找到了节点");
            System.out.println(searchNode);
        }else
        {
            System.out.println("没有找到节点");
        }
        //测试删除结点功能
        binarytree.delNode(5);
        binarytree.preOrder();
        System.out.println();
        binarytree.delNode(2);
        binarytree.preOrder();
        System.out.println();
        binarytree.delNode(1);
        binarytree.preOrder();
    }
}
//定义一个binarytree
class BinaryTree1
{
    private HeroNode root;
    public void setRoot(HeroNode root)
    {
        this.root = root;
    }
    //删除节点
    public void delNode(int no)
    {
        if (root != null)
        {
            //判断root是不是哦我们直接要删除的元素
            if (root.getNo() == no)
            {
                root = null;
            }else
            {
                root.delNode(no);
            }
        }else
        {
            System.out.println("空树不能删除");
        }
    }
    //前序遍历
    public void preOrder()
    {
        if (this.root != null)
        {
            this.root.preOrder();
        }else
        {
            System.out.println("当前二叉树为空，无法遍历");
        }
    }
    //中序遍历
    public void infixOrder()
    {
        if (this.root != null)
        {
            this.root.infixOrder();
        }else
        {
            System.out.println("当前二叉树为空，无法遍历");
        }
    }
    //后序遍历
    public void postOrder()
    {
        if (this.root != null)
        {
            this.root.postOrder();
        }
        else
        {
            System.out.println("当前二叉树为空，无法遍历");
        }
    }
    //前序查找
    public HeroNode preOrderSearch(int no)
    {
        if (root != null)
        {
            return root.preOrderSearch(no);
        }else
        {
            return null;
        }
    }
    //中序查找
    public HeroNode infixOrderSearch(int no)
    {
        if (root != null)
        {
            return  root.infixOrderSearch(no);
        }else
        {
            return null;
        }
    }
    //后序遍历
    public HeroNode postOrderSearch(int no)
    {
        if (root != null)
        {
            return  root.postOrderSearch(no);
        }else
        {
            return null;
        }
    }

}
//先创建HeroNode节点
class HeroNode
{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right; //默认为空

    public HeroNode(int no , String name)
    {
        this.name =name;
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public HeroNode getLeft()
    {
        return left;
    }

    public void setLeft(HeroNode left)
    {
        this.left = left;
    }

    public HeroNode getRight()
    {
        return right;
    }

    public void setRight(HeroNode right)
    {
        this.right = right;
    }

    @Override
    public String toString()
    {
        return "HeroNode{" + "no=" + no + ", name='" + name + '\'' + '}';
    }
    //递归删除节点
    public void delNode(int no)
    {
        //如果当前的节点的左子节点不为空，并且左子节点就是要删除的节点，将this.left = null;并且返回
        if (this.left != null && this.left.no == no)
        {
            this.left = null;
            return;
        }
        //同理对右子节点进行操作
        if (this.right != null && this.right.no == no)
        {
            this.right = null;
            return;
        }
        if (this.left != null)
        {
            this.left.delNode(no);
        }
        if (this.right != null)
        {
            this.right.delNode(no);
        }
    }
    //编写前序遍历
    public void preOrder()
    {
        System.out.println(this);//先输出父节点
        //递归先左子树前序遍历
        if (this.left != null)
        {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null)
        {
            this.right.preOrder();
        }
    }
    //中序遍历
    public void infixOrder()
    {
        //递归向左子树中序遍历
        if (this.left != null)
        {
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right != null)
        {
            this.right.infixOrder();
        }
    }
    //后序遍历
    public void postOrder()
    {
        if (this.left != null)
        {
            this.left.postOrder();
        }
        if (this.right != null)
        {
            this.right.postOrder();
        }
        System.out.println(this);
    }
    //前序遍历查找
    public HeroNode preOrderSearch(int no)
    {
        //比较当前节点是不是
        if (this.no == no)
        {
            return this;
        }
        //判断当前节点的子节点是否为空,否则会发生空指针异常
        //如果左递归谦虚查找，找到节点,则返回
        HeroNode resNode = null;
        if (this.left != null)
        {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null)
        {
            //说明左子树上找到了该节点
            return resNode;
        }
        if (this.right != null)
        {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }
    //中序遍历查找
    public HeroNode infixOrderSearch(int no)
    {
        HeroNode resNode = null;
        if (this.left != null)
        {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null)
        {
            return resNode;
        }
        if (this.no == no)
        {
            return this;
        }
        if (right != null)
        {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }
    //后序遍历查找
    public HeroNode postOrderSearch(int no)
    {
        HeroNode resNode = null;
        if (this.left != null)
        {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null)
        {
            return resNode;
        }
        if (this.right != null)
        {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null)
        {
            return resNode;
        }
        if (this.no == no)
        {
            return this;
        }
        return resNode;
    }
}
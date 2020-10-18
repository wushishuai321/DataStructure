package Tree.threadedbinarytree;

public class ThreadedBinaryTreeDemo
{
    public static void main(String[] args)
    {
        //测试中序线索化二叉树
        HeroNode root = new HeroNode(1, "a");
        HeroNode node2 = new HeroNode(3, "b");
        HeroNode node3 = new HeroNode(6, "c");
        HeroNode node4 = new HeroNode(8, "d");
        HeroNode node5 = new HeroNode(10, "e");
        HeroNode node6 = new HeroNode(14, "f");

        node2.setParent(root);
        node3.setParent(root);
        node4.setParent(node2);
        node5.setParent(node2);
        node6.setParent(node3);

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.preOrder();
        //进行中序线索化操作
/*        threadedBinaryTree.threadNodes();
        HeroNode leftnode = node5.getLeft();
        System.out.println("10号节点的前驱结点是："+leftnode);
        System.out.println("10号节点的后继节点是："+node5.getRight());
        //当线索化二叉树后不能够在使用原来的遍历方法
        System.out.println("使用线索化的方式遍历 线索化二叉树");
        threadedBinaryTree.threadeList();*/

        //进行前序线索化操作
/*        threadedBinaryTree.preThreadNodes(root);
        System.out.println(node2.getRight());
        System.out.println();
        threadedBinaryTree.preThreadList();*/

        //进行后序线索化操作
        threadedBinaryTree.postThreadNodes(root);
        System.out.println(node3.getRight());
        threadedBinaryTree.postThreadList();

    }
}

//创建一个二叉树
class ThreadedBinaryTree
{
    private HeroNode root;
    //为了实现线索化需要创建一个指向当前节点的前驱节点的指针
    private HeroNode pre = null; //pre总是在节点的前面

    public void setRoot(HeroNode root)
    {
        this.root = root;
    }

    //重载threadNodes方法
    public void threadNodes()
    {
        this.threadNodes(root);
    }

    //遍历中序线索化二叉树
    public void threadeList()
    {
        //定义一个临时变量来存储当前的节点
        HeroNode node = root;
        while (node != null)
        {
            while (node.getLeftType() == 0)
            {
                node = node.getLeft();
            }
            //打印当前这个节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while (node.getRightType() == 1)
            {
                //获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node = node.getRight();
        }
    }

    //编写一个前序遍历前序线索化二叉树
    public void preThreadList()
    {
        HeroNode node = root;
        while (node != null)
        {
            while (node.getLeftType() == 0)
            {
                System.out.println(node);
                node = node.getLeft();
            }
            while (node.getRightType() == 1)
            {
                System.out.println(node);
                node = node.getRight();
            }
            System.out.println(node);
            node = node.getRight();
        }
    }

    //后序线索二叉树的后序遍历
    public void postThreadList()
    {
        HeroNode node = root;
        HeroNode preNode = null;
        //进行遍历到第一个节点
        while (node != null && node.getLeftType() == 0)
        {
            node = node.getLeft();
        }
        //然后进行判断是不是我们后序遍历的第一个节点
        while (node != null)
        {
            if (node.getRightType() == 1)
            {
                //用于循环一个子树中的遍历,如果返回了一个非叶子节点就会退出
                System.out.print(node);
                preNode = node;
                node = node.getRight();
            } else  //如果到达了非叶子节点就会判断这个节点是不是子树的根节点。
            {
                if (node.getRight() == preNode)
                {
                    System.out.print(node);
                    //特别注意一下这个退出条件，如果没有这个条件否则会发生死循环，而且这个条件不能和下面的语句互换///////
                    //特别重要
                    if (node == root)
                    {
                        return;
                    }
                    preNode = node;
                    node = node.getParent();
                } else
                {
                    node = node.getRight();
                    while (node != null && node.getLeftType() == 0)
                    {
                        node = node.getLeft();
                    }
                }
            }
        }
    }

    //编写对二叉树进行中序线索化的方法
    public void threadNodes(HeroNode node) // node就是当前需要线索化的节点
    {
        if (node == null)//不能线索化
        {
            return;
        }
        //1.先线索化左子树
        threadNodes(node.getLeft());

        //2.线索化当前节点      有点难度

        //先处理当前节点的前驱节点
        if (node.getLeft() == null)
        {
            //让当前节点的左指针，指向前驱节点
            node.setLeft(pre);
            //修改当前节点的做指针类型
            node.setLeftType(1);
        }
        //处理后继节点
        if (pre != null && pre.getRight() == null)
        {
            //让前驱节点的右指针指向当前这个节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        //没处理一个节点后，让当前节点是下一个节点的前驱节点。
        pre = node;

        //3.在线索化右子树
        threadNodes(node.getRight());
    }

    //对二叉树的前序线索化
    public void preThreadNodes(HeroNode node)
    {
        if (node == null)
        {
            return;
        }
        if (node.getLeft() == null)
        {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null)
        {
            //让前驱节点的右指针指向当前这个节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        pre = node;
        if (node.getLeftType() == 0)
        {
            preThreadNodes(node.getLeft());
        }
        if (node.getRightType() == 0)
        {
            preThreadNodes(node.getRight());
        }

    }

    //对二叉树的后序线索化
    public void postThreadNodes(HeroNode node)
    {
        if (node == null)
        {
            return;
        }
        postThreadNodes(node.getLeft());
        postThreadNodes(node.getRight());
        if (node.getLeft() == null)
        {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null)
        {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;

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
            } else
            {
                root.delNode(no);
            }
        } else
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
        } else
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
        } else
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
        } else
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
        } else
        {
            return null;
        }
    }

    //中序查找
    public HeroNode infixOrderSearch(int no)
    {
        if (root != null)
        {
            return root.infixOrderSearch(no);
        } else
        {
            return null;
        }
    }

    //后序遍历
    public HeroNode postOrderSearch(int no)
    {
        if (root != null)
        {
            return root.postOrderSearch(no);
        } else
        {
            return null;
        }
    }
}

//创建一个HeroNode
class HeroNode
{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right; //默认为空
    private HeroNode parent;

    public HeroNode getParent()
    {
        return parent;
    }

    public void setParent(HeroNode parent)
    {
        this.parent = parent;
    }

    private int leftType; // 如果lefttype=0表示指向的是左子树，如果为1则表示指向的是前驱节点

    public int getLeftType()
    {
        return leftType;
    }

    public void setLeftType(int leftType)
    {
        this.leftType = leftType;
    }

    public int getRightType()
    {
        return rightType;
    }

    public void setRightType(int rightType)
    {
        this.rightType = rightType;
    }

    private int rightType; // 同理和上面一样

    public HeroNode(int no, String name)
    {
        this.name = name;
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
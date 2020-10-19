package hufumanTree;

import java.io.*;
import java.util.*;

public class HuffManCode
{
    public static Map<Byte,String> huff;
    public static void main(String[] args)
    {
        String str = "i like like like java do you like a java";
        byte[] strBytes = str.getBytes();
        for (byte i:strBytes)
        {
            System.out.println(i);
        }
        System.out.println(strBytes.length);

/*        List<Node1> nodes = getNodes(strBytes);
        System.out.println(nodes);

        //测试一下创建的二叉树
        Node1 root = creatHuffManTree1(nodes);
        //测试是否生成了对应的哈夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(root);
        System.out.println("生成的哈夫曼编码表"+huffManCodes);
        byte[] by = zip(strBytes,huffmanCodes);
        System.out.println(Arrays.toString(by));
        System.out.println(by.length);*/
        byte[] by = huffmanZip(strBytes);
        System.out.println(Arrays.toString(by));
        byte[] deby = decode(huffManCodes,by);
        System.out.println(new String(deby));

        //测试压缩文件
        String srcFile = "C:\\Users\\asus\\Desktop\\好看的照片\\P1000087.jpg";
        String dstFile = "C:\\Users\\asus\\Desktop\\好看的照片\\2.zip";
        zipFile(srcFile,dstFile);
        System.out.println("压缩文件Ok");
    }

    //使用赫夫曼编码进行文件的压缩
    public static void zipFile(String srcFile,String dstFile)
    {
        FileInputStream ip = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try
        {
            ip = new FileInputStream(srcFile);
            byte[] b = new byte[ip.available()];
            ip.read(b);
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流，存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流相关联的ObjectOutputStrem
            oos = new ObjectOutputStream(os);
            //把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //这里以对象流的方式写入赫夫曼编码，是为了以后回复源文件时使用
            //注意一定要把赫夫曼编码写入压缩文件
            oos.writeObject(huffManCodes);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }finally
        {
            try
            {
                ip.close();
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    //编写一个完成对压缩文件的解压
    public static void unZipFile(String zipFile,String dstFile)
    {

    }

    //使用一个方法将前面的方法封装起来，便于我们的调用
    public static byte[] huffmanZip(byte[] bytes)
    {
        List<Node1> nodes = getNodes(bytes);
        Node1 root = creatHuffManTree1(nodes);
        Map<Byte,String> huffmanCodes = getCodes(root);
        huff = getCodes(root);
        byte[] by = zip(bytes,huffmanCodes);
        return by;
    }

    //编写一个方法，将字符串对应的Byte[] 数组，通过赫夫曼编码表，返回一个赫夫曼编码压缩后的byte[]
    private static String bytetoBitString(byte bytes,boolean flag)
    {
        //使用遍历保存bytes
        int temp =bytes;
        if (flag)
        {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);

        if (flag)
        {
            return str.substring(str.length()-8);
        }else
        {
            return str;
        }
    }
    //编写一个方法完成对压缩数据的解码
    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] bytes)
    {
        //先得到bytes对应的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i =0;i < bytes.length;i++)
        {
            //判断是不是最后一个字节
            boolean flag = (i == bytes.length-1);
            stringBuilder.append(bytetoBitString(bytes[i],!flag));
        }
        System.out.println(stringBuilder.toString());
        //把字符串按照指定的赫夫曼进行解码
        //把赫夫曼编码表进行一个调换
        Map<String,Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte,String > entry : huffmanCodes.entrySet())
        {
            map.put(entry.getValue(),entry.getKey());
        }
        System.out.println(map);
        //创建一个集合
        List<Byte> list = new ArrayList<>();
        for (int i= 0;i <stringBuilder.length();)
        {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag)
            {
                //取出一个‘1’‘0’
                String key = stringBuilder.substring(i,i+count);
                b = map.get(key);
                if (b == null)
                {
                    count++;
                }else
                {
                    flag = false;
                }
            }
            list.add(b);
            i+= count; // 直接移动到count位置
        }
        //当for循环结束后存放了所有的字符
        byte[] b = new byte[list.size()];
        for (int i =0;i <b.length;i++)
        {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     *
     * @param bytes 这是原始的字符串对应的byte[]
     * @param huffManCodes 生成的赫夫曼编码map
     * @return返回赫夫曼编码处理后的byte[]
     */
    private static byte[] zip(byte[] bytes,Map<Byte,String > huffManCodes)
    {
        //1.先使用huffmanCodes，将bytes转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes数组
        for (byte b : bytes)
        {
            stringBuilder.append(huffManCodes.get(b));
        }
        System.out.println("测试"+stringBuilder.toString());
        //1010100010111111110010001011111111001000101111111100100101001101110001110000011011
        //装化成byte数组
        //统计长度
        int len = stringBuilder.length() %8 == 0 ? stringBuilder.length()/8:stringBuilder.length()/8+1;
        //创建一个存储压缩后的byte数组
        byte[] by = new byte[len];
        int index = 0;
        for (int i =0;i<stringBuilder.length();i+=8)
        {
            //因为是每8位对应一个byte所以步长是加8
            String strByte;
            if (i+8>stringBuilder.length())
            {
                strByte = stringBuilder.substring(i);
            }else
            {   strByte = stringBuilder.substring(i,i+8);
            }
            //将得到的strByte转化成byte放入到byte【】
            by[index] = (byte) Integer.parseInt(strByte,2);
            index++;
        }
        return by;

    }


    //赫夫曼编码
    //1.将赫夫曼编码表放在Map<Byte,String>例如
    //2.在生成赫夫曼编码表时要拼接路径,定义一个StringBuilder存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();
    static Map<Byte,String> huffManCodes = new HashMap<Byte, String>();
    //为了调用方便，重载getCodes
    private static Map<Byte,String> getCodes(Node1 root)
    {
        if (root == null)
        {
            return null;
        }
        //处理左子树
        getCodes(root.left,"0",stringBuilder);
        getCodes(root.right,"1",stringBuilder);
        return huffManCodes;
    }
    /**
     * 将传入的node节点的所有叶子节点的赫夫曼编码得到,并且放入到huffmanCodes中
     * @param node 传入节点
     * @param code 路径左子节点是0，右子节点为1。
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node1 node,String code,StringBuilder stringBuilder)
    {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        //将code加入到Stringbuilder1
        stringBuilder1.append(code);
        if (node != null)
        {
            //判断当前的node是叶子节点还是非叶子节点
            if (node.data == null)
            {
                //递归进行处理
                getCodes(node.left,"0",stringBuilder1);
                getCodes(node.right,"1",stringBuilder1);
            }else
            {
                //说明是叶子节点
                //就表示找到了某个叶子节点的最后
                huffManCodes.put(node.data,stringBuilder1.toString());
            }
        }
    }
    /**
     *
     * @param bytes 接受字节数组
     * @return 返回的就是List形式
     */
    private static List<Node1> getNodes(byte[] bytes)
    {
        //先创建一个ArrayList
        ArrayList<Node1> nodes = new ArrayList<>();
        //存储每一个Byte出现的次数,使用map来存储
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes)
        {
            Integer count = counts.get(b);
            if (count == null)
            {
                //说明map里面还没有字符数据
                counts.put(b,1);
            }else
            {
                counts.put(b,count+1);
            }
        }
        //把每一个键值对，装换成一个Node1对象并且加入到Nodes这个集合
        //遍历map
        for (Map.Entry<Byte,Integer> entry:counts.entrySet())
        {
            nodes.add(new Node1(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }
    //可以通过一个List创建一个赫夫曼数
    private static Node1 creatHuffManTree1(List<Node1> nodes)
    {
        while (nodes.size() > 1)
        {
            Collections.sort(nodes);// 排序的从小到大
            Node1 left = nodes.get(0);
            Node1 right = nodes.get(1);
            //创建一个新的二叉树
            //新的根节点只有value没有data
            Node1 parent = new Node1(null,left.weight+right.weight);
            parent.left = left;
            parent.right = right;
            //将处理过的二叉树的子节点删除掉
            nodes.remove(0);  //////
            nodes.remove(0);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}
//创建一个Node,带数据的权值
class Node1 implements Comparable<Node1>
{
    Map<Byte,String> huff;
    Byte data;//存放数据本身。
    int weight;//在字符串中出现的次数
    Node1 left;
    Node1 right;
    public Node1(Byte data, int weight)
    {
        this.data = data;
        this.weight = weight;
    }
    @Override
    public int compareTo(Node1 o)
    {
        //从小到大排序
        return this.weight - o.weight;
    }
    //编写一个toString方法
    @Override
    public String toString()
    {
        return "Node1{" + "data=" + data + ", weight=" + weight + '}';
    }

    //前序遍历
    public void preOrder()
    {
        System.out.println(this);
        if (this.left != null)
        {
            this.left.preOrder();
        }
        if (this.right != null)
        {
            this.right.preOrder();
        }
    }
}
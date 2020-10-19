package hufumanTree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static hufumanTree.HuffManCode.huff;


public class HuffManDecode
{
    public static void main(String[] args)
    {
        byte[] by = {-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28};
        /*decode(huff,by);*/
        Map<Byte,String> huff1  = huff;
        System.out.println(huff1);
    }
    //将一个byte转换成一个二进制的字符串
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
        return null;
    }

}

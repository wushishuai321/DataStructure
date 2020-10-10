package Recursion;

public class Migong
{
    static int count = 0;
    public static void main(String[] args)
    {
        //新创建一个二维数组模拟迷宫;
        int[][] map = new int[8][7];
        //使用1表示墙体
        //上下全部置位1
        for (int i = 0;i<7;i++)
        {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //把左右置位1
        for (int i= 0;i<=7;i++)
        {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //输出数组
        map[3][1] = 1;
        map[3][2] = 1;

        System.out.println("当前地图的情况:");
        print(map);
        //使用递归回溯来找一下路
        setWay(map,1,1);//map是引用类型，map会改变。
        //输出走过的地图：
        System.out.println("路径为：");
        print(map);
    }
    //使用递归来找到路
    //如果小球找到map【6】【5】，就到达终点。
    //约定map【i】【j】为0表示该点没有走
    /**
     *
     * @param map:表示传递进来的迷宫
     * @param i：表示寻找的起点横坐标
     * @param j：表示起点的纵坐标
     * @return 如果找到路了就返回真
     */
    public static boolean setWay(int[][] map,int i,int j)
    {
        if (map[5][4] == 2)
        {
            //说明通路已经找到了，直接return
            return true;
        }else
        {
            if (map[i][j] == 0)
            {
                //如果当前这个点还没有走过
                map[i][j] = 2; // 假定该店能够走通
                if (setWay(map,i+1,j))
                {
                    //向下走
                    return true;
                }else if (setWay(map,i,j+1))
                {
                    return true;
                }else if (setWay(map,i-1,j))
                {
                    return true;
                }else if (setWay(map,i,j-1))
                {
                    return true;
                }else
                {
                    //说明该店是走不通的是死路；
                    map[i][j] = 3;
                    return false;
                }

            }else
            {
                //如果当前map【i】【j】不等于0，可能是1，2，3
                return false;
            }
        }
    }
    public static void print(int[][] map)
    {for (int i =0;i<map.length;i++)
    {
        for (int j =0;j<map[i].length;j++)
        {
            System.out.print(map[i][j]+" ");
        }
        System.out.println();
    }}
}

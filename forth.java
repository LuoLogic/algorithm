import java.util.Arrays;

/**
 * forth
 */
public class forth {

    public static void main(String[] args) {
 
        //第0位初始化为0，方便后续计算mostvalue表中值
        int[] weights = {0, 2, 2, 6, 5, 4 };// 重量数组
        int[] values = { 0,6, 3, 5, 4, 6 };// 价值数组

        int bagCapacity = 10;// 背包容量

        // mostVlue[i][j]表示在面对第i件物品，且背包容量为j时所能获得的最大价值，初始化 为0
        int[][] mostValue = new int[weights.length][bagCapacity+1];
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < (bagCapacity+1); j++) {
                mostValue[i][j] = 0;
            }
        }

        

        // 递归算法测试
        // int cur = 0;
        // int result = DynamicBag_rec(weights, values, cur, bagCapacity);
        // System.out.println(result);

        // 循环算法测试
        DynamicBag_loop(weights, values, mostValue, bagCapacity);

        int []ispick=searchPick(mostValue, bagCapacity, weights);
        System.out.println("最大值:"+mostValue[weights.length-1][bagCapacity]);
        System.out.println(Arrays.toString(ispick));

    }

    // // 递归算法
    // public static int DynamicBag_rec(int[] weights, int[] values, int cur, int bagCapacity) {
    //     int n = weights.length;
    //     // cur指向当前的物品数组中序号
    //     if (cur == n) {
    //         return 0;// cur越界，没有东西可拿
    //     }

    //     //
    //     int v1 = DynamicBag_rec(weights, values, cur + 1, bagCapacity);
    //     // 剪枝
    //     if (bagCapacity >= weights[cur]) {
    //         int v2 = values[cur] + DynamicBag_rec(weights, values, cur + 1, bagCapacity - weights[cur]);
    //         return Math.max(v1, v2);
    //     } else {
    //         // 当遇到比较大的质量的物品导致不能装的时候直接返回当前的v1
    //         return v1;
    //     }
    // }

    // 循环
    public static int DynamicBag_loop(int[] weights, int[] values, int[][] mostVlue, int bagCapacity) {
        int n = weights.length-1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <=bagCapacity ; j++) {//内层循环表示当面对物品i时，j表示背包容量从1到bagCapacity时mostValue[i][j]所能取得的最大值
                if(j>=weights[i]){//表示当前背包容量为j时可以放入物品i

                    /*
                    （1）. j < weights[i] 的情况，这时候背包容量不足以放下第 i 件物品，只能选择不拿

                    mostValue[ i ][ j ] = mostValue[ i-1 ][ j ]

                    （2）. j>=weights[i] 的情况，这时背包容量可以放下第 i 件物品，我们就要考虑拿这件物品是否能获取更大的价值。

                    如果拿取，mostValue[ i ][ j ]=mostValue[ i-1 ][ j-weights[ i ] ] + v[ i ]。 这里的mostValue[ i-1 ][ j-w[ i ] ]指的就是考虑了i-1件物品，背包容量为j-w[i]时的最大价值，也是相当于为第i件物品腾出了w[i]的空间。

                        如果不拿，mostValue[ i ][ j ] = mostValue[ i-1 ][ j ] , 同（1）

                        究竟是拿还是不拿，自然是比较这两种情况那种价值最大
                    */
                        mostVlue[i][j]=Math.max(mostVlue[i-1][j], mostVlue[i-1][j-weights[i]]+values[i]);


                }else{
                    mostVlue[i][j]=mostVlue[i-1][j];
                }
                }
            }

            for (int i = 0; i <=bagCapacity; i++) {
                System.out.print("|"+i+"|"+"\t");
            }
            System.out.println("\n");

            for (int[] is : mostVlue) {
                for (int is2 : is) {
                    System.out.print(is2+"\t");
                }  

                System.out.println("\n");
            }

            return 0;

        }

        //显示哪些物品被选中了1表示选中 0表示没有 
        public static int[] searchPick(int[][] mostValue,int bagCapacity,int[] weights){
            //定义ispick数组，长度为物品个数
            int[] ispick=new int[mostValue.length];
            //定义物品个数为n
            //mostValue[n][bagCapacity]为最优值，如果mostValue[n][bagCapacity]和mostValue[n-1][bagCapacity]
            //一样，说明有没有第n件物品都一样，说明该物品没有被放入
            for(int i=(mostValue.length-1);i>1;i--){
                if(mostValue[i][bagCapacity]==mostValue[i-1][bagCapacity]){
                    ispick[i]=0;
                }else{
                    ispick[i]=1;
                    bagCapacity-=weights[i];//选中后减去选中物品weight值
                }
            }

            ispick[1]=(mostValue[1][bagCapacity]>0)?1:0;

            return ispick;
        }

    }
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * third
 */
public class third {

    public static void main(String[] args) {
        // int[] values={25,24,15};
        // int[] weights={18,15,10};
        int[] values={10,40,30,50,35,40,30};//价值数组
        int[] weights={35,30,60,50,40,10,25};//重量数组
        float bagWeight=150;//背包最大容量

        System.out.println("序号"+"\t"+"价值"+"\t"+"重量"); 
        for (int i = 0; i < weights.length; i++) {
            System.out.println(i+"\t"+values[i]+"\t"+weights[i]);
        }

        //传入函数返回 结果数组
        float result[]=bag(values, weights,bagWeight);
        System.out.println("结果:"+Arrays.toString(result));

    }

    public static float[] bag(int[] values,int[] weights,float bagWeight){
        //定义结果数组,并初始化为-1
        float[] result=new float[values.length];
        for (int i = 0; i < result.length; i++) {
           result[i]=-1.0f; 
        }
        // DecimalFormat df=new DecimalFormat("0.00");

        //定义每重量价值数组
        float valuesPerWeight[]=new float [values.length];

        //算出每个物品单位重量价值
        for (int i = 0; i < valuesPerWeight.length; i++) {
            valuesPerWeight[i]=(float)values[i]/weights[i];
            // System.out.println(valuesPerWeight[i]);
        }

        //将该物品序号和该物品的单位重量价值以键值对的形式存入HashMap
        HashMap<Integer,Float> item=new HashMap<>();
        for (int i = 0; i < valuesPerWeight.length; i++) {
            item.put(i,valuesPerWeight[i]);
        }

        // Set<Map.Entry<Integer,Float>> entrySet=item.entrySet();
        //将map中键值对集合存入List
        List<Map.Entry<Integer,Float>> list=new ArrayList<>(item.entrySet());

        //实现接口比较函数从高到低排序
        Collections.sort(list,new Comparator<Map.Entry<Integer,Float>>() {

			@Override
			public int compare(java.util.Map.Entry<Integer, Float> o1, java.util.Map.Entry<Integer, Float> o2) {
                // TODO Auto-generated method stub
                
                float rs=o2.getValue()-o1.getValue();
                if(rs>0){
                    return 1;
                }else if(rs<0){
                    return -1;
                }else{
                    return 0;
                }


				// return o1.getValue().compareTo(o2.getValue());//默认从小到大排序
			}
        });


        //定义最后装入背包物品的数量,初始化为-1，小于等于1
        float finalfloat=-1;
        //最后装入背包物品的序号由先向后存入ArrayList
        ArrayList<Integer> array =new ArrayList<Integer>();
        //遍历list,从单位价值最高的物品开始装入背包
        for (Map.Entry<Integer,Float> s: list) {
            // System.out.println(s.getKey()+"----"+s.getValue());
            //如果物品重量小于当前背包重量，直接装入并把当前物品序号存入array
            if(weights[s.getKey()]<=bagWeight){
                //装入一个物品后减去该物品重量得到剩余物品重量
                bagWeight-=weights[s.getKey()];
                array.add(s.getKey());
            }else{
                //如果当前物品重量大于剩余物品重量则只装入一部分填满背包,并得出最后装入的物品的比例
                finalfloat=bagWeight/weights[s.getKey()];
                // System.out.println("final:"+finalfloat);
                //最后bagweight等于0
                bagWeight-=(finalfloat*weights[s.getKey()]);
                array.add(s.getKey());
                break;
            }
            
            // System.out.println(bagWeight+"___");

        }


        System.out.println("finalBagWeight:"+bagWeight);
        System.out.println("finalfloat:"+finalfloat);

        //将Array中的序号和result进行匹配，如果相同则表示该物品装入背包，
        for (int j = 0; j < result.length; j++) {
            for (int i = 0; i < array.size(); i++) {

                //如果是最后一个装入背包的物品，result数组的值为finalfloat
                //否则表示该物品已经完全装入，计为1.0
                if (array.get(array.size()-1)==j) {
                    result[j]=finalfloat;
                    continue;
                }

                if (array.get(i)==j) {
                    result[j]=1.0f;
                }
            }

            //result数组对应序号遍历后仍然是初始值-1,则表示没有装入背包，计为0.0
            if (result[j]==-1.0f) {
                result[j]=0.0f;
            }
            
        }

        return result;
    }


}
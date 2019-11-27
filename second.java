import java.util.Arrays;
import java.util.Random;

import javax.lang.model.util.ElementScanner6;
import javax.sound.sampled.SourceDataLine;

/**
 * second
 */
public class second {

    public static void main(String[] args) {

        final int n=11;

        //生成随机数组
        int[] number=generateRandomNumber(n);
        //打印生成的随机数组
        System.out.println(Arrays.toString(number));

        int k=n/2;//k=n/2表示第k小的数
        System.out.println("n/2："+k);
        //定义rs为第k小的数
        //k-1是因为数组从0开始，第K小的数字即是数组中第k-1个数字
        int rs=searchN(number, 0, number.length-1,k-1);
        System.out.println("第K小结果是:"+rs);

        Arrays.sort(number);
        System.out.println("排序后数组："+Arrays.toString(number));
        
        
    }

    //产生n个100以内的随机数的数组
    public static int[] generateRandomNumber(int n){
        int[] randomN=new int[n];

        Random r=new Random();
        for (int i = 0; i < randomN.length; i++) {
            randomN[i]=r.nextInt(100);
        }

        return randomN;
    }

    //分块函数，以数组中array[index]为界限将数组中小于该数的放在左边，
    public static int partition(int[] array,int left,int right,int index){
        //将pivot用于保存array[index],方便后续比较
        int pivot=array[index];

        //将array[index]交换到当前数组的right边界位置，方便后续循环比较
        swap(array, index, right);

        //存储当前数组left边界序号，方便后续循环比较时交换位置
        int store_index=left;

        //循环比较，比array[index]小的数字从strore_index(left)开始位置摆放
        for (int i = left; i <=right; i++) {
            if(array[i]<pivot){
                swap(array, store_index, i);
                //每次交换完成，store_index++,最后一次交换完成是，将array[index]交换至最后一个小于array[index]数字的后面
                store_index++;
            } 
        }
        //将right位置上的array[index]放到中间位置，此时左边都是小于该数的，等于该数的在右边
        swap(array,store_index, right);
        //将该位置返回
        return store_index;

    }

    public static int searchN(int number[],int left,int right,int k){
        //如果此时分割后数组中只有left和right边界重合，则只有一个数字，返回该数字即为结果
        if(left==right){
            return number[left];
        }

        //在left和right-left区间中随机取一个index当array[index]来分块
        Random random_num=new Random();
        int pivot_index=left+random_num.nextInt(right-left);

        //获取分块后的中枢pivot的index值，此时数组左边的数都小于该数
        pivot_index=partition(number, left, right,pivot_index);

        //如果k恰好等于pivot_index，即pivot_index上的数就是第k小数字
        if(k==pivot_index){
            return number[k];//返回该数字
        }else if(k<pivot_index){//否则若k<pivot_index表示第k小数字在分块后的左边，继续递归，此时right边界即为pivot_index-1
            return searchN(number, left,pivot_index-1, k);
        }else{//否则若k>pivot_index表示第k小数字在分块后的右边，继续递归，此时left边界即为pivot_index+1
            return searchN(number,pivot_index+1 , right, k);
        }

    }

    public static void swap(int array[],int i,int j){
        int temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }
}
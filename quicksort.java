/**
 * quicksort
 */
import java.util.Arrays;
public class quicksort {

    public static void main(String[] args) {
        int []array={9,3,5,7,2,6,8,4};

        int left=0;
        int right=array.length-1;
        qs(array,left,right);
        System.out.println(Arrays.toString(array));
    }

    public static void qs(int[] array,int left,int right){

        if(left>right){
            return;
        }

        int index=partition(array,left,right);
        qs(array, left, index-1);
        qs(array, index+1, right);
    }

    public static int partition(int[] array,int begin,int end){
        int pivotIndex=begin;
        int pivot=array[pivotIndex];

        swap(array, pivotIndex, end);
        int low=begin;
        int high=end;

        while(low<high){
            //因为把pivot放在最后，所以low指针先走
            while(low<high&&array[low]<=pivot) low++;
            while(low<high&&array[high]>=pivot) high--;
            if(low<high) swap(array, low, high);
            
        }

        swap(array, low, end);
        return low;

    }

    public static void swap(int array[],int i,int j){
        int temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }


}
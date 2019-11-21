/**
 * first
 */
public class first {
    public static void main(String[] args) {

        int rs=PerfectN(8128,0,1);
        System.out.println(rs);
        
    }

    public static int PerfectN(int n,int sum,int i){
        //输入数字超过10000返回-1提示错误
        if(n>10000){
            return -1;
        }

        //每次递归循环数i+1；当i>=n且sum值不等于n时，返回0
        if(i>=n&&sum!=n){
            return 0;
        }


        //如果sum==n,则判断该数是完数，返回1
        if(sum==n){
            return 1;
        }


        int rs=-2;
        //判断i是否可以被n整除
        if(n%i==0){
            //可以整除则将sum加上i
            sum+=i;
            //i自增1进入下一次递归
            i++;
            //令rs接受下层递归返回值
            rs=PerfectN(n, sum, i);
        }

        //如果下层返回的rs==1,则该数n为完数，向上返回1
        if(rs==1){
            return 1;
        }
        
        //大酒店附近
        i++;
        rs=PerfectN(n, sum, i);
        
        return rs;
    }
}
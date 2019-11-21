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


        if(sum==n){
            return 1;
        }

        int rs=-2;
        if(n%i==0){
            sum+=i;
            i++;
            rs=PerfectN(n, sum, i);
        }

        if(rs==1){
            return 1;
        }
        
        i++;
        rs=PerfectN(n, sum, i);
        
        return rs;
    }
}
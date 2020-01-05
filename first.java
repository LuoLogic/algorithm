import java.util.Scanner;

/**
 * first
 */
public class first {
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入待判定的数:");
        int InputNmuber=scanner.nextInt();

        int rs=PerfectN(InputNmuber,0,1);

        switch (rs) {
            case -1:
                System.out.println("输入数字超过10000,错误");
                break;
        
            case 0:
                System.out.println("该数不是完数！");
                break;

            case 1:
                System.out.println("该数是完数！");
                break;
            default:
                break;
        }

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
        }

        //进入下一次递归
        i++;
        rs=PerfectN(n, sum, i);
        
        //最后返回rs或者向上层返回rs结果
        return rs;
    }
}
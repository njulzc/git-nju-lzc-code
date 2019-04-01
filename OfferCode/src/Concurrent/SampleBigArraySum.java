package Concurrent;

import java.math.BigInteger;
import java.util.Random;

public class SampleBigArraySum {
    public static int [] nums=new int[200000000];
    public static void main(String []args){

       long startTime=System.currentTimeMillis();
        //Random random = new Random();
        for(int i=0;i<nums.length;i++){
            //nums[i]= random.nextInt(10000000)+10000000;
            nums[i]=i;
        }
        int[] ThreadSum=new int[5];//5*9 45位
        for(int i=0;i<nums.length;i++){
            int carry;int curnum=nums[i];int j=0;
            do{
                carry=(ThreadSum[j]+curnum)/1000000000;
                ThreadSum[j]=(ThreadSum[j]+curnum)%1000000000;
                j++;
                curnum=carry;
            } while(carry!=0);
        }
        StringBuffer sb=new StringBuffer();
        for(int i=4;i>=0;i--){
            System.out.println(ThreadSum[i]);
        }
        for(int i=4;i>=0;i--){
            if(ThreadSum[i]==0)
                continue;
            sb.append(ThreadSum[i]);
            for(int j=i-1;j>=0;j--){
                if(ThreadSum[j]==0){
                    sb.append("000000000");
                }else{
                    int level=100000000;
                    while(ThreadSum[j]<level){
                        sb.append(0);
                        level=level/10;
                    }
                    sb.append(ThreadSum[j]);
                }
            }
            break;
        }
        System.out.println(sb.toString());
        BigInteger bigInteger=new BigInteger(sb.toString());
        System.out.println(bigInteger);
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    }
}

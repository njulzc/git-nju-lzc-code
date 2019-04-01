package Concurrent;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoin {
    public static int [] nums=new int[200000000];
    public static void main(String []args) throws Exception{
        //Random random = new Random();
        long startTime=System.currentTimeMillis();
        for(int i=0;i<nums.length;i++){
            // nums[i]= random.nextInt(10000000)+10000000;
            nums[i]=i;
        }
        ForkJoinTask forkJoinTask=new ForkJoinTask(0,199999999);
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        Future<BigInteger> result=forkJoinPool.submit(forkJoinTask);
        System.out.println(result.get());
        long endTime=System.currentTimeMillis();
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    }

      public static class ForkJoinTask extends RecursiveTask<BigInteger>{
        private final int threshold = 1000000;
        private int start;
        private int end;
        public ForkJoinTask(int start,int end){
            this.start=start;
            this.end=end;
        }
        @Override
        protected BigInteger compute(){
            BigInteger bigInteger;
            int[] ThreadSum=new int[5];//5*9 45位
            if(end-start<=threshold){
                for(int i=start;i<=end;i++){
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
                System.out.println(Thread.currentThread().getName()+": "+sb.toString());
                bigInteger=new BigInteger(sb.toString());
            }else{
                int mid=(start+end)>>1;
                ForkJoinTask leftTask=new ForkJoinTask(start,mid);
                ForkJoinTask rightTask=new ForkJoinTask(mid+1,end);
                leftTask.fork();
                rightTask.fork();
                bigInteger=leftTask.join().add(rightTask.join());
            }
            return bigInteger;
        }
    }
}

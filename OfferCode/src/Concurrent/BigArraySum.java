package Concurrent;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
实现海量数据的累加求和，考虑了大数问题，使用BigInteger，使用线程池实现并发
*/

public class BigArraySum {

    public static int [] nums=new int[200000000];
    public static volatile BigInteger res=new BigInteger("0");
    public static int TaskCount=20;
    public static CountDownLatch countDownLatch=new CountDownLatch(TaskCount);

    public static void main(String []args)throws InterruptedException{
        long startTime=System.currentTimeMillis();
        Random random = new Random();
        for(int i=0;i<nums.length;i++){
           // nums[i]= random.nextInt(10000000)+10000000;
            nums[i]=i;
        }
        System.out.println("数据准备完毕");
        CreateThreadPool createThreadPool=new CreateThreadPool();
        createThreadPool.CreateThead();
        countDownLatch.await();
        long endTime=System.currentTimeMillis(); //获取结束时间
        createThreadPool.shutdown();
        mathsum();

        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    }

    private static void mathsum(){
        System.out.println(res);

    }

    private static class CreateThreadPool{
        ThreadPoolExecutor pools=new ThreadPoolExecutor(4,4,60, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
        void CreateThead(){
            for(int i=0;i<TaskCount;i++){
                //System.out.println("创建中: "+i);
                MyTask task=new MyTask(i,i+1,countDownLatch);
                pools.submit(task);
            }
        }
        void shutdown(){
            pools.shutdown();
        }
    }

    static class MyTask implements Runnable{
        private CountDownLatch latch ;
        private BigInteger bigInteger;
        private int start;
        private int end;
        private int[] ThreadSum=new int[5];//5*9 45位
        public MyTask(int start,int end,CountDownLatch latch){
            this.start=start;
            this.end=end;
            this.latch=latch;
        }
        @Override
        public void run() {
            for(int i=start*(nums.length/TaskCount);i<end*(nums.length/TaskCount);i++){
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
            synchronized (res){
                res=res.add(new BigInteger(sb.toString()));
            }

            latch.countDown();
        }

    }
}


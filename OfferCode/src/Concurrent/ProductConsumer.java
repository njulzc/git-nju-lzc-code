package Concurrent;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/*
使用线程池+阻塞队列实现生产者消费者模式
 */
public class ProductConsumer {
    public static void main(String args[])throws Exception{
        LinkedBlockingQueue<product> linkedBlockingQueue=new LinkedBlockingQueue<>(5);

        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(10,10,60, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(20));
        //ThreadPoolExecutor threadPoolExecutor2=new ThreadPoolExecutor(4,4,60, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(20));
        for(int i=0;i<5;i++){
            threadPoolExecutor.submit(new ProductThread(linkedBlockingQueue));
            threadPoolExecutor.submit(new ConsumerThread(linkedBlockingQueue));
        }

        Thread.sleep(5000);
        stop();
        threadPoolExecutor.shutdownNow();

    }
    private  static class product{
        private String data;
        private int id;
        public  product(String data,int id){
            this.data=data;
            this.id=id;
        }
    }
    static volatile boolean isRunning=true;
    public static void stop() {
        isRunning = false;
    }
    private static class ProductThread implements Runnable{
        private BlockingQueue<product> blockingQueue;

        private static AtomicInteger count=new AtomicInteger();
        public ProductThread(BlockingQueue<product> blockingQueue){
            this.blockingQueue=blockingQueue;
        }
        @Override
        public void run() {
            product data=null;
            Random r=new Random();
            System.out.println("start producting id:"+Thread.currentThread().getId());
            try{
                while(isRunning){
                    Thread.sleep(r.nextInt(100));
                    data=new product(Thread.currentThread().getName(),count.getAndIncrement());

                    if(blockingQueue.offer(data)){
                        System.out.println("生产:"+data.id+" size: "+blockingQueue.size());
                    }else{
                        System.out.println("入队失败。。"+" size: "+blockingQueue.size());
                    }

                }
            }catch(InterruptedException e){

            }
        }

    }
    private static class ConsumerThread implements Runnable{
        private  BlockingQueue<product> blockingQueue;
        public ConsumerThread(BlockingQueue<product> blockingQueue){
            this.blockingQueue=blockingQueue;
        }
        @Override
        public void run() {
            System.out.println("start consuming id:"+Thread.currentThread().getId());
            Random r=new Random();
            try{
                while(true){
                    product data=blockingQueue.take();
                    if(data!=null){
                        System.out.println("消费："+data.id+" size: "+blockingQueue.size());
                        Thread.sleep(r.nextInt(200));
                    }else{
                        System.out.println("等待。。"+" size: "+blockingQueue.size());
                    }
                }
            }catch(InterruptedException e){

            }
        }

    }
}

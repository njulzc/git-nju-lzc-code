/**
 * 观察者模式：
 * myThread线程通知workThread，workThread再去通知具体处理线程
 */
package NIO;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class NIOServer {
    public static WorkThread[] workThreads=new WorkThread[3];
    public static void  main(String []args) throws InterruptedException{
        myThread myThread=new myThread(8080);
        myThread.start();
        for(int i=0;i<workThreads.length;i++){
            workThreads[i]=new WorkThread();
            workThreads[i].start();
        }

        //main线程等待mythread线程执行结束后再关闭
        myThread.join();
    }
}
/*
接受新连接*/
class myThread extends Thread{
    private ServerSocketChannel server;
    private Selector selector;
    public myThread(int port){
        try {
            server=ServerSocketChannel.open();
            //手动设置为NIO
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));//绑定端口
            System.out.println("NIO Start: "+port);
            //selector 实现一个贤臣给处理多个连接的关键类
            //从操作系统底层选取我们需要处理的连接（时间通知）
            selector=Selector.open();
            //如果有新连接就通知selector通知我
            server.register(selector, SelectionKey.OP_ACCEPT);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try{
            //服务端口不停止，需要不断获取新连接
            while(true){
                int count=selector.select(1000L);//连接数;如果1秒内有链接，就立刻返回
                System.out.println("获取中");
                if(count==0){
                    continue;
                }
                //符合条件的通知
                Set<SelectionKey> selectionKeys=selector.selectedKeys();
                Iterator<SelectionKey> iterator=selectionKeys.iterator();
                while(iterator.hasNext()){
                    SelectionKey next=iterator.next();
                    if(next.isAcceptable()){  //是不是新连接
                        //拿到新连接,此时，仅代表这个链接建立了；不一定有数据
                        SocketChannel clientSocketChannel=server.accept();
                        //显示的设置非阻塞
                        clientSocketChannel.configureBlocking(false);
                        //选择一个线程处理
                        int random=new Random().nextInt(NIOServer.workThreads.length);
                        NIOServer.workThreads[random].processSocket(clientSocketChannel);
                        System.out.println(this.getName()+":有新连接");
                    }
                }
                //清楚已经处理过的通知
                selectionKeys.clear();
                selector.selectNow();
            }
        }catch(Exception e){

        }
    }
}
class  WorkThread extends Thread{
    private Selector selector;
    //初始化一个selector
    public WorkThread(){
        try{
            selector=Selector.open();
        }catch(Exception e){
        }
    }
    //socketChannel不代表有数据
    public void processSocket (SocketChannel socketChannel) throws Exception{
        //继续交给selector，有数据就通知
        socketChannel.register(selector,SelectionKey.OP_READ);
    }
    @Override
    public void run() {
        try{
            //服务端口不停止，需要不断获取新连接
            while(true){
                int count=selector.select(1000L);//连接数;如果1秒内有链接，就立刻返回
                System.out.println("获取中");
                if(count==0){
                    continue;
                }
                //符合条件的通知
                Set<SelectionKey> selectionKeys=selector.selectedKeys();
                Iterator<SelectionKey> iterator=selectionKeys.iterator();
                while(iterator.hasNext()){
                    SelectionKey next=iterator.next();
                    if(next.isReadable()){  //是不是新连接
                        //取出客户端连接
                        SocketChannel clientSocketChannel=(SocketChannel)next.channel();
                        //显示的设置非阻塞
                        clientSocketChannel.configureBlocking(false);
                        {
                            //创建缓冲区
                            ByteBuffer requestBuffer= ByteBuffer.allocate(1024);
                            //读取请求内容
                            clientSocketChannel.read(requestBuffer);
                            //这是对业务的数据处理，通常单独创建业务线程池。
                            String request=new String(requestBuffer.array());
                            System.out.println(this.getName()+"收到请求内容： "+request);
                        }
                    }
                }
                //清楚已经处理过的通知
                selectionKeys.clear();
                selector.selectNow();
            }
        }catch(Exception e){

        }
    }
}
import java.io.*;
import java.net.*;
import java.util.*;

public class TCPServer {
    boolean started = false;
    ServerSocket ss = null;

    public static volatile List<Client> clients = new ArrayList<Client>();
    public static volatile int id=1;
    public static void main(String[] args) {
        new TCPServer().start();
    }

    public void start() {
        try {
            ss = new ServerSocket(8888);
            started = true;
        } catch (BindException e) {
            System.out.println("端口使用中....");
            System.out.println("请关掉相关程序并重新运行服务器！");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            while (started) {
                Socket s = ss.accept();
                Client c = new Client(s);
                System.out.println("客户端: "+id+" "+ s.getInetAddress().getLocalHost()+ "已连接到服务器");
                new Thread(c,"clients:"+id++).start();
                clients.add(c);
                //dis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ss.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    class Client implements Runnable {
        private Socket s;
        private DataInputStream dis = null;
        private DataOutputStream dos = null;
        private boolean bConnected = false;
        private  String ipName=null;
        public Client(Socket s) {
            this.s = s;
            try {
                dis = new DataInputStream(s.getInputStream());
                dos = new DataOutputStream(s.getOutputStream());
                while(ipName==null)
                    ipName=dis.readUTF();
                bConnected = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void send(String str) {
            try {
                dos.writeUTF(str);
            } catch (IOException e) {
                clients.remove(this);
                System.out.println("对方退出了！我从List里面去掉了！");
                //e.printStackTrace();
            }
        }

        public void run() {
            String str;
            try {
                str = "======== 欢迎【"+ipName  +"（id"+id+"）】进入聊天室！聊天室当前在线人数【"
                        + clients.size() + "】========";
                for (int i = 0; i < clients.size(); i++) {
                    Client c = clients.get(i);
                    c.send(str);
                }
                while (bConnected) {
                    str= dis.readUTF();
                    System.out.println(Thread.currentThread().getName()+": "+str);
                    for (int i = 0; i < clients.size(); i++) {
                        Client c = clients.get(i);

                        if(c.s==s){
                            c.send("                                                              "
                                    +"                                  "
                                    + Thread.currentThread().getName()+": "+str);
                        } else
                            c.send(Thread.currentThread().getName()+": "+str);
                    }
                }
            } catch(EOFException e){
                    System.out.println(Thread.currentThread().getName()+": "+"Client closed!");
                    clients.remove(this);
                        for (int i = 0; i < clients.size(); i++) {
                            Client c = clients.get(i);
                            c.send("========系统提示："+ipName  +"(id"+id+")】退出了聊天室！聊天室当前在线人数【"
                                    + clients.size() + "】=========");
                        }
            } catch(IOException e){
                    e.printStackTrace();
            } finally{
                    try {
                        if (dis != null) dis.close();
                        if (dos != null) dos.close();
                        if (s != null) {
                            s.close();
                            //s = null;
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
            }
        }

    }
}
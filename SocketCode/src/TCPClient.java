import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.regex.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class TCPClient extends Frame {
    Socket s = null;
    DataOutputStream dos = null;
    DataInputStream dis = null;
    private boolean bConnected = false;

    TextField tfTxt = new TextField();

    TextArea taContent = new TextArea();

    Thread tRecv = new Thread(new RecvThread());

    public static String IP;
    public static String  idname;

    public TCPClient(String IP,String idname){
        this.IP=IP; this.idname=idname;
        launchFrame();
    }


    public void launchFrame() {
        setLocation(400, 300);
        this.setSize(300, 300);
        menu();
        add(tfTxt, BorderLayout.SOUTH);
        add(taContent, BorderLayout.NORTH);
        menu();
        pack();
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent arg0) {
                disconnect();
                System.exit(0);
            }

        });
        tfTxt.addActionListener(new TFListener());
        setVisible(true);
        connect(IP,idname);

        tRecv.start();
    }

    public void connect(String ip,String idName) {
        try {
            s = new Socket(ip, 8888);
            dos = new DataOutputStream(s.getOutputStream());
            dis = new DataInputStream(s.getInputStream());
            dos.writeUTF(idName);
            System.exit(0);
            bConnected = true;
        } catch (UnknownHostException e) {
            System.out.println("查询不到当前ip端口");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void disconnect() {
        try {
            dos.close();
            dis.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class TFListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String str = tfTxt.getText().trim();
            //taContent.setText(str);
            tfTxt.setText("");

            try {
//System.out.println(s);
                dos.writeUTF(str);
                dos.flush();
                //dos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }

    private class RecvThread implements Runnable {

        public void run() {
            try {
                while(bConnected) {
                    String str = dis.readUTF();
                    //System.out.println(str);
                    taContent.setText(taContent.getText() + str + '\n');
                }
            } catch (SocketException e) {
                System.out.println("退出了，bye!");
            } catch (EOFException e) {
                System.out.println("退出了，bye - bye!");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
    public void menu(){
        MenuBar mb = new MenuBar();
        Menu mFile = new Menu("msg");
        MenuItem miFileSend = new MenuItem("sendmsg");
        mFile.add(miFileSend);
        mb.add(mFile);
        setMenuBar(mb);
        //miFileSend.addActionListener();
    }

}

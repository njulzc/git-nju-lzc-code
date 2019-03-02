import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class TCPClientLogin extends Frame {
    TextField tfTxt = new TextField();
    TextField tfTxtidname = new TextField();
    public void launchFrame(){

        setLocation(400, 300);
        this.setSize(150, 100);
        add(tfTxt,BorderLayout.NORTH);
        add(tfTxtidname,BorderLayout.SOUTH);
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent arg0) {

                System.exit(0);
            }

        });
        tfTxtidname.addActionListener(new TCPClientLogin.TFListener());
        setVisible(true);
    }
    private class TFListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String strIP = tfTxt.getText().trim();
            String strIdname = tfTxtidname.getText().trim();
            //taContent.setText(str);
            tfTxt.setText("");
            tfTxtidname.setText("");
            new TCPClient(strIP,strIdname);
            try{
                Thread.sleep(1000);
                System.exit(0);
            }catch(InterruptedException E){
                E.printStackTrace();
            }
        }

    }
    public static void  main(String []args){
        new TCPClientLogin().launchFrame();
    }
}

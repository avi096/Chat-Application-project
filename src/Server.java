import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;


public class Server extends JFrame implements ActionListener {

     static ServerSocket serv;
     static Socket sckt;
     static DataInputStream din;
     static DataOutputStream dout;

      JPanel pan;
      JTextField te;
      JButton bt;
     static JTextArea ar;
   public Server()
    {    pan =new JPanel();
    pan.setLayout(null);
    pan.setBackground(new Color(8,99,90));
     pan.setBounds(0,0,500,40);
     add(pan);

        ImageIcon I1=new ImageIcon(ClassLoader.getSystemResource("icon/1.png"));
        Image I2= I1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);   //change size of image
        ImageIcon I3=new ImageIcon(I2);
        JLabel l1=new JLabel(I3);
        l1.setBounds(10,5,30,30);
        pan.add(l1);              //add label in frame

        l1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });

        ImageIcon I4=new ImageIcon(ClassLoader.getSystemResource("icon/3.jpg"));
        Image I5=I4.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon I6=new ImageIcon(I5);
        JLabel l2=new JLabel(I6);
        l2.setBounds(450,5,30,30);
         pan.add(l2);

        JLabel l3=new JLabel("SERVER");
        l3.setFont(new Font("MONOSPACED",Font.BOLD,20));
        l3.setBounds(55,-2,120,40);
         l3.setForeground(Color.WHITE);
         pan.add(l3);

        setLayout(null);   //frame layout null
       getContentPane().setBackground(Color.WHITE);   //frame background colour

      te=new JTextField();
      te.setBounds(3,562,403,35);
      te.setFont(new Font(Font.DIALOG,Font.PLAIN,15));
      add(te);

      ar=new JTextArea();
      //ar.setBounds(4,45,490,510);
      ar.setFont(new Font(Font.MONOSPACED,Font.PLAIN,15));
      //ar.setBackground(Color.BLUE);
        ar.setEditable(false);
        ar.setLineWrap(true);
        ar.setWrapStyleWord(true);
      //add(ar);
        JScrollPane sbar=new JScrollPane(ar);
        sbar.setBounds(4,45,490,510);
        sbar.setBorder(BorderFactory.createEmptyBorder());
        add(sbar);

      bt= new JButton("SEND");
      bt.setBounds(410,562,85,35);
      bt.setBackground(new Color(8,99,90));
      bt.setForeground(Color.WHITE);
      bt.setFont(new Font(Font.MONOSPACED,Font.BOLD,15));
      bt.addActionListener(this);
      add(bt);

     setSize(500,600);
     setLocation(250,250);
     setUndecorated(true); //hide the header
      setVisible(true);
    }


  public void actionPerformed(ActionEvent ae)
  {  try {


          String out = te.getText();
          ar.setText(ar.getText() + "\nMe : " + out);
          dout.writeUTF(out);
          dout.flush();
          te.setText("");
  }catch(Exception e){
           System.out.println(e);
  }

  }
    public static void main(String[] args){

     new Server().setVisible(true);


 try{
      serv=new ServerSocket(1200);
      sckt= serv.accept();
      while(!sckt.isClosed()) {
          din = new DataInputStream(sckt.getInputStream());
          dout = new DataOutputStream(sckt.getOutputStream());
          String msg = "";
          msg = din.readUTF();
          ar.setText(ar.getText() + "\nClient : " + msg);
      }

       sckt.close();
      serv.close();
 }catch(Exception e){
   System.out.println(e);
 }

    }

}
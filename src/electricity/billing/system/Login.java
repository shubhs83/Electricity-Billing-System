package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    
     JButton  login,signup,cancel;
     JTextField username,password;
     Choice loginin;
    Login(){
        super("Login Page");                                  //Frame Heading(super must be the first statment inside the constructor)
        getContentPane().setBackground(Color.WHITE);             // frame Background color
        setLayout(null);
        
        
        JLabel lblusername= new JLabel("Username");           //write anything on frame
        lblusername.setBounds(270,20,80,20);
        add(lblusername);
        
        
        username=new JTextField();
        username.setBounds(350,20,150,20);
        add(username);
        
        
        JLabel lblpassword= new JLabel("Password");           
        lblpassword.setBounds(270,60,80,20);
        add(lblpassword);
        
        password=new JTextField();
        password.setBounds(350,60,150,20);
        add(password);
        
        JLabel logininas= new JLabel("Login in as");           
        logininas.setBounds(270,100,80,20);
        add(logininas);
        
        
        loginin=new Choice();
        loginin.add("Admin");
        loginin.add("Customer");
        loginin.setBounds(350,100,150,20);
        add(loginin);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i2=i1.getImage().getScaledInstance(9, 9, Image.SCALE_DEFAULT);
        
        login=new JButton("Login",new ImageIcon(i2));
        login.setBounds(300,150,80,20);
        login.addActionListener(this);
        add(login);
        
        
       /* ImageIcon i3=new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i4=i1.getImage().getScaledInstance(4, 4, Image.SCALE_DEFAULT);*/
        signup=new JButton("Signup"/*,new ImageIcon(i4)*/);
        signup.setBounds(400,150,80,20);
        signup.addActionListener(this);
        add(signup);
        
        ImageIcon i5=new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i6=i5.getImage().getScaledInstance(5, 5, Image.SCALE_DEFAULT);
        
        cancel=new JButton("Cancel",new ImageIcon(i6));
        cancel.setBounds(340,180,80,20);
        cancel.addActionListener(this);
        add(cancel);
        
        
        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i8=i7.getImage().getScaledInstance(230, 230, Image.SCALE_DEFAULT);
        ImageIcon i9=new ImageIcon(i8);
        JLabel image=new JLabel(i9);
        image.setBounds(0,0,230,230);
        add(image);
        
        
        setSize(600,270);
        setLocation(380,180);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== login){
            String susername=username.getText();
            String spassword=password.getText();
            String user=loginin.getSelectedItem();
            
            try{
                Conn c=new Conn();
                String query="select *from login where username='"+susername+"' and password='"+spassword+"'and user='"+user+"'";
                
                ResultSet rs=c.s.executeQuery(query);
                
                if(rs.next()){
                    
                    String meter=rs.getString("meter_no");
                    setVisible(false);
                    new Project(user,meter);
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                    username.setText("");
                    password.setText("");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else if(ae.getSource()==cancel){
            setVisible(false);
            
        }else if(ae.getSource()==signup){
            setVisible(false);
            new Signup();
            
        }
        
    }
    
    public static void main(String args[]){
        new Login();
    }
    
}

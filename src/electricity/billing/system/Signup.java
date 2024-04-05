package electricity.billing.system;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class Signup extends JFrame implements ActionListener {
    
    JButton create,back;
    Choice accountType;
    JTextField meter,username,name,password;
    Signup(){
       // setSize(650,350);
       // setLocation(250,150);
        
        setBounds(280,160,650,350);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
       // setVisible(true);
        
        JPanel panel=new JPanel();
        panel.setBounds(25,25,600,270);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173,216,230),2),"Create Account",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(172,216,230)));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(new Color(34,139,34));
        add(panel);
        
        
        JLabel heading=new JLabel(" Create Account As");
        heading.setBounds(80,40,140,18);
        heading.setForeground(Color.GRAY);
        heading.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(heading);
        
        accountType=new Choice();
        accountType.add("Admin");
        accountType.add("Customer");
        accountType.setBounds(230,40,130,18);
        panel.add(accountType);
        
        
        
        JLabel lblmeter=new JLabel("Meter Number");
        lblmeter.setBounds(85,80,140,18);
        lblmeter.setForeground(Color.GRAY);
        lblmeter.setFont(new Font("Tahoma",Font.BOLD,14));
        lblmeter.setVisible(false);
        panel.add(lblmeter);
        
        meter=new JTextField();
        meter.setBounds(230,80,130,18);
        meter.setVisible(false);
        panel.add(meter);
        
        
       
        
        
        JLabel lblusername=new JLabel("Username");
        lblusername.setBounds(85,120,140,18);
        lblusername.setForeground(Color.GRAY);
        lblusername.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(lblusername);
        
        username=new JTextField();
        username.setBounds(230,120,130,18);
        panel.add(username);
        
        
        JLabel lblname=new JLabel("Name");
        lblname.setBounds(85,160,140,18);
        lblname.setForeground(Color.GRAY);
        lblname.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(lblname);
        
        name=new JTextField();
        name.setBounds(230,160,130,18);
        panel.add(name);
        
         meter.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe){}
            
            @Override
            public void focusLost(FocusEvent fe){
            
            try{
                Conn c=new Conn();
                ResultSet rs=c.s.executeQuery("select *from login where meter_no='"+meter.getText()+"'");
                while(rs.next()){
                    name.setText(rs.getString("name"));
                }
                
            }catch(Exception e){
                e.printStackTrace();
            }
            }
        }
        );
        
        
        JLabel lblpassword=new JLabel("Password");
        lblpassword.setBounds(85,200,140,18);
        lblpassword.setForeground(Color.GRAY);
        lblpassword.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(lblpassword);
        
        password=new JTextField();
        password.setBounds(230,200,130,18);
        panel.add(password);
    
     accountType.addItemListener(new ItemListener(){
         public void  itemStateChanged(ItemEvent ae){
             String user=accountType.getSelectedItem();
             if(user.equals("Customer")){
                 lblmeter.setVisible(true);
                 meter.setVisible(true);
                 name.setEditable(false);
             }else{
                 lblmeter.setVisible(false);
                 meter.setVisible(false);
                 name.setEditable(true);
                 
             }
         }
     });
        
        create=new JButton("Create");
        create.setBackground(Color.BLACK);
        create.setForeground(Color.WHITE);
        create.setBounds(120,235,100,20);
        create.addActionListener(this);
        panel.add(create);
        
        
        back=new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(250,235,100,20);
        back.addActionListener(this);
        panel.add(back);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i2=i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(380, 25, 230, 230);
        panel.add(image);
        
        setVisible(true);
        
        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==create){
            String atype=accountType.getSelectedItem();
            String susername=username.getText();
            String sname=name.getText();
            String spassword=password.getText();
            String smeter=meter.getText();
            
            try{
                Conn c=new Conn();
                
                String query=null;
                       if(atype.equals("Admin")){ 
                           query="insert into login values('"+smeter+"','"+susername+"','"+sname+"','"+spassword+"','"+atype+"')";
                       }else{
                           query="update login set username='"+susername+"',password='"+spassword+"',user='"+atype+"'where meter_no='"+smeter+"'";
                       }
                c.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null,"Account Created Successfully");
                
                setVisible(false);
                new Login();
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else if(ae.getSource()== back){
            setVisible(false);
            new Login();
        }
        
    }
    public static void main(String args[]){
        new Signup();
        
    }
    
}

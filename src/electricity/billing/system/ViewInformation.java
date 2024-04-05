
package electricity.billing.system;

import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class ViewInformation extends JFrame implements ActionListener {
    
    JButton cancel;
    ViewInformation(String meter){
        setBounds(200,100,800,550);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading=new JLabel("VIEW CUSTOMER INFORMATION");
        heading.setBounds(230,0,450,30);
        heading.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(heading);
        
        
        //
        JLabel lblname=new JLabel("NAME");
        lblname.setBounds(50,70,90,18);
        add(lblname);
        
        JLabel name=new JLabel("");
        name.setBounds(230,70,90,18);
        add(name);
        
        
        //
        JLabel lblmeternumber=new JLabel(" Meter Number ");
        lblmeternumber.setBounds(50,110,90,18);
        add(lblmeternumber);
        
        JLabel meternumber=new JLabel("");
        meternumber.setBounds(230,110,90,18);
        add(meternumber);
        
        
         //
        JLabel lbladdress=new JLabel("Address");
        lbladdress.setBounds(50,150,90,18);
        add(lbladdress);
        
        JLabel address=new JLabel("");
        address.setBounds(230,150,90,18);
        add(address);
        
        
        //
        JLabel lblcity=new JLabel(" City ");
        lblcity.setBounds(50,190,90,18);
        add(lblcity);
        
        JLabel city=new JLabel("");
        city.setBounds(230,190,90,18);
        add(city);
        
        
         //
        JLabel lblstate=new JLabel("State ");
        lblstate.setBounds(450,70,90,18);
        add(lblstate);
        
        JLabel state=new JLabel("");
        state.setBounds(550,70,90,18);
        add(state);
        
        //
        JLabel lblemail=new JLabel("Email");
        lblemail.setBounds(450,110,90,18);
        add(lblemail);
        
        JLabel email=new JLabel("");
        email.setBounds(550,110,90,18);
        add(email);
        
        
        //
        JLabel lblphone=new JLabel("phone ");
        lblphone.setBounds(450,150,90,18);
        add(lblphone);
        
        JLabel phone=new JLabel("");
        phone.setBounds(550,150,90,18);
        add(phone);
        
        try{
            Conn c =new Conn();
            ResultSet rs=c.s.executeQuery("select *from customer where meter_no='"+meter+"'");
            while(rs.next()){
                name.setText(rs.getString("name"));
                address.setText(rs.getString("address"));
                city.setText(rs.getString("city"));
                state.setText(rs.getString("state"));
                email.setText(rs.getString("email"));
                phone.setText(rs.getString("name"));
                meternumber.setText(rs.getString("meter_no"));

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        cancel=new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(270, 260, 90, 23);
        cancel.addActionListener(this);
        add(cancel);
        
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image i2=i1.getImage().getScaledInstance(550, 280, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(18,280,530,260);
        add(image);
                
        
        
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
    }
    public static void main(String args[]){
        new ViewInformation("");
    }
}

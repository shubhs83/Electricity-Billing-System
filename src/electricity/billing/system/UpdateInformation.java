package electricity.billing.system;


import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class UpdateInformation extends JFrame implements ActionListener{
    JLabel name;
    JTextField tfaddress,tfcity,tfemail,tfphone,tfstate;
    JButton update,cancel;
    String meter;
    UpdateInformation(String meter){
        this.meter=meter;
        setBounds(270,130,870,400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        
          JLabel heading=new JLabel("UPDATE CUSTOMER INFORMATION");
        heading.setBounds(90,0,350,30);
        heading.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(heading);
        
        
        //
        JLabel lblname=new JLabel("NAME");
        lblname.setBounds(25,60,90,18);
        add(lblname);
        
        name=new JLabel("");
        name.setBounds(210,60,180,18);
        add(name);
        
        
        //
        JLabel lblmeternumber=new JLabel(" Meter Number ");
        lblmeternumber.setBounds(25,90,90,18);
        add(lblmeternumber);
        
        JLabel meternumber=new JLabel("");
        meternumber.setBounds(220,90,180,18);
        add(meternumber);
        
        
         //
        JLabel lbladdress=new JLabel("Address");
        lbladdress.setBounds(25,120,90,18);
        add(lbladdress);
        
        tfaddress=new JTextField("");
        tfaddress.setBounds(210,120,180,18);
        add(tfaddress);
        
        
        //
        JLabel lblcity=new JLabel(" City ");
        lblcity.setBounds(25,150,180,18);
        add(lblcity);
        
        tfcity=new JTextField("");
        tfcity.setBounds(210,150,180,18);
        add(tfcity);
        
        
         //
        JLabel lblstate=new JLabel("State ");
        lblstate.setBounds(25,180,180,18);
        add(lblstate);
        
        tfstate=new JTextField("");
        tfstate.setBounds(210,180,180,18);
        add(tfstate);
        
        //
        JLabel lblemail=new JLabel("Email");
        lblemail.setBounds(25,210,180,18);
        add(lblemail);
        
        tfemail=new JTextField("");
        tfemail.setBounds(210,210,180,18);
        add(tfemail);
        
        
        //
        JLabel lblphone=new JLabel("phone ");
        lblphone.setBounds(25,240,180,18);
        add(lblphone);
        
        tfphone=new JTextField("");
        tfphone.setBounds(210,240,180,18);
        add(tfphone);
        
        try{
            Conn c =new Conn();
            ResultSet rs=c.s.executeQuery("select *from customer where meter_no='"+meter+"'");
            while(rs.next()){
                name.setText(rs.getString("name"));
                tfaddress.setText(rs.getString("address"));
                tfcity.setText(rs.getString("city"));
                tfstate.setText(rs.getString("state"));
                tfemail.setText(rs.getString("email"));
                tfphone.setText(rs.getString("name"));
                meternumber.setText(rs.getString("meter_no"));

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        update=new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(50, 300, 90, 23);
        update.addActionListener(this);
        add(update);
        
        cancel=new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(210, 300, 90, 23);
        cancel.addActionListener(this);
        add(cancel);
        
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image i2=i1.getImage().getScaledInstance(350, 280, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(450,40,350,280);
        add(image);  
                
        
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==update){
            String address=tfaddress.getText();
            String city=tfcity.getText();  
            String state=tfstate.getText();
            String email=tfemail.getText();
            String phone=tfphone.getText();
            
            try{
                Conn c=new Conn();
               // c.s.executeUpdate("update customer set address='"+address+"',city='"+city+"','"+state+"','"+email+"' ,'"+phone+"'");
                  c.s.executeUpdate("update customer set address = '"+address+"', city = '"+city+"', state = '"+state+"', email = '"+email+"', phone = '"+phone+"' where meter_no = '"+meter+"'");
                JOptionPane.showMessageDialog(null, "User Information Updated Successfully");
                setVisible(false);
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            setVisible(false);
        }
        
    }
    public static void main(String args[]){
        new UpdateInformation("");
    }
}


package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;


public class NewCustomer extends JFrame implements ActionListener{
    
    JTextField tfname,tfaddress,tfcity,tfstate,tfemail,tfphone;
    JButton next,cancel;
    JLabel lblmeter;
    NewCustomer(){
        setSize(650,450);
        setLocation(380,180);
       
        
        JPanel p=new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);
        
        
        JLabel heading=new JLabel("New Customer");
        heading.setBounds(160,10,180,18);
        heading.setFont(new Font("Tahoma",Font.PLAIN,22));
        p.add(heading);
        
        
        JLabel lblname=new JLabel("Customer Name");
        lblname.setBounds(90,70,100,18);
        p.add(lblname);
        
        tfname=new JTextField();
        tfname.setBounds(220,70,180,18);
        p.add(tfname);
        
        //
        JLabel lblmeterno=new JLabel("Meter Number");
        lblmeterno.setBounds(90,100,100,18);
        p.add(lblmeterno);
        
        lblmeter=new JLabel("");
        lblmeter.setBounds(220,100,100,18);
        p.add(lblmeter);
        
        
        Random ran=new Random();
        long number=ran.nextLong()%1000000;
        lblmeter.setText(""+Math.abs(number));
        
        //
        JLabel lbladdress=new JLabel("Address");
        lbladdress.setBounds(90,130,100,18);
        p.add(lbladdress);
        
        tfaddress=new JTextField();
        tfaddress.setBounds(220,130,180,18);
        p.add(tfaddress);
        
        //
        JLabel lblcity=new JLabel("City");
        lblcity.setBounds(90,160,100,18);
        p.add(lblcity);
        
        tfcity=new JTextField();
        tfcity.setBounds(220,160,180,18);
        p.add(tfcity);
        
        
        //
        JLabel lblstate=new JLabel("State");
        lblstate.setBounds(90,190,100,18);
        p.add(lblstate);
        
        tfstate=new JTextField();
        tfstate.setBounds(220,190,180,18);
        p.add(tfstate);
        
        
        //
        
        JLabel lblemail=new JLabel("Email");
        lblemail.setBounds(90,220,100,18);
        p.add(lblemail);
        
        tfemail=new JTextField();
        tfemail.setBounds(220,220,180,18);
        p.add(tfemail);
        
        //
        
        JLabel lblphone=new JLabel("Phone Number");
        lblphone.setBounds(90,250,100,18);
        p.add(lblphone);
        
        tfphone=new JTextField();
        tfphone.setBounds(220,250,180,18);
        p.add(tfphone);
        
        //
        next=new JButton("Next");
        next.setBounds(120,300,90,20);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);
        
        
        cancel=new JButton("Cancel");
        cancel.setBounds(240,300,90,20);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        p.add(cancel);
        
        
        setLayout(new BorderLayout());
        add(p,"Center");
        
        //
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2=i1.getImage().getScaledInstance(130, 280, Image.SCALE_DEFAULT);
        ImageIcon  i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        add(image,"West");
        
        getContentPane().setBackground(Color.WHITE);
        
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==next){
            String name=tfname.getText();
            String meter=lblmeter.getText();
            String address=tfaddress.getText();
            String city=tfcity.getText();
            String state=tfstate.getText();
            String email=tfemail.getText();
            String phone=tfphone.getText();
            
            String query1="insert into customer values('"+name+"','"+meter+"','"+address+"','"+city+"','"+state+"','"+email+"','"+phone+"')";
            String query2="insert into login values('"+meter+"','','"+name+"','','')";
            
            try{
                Conn c=new Conn();
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
                setVisible(false);
                
                // new Frame
                new MeterInfo(meter);
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else if(ae.getSource()==cancel){
            setVisible(false);
        }
    }
    public static void main(String args[]){
        new NewCustomer();
    }
    
}

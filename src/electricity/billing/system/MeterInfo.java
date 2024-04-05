
package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;


public class MeterInfo extends JFrame implements ActionListener{
    
    JTextField tfname,tfaddress,tfcity,tfstate,tfemail,tfphone;
    JButton next,cancel;
    JLabel lblmeter;
    Choice meterlocation,metertype,phasecode,billtype;
    String meternumber;
    
    MeterInfo(String meternumber){
        this.meternumber=meternumber;
        setSize(650,450);
        setLocation(380,180);
       
        
        JPanel p=new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);
        
        
        JLabel heading=new JLabel("Meter Information");
        heading.setBounds(160,10,180,18);
        heading.setFont(new Font("Tahoma",Font.PLAIN,22));
        p.add(heading);
        
        
        JLabel lblname=new JLabel("Meter Number");
        lblname.setBounds(90,70,100,18);
        p.add(lblname);
        
        JLabel lblmeternumber=new JLabel(meternumber);
        lblmeternumber.setBounds(220,70,100,18);
        p.add(lblmeternumber);
        
        //
        JLabel lblmeterno=new JLabel("Meter Location");
        lblmeterno.setBounds(90,100,100,18);
        p.add(lblmeterno);
        
       meterlocation=new Choice();
       meterlocation.add("Outside");
       meterlocation.add("inside");
       meterlocation.setBounds(220,100,180,18);
       p.add(meterlocation);
        
        
     
       //
        JLabel lbladdress=new JLabel("Meter Type");
        lbladdress.setBounds(90,130,100,18);
        p.add(lbladdress);
        
       metertype=new Choice();
       metertype.add("Electric Meter");
       metertype.add("Solar Meter");
       metertype.add("Smart Meter");
       metertype.setBounds(220,130,180,18);
       p.add(metertype);
        
        //
        JLabel lblcity=new JLabel("Phase Code");
        lblcity.setBounds(90,160,100,18);
        p.add(lblcity);
        
       phasecode=new Choice();
       phasecode.add("011");
       phasecode.add("022");
       phasecode.add("033");
       phasecode.add("044");
       phasecode.add("055");
       phasecode.add("066");
       phasecode.add("077");
       phasecode.add("088");
       phasecode.add("099");
       phasecode.setBounds(220,160,180,18);
       p.add(phasecode);
        
        
        //
        JLabel lblstate=new JLabel("Bill Type");
        lblstate.setBounds(90,190,100,18);
        p.add(lblstate);
        
       billtype=new Choice();
       billtype.add("Normal");
       billtype.add("Industrial Meter");
       billtype.setBounds(220,190,180,18);
       p.add(billtype);
        
        
        //
        
        JLabel lblemail=new JLabel("Days");
        lblemail.setBounds(90,220,100,18);
        p.add(lblemail);
        
        JLabel lblemails=new JLabel("30 Days");
        lblemails.setBounds(220,220,100,18);
        p.add(lblemails);
        
        JLabel lblphone=new JLabel("Note");
        lblphone.setBounds(90,250,100,18);
        p.add(lblphone);
        
         JLabel lblphones=new JLabel("By Default Bill is Calculated for 30 Days Only");
        lblphones.setBounds(220,250,450,18);
        p.add(lblphones);
        
        //
        next=new JButton("Submit");
        next.setBounds(200,300,90,20);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);
        
        
        
        
        
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
            String meter=meternumber;
            String location=meterlocation.getSelectedItem();
            String type=metertype.getSelectedItem();
            String code=phasecode.getSelectedItem();
            String typebill=billtype.getSelectedItem();
            String days="30";
            
            String query="insert into meter_info values('"+meter+"','"+location+"','"+type+"','"+code+"','"+typebill+"','"+days+"')";
            
            
            try{
                Conn c=new Conn();
                c.s.executeUpdate(query);
                
                
                JOptionPane.showMessageDialog(null, "Meter Information Added Successfully");
                setVisible(false);
                
                // new Frame
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else if(ae.getSource()==cancel){
            setVisible(false);
        }
    }
    public static void main(String args[]){
        new MeterInfo("");
    }
    
}

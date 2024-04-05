
package electricity.billing.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class DepositDetails extends JFrame implements ActionListener{
    
    Choice meternumber,cmonth;
    JTable table;
    JButton search,print;
    DepositDetails(){
        super("Deposit Details");
        setSize(700,600);
        setLocation(300,50);
        
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        JLabel lblmeternumber=new JLabel("Search By Meter Number");
        lblmeternumber.setBounds(18,18,150,18);
        add(lblmeternumber);
        
        meternumber=new Choice();
        meternumber.setBounds(180,18,150,18);
        add(meternumber);
        
        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select *from Customer ");
            while(rs.next()){
                meternumber.add(rs.getString("meter_no"));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JLabel lblmonth=new JLabel("Search By  Month");
        lblmonth.setBounds(350,18,130,18);
        add(lblmonth);
        
        cmonth=new Choice();
        cmonth.setBounds(485,18,150,18);
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("Octomber");
        cmonth.add("November");
        cmonth.add("December");
        add(cmonth);
        
        table=new JTable();
        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select * from bill");
            
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        JScrollPane sp=new JScrollPane(table);
        sp.setBounds(0,90,650,550);
        add(sp);
        
        search=new JButton("Search");
        search.setBounds(115,65,75,18);
        search.addActionListener(this);
        add(search);
        
        
        print=new JButton("Print");
        print.setBounds(18,65,75,18);
        print.addActionListener(this);
        add(print);
                
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==search){
            
            
          String query="select *from bill where meter_no='"+meternumber.getSelectedItem()+"'and month='"+cmonth.getSelectedItem()+"'";
          try{
              Conn c=new Conn();
              ResultSet rs=c.s.executeQuery(query);
              table.setModel(DbUtils.resultSetToTableModel(rs));
          }catch(Exception e){
              e.printStackTrace();
          }
        }else{
            try{
                table.print();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
    }
    public static void main(String args[]){
        new DepositDetails();
    }
    
}

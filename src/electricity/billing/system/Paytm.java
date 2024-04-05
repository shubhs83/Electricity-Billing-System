
package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Paytm extends JFrame implements ActionListener{
    JButton back;
    
    String meter;
    Paytm(String meter){
        this.meter=meter;
        
        JEditorPane j=new JEditorPane();
        try{
            j.setPage("https://paytm.com/online-payments");
        }catch(Exception e){
            j.setContentType("text/html");
            j.setText("<html>Could not Load</html>");
            
        }
        
        JScrollPane pane=new JScrollPane(j);
        add(pane);
        
        back=new JButton("Back");
        back.setBounds(600,18,70,25);
        back.addActionListener(this);
        j.add(back); 
        
        setSize(700,500);
        setLocation(400,150);
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new PayBill(meter);
    }
    public static void main(String args[]){
        new Paytm("");
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangmanui;

import hangman.Hangman;
import hangman.Hangman_Service;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author nialldillane
 */
public class HangmanUI extends javax.swing.JFrame {

    private Hangman hangmanProxy;
    
    private javax.swing.JLabel nameJLabel;
    private javax.swing.JTextField nameJTextField;
    private javax.swing.JButton submitJButton;
    
    public HangmanUI(){
        
        try{
            Hangman_Service service = new Hangman_Service();
            hangmanProxy = service.getHangmanPort();
        }
        catch(Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        } 
        
        simpleJButton();
    }
    
    private void simpleJButton(){    
        JFrame f = new JFrame("Button Example"); //submit button
        JButton b = new JButton("Submit");    
        JLabel label = new JLabel();		
        JLabel label1 = new JLabel();
        JTextField textfield= new JTextField();
        
        b.setBounds(100,100,140, 40); //enter name label
        label.setText("Enter Name: ");
        label.setBounds(10, 10, 100, 100); //empty label which will show event after button clicked
        label1.setBounds(10, 110, 200, 100); //textfield to enter name
        textfield.setBounds(110, 50, 130, 30); //add to frame
        f.add(label1);
        f.add(textfield);
        f.add(label);
        f.add(b);    
        f.setSize(300,300);    
        f.setLayout(null);    
        f.setVisible(true);    
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textfield.getText();
                label1.setText("Submitting name...");
                int a = hangmanProxy.registerUser(name, "dillane", "nialldillane", "password1234", "niall@dillane.com");
                label1.setText(a + "\nname: \"" + name +  "\" submitted");
            }
        });
    }        

    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(
            new Runnable(){
                public void run(){
                    new HangmanUI().setVisible(true);
                }
            });
    }
}

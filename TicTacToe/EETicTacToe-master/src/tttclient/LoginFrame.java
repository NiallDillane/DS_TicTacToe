/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tttclient;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/**
 *
 * @author 16163842
 *
 *
 * REFERENCE: https://www.onlinetutorialspoint.com/java/java-swing-login-example.html
 */
public class LoginFrame extends JFrame implements ActionListener{
    
    private TTTWebService proxy;
    private TTTWebService_Service service;
    JPanel panel;
    JLabel user_label, password_label, message;
    JTextField userName_text;
    JPasswordField password_text;
    JButton submit, register;
    private GameFrame game;
   
    // Constructor
    LoginFrame() {
        
        try {
            service = new TTTWebService_Service();
            proxy = service.getTTTWebServicePort();
        } catch (Exception e) {
            System.exit(1);
            System.out.println("ERROR");

        }

        // Creates new username JLabel, giving it some text. Assigns userName_text to a new JTextField
        user_label = new JLabel();
        user_label.setText("User Name :");
        userName_text = new JTextField();

        // Creates new password JLabel, giving it some text. Assigns password_text to a new JTextField
        password_label = new JLabel();
        password_label.setText("Password :");
        password_text = new JPasswordField();

        // The login and register buttons
        submit = new JButton("Login");
        register = new JButton("Register");

        // Creating the panel
        panel = new JPanel(new GridLayout(4, 1));

        // Adding all the elements to the panel
        panel.add(user_label);
        panel.add(userName_text);
        panel.add(password_label);
        panel.add(password_text);

        // Message to the user added
        message = new JLabel();
        panel.add(message);

        // Adding out buttons
        panel.add(submit);
        panel.add(new JLabel()); // This is just for indentation of the register button
        panel.add(register);

        // Specifies when the 'X' is clicked the application should exit
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // The listeners for the buttons
        submit.addActionListener(this);
        register.addActionListener(this);

        // Add panel, setting title size and visibility
        add(panel, BorderLayout.CENTER);
        setTitle("Welcome Willkommen Bienvenue Bienvenido");
        setSize(300, 150);
        setVisible(true);
        this.setLocationRelativeTo(null);

    }
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            // do login
            String userName = userName_text.getText();
            String password = String.valueOf(password_text.getPassword());
            int loginStateInt = proxy.login(userName,password);
            if (loginStateInt > 0) {
                game = new GameFrame(loginStateInt, userName);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this,"Incorrect details");
            }
        }
        else if (ae.getSource() == register) {
            // go to register screen
            this.dispose();
            RegisterFrame register = new RegisterFrame();
        }

    }
}

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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/**
 *
 * @author 16163842
 */
public class RegisterFrame extends JFrame implements ActionListener {
    
    private TTTWebService proxy;
    private TTTWebService_Service service;
    private boolean registerState;
    private GameFrame game;
    JPanel panel;
    JLabel user_label, password_label, blank, name_label, surname_label;
    JTextField userName_text, name_text, surname_text;
    JPasswordField password_text;
    JButton submit, goback;
    
    
    RegisterFrame() {
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
        
        name_label = new JLabel();
        name_label.setText("First Name :");
        name_text = new JTextField();
        
        surname_label = new JLabel();
        surname_label.setText("Second Name :");
        surname_text = new JTextField();

        // The login and register buttons
        submit = new JButton("Register");
        goback = new JButton("Go back");

        // Creating the panel
        panel = new JPanel(new GridLayout(6, 1));

        // Adding all the elements to the panel
        panel.add(user_label);
        panel.add(userName_text);
        panel.add(password_label);
        panel.add(password_text);
        panel.add(name_label);
        panel.add(name_text);
        panel.add(surname_label);
        panel.add(surname_text);

        // Message to the user added
        blank = new JLabel();
        panel.add(blank);

        // Adding out buttons
        panel.add(submit);
        panel.add(new JLabel()); // This is just for indentation of the register button
        panel.add(goback);

        // Specifies when the 'X' is clicked the application should exit
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // The listeners for the buttons
        submit.addActionListener(this);
        goback.addActionListener(this);

        // Add panel, setting title size and visibility
        add(panel, BorderLayout.CENTER);
        setTitle("Registration");
        setSize(500, 150);
        setVisible(true);
        this.setLocationRelativeTo(null);
    }
    
    public boolean getRegisterState() {
        return this.registerState;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            // do register
            String userName = userName_text.getText();
            String password = String.valueOf(password_text.getPassword());
            String firstName = name_text.getText();
            String surname = surname_text.getText();
            String registerStateStr = proxy.register(userName, password, firstName, surname);
            if (Integer.parseInt(registerStateStr) != -1) {
                int loginStateInt = proxy.login(userName, password);
                game = new GameFrame(loginStateInt, userName);
                this.dispose();
            }
  
        }
        else if (ae.getSource() == goback) {
            this.dispose();
            LoginFrame login = new LoginFrame();
        }
        
    }

}

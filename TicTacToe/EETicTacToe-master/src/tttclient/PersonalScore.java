/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tttclient;
import java.awt.BorderLayout;
import java.awt.GridLayout;

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
public class PersonalScore extends JFrame{
   
    
    private TTTWebService proxy;
    private TTTWebService_Service service;
    JPanel panel;
    JLabel lossTitle, drawTitle, drawLabel, lossLabel, winTitle, winLabel;
    String table;
    int draws = 0;
    int wins = 0;
    int losses = 0;
    int player;
    String username;
    
    public PersonalScore(int player, String username) {
        
        try {
            service = new TTTWebService_Service();
            proxy = service.getTTTWebServicePort();
        } catch (Exception e) {
            System.exit(1);
            System.out.println("ERROR");

        }
        this.username = username;
        this.player = player;
        table = proxy.leagueTable();
        System.out.println(username);
        
        lossTitle = new JLabel();
        lossTitle.setText("Losses");
        drawTitle = new JLabel();
        drawTitle.setText("Draws");
        winTitle = new JLabel();
        winTitle.setText("Wins");
        drawLabel = new JLabel();
        lossLabel = new JLabel();
        winLabel = new JLabel();
        winLabel.setHorizontalAlignment(JLabel.CENTER);
        lossLabel.setHorizontalAlignment(JLabel.CENTER);
        drawLabel.setHorizontalAlignment(JLabel.CENTER);
        lossTitle.setHorizontalAlignment(JLabel.CENTER);
        drawTitle.setHorizontalAlignment(JLabel.CENTER);
        winTitle.setHorizontalAlignment(JLabel.CENTER);
      
        String[] lines = table.split("\n");
        
        // Fills the personal scores 
        for (int i = 0; i < lines.length && !(lines[0].equals("ERROR-NOGAMES")); i++) {
            if (lines[i].split(",")[1].equals(username)) { // if you are player 1 of the game
                switch (lines[i].split(",")[3]) {
                    case "1": wins++; break;  // +1 win
                    case "3":  draws++; break;// +1 draw
                    case "2": losses++; break; // +1 loss
                }
            } if (lines[i].split(",")[2].equals(username)) { // if you are player 2 of the game
                switch (lines[i].split(",")[3]) {
                    case "2": wins++; break; // +1 win
                    case "3": draws++; break; // +1 draw
                    case "1": losses++ ; break; // +1 loss
                }
            }
        }
        
        drawLabel.setText(Integer.toString(draws));
        lossLabel.setText(Integer.toString(losses));
        winLabel.setText(Integer.toString(wins));
        
        panel = new JPanel(new GridLayout(2, 3));

        // Adding all the elements to the panel
        panel.add(winTitle);
        panel.add(lossTitle);
        panel.add(drawTitle);
        panel.add(winLabel);
        panel.add(lossLabel);
        panel.add(drawLabel);


        // Specifies when the 'X' is clicked the application should exit
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        // Add panel, setting title size and visibility
        this.add(panel, BorderLayout.CENTER);
        this.setTitle("Welcome Willkommen Bienvenue Bienvenido");
        this.setSize(300, 150);
        this.setVisible(true);
        setLocationRelativeTo(null);
    }

}

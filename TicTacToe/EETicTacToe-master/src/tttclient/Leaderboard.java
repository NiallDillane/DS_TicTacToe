/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tttclient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Leaderboard
 * @author 16163842
 */
public class Leaderboard extends JFrame{
    private TTTWebService proxy;
    private TTTWebService_Service service;
    JFrame frame;
    JTable table;
    JScrollPane jscroll;
    ArrayList<Users> users;
    DefaultTableModel model;
    
    Leaderboard() {
        try {
            service = new TTTWebService_Service();
            proxy = service.getTTTWebServicePort();
        } catch (Exception e) {
            System.exit(1);
            System.out.println("ERROR");

        }
        
        String table1 = proxy.leagueTable();
        frame = new JFrame();
        frame.setTitle("Leaderboard");
        jscroll = new javax.swing.JScrollPane();
        table = new JTable();
        users = new ArrayList<Users>(); // holds a list of all users
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "Wins", "Losses", "Draws"
            }
        ){
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jscroll.setViewportView(table);
        
        String[] lines = table1.split("\n");
        
        model = (DefaultTableModel) table.getModel();
        
        
        boolean exists = false;
        // Fills the users array list, only users found to have won/lost/drew games will be shown
        for (int i = 0; i < lines.length && !(lines[0].equals("ERROR-NOGAMES")); i++ ) {
            for (int j = 0; j < users.size(); j++) {
                if (users.get(j).uid.equals(lines[i].split(",")[2])) {
                    exists = true;
                } else if (users.get(j).uid.equals(lines[i].split(",")[1])) {
                    exists = true;
                }
            }
            if (!exists) {
                users.add(new Users(lines[i].split(",")[1]));
                users.add(new Users(lines[i].split(",")[2]));
            }
        }
        // Sets the wins, draws and losses of each user
        for (int i = 0; i < lines.length && !(lines[0].equals("ERROR-NOGAMES")); i++) {
            for (int j = 0; j < users.size(); j++) {
                if (users.get(j).uid.equals(lines[i].split(",")[1])) {
                    switch (lines[i].split(",")[3]) {
                    case "1": users.get(j).wins++; break;  // +1 win
                    case "3":  users.get(j).draws++; break;// +1 draw
                    case "2": users.get(j).losses++; break; // +1 loss
                    }
                } if (users.get(j).uid.equals(lines[i].split(",")[2])) {
                    switch (lines[i].split(",")[3]) {
                    case "1": users.get(j).losses++; break;  // +1 win
                    case "3":  users.get(j).draws++; break;// +1 draw
                    case "2": users.get(j).wins++; break; // +1 loss
                    }
                }
            }
        }
        
        // Sorts the user list with an overriden compartor
        Collections.sort(users, new UserComparator());
       
        // Fills the tabel
        for (int i = 0; i < users.size(); i++) {
            model.addRow(new Object[]{users.get(i).uid, users.get(i).wins, users.get(i).losses, users.get(i).draws});
        }
        

        frame.add(jscroll); 
        frame.setSize(500, 200); 
        frame.setVisible(true); 
        frame.setLocationRelativeTo(null);
                
    }
}
    
/**
 * Users class represents a user
 * @author 16163842
 */
class Users {
    String uid;
    int losses;
    int wins;
    int draws;
    
    Users(String uid) {
        this.uid = uid;
        this.losses = 0;
        this.wins = 0;
        this.draws = 0;
    }
    
}

class UserComparator implements Comparator<Users> {
    @Override
    public int compare(Users u1, Users u2) {
        return Integer.compare(u2.wins, u1.wins);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tttclient;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author 16163842
 */
public class OpenGameUpdater implements Runnable{
    JTable openGameTable;
    TTTWebService proxy;
    DefaultTableModel model;
    ButtonColumn buttonColumn;
    int player;
    GameFrame gf;
    private Boolean stop = false;
    String username;
    
    /**
     * Updater for the open games table, implements runnable
     * @param gf
     * @param table
     * @param proxy
     * @param player
     * @param username 
     */
    public OpenGameUpdater(GameFrame gf, JTable table, TTTWebService proxy, int player, String username) {
        openGameTable = table;
        this.player = player;
        this.proxy = proxy;
        this.gf = gf;
        this.username = username;
        model = (DefaultTableModel) openGameTable.getModel();
    }
    
    // If joinGame is clicked
    Action joinGame = new AbstractAction()
    {
        public void actionPerformed(ActionEvent e)
            {
                stop = true;
                gf.dispose();
                JTable tab = (JTable)e.getSource();
                int row = Integer.valueOf( e.getActionCommand() );
                System.out.println(tab.getValueAt(row, 0));
                GameScreen gs = new GameScreen(player, Integer.parseInt(tab.getValueAt(row, 0).toString()), username);
            }
    };
    
    @Override
    public void run() {
        int prevID = -1; // last game ID preset
        int currentID;
        // Keep running until interrupted or interruped by join game action
        while (true && !Thread.currentThread().isInterrupted() && !stop) {
            String[] vals = proxy.showOpenGames().split(",|\n");
            // If there are open games
            if (vals.length != 1) {
                currentID = Integer.parseInt(vals[vals.length - 3]);
            } else {
                currentID = -99;
            }
            // If there is a new game since the last update
            if (currentID != prevID) {
                
                prevID = currentID;
                if (model.getRowCount() > 0) {
                    for (int i = model.getRowCount() - 1; i > -1; i--) {
                        model.removeRow(i);
                    }
                }
            
                for (int i = 0; i < vals.length - 1; i+=3) {
                    model.addRow(new Object[]{vals[i], vals[i+1], vals[i+2], "Join"});
                    buttonColumn = new ButtonColumn(openGameTable, joinGame, 3);
                    buttonColumn.setMnemonic(KeyEvent.VK_D); // sets mnemonic for button, D in this case
                }
            
            }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    //System.out.println("Sleep interrupted");
                    Thread.currentThread().interrupt();
                }
        }
    }
    
    
 
    
}


   

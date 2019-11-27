/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tttclient;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;


/**
 *  The TicTacToe game view
 * @author 16163842
 */
public class GameScreen extends javax.swing.JPanel {

    TTTWebService proxy;
    TTTWebService_Service service;
    int player;
    JFrame frame;
    String gid;
    boolean yourTurn = false;
    Thread turnUpdater;
    String username;
    
    /**
     * Constructor called when you click 'New Game'
     * @param p
     * @param username 
     */
    public GameScreen(int p, String username) {
        try {
            service = new TTTWebService_Service();
            proxy = service.getTTTWebServicePort();
        } catch (Exception e) {
            System.exit(1);
            System.out.println("ERROR");
        }
        
        this.player = p;
        this.username = username;
        gid = proxy.newGame(p);
        initComponents();
        frame = new JFrame("Game: " + gid + " User: " + p);
        frame.setSize(400,380);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().add(this);
        frame.setLocationRelativeTo(null);
        proxy.setGameState(Integer.parseInt(gid), -1);
        yourTurn = true; // because you started the game
        
        turnUpdater = new Thread(new TurnUpdater(proxy, player, Integer.parseInt(gid),this, true));// owner is true because you created game
        turnUpdater.start();
        
        // Reference: https://stackoverflow.com/questions/9093448/how-to-capture-a-jframes-close-button-click-event
        // Adds a listener for when the X button is clicked
        // When the X button is clicked, show a dialog to confirm 
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame, 
                    "Are you sure you want to close the game? You will lose if the game has started", "Please don't leave.. :(", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    frame.dispose();
                    //setGameState to won for opposite player if gamestate is in progress
                    String gameState = proxy.getGameState(Integer.parseInt(gid));
                    if (gameState.equals("0")) { // if game is in progress and you have clicked exit
                        proxy.setGameState(Integer.parseInt(gid),2);// you are player 1 here because you created the game, set game won to player2
                        turnUpdater.interrupt();
                    } else if (gameState.equals("-1")) { // else if another player hasnt joined and you click exit
                        proxy.deleteGame(Integer.parseInt(gid),player);
                        turnUpdater.interrupt();
                    }
                    new GameFrame(player, username);
                }
            }
    });
    }
    
    /**
     * Called when you join an existing game which you are not the owner of
     * @param p
     * @param gid
     * @param username 
     */
    public GameScreen(int p, int gid, String username) {
        try {
            service = new TTTWebService_Service();
            proxy = service.getTTTWebServicePort();
        } catch (Exception e) {
            System.exit(1);
            System.out.println("ERROR");
        }
        this.username = username;
        this.player = p;
        proxy.joinGame(p,gid);
        this.gid = String.valueOf(gid);
        initComponents();
        frame = new JFrame("Game: " + gid + " User: " + p);
        frame.setSize(400,380);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().add(this);
        frame.setLocationRelativeTo(null);
        System.out.println("Joined game: " + gid);
        proxy.setGameState(gid, 0);
        
        turnUpdater = new Thread(new TurnUpdater(proxy, player, gid, this, false)); // owner is false because you joined game not created
        turnUpdater.start();
        
        // Reference: https://stackoverflow.com/questions/9093448/how-to-capture-a-jframes-close-button-click-event
        // Adds a listener for when the X button is clicked
        // When the X button is clicked, show a dialog to confirm 
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame, 
                    "Are you sure you want to close the game?", "Please don't leave.. :(", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    System.out.println("we have reached here");
                    frame.dispose();
                    turnUpdater.interrupt(); 
                    String gameState = proxy.getGameState(gid);
                    if (gameState.equals("0")) { // if game is in progress and you have clicked exit
                        proxy.setGameState(gid,1);// you are player 1 here because you created the game, set game won to player2
                        turnUpdater.interrupt();
                    }
                    new GameFrame(player, username);
                }
            }
    });
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jButton1.setPreferredSize(new java.awt.Dimension(73, 72));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setPreferredSize(new java.awt.Dimension(73, 72));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setPreferredSize(new java.awt.Dimension(73, 72));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setPreferredSize(new java.awt.Dimension(73, 72));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setPreferredSize(new java.awt.Dimension(73, 72));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setPreferredSize(new java.awt.Dimension(73, 72));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setPreferredSize(new java.awt.Dimension(73, 72));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setPreferredSize(new java.awt.Dimension(73, 72));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setPreferredSize(new java.awt.Dimension(73, 72));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel1.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Checks if a square is available for taking
     * @param x
     * @param y
     * @return 
     */
    boolean squareAvailable(int x, int y) {
      String avail = proxy.checkSquare(x, y, Integer.parseInt(gid));
      switch (avail) {
          default: return false;
          case "1": return false; // taken
          case "0": return true; // not taken
      }
    }
    /**
     * Takes the available square, setting buttons to not be disabled to only allow a single button click per turn
     * @param x
     * @param y
     * @param b 
     */
    void takeSquare(int x,int y, JButton b) {
        if (squareAvailable(x,y) && yourTurn == true && !(proxy.getGameState(Integer.parseInt(gid)).equals("-1"))) {
            jButton1.setEnabled(false);jButton2.setEnabled(false);jButton3.setEnabled(false);jButton4.setEnabled(false);jButton5.setEnabled(false);
            jButton6.setEnabled(false);jButton7.setEnabled(false);jButton8.setEnabled(false);jButton9.setEnabled(false);
            proxy.takeSquare(x, y, Integer.parseInt(gid), player);
            System.out.println("You have taken square" + x + " " + y + " in game " + gid);
            b.setText("X");
            checkWin();
        } else {
            jButton1.setEnabled(true);jButton2.setEnabled(true);jButton3.setEnabled(true);jButton4.setEnabled(true);jButton5.setEnabled(true);
            jButton6.setEnabled(true);jButton7.setEnabled(true);jButton8.setEnabled(true);jButton9.setEnabled(true);
        }
    }
    /**
     * Sets it to your turn
     * @param t 
     */
    void setTurn(boolean t) {
        yourTurn = t;
        jButton1.setEnabled(true);jButton2.setEnabled(true);jButton3.setEnabled(true);jButton4.setEnabled(true);jButton5.setEnabled(true);
        jButton6.setEnabled(true);jButton7.setEnabled(true);jButton8.setEnabled(true);jButton9.setEnabled(true);
    }
    
    /**
     * Called from the TurnUpdater thread, updates the board
     */
    void updateBoard() {
        String board = proxy.getBoard(Integer.parseInt(gid));
        String[] lines = board.split("\n");
        for (int i = 0; i < lines.length; i++) {
            if (Integer.parseInt(lines[i].split(",")[0]) != player) { // updating board for the other players turns
                String xy = lines[i].split(",")[1] + lines[i].split(",")[2];
                    switch(xy) {
                        case "00":  jButton1.setText("0"); break;
                        case "10":  jButton4.setText("0"); break;
                        case "20":  jButton5.setText("0"); break;
                        case "01":  jButton3.setText("0"); break;
                        case "11":  jButton2.setText("0"); break;
                        case "21":  jButton8.setText("0"); break;
                        case "02":  jButton6.setText("0"); break;
                        case "12":  jButton7.setText("0"); break;
                        case "22":  jButton9.setText("0"); break;
                    } 
            }
        }
    }
    
    /**
     * Java wrapper class for calling the web service checkwin to avoid duplication
     * Sets game state
     */
    void checkWin() {
        String wonStr = proxy.checkWin(Integer.parseInt(gid));
        switch (wonStr) {
            case "0": break;
            case "1": proxy.setGameState(Integer.parseInt(gid),1); break;
            case "2": proxy.setGameState(Integer.parseInt(gid),2); break;
            case "3": proxy.setGameState(Integer.parseInt(gid),3); break;
            default: break;
        }
    }
    /**
     * Java wrapper class for calling the webservice checkGameState to avoid duplication
     * Checks the state of the game
     */
    void checkGameState() {
        String gameState = proxy.getGameState(Integer.parseInt(gid));
        switch (gameState) {
            case "1": JOptionPane.showMessageDialog(this,"Player 1 has won!"); frame.dispose(); turnUpdater.interrupt(); new GameFrame(player, username); break; 
            case "2": JOptionPane.showMessageDialog(this,"Player 2 has won!"); frame.dispose(); turnUpdater.interrupt(); new GameFrame(player, username); break;
            case "3": JOptionPane.showMessageDialog(this,"A draw, what a poorly designed game..."); frame.dispose(); turnUpdater.interrupt(); new GameFrame(player, username);  break;
            default: break;
        }
    }
    /**
     * Enables killing of the game
     */
    void killGame() {
        frame.dispose(); turnUpdater.interrupt(); new GameFrame(player,username);
    }

    // X - -
    // - - -
    // - - -
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int x = 0;
        int y = 0;
        takeSquare(x,y,jButton1);
    }//GEN-LAST:event_jButton1ActionPerformed
    // - X -
    // - - -
    // - - -    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int x = 1;
        int y = 0;
        takeSquare(x,y,jButton4);
    }//GEN-LAST:event_jButton4ActionPerformed
    // - - X
    // - - -
    // - - - 
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int x = 2;
        int y = 0;
        takeSquare(x,y,jButton5);
    }//GEN-LAST:event_jButton5ActionPerformed
    // - - -
    // X - -
    // - - - 
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int x = 0;
        int y = 1;
        takeSquare(x,y,jButton3);
    }//GEN-LAST:event_jButton3ActionPerformed
    // - - -
    // - X -
    // - - - 
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int x = 1;
        int y = 1;
        takeSquare(x,y,jButton2);
    }//GEN-LAST:event_jButton2ActionPerformed
    // - - -
    // - - X
    // - - - 
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        int x = 2;
        int y = 1;
        takeSquare(x,y,jButton8);
    }//GEN-LAST:event_jButton8ActionPerformed
    // - - -
    // - - -
    // X - - 
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int x = 0;
        int y = 2;
        takeSquare(x,y,jButton6);
    }//GEN-LAST:event_jButton6ActionPerformed
    // - - -
    // - - -
    // - X - 
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int x = 1;
        int y = 2;
        takeSquare(x,y,jButton7);
    }//GEN-LAST:event_jButton7ActionPerformed
    // - - -
    // - - -
    // - - X 
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        int x = 2;
        int y = 2;
        takeSquare(x,y,jButton9);
    }//GEN-LAST:event_jButton9ActionPerformed
    /**
     * Sets the timeout label which is set to 10 seconds
     * @param t 
     */
    public void setTimeout(String t) {
        jLabel1.setText(t);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}

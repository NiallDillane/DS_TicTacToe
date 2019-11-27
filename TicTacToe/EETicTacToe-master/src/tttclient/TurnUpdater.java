/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tttclient;

/**
 *
 * @author 16163842
 */
public class TurnUpdater implements Runnable{
    TTTWebService proxy;
    int player;
    int gid;
    boolean yourTurn;
    boolean owner;
    GameScreen gs;
    int state;
    int timeout = 10;
    
    public TurnUpdater(TTTWebService proxy, int player, int gid, GameScreen gs, boolean owner) {
        this.player = player;
        this.gid = gid;
        this.proxy = proxy;
        this.gs = gs;
        this.owner = owner;
    }
    
    @Override
    public void run() {
        // While the thread isnt interrutped
        while (true && !Thread.currentThread().isInterrupted()) {
        String board = proxy.getBoard(gid); // reference the board
        String[] lines = board.split("\n"); // get the lines of the board
        
        if (!lines[lines.length - 1].equals("ERROR-NOMOVES")) { // if at least one move has been made
            gs.checkWin(); // keep checking if the game has been won
            gs.checkGameState(); // keep checking if the other player has left the game
            gs.updateBoard(); // keep updating the board 
            if (Integer.parseInt(lines[lines.length - 1].split(",")[0]) == player) { // you took the last turn
                gs.setTurn(false);
            } else { // its your turn
                gs.setTurn(true);
            }
        } else { // if no moves have been made yet
            if (proxy.getGameState(gid).equals("-1")) { // if no one has joined the game, decrement the timeout until it hits 0 then delete the game
                if (timeout == 0) {
                    proxy.deleteGame(gid, player);
                    gs.killGame();
                }
                gs.setTimeout("Timeout in : " + Integer.toString(timeout));
                timeout--;
            } else {
                gs.setTimeout("");
        }
            gs.checkGameState(); // if no moves have been made but the other has joined the game, this checks if either the other player quits the game
        }
      
                
        try {
            Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    }
    


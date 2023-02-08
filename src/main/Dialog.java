package main;

import javax.swing.JOptionPane;

public class Dialog extends JOptionPane {
  Game game;

  public Dialog(GamePanel panel, Game game) {
    this.game = game;
    int result = showConfirmDialog(
        panel,
        "Your Points are: Win"  + "Do you want to play again?",
        "Do you want to play again?",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE);

    if(result == JOptionPane.YES_OPTION) {
      // game.playAgain();
    } else {
      System.exit(0);
    }
  }

}
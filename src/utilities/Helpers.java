package utilities;

import static utilities.Constants.PlayerConstants.IDLE_DOWN;
import static utilities.Constants.PlayerConstants.IDLE_UP;
import static utilities.Constants.PlayerConstants.RUNNING_LEFT;
import static utilities.Constants.PlayerConstants.RUNNING_RIGHT;

import entities.Player;
import main.Game;

public class Helpers {
  private Helpers() {}

  public static boolean canMove(Player player, float x, float y) {
    float height = player.getHitBox().height;
    float width = player.getHitBox().width;

    switch (player.getPlayerAction()) {
      case RUNNING_LEFT:
        if (!isLimit(x, y, player) && !isLimit(x, y + height, player))
            return true;
        break;
      case RUNNING_RIGHT:
        if (!isLimit(x + width, y, player) && !isLimit(x + width, y + height, player))
            return true;
        break;
      case IDLE_UP:
        if (!isLimit(x, y, player) && !isLimit(x + width, y, player))
            return true;
        break;
      case IDLE_DOWN:
        if (!isLimit(x, y + height, player) && !isLimit(x + width, y + height, player))
            return true;
        break;
    }

    return false;
  }

  public static boolean isLimit(float x, float y, Player player) {
    if (y < 0) {
      player.win();
      return true;
    } else if (x < 0 || x >= Game.GAME_WIDTH || y >= Game.GAME_HEIGTH) {
			return true;
    }
      
    return false;
  }
  
}

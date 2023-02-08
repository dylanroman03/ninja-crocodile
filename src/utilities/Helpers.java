package utilities;

import static utilities.Constants.PlayerConstants.IDLE_DOWN;
import static utilities.Constants.PlayerConstants.IDLE_UP;
import static utilities.Constants.PlayerConstants.RUNNING_LEFT;
import static utilities.Constants.PlayerConstants.RUNNING_RIGHT;

import entities.Player;
import main.Game;
import managers.CrocodileManager;

public class Helpers {
  public static boolean canMove(Player player, float x, float y, CrocodileManager cocodrileManager) {
    float height = player.getHitBox().height;
    float width = player.getHitBox().width;

    switch (player.getPlayerAction()) {
      case RUNNING_LEFT:
        if (!isLimit(x, y, cocodrileManager, player))
          if (!isLimit(x, y + height, cocodrileManager, player))
            return true;
        break;
      case RUNNING_RIGHT:
        if (!isLimit(x + width, y, cocodrileManager, player))
          if (!isLimit(x + width, y + height, cocodrileManager, player))
            return true;
        break;
      case IDLE_UP:
        if (!isLimit(x, y, cocodrileManager, player))
          if (!isLimit(x + width, y, cocodrileManager, player))
            return true;
        break;
      case IDLE_DOWN:
        if (!isLimit(x, y + height, cocodrileManager, player))
          if (!isLimit(x + width, y + height, cocodrileManager, player))
            return true;
        break;
    }

    return false;
  }

  public static boolean isLimit(float x, float y, CrocodileManager crocodileManager, Player player) {
    if (x < 0 || x >= Game.GAME_WIDTH)
			return true;
		if (y < 0 || y >= Game.GAME_HEIGTH - Game.TILES_SIZE)
			return true;

    return false;
  }
  
}

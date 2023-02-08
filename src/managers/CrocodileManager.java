package managers;

// import static utilities.Constants.GetGlobePoints;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import entities.Cocodrile;
import entities.Player;
import main.Game;

public class CrocodileManager {
  private Cocodrile[] cocodriles = new Cocodrile[3];
  private Player player;

  public CrocodileManager() {
    int initY = Game.TILES_SIZE * 3;

    for (int i = 0; i < cocodriles.length; i++) {
      Random r = new Random();
      int type = r.nextInt(5 - 1) + 1;
      cocodriles[i] = new Cocodrile((Game.TILES_SIZE * i * 4), initY, type);
    }
  }

  public void render(Graphics g) {
    for (Cocodrile globe : cocodriles) {
      if (globe.visible) {
        globe.render(g);
      }
    }
  }

  public void update() {
    for (Cocodrile cocodrile : cocodriles) {
      cocodrile.update();
    }
  }

  public boolean intersectCocodrile(Rectangle2D missile) {
    for (Cocodrile globe : cocodriles) {
      if (globe.visible) {
        if (globe.getHitBox().intersects(missile)) {
          return true;
        }
      }
    }
    return false;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

}

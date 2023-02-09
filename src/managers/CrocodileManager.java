package managers;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import entities.Crocodrile;
import main.Game;

public class CrocodileManager {
  private Crocodrile[] crocodiles = new Crocodrile[3];

  public CrocodileManager() {
    int initY = Game.TILES_SIZE * 3;

    for (int i = 0; i < crocodiles.length; i++) {
      crocodiles[i] = new Crocodrile((Game.TILES_SIZE * i * 4), initY);
    }
  }

  public void render(Graphics g) {
    for (Crocodrile crocodile : crocodiles) {
      crocodile.render(g);
    }
  }

  public void update() {
    for (Crocodrile cocodrile : crocodiles) {
      cocodrile.update();
    }
  }

  public boolean intersectCocodrile(Rectangle2D missile) {
    for (Crocodrile globe : crocodiles) {
      if (globe.getHitBox().intersects(missile))
        return true;
    }
    return false;
  }

}

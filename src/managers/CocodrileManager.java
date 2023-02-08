package managers;

// import static utilities.Constants.GetGlobePoints;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import entities.Cocodrile;
import entities.Player;
import main.Game;

public class CocodrileManager {
  private Cocodrile[] cocodriles = new Cocodrile[3];
  private Player player;

  public CocodrileManager() {
   int initY = Game.TILES_SIZE * 3;

    for (int i = 0; i < cocodriles.length; i++) {
        Random r = new Random();
        int type = r.nextInt(5-1) + 1;
        cocodriles[i] = new Cocodrile((Game.TILES_SIZE * i), initY, type);
    }
  }

  public void render(Graphics g) {
    for (Cocodrile globe : cocodriles) {
      // for (Cocodrile globe : globes) {
          if (globe.visible) {
            globe.render(g);
          }
      // } 
    }
  }

  public boolean intersectCocodrile(Rectangle2D missile) {
    for (Cocodrile globe : cocodriles) {
      // for (Cocodrile globe : globes2) {
        if (globe.visible) {
          if(globe.getHitBox().intersects(missile)) {
            globe.visible = false;
            // player.setGoals(GetGlobePoints(globe.type));
            return true;
          }
        }
      // }
    }
    return false;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }
  
}

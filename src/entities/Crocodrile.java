package entities;

import static utilities.Constants.PATH_COCODRILE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilities.LoadSave;

public class Crocodrile extends Entity {
  private BufferedImage image;

  public Crocodrile(float x, float y) {
    super(x, y, Game.TILES_SIZE, Game.TILES_SIZE);

    initHitBox(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
    getImage();
  }

  private void getImage() {
    image  = LoadSave.getImage(PATH_COCODRILE);
  }

  public void render(Graphics g) {
    g.drawImage(image, (int) hitBox.x, (int) hitBox.y, Game.TILES_SIZE, Game.TILES_SIZE, null);

    if (Game.DEBUG) {
      showHitBox(g);
    }
  }

  public void update() {
    hitBox.x -= 2;
    if (hitBox.getMaxX() < 0) {
      hitBox.x = Game.GAME_WIDTH;
    }
  }
}

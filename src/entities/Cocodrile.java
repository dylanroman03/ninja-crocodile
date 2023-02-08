package entities;

// import static utilities.Constants.GetGlobePath;
import static utilities.Constants.PATH_COCODRILE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilities.LoadSave;

public class Cocodrile extends Entity {
  public int type;
  private BufferedImage image;
  public boolean visible = true;

  public int getType() {
    return type;
  }

  public Cocodrile(float x, float y, int type) {
    super(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
    this.type = type;

    initHitBox(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
    getImage();
  }

  private void getImage() {
    // String path = GetGlobePath(type);
    image  = LoadSave.getImage(PATH_COCODRILE);
  }

  public void render(Graphics g) {
    g.drawImage(image, (int) getHitBox().x, (int) getHitBox().y, Game.TILES_SIZE, Game.TILES_SIZE, null);

    if (Game.DEBUG) {
      showHitBox(g);
    }
  }
}

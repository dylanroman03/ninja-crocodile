package entities;

import static utilities.Constants.PATH_WARRIOR_LIST;
import static utilities.Constants.PlayerConstants.IDLE_DOWN;
import static utilities.Constants.PlayerConstants.IDLE_UP;
import static utilities.Constants.PlayerConstants.RUNNING_LEFT;
import static utilities.Constants.PlayerConstants.RUNNING_RIGHT;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilities.LoadSave;


public class Player extends Entity {
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 10;
	private int playerAction = IDLE_UP;
	private boolean moving = false;
	private boolean left, up, right, down;
	private float playerSpeed = 1f;

	public int[][] lvlData;
	// public BoxManager boxManager;
	// public BushManager bushManger;

	// public void setBoxManager(BoxManager boxManager) {
	// 	// this.boxManager = boxManager;
	// }

	private float xDrawOffset = 26;
	private float yDrawOffset = 18;

	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitBox(x, y, Game.TILES_SIZE - (Game.SCALE * 5f), Game.TILES_SIZE - (Game.SCALE * 1.5f));
	}

	public void update() {
		updatePosition();
		updateAnimationTick();
		setAnimation();
	}

	public void render(Graphics g) {
		g.drawImage(animations[playerAction][aniIndex], (int) (hitBox.x - xDrawOffset), (int) (hitBox.y - yDrawOffset),
				width, height, null);

		if (Game.DEBUG) {
			showHitBox(g);
		}
	}

	private void updateAnimationTick() {
		if (moving) {
			aniTick++;
			if (aniTick >= aniSpeed) {
				aniTick = 0;
				aniIndex++;
				if (aniIndex >= 14) {
					aniIndex = 0;
				}

			}
		} else {
			aniIndex = 0;
		}
	}

	private void setAnimation() {
		int startAni = playerAction;

		if (moving) {
			if (left)
				playerAction = RUNNING_LEFT;
			else if (right)
				playerAction = RUNNING_RIGHT;
			else if (down) {
				playerAction = IDLE_DOWN;
			} else {
				playerAction = IDLE_UP;
			}

		} else {
			playerAction = IDLE_UP;
		}

		if (startAni != playerAction)
			resetAniTick();
	}

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}

	private void updatePosition() {
		moving = false;

		if (!left && !right && !up && !down)
			return;

		float xSpeed = 0, ySpeed = 0;

		if (left && !right) {
			xSpeed = -playerSpeed;
			moving = true;
		} else if (right && !left) {
			xSpeed = playerSpeed;
			moving = true;
		}

		if (up && !down) {
			ySpeed = -playerSpeed;
			moving = true;
		} else if (down && !up) {
			ySpeed = playerSpeed;
			moving = true;
		}

		// boolean canMove = canMove(this, hitBox.x + xSpeed, hitBox.y + ySpeed);
		// if (canMove) {
			hitBox.x += xSpeed;
			hitBox.y += ySpeed;
		// }
	}

	private void loadAnimations() {
		animations = new BufferedImage[4][14];

		for (int j = 0; j < animations.length; j++) {
			for (int i = 0; i < animations[j].length; i++) {
				String index = "0";

				if (i < 10) {
					index += index + i;
				} else {
					index += i;
				}

				animations[j][i] = LoadSave.getImage(PATH_WARRIOR_LIST[j] + "Run/0_Warrior_Run_" + index + ".png");
			}
		}
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
		this.up = this.down = false;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		left = right = false;
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		up = down = false;
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		left = right = false;
		this.down = down;
	}

	public void resetDirection() {
		left = false;
		right = false;
		down = false;
		up = false;
		moving = false;
	}

	public void setLvlData(int[][] lvlDate) {
		this.lvlData = lvlDate;
	}

	// public BoxManager getBoxManager() {
	// 	return boxManager;
	// }

	// public int getPlayerAction() {
	// 	return playerAction;
	// }

  // public void setBushManager(BushManager bushManager) {
	// 	this.bushManger = bushManager;
  // }
}


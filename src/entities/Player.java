package entities;

import static utilities.Constants.PATH_WARRIOR;
import static utilities.Constants.PlayerConstants.IDLE_DOWN;
import static utilities.Constants.PlayerConstants.IDLE_UP;
import static utilities.Constants.PlayerConstants.RUNNING_LEFT;
import static utilities.Constants.PlayerConstants.RUNNING_RIGHT;
import static utilities.Helpers.canMove;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import managers.CrocodileManager;
import utilities.LoadSave;

public class Player extends Entity {
	private BufferedImage image;
	private int playerAction = IDLE_UP;
	private boolean left;
	private boolean up;
	private boolean right;
	private boolean down;
	private float playerSpeed = 2.5f;

	private CrocodileManager crocodileManager;
	public Game game;

	public void setCrocodileManager(CrocodileManager boxManager) {
		this.crocodileManager = boxManager;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	private float xDrawOffset = 26;
	private float yDrawOffset = 18;

	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitBox(x, y, Game.TILES_SIZE - (Game.SCALE * 5f), Game.TILES_SIZE - (Game.SCALE * 1.5f));
	}

	public void update() {
		updatePosition();
		setMoving();

		boolean intersectCocodrile = crocodileManager.intersectCocodrile(getHitBox());

		if (intersectCocodrile) {
			reboot();
		}
	}

	public void render(Graphics g) {
		g.drawImage(image, (int) (hitBox.x - xDrawOffset), (int) (hitBox.y - yDrawOffset),
				width, height, null);

		if (Game.DEBUG) {
			showHitBox(g);
		}
	}

	private void setMoving() {
		if (left)
			playerAction = RUNNING_LEFT;
		else if (right)
			playerAction = RUNNING_RIGHT;
		else if (down) {
			playerAction = IDLE_DOWN;
		} else {
			playerAction = IDLE_UP;
		}
	}

	private void updatePosition() {

		if (!left && !right && !up && !down)
			return;

		float xSpeed = 0;
		float ySpeed = 0;

		if (left && !right) {
			xSpeed = -playerSpeed;
		} else if (right && !left) {
			xSpeed = playerSpeed;
		}

		if (up && !down) {
			ySpeed = -playerSpeed;
		} else if (down && !up) {
			ySpeed = playerSpeed;
		}

		boolean canMove = canMove(this, hitBox.x + xSpeed, hitBox.y + ySpeed);
		if (canMove) {
			hitBox.x += xSpeed;
			hitBox.y += ySpeed;
		}
	}

	private void loadAnimations() {
		image = LoadSave.getImage(PATH_WARRIOR);
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public int getPlayerAction() {
		return playerAction;
	}

	private void reboot() {
		hitBox.x = Game.GAME_WIDTH / 2;
		hitBox.y = Game.GAME_HEIGTH - Game.TILES_SIZE;
	}

	public void win() {
		game.callDialog(true);
	}
}

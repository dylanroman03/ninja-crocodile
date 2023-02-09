package main;

import static managers.StatusManager.renderTime;
import static utilities.Constants.BACKGROUND_PATH;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import entities.Player;
import managers.CrocodileManager;
import utilities.LoadSave;


public class Game {
	private GamePanel gamePanel;
	private boolean isGaming = true;
	private int time = 0;
	private boolean isPlaying = true;
	private BufferedImage background;
	private TimerTask task;

	private Player player;
	private CrocodileManager cocodrileManager;

	public static final int TILES_DEFAULT_SIZE = 17;
	public static final float SCALE = 3f;
	public static final int TILES_WIDTH = 15;
	public static final int TILES_HEIGTH = 15;
	public static final int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public static final int GAME_WIDTH = TILES_SIZE * TILES_WIDTH;
	public static final int GAME_HEIGTH = TILES_SIZE * TILES_HEIGTH;
	public static final boolean DEBUG = false;

	public Game() {
		background = LoadSave.getImage(BACKGROUND_PATH);
		initClasses();

		gamePanel = new GamePanel(this);
		new GameWindow(gamePanel);
		gamePanel.requestFocus();

		startGameLoop();
	}

	private void initClasses() {
		int xInit = Game.GAME_WIDTH / 2;
		int yInit = Game.GAME_HEIGTH - Game.TILES_SIZE;

		cocodrileManager = new CrocodileManager();
		player = new Player(xInit, yInit, TILES_SIZE + 30, TILES_SIZE + 30);
		player.setCrocodileManager(cocodrileManager);
		player.setGame(this);

		int timeout = DEBUG ? 15000 : 120000;
		time = timeout / 1000;
		Timer timer = new Timer();
		task = new TimerTask() {

			@Override
			public void run() {
				callDialog(false);
			}
		};
		timer.schedule(task, timeout);
	}

	public void callDialog(boolean win) {
		isPlaying = false;
		task.cancel();
		new Dialog(gamePanel, this, win);
	}

	private void startGameLoop() {
		while (isGaming) {
			run();	
		}
	}

	public void update() {
		player.update();
		cocodrileManager.update();
	}

	public void render(Graphics g) {
    g.drawImage(background, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGTH, null);
		cocodrileManager.render(g);
		player.render(g);
		renderTime(g, time);
	}

	public void run() {

		/// Esto es para que halla 120 fps por segundo, de ese modo existe fluides en el juego
		double timePerFrame = 1000000000.0 / 120;
		double timePerUpdate = 1000000000.0 / 120;

		long previousTime = System.nanoTime();

		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;

		while (isGaming) {
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if (deltaU >= 1) {
				update();
				deltaU--;
			}

			if (deltaF >= 1) {
				if (isPlaying) {
					gamePanel.repaint();
				}

				deltaF--;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				time--;
			}
		}
	}

	public void playAgain() {
		initClasses();
		isPlaying = true;
	}

	public Player getPlayer() {
		return player;
	}
}

package main;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import entities.Player;
import managers.CrocodileManager;

// import entities.Player;
// import levels.BoxManager;
// import levels.BushManager;
// import levels.LevelManager;

public class Game implements Runnable {
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	private boolean isGaming = true;
	private int time = 0;
	private boolean isPlaying = true;

	private Player player;
	private CrocodileManager cocodrileManager;
	// private LevelManager levelManager;
  // private BoxManager boxManager;
  // private BushManager bushManager;

	public final static int TILES_DEFAULT_SIZE = 17;
	public final static float SCALE = 3f;
	public final static int TILES_WIDTH = 15;
	public final static int TILES_HEIGTH = 15;
	public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_WIDTH;
	public final static int GAME_HEIGTH = TILES_SIZE * TILES_HEIGTH;
	public final static boolean DEBUG = true;

	public Game() {
		initClasses();

		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();

		startGameLoop();
	}

	private void initClasses() {
		int xInit = Game.GAME_WIDTH / 2;
		int yInit = Game.GAME_HEIGTH - Game.TILES_SIZE;

		cocodrileManager = new CrocodileManager();
		player = new Player(xInit, yInit, TILES_SIZE + 30, TILES_SIZE + 30);
		player.setCrocodileManager(cocodrileManager);

		time = 0;
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				isPlaying = false;
				callDialog(false);
			}
		};
		timer.schedule(task, 5000);
	}

	protected void callDialog(boolean win) {
		new Dialog(gamePanel, this, win);
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void update() {
		player.update();
		cocodrileManager.update();
	}

	public void render(Graphics g) {
		// levelManager.render(g);
		// bushManager.render(g);
    // boxManager.render(g);
		cocodrileManager.render(g);
		player.render(g);
	}

	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long previousTime = System.nanoTime();

		int frames = 0;
		int updates = 0;
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
				updates++;
				deltaU--;
			}

			if (deltaF >= 1) {
				if (isPlaying) {
					gamePanel.repaint();
				}

				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;

			}
		}
	}

	public void playAgain() {
		initClasses();
		isPlaying = true;
	}

	// public void windowsFocusLost() {
		// player.resetDirection();
	// }

	public Player getPlayer() {
		return player;
	}
}

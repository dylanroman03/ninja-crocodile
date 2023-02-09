package utilities;

public class Constants {
	private Constants() {}

	public static class PlayerConstants {
		public static final int IDLE_UP = 0;
		public static final int IDLE_DOWN = 3;
		public static final int RUNNING_LEFT = 1;
		public static final int RUNNING_RIGHT = 2;
	}


	public static final String[] PATH_WARRIOR_LIST = {
			"res/Warrior_animations/Front/PNG Sequences/Warrior_clothes_1/",
			"res/Warrior_animations/Left_Side/PNG Sequences/Warrior_clothes_1/",
			"res/Warrior_animations/Right_Side/PNG Sequences/Warrior_clothes_1/",
			"res/Warrior_animations/Front/PNG Sequences/Warrior_clothes_1/"
		};

	public static final String PATH_COCODRILE = "res/cocodrile.png";
	public static final String BACKGROUND_PATH = "res/background.png";

	public static String getTimePath (int time) {
		return "res/Time/" + time + ".png";
	}

}


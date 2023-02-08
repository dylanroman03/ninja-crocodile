package utilities;

import static utilities.Constants.PATH_FILE_LEVELS;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class LoadSave {
  public static BufferedImage getImage(String path) {
    BufferedImage img = null;

    try {
      InputStream is = new FileInputStream(path);
      img = ImageIO.read(is);
      is.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return img;
  }

  // static public Box[] getInitBoxes(int[][] lvlDate) {
  //   Box[] boxes = null;
  //   int length = 0;
  //   int e = 0;

  //   for (int i = 0; i < lvlDate.length; i++) {
  //     for (int j = 0; j < lvlDate[i].length; j++) {
  //       if (lvlDate[i][j] == 2 || lvlDate[i][j] == 3) length++;
  //     }
  //   }

  //   boxes = new Box[length];

  //   for (int i = 0; i < lvlDate.length; i++) {
  //    for (int j = 0; j < lvlDate[0].length; j++) {
  //      if (lvlDate[i][j] == 2 || lvlDate[i][j] == 3) {
  //         boxes[e] = new Box((float) (Game.TILES_SIZE * j), (float) (Game.TILES_SIZE * i), e);
  //         e++;
  //      }
  //    } 
  //   }

  //   return boxes;
  // }

  // static public Bush[] getInitBushes(int[][] lvlDate) {
  //   Bush[] boxes = null;
  //   int length = 0;
  //   int e = 0;

  //   for (int i = 0; i < lvlDate.length; i++) {
  //     for (int j = 0; j < lvlDate[i].length; j++) {
  //       if (lvlDate[i][j] == 1) length++;
  //     }
  //   }

  //   boxes = new Bush[length];

  //   for (int i = 0; i < lvlDate.length; i++) {
  //    for (int j = 0; j < lvlDate[0].length; j++) {
  //      if (lvlDate[i][j] == 1) {
  //         boxes[e] = new Bush((float) (Game.TILES_SIZE * j), (float) (Game.TILES_SIZE * i));
  //         e++;
  //      }
  //    } 
  //   }

  //   return boxes;
  // }

  static public int[][] getLevelData() {
    int[][] matrix = null;

    try {
      File myObj = new File(PATH_FILE_LEVELS + "01.txt");
      Scanner data = new Scanner(myObj);
      int x = data.nextInt();
      int y = data.nextInt();
      matrix = new int[x][y]; 

      for (int i = 0; i < x; i++) {
        data.nextLine();
        for (int j = 0; j < y; j++) {
          matrix[i][j] = data.nextInt();
        }
      }

      data.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return matrix;
  }
}

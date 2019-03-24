package graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import util.Vector2f;

public class Sprite {

    private BufferedImage SPRITESHEET = null;
    private BufferedImage[][] spriteArray;
    public int spriteWidth;
    public int spriteHeight;
    private int spriteX; // number of pics in a row of SpriteArray
    private int spriteY; // number of rows


    public Sprite(String file, int width, int height) {
        this.spriteWidth = width;
        this.spriteHeight = height;
      //  System.out.println("Loading: " + file + "...");
        SPRITESHEET = loadSprite(file);

        spriteX = SPRITESHEET.getWidth() / spriteWidth;
        spriteY = SPRITESHEET.getHeight() / spriteHeight;
        loadSpriteArray();

    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int width) {
        spriteWidth = width;
        spriteX = SPRITESHEET.getWidth() / spriteWidth;
    }

    public void setHeight(int height) {
        spriteHeight = height;
        spriteY = SPRITESHEET.getHeight() / spriteHeight;
    }

    public int getWidth() {
        return spriteWidth;
    }

    public int getHeight() {
        return spriteHeight;
    }

    private BufferedImage loadSprite(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(new File("/home/nitropawel/workspace/Towers/res/" + file));
        } catch (Exception e) {
            System.out.println("ERROR: could not load file: " + file);
        }
        return sprite;
    }

    public BufferedImage getSprite(int x, int y) {
        return SPRITESHEET.getSubimage(x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight);
    }

    public BufferedImage getSpriteSheet() {
        return SPRITESHEET;
    }

    public BufferedImage[] getSpriteArray(int i) {
        return spriteArray[i];
    }

    private void loadSpriteArray() {
        spriteArray = new BufferedImage[spriteY][spriteX];
        for (int y = 0; y < spriteY; y++) {
            for (int x = 0; x < spriteX; x++) {
                spriteArray[y][x] = getSprite(x, y);
            }
        }
    }

    public static void drawArray(Graphics2D g, ArrayList<BufferedImage> image, Vector2f pos, int width, int height,
                                 int xOffset, int yOffset) {
        float x = pos.x;
        float y = pos.y;

        for (int i = 0; i < image.size(); i++) {
            if (image.get(i) != null) {
                g.drawImage(image.get(i), (int) x, (int) y, width, height, null);
            }
            x += xOffset;
            y += yOffset;
        }
    }
    /*
     * public static void drawArray(Graphics2D g, Font f, String word, Vector2f pos,
     * int width, int height, int xOffset, int yOffset) { float x = pos.x; float y =
     * pos.y;
     *
     * for(int i = 0; i < word.length(); i++) { if(word.charAt(i) != 32)
     * g.drawImage(f.getFont(word.charAt(i)), (int) x, (int) y, width, height,
     * null); x += xOffset; y += yOffset; }
     *
     *
     * }
     */

}
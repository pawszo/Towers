package entity;

import graphics.Animation;
import graphics.Sprite;
import states.PlayState;
import util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Missiles {

    private final int FLAME = 0;
    private final int ICE = 1;
    private final int ENERGY = 2;
    private final int EARTH = 3;

    public int element;

    private int currentAnimation;

    private Animation ani;
    private Sprite sprite;
    private Vector2f pos;
    private Vector2f startPos;
    private int size;

    public int power;

    private float dx;
    private float dy;
    private float speed = 4f;

    float bulletX;
    float bulletY;
    float dirX;
    float dirY;
    float dirLength;

    public double a=1;
    public double b=1;
    public double c=1;
    public double x=1;
    public double y=1;
    public double sin=1;
    public double cos=1;

    public boolean renew;

    public void toggleRenew() {
        this.renew = false;
    }

    public Point getPoint() {
        int x = (int) getPos().x;
        int y = (int) getPos().y;
        return new Point(x, y);
    }

    public Missiles(Sprite sprite, int size, int element, Vector2f pos, int level) {
        this.element = element;
        this.power = level + 60;
        this.pos = pos;
        this.setStartPos(pos.copyVector2f(pos));
        this.size = size;
        this.sprite = sprite;
        ani = new Animation();
        setAnimation(element, sprite.getSpriteArray(element), 10);
    }

    public Missiles(Missiles missile) {
        this.element = missile.element;
        this.power = missile.power;
        this.pos = missile.pos;
        this.size = missile.size;
        this.sprite = missile.sprite;
        ani = new Animation();
        setAnimation(element, sprite.getSpriteArray(element), 10);
    }

    public void animate() {
        if (this.element == FLAME) {
            if (currentAnimation != FLAME || ani.getDelay() == -1) {
                setAnimation(FLAME, sprite.getSpriteArray(FLAME), 5);
            }
        } else if (this.element == ICE) {
            if (currentAnimation != ICE || ani.getDelay() == -1) {
                setAnimation(ICE, sprite.getSpriteArray(ICE), 5);
            }
        } else if (this.element == ENERGY) {
            if (currentAnimation != ENERGY || ani.getDelay() == -1) {
                setAnimation(ENERGY, sprite.getSpriteArray(ENERGY), 5);
            }
        } else if (this.element == EARTH) {
            if (currentAnimation != EARTH || ani.getDelay() == -1) {
                setAnimation(EARTH, sprite.getSpriteArray(EARTH), 5);
            }
        } else {
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
        }
    }

    public void setAnimation(int i, BufferedImage[] frames, int delay) {
        currentAnimation = i;
        ani.setFrames(frames);
        ani.setDelay(delay);
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSpeed(float f) {
        speed = f;
    }

    public void update() {
        animate();
        ani.update();
    }

    public void render(Graphics2D g) {
        g.drawImage(ani.getImage(), (int) (pos.x), (int) (pos.y), size, size, null);
    }

    public void input() {
    }

    public int getSize() {
        return this.size;
    }

    public Animation getAnimation() {
        return ani;
    }

    public Vector2f getPos() {
        return this.pos;
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
    }

    public void movePos(float x, float y) {
        this.pos.addX(x);
        this.pos.addY(y);
    }

    public void move() {

        if(PlayState.aim == null) {
            PlayState.aim = new Vector2f(1200, 350);
        }
        if((this.getPos().x - this.getStartPos().x < 3) && (this.getPos().x - this.getStartPos().x > -3) && (this.getPos().y - this.getStartPos().y < 3) && (this.getPos().y - this.getStartPos().y > -3)) {

            a = PlayState.aim.x - this.getStartPos().x;
            b = PlayState.aim.y - this.getStartPos().y;
            c = Math.sqrt((a * a) + b * b);

            cos = a / c;
            sin = b / c;

            x = cos * speed;
            y = sin * speed;
        }

    //    System.out.println("distance c: " + c);
    //    System.out.println("Angle: " + angle);
        dx = (float) x;
        dy = (float) y;
        movePos(dx, dy);
        if(getPos().x > 1300 || getPos().y > 1000 || getPos().y < -100) {
            this.renew = true;
        }

    }

    public Vector2f getStartPos() {
        return this.startPos;
    }

    public void setStartPos(Vector2f pos) {
        this.startPos = pos;
    }


}

package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import graphics.Animation;
import graphics.Sprite;
import util.KeyHandler;
import util.MouseHandler;
import util.Vector2f;

public abstract class Entity {

    private final int UP = 0;
    private final int LEFT = 1;
    private final int DOWN = 2;
    private final int RIGHT = 3;

    public int hitpoints;
    protected int initialHitpoints;

    protected int currentAnimation;

    protected Animation ani;
    protected Sprite sprite;
    protected Vector2f pos;
    protected int size;

    protected boolean up;
    protected boolean down;
    protected boolean left;
    protected boolean right;
    protected boolean attack;
    protected boolean enter;
    protected boolean alt;
    protected boolean backspace;
    protected int attackSpeed;

    public boolean die;

    protected float dx;
    protected float dy;

    protected float speed = 2f;
    protected float acc = 2f;
    protected float deacc = 2f;

    public Entity(Sprite sprite, Vector2f orgin, int size, int hitpoints) {
        this.hitpoints = hitpoints;
        this.initialHitpoints = hitpoints;
        this.sprite = sprite;
        pos = orgin;
        this.size = size;
        ani = new Animation();
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
    }

    public void setAnimation(int i, BufferedImage[] frames, int delay) {
        currentAnimation = i;
        ani.setFrames(frames);
        ani.setDelay(delay);
    }

    public void addHitpoints(int hitpoints) {
        this.hitpoints += hitpoints;
    }
    public int getHitpoints(){
        return this.hitpoints;
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

    public void animate() {
        if (up) {
            if (currentAnimation != UP || ani.getDelay() == -1) {
                setAnimation(UP, sprite.getSpriteArray(UP), 5);
            }
        } else if (left) {
            if (currentAnimation != LEFT || ani.getDelay() == -1) {
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
            }
        } else if (down) {
            if (currentAnimation != DOWN || ani.getDelay() == -1) {
                setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
            }
        } else if (right) {
            if (currentAnimation != RIGHT || ani.getDelay() == -1) {
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
            }
        } else {
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
        }
    }

    public void update() {
        animate();
        ani.update();
    }

    public abstract void render(Graphics2D g);

    public void input(KeyHandler key, MouseHandler mouse) {
    }

    public int getSize() {
        return this.size;
    }

    public Animation getAnimation() {
        return ani;
    }
    public Vector2f getPos() { return this.pos; }

}
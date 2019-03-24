package entity;

import graphics.Animation;
import graphics.Sprite;
import util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public abstract class Tower {

    private final int FULL_HP = 0;
    private final int MEDIUM_HP = 1;
    private final int LOW_HP = 2;
    private final int NO_HP = 3;

    protected int currentAnimation;
    protected int size;
    protected int attackSpeed;
    protected long attackSpeedCount;
    protected int hitpoints;
    protected int initialHitpoints;

    protected Sprite sprite;
    protected Animation ani;
    protected Vector2f pos;



    public abstract void render(Graphics2D g);

    public Tower(Sprite sprite, Vector2f vector2f, int size, int attackSpeed, int hitpoints) {
        this.attackSpeedCount = System.currentTimeMillis();
        this.size = size;
        this.attackSpeed = attackSpeed;
        this.hitpoints = hitpoints;
        this.initialHitpoints = hitpoints;
        this.pos = vector2f;
        ani = new Animation();
        setAnimation(FULL_HP, sprite.getSpriteArray(FULL_HP), 10);

    }

    public void animate() {
        if (hitpoints >= initialHitpoints * 0.8) {
            if (currentAnimation != FULL_HP || ani.getDelay() == -1) {
                setAnimation(FULL_HP, sprite.getSpriteArray(FULL_HP), 5);
            }
        }
        else if ((hitpoints < initialHitpoints * 0.8) && (hitpoints >= initialHitpoints * 0.5)) {
            if (currentAnimation != MEDIUM_HP || ani.getDelay() == -1) {
                setAnimation(MEDIUM_HP, sprite.getSpriteArray(MEDIUM_HP), 5);
            }
        }
        else if ((hitpoints < initialHitpoints * 0.5) && (hitpoints >= initialHitpoints * 0.15)) {
            if (currentAnimation != LOW_HP || ani.getDelay() == -1) {
                setAnimation(LOW_HP, sprite.getSpriteArray(LOW_HP), 5);
        }
        }
        else if (hitpoints < initialHitpoints * 0.15) {
            if (currentAnimation != NO_HP || ani.getDelay() == -1) {
                setAnimation(NO_HP, sprite.getSpriteArray(NO_HP), 5);
            }
        }
        else {
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
        }
    }

    public void setAnimation(int i, BufferedImage[] frames, int delay) {
        currentAnimation = i;
        ani.setFrames(frames);
        ani.setDelay(delay);
    }

    public Animation getAnimation() {
        return ani;
    }

    public void update() {
        animate();
        ani.update();

    }
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public int getHitpoints() { return this.hitpoints; }
    public void setHitpoints(int hitpoints) { this.hitpoints = hitpoints; }
    public void addHitpoints(int hitpoints) { this.hitpoints += hitpoints; }

    public Vector2f getPos() { return this.pos; }

    public void shoot() {
        if(System.currentTimeMillis() - attackSpeedCount > attackSpeed * 100) {
            attackSpeedCount = System.currentTimeMillis();
        }
    }
}

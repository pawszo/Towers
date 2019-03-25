package entity;

import graphics.Animation;
import graphics.Sprite;
import states.PlayState;
import util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;


public abstract class Tower {

    private final int ENERGY = 0;
    private final int FIRE = 1;
    private final int ICE = 2;
    private final int EARTH = 3;

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
        this.sprite = sprite;
        this.attackSpeed = attackSpeed;
        this.hitpoints = hitpoints;
        this.initialHitpoints = hitpoints;
        this.pos = vector2f;
        ani = new Animation();
        setAnimation(FIRE, sprite.getSpriteArray(FIRE), 5);

    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public void animate() {
        if (PlayState.element == 0) {
            if (currentAnimation != ENERGY || ani.getDelay() == -1) {
                setAnimation(ENERGY, sprite.getSpriteArray(ENERGY), 5);
            }
        }
        else if (PlayState.element == 1) {
            if (currentAnimation != FIRE || ani.getDelay() == -1) {
                setAnimation(FIRE, sprite.getSpriteArray(FIRE), 5);
            }
        }
        else if (PlayState.element == 2) {
            if (currentAnimation != ICE || ani.getDelay() == -1) {
                setAnimation(ICE, sprite.getSpriteArray(ICE), 5);
        }
        }
        else if (PlayState.element == 3) {
            if (currentAnimation != EARTH || ani.getDelay() == -1) {
                setAnimation(EARTH, sprite.getSpriteArray(EARTH), 5);
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

    public int getCurrentAnimation() {
        return this.currentAnimation;
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

package entity;

import graphics.Sprite;
import util.KeyHandler;
import util.MouseHandler;
import util.Vector2f;

import java.awt.*;

public class Enemy extends Entity{

    public int type;

    public boolean start; //if true, enemies start walking;

    public Enemy(Sprite sprite, Vector2f startPosition, int size, int hitpoints, int type, int level) {
        super(sprite, startPosition, size, hitpoints);
        this.type = type;
        this.setSpeed((float) (1 + level*0.3));

    }

    public void update() {
            super.update();
            move();
            pos.x += dx;
        }

    public void move() {
        if((pos.x > -50) && start) {
            dx = -speed;
        }
    }

    public Point getPoint() {
        int x = (int) getPos().x;
        int y = (int) getPos().y;
        return new Point(x, y);
    }


    @Override
    public void render(Graphics2D g) {
        g.drawImage(ani.getImage(), (int) (pos.x), (int) (pos.y), size, size, null);
    }

    public void input() {
        if(type == 0) up = true;
        else if(type == 1) left = true;
        else if(type == 2) down = true;
        else right = true;
    }
}

package entity;

import graphics.Sprite;
import util.Vector2f;
import java.awt.*;

public class Tower1 extends Tower{

    public Tower1(Sprite sprite, Vector2f vector2f, int size, int attackSpeed, int hitpoints) {
        super(sprite, vector2f, size, attackSpeed, hitpoints);
    }


    @Override
    public void render(Graphics2D g) {
        g.drawImage(ani.getImage(), (int) (pos.x), (int) (pos.y), size, size, null);
    }

    public void update() {
        super.update();
    }

    @Override
    public void shoot() {
        super.shoot();
    }


}

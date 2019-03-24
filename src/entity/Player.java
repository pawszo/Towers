package entity;

import java.awt.Graphics2D;
import java.util.Iterator;

import graphics.Sprite;
import states.GameState;
import states.GameStateManager;
import states.PlayState;
import util.KeyHandler;
import util.MouseHandler;
import util.Vector2f;

public class Player extends Entity {

    public boolean setTower;
    public Vector2f tempLocation;
    private long towerPlacementLimiter; // sets a delay to tower placement
    private long altLimiter; // sets a delay to element shifting
    public boolean enterLimiter; // enables to enter only once per level
    private long bspaceLimiter;

    public Player(Sprite sprite, Vector2f startPosition, int size, int hitpoints) {
        super(sprite, startPosition, size, hitpoints);
        super.setSpeed(4f);
    }

    public void move() {
        if (up) {
            dy -= acc;
            if (dy < -speed) {
                dy = -speed;
            }
        } else {
            if (dy < 0) {
                dy += deacc;
                if (dy > 0) {
                    dy = 0;
                }
            }
        }
        if (left) {
            dx -= acc;
            if (dx < -speed) {
                dx = -speed;
            }
        } else {
            if (dx < 0) {
                dx += deacc;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }
        if (down) {
            dy += acc;
            if (dy > speed) {
                dy = speed;
            }
        } else {
            if (dy > 0) {
                dy -= deacc;
                if (dy < 0) {
                    dy = 0;
                }
            }
        }
        if (right) {
            dx += acc;
            if (dx > speed) {
                dx = speed;
            }
        } else {
            if (dx > 0) {
                dx -= deacc;
                if (dx < 0) {
                    dx = 0;
                }
            }
        }
        if (attack) {
            if ((System.currentTimeMillis() - towerPlacementLimiter) > 600) {
                this.tempLocation = new Vector2f(pos.x, pos.y);
                this.towerPlacementLimiter = System.currentTimeMillis();

                setTower = true;
            }
        }
        if (enter) {
            if (!enterLimiter) {
                System.out.println("BATTLE BEGINS");
                this.enterLimiter = true;
            }
        }
        if (alt) {
            if ((System.currentTimeMillis() - altLimiter) > 200) {
                if (PlayState.element == 0) {
                    PlayState.element = 1;
                } else if (PlayState.element == 1) {
                    PlayState.element = 2;
                } else if (PlayState.element == 2) {
                    PlayState.element = 3;
                } else {
                    PlayState.element = 0;
                }

          //      System.out.println("SWITCHING ELEMENT: " + PlayState.element);
                System.out.print("Element: ");
                switch (PlayState.element) {
                    case 0:
                        System.out.println("ENERGY");
                        break;
                    case 1:
                        System.out.println("FIRE");
                        break;
                    case 2:
                        System.out.println("ICE");
                        break;
                    default:
                        System.out.println("EARTH");
                        break;
                }

                this.altLimiter = System.currentTimeMillis();

            }

        }
        if (backspace) {
            if ((System.currentTimeMillis() - bspaceLimiter) > 200) {
                if (!GameStateManager.menuOn) GameStateManager.menuOn = true;
                else {
                    GameStateManager.menuOn = false;
                }
                System.out.println("Toggle menu. GameStateManager.menuOn = " + GameStateManager.menuOn);
                this.bspaceLimiter = System.currentTimeMillis();
            }
        }
    }

    public void update() {
        super.update();
        move();
        if (!GameStateManager.menuOn) {
            pos.x += dx;
            pos.y += dy;
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(ani.getImage(), (int) (pos.x), (int) (pos.y), size, size, null);
    }

    public void input(MouseHandler mouse, KeyHandler key) {

        if (mouse.getButton() == 1) {
            //   System.out.println("Player: " + pos.x + ", " + pos.y);
        }
        if (key.up.down) {
            up = true;
        } else {
            up = false;
        }
        if (key.left.down) {
            left = true;
        } else {
            left = false;
        }
        if (key.down.down) {
            down = true;
        } else {
            down = false;
        }
        if (key.right.down) {
            right = true;
        } else {
            right = false;
        }
        if (key.attack.down) {
            attack = true;
        } else {
            attack = false;
        }
        if (key.enter.down) {
            enter = true;
        } else {
            enter = false;
        }
        if (key.alt.down) {
            alt = true;
        } else {
            alt = false;
        }
        if (key.menu.down) {
            backspace = true;
        } else {
            backspace = false;
        }
    }

}
package states;

import java.awt.Graphics2D;
import java.util.*;

import entity.Enemy;
import entity.Missiles;
import game.Window;
import graphics.Sprite;
import util.KeyHandler;
import util.MouseHandler;
import util.Vector2f;
import entity.Player;
import entity.Tower1;

public class PlayState extends GameState {


    private int level;
    private int towerCounter;
    private Player player;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>((level * 2) + 10);
    private ArrayList<Tower1> resources = new ArrayList<Tower1>(level + 3);
    private ArrayList<Missiles> missiles = new ArrayList<Missiles>(10);
    private ArrayList<Enemy> deadEnemies = new ArrayList<Enemy>(0);

    private Vector2f towerLoc;
    private Vector2f missileLoc;
    public static Vector2f aim;
    public static ArrayList<Integer> elements = new ArrayList<Integer>(4);
    public static int element;
    private ArrayList<Vector2f> towerPosArr = new ArrayList<Vector2f>(0);
    private boolean win;
    private int enemyCountDown;
    private Window frame;

    public PlayState(GameStateManager gsm, int level, Window frame) {
        super(gsm);
        this.frame = frame;
        this.level = level;
        this.towerCounter = level + 3;
        player = new Player(this, frame, new Sprite("sprite/sprite.png", 64, 64), new Vector2f(50, 350), 128, 1);
        generateEnemies("sprite/enemy.png", level, (level + 10) * 10);
        enemyCountDown = level * 2 + 10;
        generateElementArray();
    }


    public void setWin() {

        if (enemyCountDown <= 0) {
            System.out.println("WIN");
            this.win = true;
        }
    }

    public ArrayList<Tower1> getResources() {
        return this.resources;
    }

    public boolean getWin() {
        return win;
    }

    public void update() {


        if (!GameStateManager.menuOn) {
            for (Tower1 t : resources) {
                t.update();
            }
            for (Enemy e : enemies) e.update();
            for (int i = 0; i < deadEnemies.size(); i++) {
                // System.out.println("Enemy " + deadEnemies.get(i).toString() + " DIES. e.countdown = " + enemyCountDown);
                enemies.remove(deadEnemies.get(i));
                enemyCountDown--;
                System.out.println(++GameStateManager.SCORE);


            }
            deadEnemies.clear();
            for (Missiles m : missiles) {
                m.update();
            }
            if (!player.enterLimiter) towerPlacing();
        }

        player.update();

        collisionDetection(enemies, missiles);
        setWin();
    }

    /**
     * Rendering graphics
     */
    public void render(Graphics2D g) {
        for (Tower1 t : resources) {
            t.render(g);

        }
        battle(g);
    }

    /**
     * Entity movement
     */
    public void input(MouseHandler mouse, KeyHandler key) {
        player.input(mouse, key);
        enemyWalkSystem();


    }

    /**
     * Press enter to remove the player and trigger enemies. Rendering enemies array.
     * Towers in resources array start shooting.
     *
     * @param g
     */
    public void battle(Graphics2D g) {
        if (player.enterLimiter) {
            for (Enemy e : enemies) {
                e.start = true;
            }
        }
        if (!player.enterLimiter) {
            player.render(g);
        } else {

            for (Enemy e : enemies) {
                e.render(g);
            }
            for (Tower1 t : resources) {
                t.shoot();
            }
            for (Missiles m : missiles) {
                m.render(g);
            }
            if (!GameStateManager.menuOn) missileSystem();
        }
    }

    /**
     * generate enemies
     * type 1: ENERGY
     * type 2: FIRE
     * type 3: ICE
     * type 4: EARTH
     *
     * @param file
     * @param level
     */
    private void generateEnemies(String file, int level, int hitpoints) {
        for (int i = 0; i < (level * 2) + 10; i++) {
            Random randX = new Random();
            Random randY = new Random();
            Random type = new Random();
            enemies.add(new Enemy(new Sprite(file, 64, 64), new Vector2f(1280 + randX.nextInt(700), randY.nextInt(600)), 128, hitpoints, type.nextInt(4), level));
        }
        
    }

    @Override //obsolete
    public void printInstructions(Graphics2D g) {
        // TODO Auto-generated method stub

    }

    public void decrementTowerCounter() {
        this.towerCounter--;
    }

    /**
     * Allows player to place towers, decrease attackSpeed to actually increase the rate
     */
    private void towerPlacing() {
        if (player.setTower && towerCounter > 0) {
            towerLoc = player.tempLocation.copyVector2f(player.tempLocation);
            missileLoc = player.tempLocation.copyVector2f(player.tempLocation);
            Vector2f missileStartLoc = missileLoc.copyVector2f(missileLoc);
            resources.add(new Tower1(new Sprite("sprite/tower.png", 64, 64), towerLoc, 128, 20, 100));
            towerPosArr.add(towerLoc.copyVector2f(towerLoc));
            missiles.add(new Missiles(new Sprite("sprite/missile.png", 64, 64), 128, PlayState.element, missileStartLoc, level));
            decrementTowerCounter();
            player.setTower = false;
        }

    }

    /**
     * Controls enemy movement
     */
    private void enemyWalkSystem() {
        for (Enemy e : enemies) {
            e.input();
            if (e.getHitpoints() <= 0) {
                e.die = true;
            }
            if (e.getPoint().getX() < -50) {

                frame.getGameoverPanel().setScore(GameStateManager.SCORE);
                frame.getCl().show(frame.getMainPanel(), "GAMEOVERPANEL");
                frame.getGamePanel().stopRender();

            }
        }
    }

    /**
     * Moving missiles and assigning a new instances in their place when condition renew is met
     */
    private void missileSystem() {

        for (int i = 0; i < missiles.size(); i++) {
            missiles.get(i).move();
            if (missiles.get(i).renew) {
                Vector2f missileStartLoc = towerPosArr.get(i).copyVector2f(towerPosArr.get(i));
                missiles.set(i, new Missiles(new Sprite("sprite/missile.png", 64, 64), 128, PlayState.element, missileStartLoc, level));
                missiles.get(i).toggleRenew();
            }
        }
    }

    /**
     * When a missile collides with an enemy hitpoints (or negative hitpoints) are added to enemy's hp
     */
    private void collisionDetection(ArrayList<Enemy> enemies, ArrayList<Missiles> missiles) {
        for (Enemy e : enemies) {
            for (Missiles m : missiles) {
                int eSize = e.getSize() / 2;
                int mSize = m.getSize() / 2;
                if (m.getPoint().x < e.getPoint().x + eSize &&
                        m.getPoint().x + mSize > e.getPoint().x &&
                        m.getPoint().y < e.getPoint().y + eSize * 1.3 && /** 1.3 * eSize - adjusts the size due height greater then width in sprite image*/
                        m.getPoint().y + mSize > e.getPoint().y) {
                    if (e.type == m.element) {
                        e.addHitpoints(-m.power);
                    }
                    m.renew = true;
                    if (e.getHitpoints() <= 0) deadEnemies.add(e);

                }
            }
        }

    }

    /**
     * Use alt to switch element of missiles. This void populates elements array which is iterated in Player.move()
     */
    private void generateElementArray() {
        PlayState.elements.add(Integer.valueOf(0));
        PlayState.elements.add(Integer.valueOf(1));
        PlayState.elements.add(Integer.valueOf(2));
        PlayState.elements.add(Integer.valueOf(3));
    }
}


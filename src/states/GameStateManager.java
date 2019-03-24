package states;

import java.awt.*;
import java.util.ArrayList;

import game.GamePanel;
import util.KeyHandler;
import util.MouseHandler;
import util.Vector2f;

public class GameStateManager {

    private ArrayList<GameState> states;

    public static Vector2f map;

    public static final int PLAY = 0;
    public static final int MENU = 1;
    public static final int GAMEOVER = 2;
    public static boolean menuOn;
    public PlayState ps;
    public MenuState ms;
    private int level;

    public GameStateManager() {
        level = 1;
        ps = new PlayState(this, level);
      //  ms = new MenuState(this);
        map = new Vector2f(GamePanel.width, GamePanel.height);
        Vector2f.setWorldVar(map.x, map.y);
        states = new ArrayList<GameState>();
        states.add(ps);
        //states.add(ms);
    }

    public void pop(int state) {
        states.remove(state);
    }

    public void add(int state) {
        if (state == PLAY) {
            states.add(ps);
        }
        if (state == MENU) {
            states.add(ms);
        }
        if (state == GAMEOVER) {
            states.add(new GameOverState(this));
        }
    }

    public void addAndPop(int state) {
        states.remove(0);
        ps = new PlayState(this, level);
        add(state);
    }

    public void update() {
        for (int i = 0; i < states.size(); i++) {
            states.get(i).update();
        }
        nextLevel();
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        for (int i = 0; i < states.size(); i++) {
            states.get(i).input(mouse, key);
        }
    }

    public void render(Graphics2D g) {
        for (int i = 0; i < states.size(); i++) {
          //s  System.out.println(states.get(i).toString());
            states.get(i).render(g);
        }
    }

    public void nextLevel(){
        if(ps.getWin()) {
            System.out.println("NEXT LEVEL (" + (level+1) + ")");
            level++;
            addAndPop(0);
        }
    }

}
package states;

import java.awt.Graphics2D;

import util.KeyHandler;
import util.MouseHandler;

public abstract class GameState {

    private GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;

    }

    public abstract void update();

    public abstract void input(MouseHandler mouse, KeyHandler key);

    public abstract void render(Graphics2D g);

    public abstract void printInstructions(Graphics2D g);



}
package graphics;

import java.awt.image.BufferedImage;

public class Animation {

    private BufferedImage[] frames;

    private int currentFrame;
    private int numFrames;
    private int count;
    private int delay;
    private int timesPlayed;

    public BufferedImage getImage() {
        return frames[currentFrame];
    }

    public int getDelay() {
        return delay;
    }

    public int getFrame() {
        return currentFrame;
    }

    public int getCount() {
        return count;
    }

    public boolean hasPlayedOnce() {
        return timesPlayed > 0;
    }

    public boolean hasPlayed(int i) {
        return timesPlayed == i;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setFrame(int i) {
        currentFrame = i;
    }

    public void numFrames(int numFrames) {
        this.numFrames = numFrames;
    }

    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
        currentFrame = 0;
        count = 0;
        timesPlayed = 0;
        delay = 2;
        numFrames = frames.length;
    }

    public void update() {
        if (delay == -1)
            return;
        count++;
        if (count == delay) {
            currentFrame++;
            count = 0;
        }
        if (currentFrame == numFrames) {
            currentFrame = 0;
            timesPlayed++;
        }
    }

    public Animation(BufferedImage[] frames) {
        timesPlayed = 0;
        setFrames(frames);
    }

    public Animation() {
        timesPlayed = 0;
    }

}
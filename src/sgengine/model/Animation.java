/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author pi
 */
public class Animation<T> {

    private ArrayList<T> animationFrames = new ArrayList();
    private long frameTimeMs;
    private long lastTime;
    private int currentFrame;

    public Animation(long frameTimeMs, T... frames) {
        this.frameTimeMs = frameTimeMs;
        animationFrames.addAll(Arrays.asList(frames));
        lastTime = System.currentTimeMillis();
        currentFrame = 0;
    }

    public long getFrameTimeMs() {
        return frameTimeMs;
    }

    public void setFrameTimeMs(long frameTimeMs) {
        this.frameTimeMs = frameTimeMs;
    }

    public ArrayList<T> getAnimationFrames() {
        return animationFrames;
    }

    public T getFrameAtTime(long currentTime) {

        while (lastTime < currentTime) {
            lastTime += frameTimeMs;
            currentFrame++;

            if (currentFrame >= animationFrames.size()) {
                currentFrame = 0;
            }
        }

        return animationFrames.get(currentFrame);
    }

}

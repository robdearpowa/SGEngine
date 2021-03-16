/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.entity;

import java.awt.Color;
import sgengine.inteface.TextRenderer;
import sgengine.model.Entity;
import sgengine.model.Sprite;

/**
 *
 * @author pi
 */
public class GameManager extends Entity implements TextRenderer {

    private long lastTime = 0;
    private long lastTimeFps = 0;
    private float lastFps = 0;
    private Sprite sprite;

    @Override
    public void start() {
        getMainWindow().setScaleX(3);
        getMainWindow().setScaleY(3);
        setDrawOrder(100);
        position.setX(0);
        position.setY(10);
    }

    @Override
    public void update() {

    }

    @Override
    public Color getTextColor() {
        return Color.white;
    }

    @Override
    public String renderText() {
        long now = System.currentTimeMillis();

        float frameTime = (now - lastTime) / 1000f;

        float fps = 0f;
        if (frameTime != 0) {
            fps = 1f / frameTime;
        }

        if (now > lastTimeFps + 500) {
            lastFps = fps;
            lastTimeFps = now;
        }

        lastTime = now;

        return String.valueOf(lastFps);
    }
}

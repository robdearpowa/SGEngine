/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import sgengine.model.Entity;

/**
 *
 * @author pi
 */
public class GameManager extends Entity {

    private long lastTime = 0;

    @Override
    public void start() {
        getMainWindow().setScaleX(3);
        getMainWindow().setScaleY(3);
        setDrawOrder(100);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2d) {
        long now = System.currentTimeMillis();

        float frameTime = (now - lastTime) / 1000f;

        float fps = 0f;
        if (frameTime != 0) {
            fps = 1f / frameTime;
        }

        lastTime = now;

        g2d.setColor(Color.white);
        g2d.drawString(String.valueOf(fps), 0, 10);
    }

}

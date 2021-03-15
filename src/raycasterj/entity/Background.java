/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raycasterj.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import raycasterj.model.Entity;

/**
 *
 * @author pi
 */
public class Background extends Entity {

    private int w, h;

    @Override
    public void start() {
        setDrawOrder(-100);
    }

    @Override
    public void update() {
        w = getMainWindow().getScaledDimension().width;
        h = getMainWindow().getScaledDimension().height;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0, 0, w, h);
    }

}

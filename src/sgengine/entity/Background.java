/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import sgengine.Main;
import sgengine.inteface.SpriteRenderer;
import sgengine.model.Entity;
import sgengine.model.Sprite;

/**
 *
 * @author pi
 */
public class Background extends Entity implements SpriteRenderer {

    private int w, h;
    private Sprite sprite;

    @Override
    public void start() {
        setDrawOrder(-100);

        w = Main.WIDTH;
        h = Main.HEIGHT;

        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = (Graphics2D) bi.getGraphics();

        g2d.setColor(Color.gray);
        g2d.fillRect(0, 0, w, h);

        sprite = new Sprite(bi);
    }

    @Override
    public void update() {
    }

    @Override
    public Sprite renderSprite() {
        return sprite;
    }
}

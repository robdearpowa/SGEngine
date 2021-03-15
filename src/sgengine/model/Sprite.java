/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author pi
 */
public class Sprite {

    private Data2D size;
    private BufferedImage spriteData;
    private String resource;

    public Sprite(String resource, Data2D size) {
        setResource(resource);
        setSize(size);
    }

    public Data2D getSize() {
        return size;
    }

    public void setSize(Data2D size) {
        this.size = size;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
        loadSpriteData();
    }

    public void draw(Graphics2D g2d, Data2D where) {
        if (spriteData != null) {
            g2d.drawImage(spriteData, where.getX(), where.getY(), size.getX(), size.getY(), null, null);
        }
    }

    private void loadSpriteData() {
        spriteData = null;

        if (resource != null) {
            try {
                spriteData = ImageIO.read(getClass().getResourceAsStream("/assets/" + resource));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

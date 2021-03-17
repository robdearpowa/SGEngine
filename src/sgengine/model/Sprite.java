/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.model;

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
    private boolean flippedHorizontal;
    private boolean flippedVertical;
    private Data2D pivot;

    public Sprite(String resource, Data2D size) {
        setResource(resource);
        setSize(size);
        setFlippedHorizontal(false);
        setFlippedVertical(false);
        setPivot(new Data2D(0, 0));
    }

    public Sprite(BufferedImage spriteData) {
        setSpriteData(spriteData);
        setFlippedHorizontal(false);
        setFlippedVertical(false);
        setPivot(new Data2D(0, 0));
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

    public BufferedImage getSpriteData() {
        return spriteData;
    }

    public void setSpriteData(BufferedImage spriteData) {
        this.spriteData = spriteData;
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

    public boolean isFlippedHorizontal() {
        return flippedHorizontal;
    }

    public void setFlippedHorizontal(boolean flippedHorizontal) {
        this.flippedHorizontal = flippedHorizontal;
    }

    public boolean isFlippedVertical() {
        return flippedVertical;
    }

    public void setFlippedVertical(boolean flippedVertical) {
        this.flippedVertical = flippedVertical;
    }

    public Data2D getPivot() {
        return pivot;
    }

    public void setPivot(Data2D pivot) {
        this.pivot = pivot;
    }

}

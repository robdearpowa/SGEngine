/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.entity;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import sgengine.Main;
import sgengine.inteface.SpriteRenderer;
import sgengine.inteface.TextRenderer;
import sgengine.logic.Controller;
import sgengine.model.Data2D;
import sgengine.model.Entity;
import sgengine.model.Scene;
import sgengine.model.Sprite;

/**
 *
 * @author pi
 */
public class Camera extends Entity {

    private BufferedImage frameToRender;
    private Data2D renderingResolution;

    public Camera() {
        tag = "mainCamera";
    }

    @Override
    public void start() {
        drawOrder = 200;
        renderingResolution = new Data2D(Main.WIDTH, Main.HEIGHT);
    }

    public void draw(Graphics2D g2d) {
        Scene currentScene = Controller.getInstance().getMainLooper().getCurrentScene();
        Dimension panelDimension = Controller.getInstance().getMainWindow().getScaledDimension();

        if (currentScene != null) {

            frameToRender = new BufferedImage(renderingResolution.getX(), renderingResolution.getY(), BufferedImage.TYPE_INT_RGB);

            Graphics2D fg2d = (Graphics2D) frameToRender.getGraphics();

            currentScene.getEntityListInDrawingOrder().forEach(entity -> {

                Data2D ePos = entity.getPosition();

                if (entity instanceof SpriteRenderer) {
                    Sprite sprite = ((SpriteRenderer) entity).renderSprite();

                    fg2d.drawImage(sprite.getSpriteData(),
                            ePos.getX() - position.getX(),
                            ePos.getY() - position.getY(),
                            sprite.getSpriteData().getWidth(),
                            sprite.getSpriteData().getHeight(),
                            null);
                }

                if (entity instanceof TextRenderer) {
                    String text = ((TextRenderer) entity).renderText();
                    Color color = ((TextRenderer) entity).getTextColor();

                    fg2d.setColor(color);
                    fg2d.drawString(text, ePos.getX(), ePos.getY());
                }
            });

            fg2d.dispose();

            g2d.drawImage(frameToRender, 0, 0, panelDimension.width, panelDimension.height, null);
        }
    }
}

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
import java.util.ArrayList;
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

    public final static int DEFAULT_WIDTH = 128;
    public final static int DEFAULT_HEIGHT = 78;
    public final static String DEFAULT_TAG = "mainCamera";

    private BufferedImage frameToRender;
    private Data2D renderingResolution;
    private ArrayList<DrawListener> drawListeners = new ArrayList();

    public Camera() {
        tag = "mainCamera";
    }

    @Override
    public void start() {
        drawOrder = 200;
        renderingResolution = new Data2D(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public void draw(Graphics2D g2d) {
        Scene currentScene = Controller.getInstance().getMainLooper().getCurrentScene();
        Dimension panelDimension = Controller.getInstance().getMainWindow().getDimension();

        if (currentScene != null) {

            frameToRender = new BufferedImage(renderingResolution.getX(), renderingResolution.getY(), BufferedImage.TYPE_INT_RGB);

            Graphics2D fg2d = (Graphics2D) frameToRender.getGraphics();

            currentScene.getEntityListInDrawingOrder().forEach(entity -> {

                Data2D ePos = entity.getPosition();

                draw(fg2d, entity, ePos);
            });

            drawListeners.forEach(l -> {
                l.onPostDraw(this, fg2d);
            });
            drawListeners.clear();

            fg2d.dispose();

            g2d.drawImage(frameToRender, 0, 0, panelDimension.width, panelDimension.height, null);
        }
    }

    public void draw(Graphics2D g2d, Object obj, Data2D ePos) {
        if (obj instanceof SpriteRenderer) {
            Sprite sprite = ((SpriteRenderer) obj).renderSprite();
            BufferedImage spriteData = sprite.getSpriteData();

            Data2D flipped = new Data2D(
                    (sprite.isFlippedHorizontal() ? -1 : 1),
                    (sprite.isFlippedVertical() ? -1 : 1)
            );

            Data2D offset = new Data2D(
                    (sprite.isFlippedHorizontal() ? spriteData.getWidth() : 0),
                    (sprite.isFlippedVertical() ? spriteData.getHeight() : 0)
            );

            Data2D pivot = sprite.getPivot();

            g2d.drawImage(spriteData,
                    ePos.getX() - position.getX() + offset.getX() - pivot.getX(),
                    ePos.getY() - position.getY() + offset.getY() - pivot.getY(),
                    spriteData.getWidth() * flipped.getX(),
                    spriteData.getHeight() * flipped.getY(),
                    null);
        }

        if (obj instanceof TextRenderer) {
            String text = ((TextRenderer) obj).renderText();
            Color color = ((TextRenderer) obj).getTextColor();

            g2d.setColor(color);
            g2d.drawString(text, ePos.getX(), ePos.getY());
        }
    }

    public void addDrawListener(DrawListener listener) {
        drawListeners.add(listener);
    }

    public interface DrawListener {

        public void onPostDraw(Camera camera, Graphics2D g2d);
    }
}

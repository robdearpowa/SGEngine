/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.entity;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import sgengine.inteface.TextRenderer;
import sgengine.logic.Controller;
import sgengine.model.Audio;
import sgengine.model.Data2D;
import sgengine.model.Entity;
import sgengine.model.Sprite;
import sgengine.ui.WindowWrapper.KeyEventListener;

/**
 *
 * @author pi
 */
public class GameManager extends Entity implements TextRenderer, KeyEventListener {

    private long lastTime = 0;
    private long lastTimeFps = 0;
    private float lastFps = 0;
    private Sprite sprite;
    private ArrayList<Entity> treeList;
    private boolean currentFullscreenState;
    private Audio bgMusic;

    @Override
    public void start() {
        setDrawOrder(100);
        position.setX(0);
        position.setY(10);

        getMainWindow().setWidth(800);
        getMainWindow().setHeight(450);

        getCurrentScene().getCameraList().forEach(c -> {
            c.setRenderingResolution(new Data2D(160, 90));
        });

        currentFullscreenState = false;

        treeList = new ArrayList();

        TestTree tree1 = new TestTree();
        TestTree tree2 = new TestTree();
        TestTree tree3 = new TestTree();
        TestTree tree4 = new TestTree();
        TestTree tree5 = new TestTree();

        tree1.setPosition(new Data2D(40, 50));
        tree2.setPosition(new Data2D(50, 50));
        tree3.setPosition(new Data2D(40, 60));
        tree4.setPosition(new Data2D(45, 10));
        tree5.setPosition(new Data2D(20, 10));

        treeList.add(tree1);
        treeList.add(tree2);
        treeList.add(tree3);
        treeList.add(tree4);
        treeList.add(tree5);

        getCurrentScene().addAllToEntityList(treeList);
        getMainWindow().addKeyEventListener(this);

        //bgMusic = new Audio("bg1.mid");
        //bgMusic.play();
    }

    @Override
    public void update() {
        //if (!bgMusic.isPlaying()) {
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

    @Override
    public void onKeyTyped(KeyEvent e) {

    }

    @Override
    public void onKeyPressed(KeyEvent e) {

    }

    @Override
    public void onKeyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            currentFullscreenState = !currentFullscreenState;
            getMainWindow().setFullscreen(currentFullscreenState);
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Controller.getInstance().getMainLooper().setIsRunning(false);
        }
    }
}

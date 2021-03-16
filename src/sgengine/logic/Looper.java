/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.logic;

import sgengine.model.Scene;
import sgengine.ui.WindowWrapper;

/**
 *
 * @author pi
 */
public class Looper {

    private Thread updateThread;

    private volatile boolean isRunning;
    private volatile boolean isPaused;
    private volatile Scene currentScene;

    public boolean isIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public boolean isIsPaused() {
        return isPaused;
    }

    public void setIsPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
        //Quando imposto una scena devo firerare tutti gli start di tutte le entità
        startScene();
        isPaused = false;
    }

    public void start() {
        currentScene = null;
        isRunning = false;
        isPaused = true;

        updateThread = new Thread(() -> {
            System.out.println("Update isRunning: " + isRunning);

            long lastTimeMs = 0;
            long lastTimeDrawMs = 0;

            while (isRunning) {

                if (!isPaused) {

                    try {

                        long currentTimeMs = System.currentTimeMillis();

                        if (currentTimeMs > lastTimeMs + 16) {
                            update();
                            lastTimeMs = currentTimeMs;
                        }

                        if (currentTimeMs > lastTimeDrawMs + 16) {
                            draw();
                            lastTimeDrawMs = currentTimeMs;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }

            //Exit process
            System.out.println("Update isRunning: " + isRunning);
            System.exit(0);
        });

        System.out.println("Adding window listener");
        Controller.getInstance().getMainWindow().addWindowEventListener(new WindowWrapper.WindowEventListener() {
            @Override
            public void onWindowOpen() {
                isRunning = true;

                System.out.println("Starting looper, running: " + isRunning);
                updateThread.start();
            }

            @Override
            public void onWindowClose() {
                isRunning = false;
            }
        });
        Controller.getInstance().getMainWindow().drawWindow();
    }

    private void startScene() {
        try {
            if (currentScene != null) {
                currentScene.getEntityList().forEach(e -> {
                    e.start();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update() {
        try {
            if (currentScene != null) {
                currentScene.getEntityList().forEach(e -> {
                    e.update();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO HO TOLTO synch andra bene?
    private void draw() {

        try {
            if (currentScene != null) {
                Controller.getInstance().getMainWindow().prepareGraphics();

                //Ora che le entità sono ordinate per drawingOrder posso procedere a disegnarle
                currentScene.getCameraList().forEach(c -> {
                    Controller.getInstance().getMainWindow().renderCamera(c);
                });

                Controller.getInstance().getMainWindow().disposeGraphics();
            }
        } catch (Exception e) {

        }
    }

}

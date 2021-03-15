/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raycasterj;

import raycasterj.entity.Background;
import raycasterj.entity.GameManager;
import raycasterj.entity.TestEntity;
import raycasterj.logic.Controller;
import raycasterj.logic.Looper;
import raycasterj.model.Entity;
import raycasterj.model.Scene;

/**
 *
 * @author pi
 */
public class Main {

    private static Looper mainLooper;
    public static int WIDTH = 128;
    public static int HEIGHT = 78;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("App started");
        System.setProperty("sun.java2d.opengl", "true");
        System.out.println("Starting mainLooper");
        Controller.getInstance().createMainWindow("RaycasterJ v0.1", WIDTH, HEIGHT);
        Controller.getInstance().createMainLooper();

        mainLooper = Controller.getInstance().getMainLooper();
        mainLooper.start();

        Scene testScene = new Scene("Test_scene");
        Entity pippo = new TestEntity();
        Entity bg = new Background();
        Entity gameManager = new GameManager();
        testScene.getEntityList().add(pippo);
        testScene.getEntityList().add(bg);
        testScene.getEntityList().add(gameManager);

        mainLooper.setCurrentScene(testScene);

        System.out.println("mainLooper started");
    }

}

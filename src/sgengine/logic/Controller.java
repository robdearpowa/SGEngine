/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.logic;

import java.util.ArrayList;
import sgengine.model.Scene;
import sgengine.ui.WindowWrapper;

/**
 *
 * @author pi
 */
public class Controller {

    public static String MAIN_WINDOW_TAG = "mainWindow";
    private static Controller instance = null;

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }

        return instance;
    }

    private ArrayList<WindowWrapper> windowList;
    private ArrayList<Scene> sceneList;
    private Looper mainLooper;

    private Controller() {
        windowList = new ArrayList();
        sceneList = new ArrayList();
    }

    public void createMainLooper() {
        if (mainLooper == null) {
            mainLooper = new Looper();
        }
    }

    public Looper getMainLooper() {
        return mainLooper;
    }

    public WindowWrapper getWindowByTag(String tag) {
        WindowWrapper result = null;

        for (WindowWrapper w : windowList) {
            if (tag.equals(w.getTag())) {
                result = w;
                break;
            }
        }

        return result;
    }

    public void addWindow(WindowWrapper window) {
        windowList.add(window);
    }

    public void removeWindowByTag(String tag) {
        WindowWrapper result = null;

        for (WindowWrapper w : windowList) {
            if (tag.equals(w.getTag())) {
                result = w;
                break;
            }
        }

        windowList.remove(result);
    }

    public WindowWrapper getMainWindow() {
        return getWindowByTag(MAIN_WINDOW_TAG);
    }

    public void createMainWindow(String title, int w, int h) {
        if (getWindowByTag(MAIN_WINDOW_TAG) == null) {
            WindowWrapper main = new WindowWrapper(title, MAIN_WINDOW_TAG, w, h);
            windowList.add(main);
        }
    }

    public ArrayList<Scene> getSceneList() {
        return sceneList;
    }

}

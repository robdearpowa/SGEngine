/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import sgengine.model.Entity;

/**
 *
 * @author pi
 */
public class WindowWrapper {

    private String title, tag;
    private int w, h;
    private double scaleX, scaleY;

    private JFrame mainFrame;
    private JPanel mainPanel;
    private BufferedImage currentFrame;

    private ArrayList<WindowEventListener> windowListenerList;
    private ArrayList<KeyEventListener> keyEventListenerList;

    private WindowWrapper() {

    }

    public WindowWrapper(String title, String tag, int w, int h) {
        this.title = title;
        this.w = w;
        this.h = h;
        this.tag = tag;
        this.windowListenerList = new ArrayList();
        this.keyEventListenerList = new ArrayList();
        this.scaleX = 1;
        this.scaleY = 1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void addWindowEventListener(WindowEventListener listener) {
        if (windowListenerList.contains(listener)) {
            return;
        }

        windowListenerList.add(listener);
    }

    public void removeWindowEventListener(WindowEventListener listener) {
        if (windowListenerList.contains(listener)) {
            windowListenerList.remove(listener);
        }
    }

    public void addKeyEventListener(KeyEventListener listener) {
        if (keyEventListenerList.contains(listener)) {
            return;
        }

        keyEventListenerList.add(listener);
    }

    public void removeKeyEventListener(KeyEventListener listener) {
        if (keyEventListenerList.contains(listener)) {
            keyEventListenerList.remove(listener);
        }
    }

    public double getScaleX() {
        return scaleX;
    }

    public void setScaleX(double scaleX) {
        this.scaleX = scaleX;
        updateScreen();
    }

    public double getScaleY() {
        return scaleY;
    }

    public void setScaleY(double scaleY) {
        this.scaleY = scaleY;
        updateScreen();
    }

    public Dimension getDimension() {
        return mainPanel != null ? mainPanel.getSize() : null;
    }

    public Dimension getScaledDimension() {
        return mainPanel != null ? new Dimension(mainPanel.getSize().width / (int) scaleX, mainPanel.getSize().height / (int) scaleY) : null;
    }

    public void drawWindow() {
        mainFrame = new JFrame(title);
        System.out.println("Frame init");
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setResizable(false);

        mainPanel = new JPanel();
        mainPanel.setVisible(true);

        mainFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("Frame opened");
                sendWindowOpened();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Frame closed");
                sendWindowClosed();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {
                //Non implementato
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                //Non implementato
            }

            @Override
            public void windowActivated(WindowEvent e) {
                //Non implementato
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                //Non implementato
            }

        });
        mainFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                sendKeyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                sendKeyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                sendKeyReleased(e);
            }

        });

        mainFrame.setVisible(true);
        updateScreen();
    }

    public void closeWindow() {
        if (mainFrame != null) {
            mainFrame.dispose();
        }
    }

    public void drawEntity(Entity entity) {
        Graphics2D g2d = (Graphics2D) currentFrame.getGraphics();
        g2d.setPaintMode();
        g2d.scale(scaleX, scaleY);
        entity.draw(g2d);
    }

    public void prepareGraphics() {
        currentFrame = new BufferedImage(mainPanel.getWidth(), mainPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    public void disposeGraphics() {
        mainPanel.getGraphics().drawImage(currentFrame, 0, 0, null);
        mainPanel.getGraphics().dispose();
    }

    private void sendWindowOpened() {
        windowListenerList.forEach(l -> {
            l.onWindowOpen();
        });
    }

    private void sendWindowClosed() {
        windowListenerList.forEach(l -> {
            l.onWindowClose();
        });
    }

    private void sendKeyTyped(KeyEvent e) {
        keyEventListenerList.forEach(l -> {
            l.onKeyTyped(e);
        });
    }

    private void sendKeyPressed(KeyEvent e) {
        keyEventListenerList.forEach(l -> {
            l.onKeyPressed(e);
        });
    }

    private void sendKeyReleased(KeyEvent e) {
        keyEventListenerList.forEach(l -> {
            l.onKeyReleased(e);
        });
    }

    private void updateScreen() {
        if (mainPanel != null && mainFrame != null) {
            mainPanel.setPreferredSize(new Dimension(w * (int) scaleX, h * (int) scaleY));
            mainFrame.pack();
        }
    }

    public interface WindowEventListener {

        void onWindowOpen();

        void onWindowClose();

    }

    public interface KeyEventListener {

        void onKeyTyped(KeyEvent e);

        void onKeyPressed(KeyEvent e);

        void onKeyReleased(KeyEvent e);
    }
}

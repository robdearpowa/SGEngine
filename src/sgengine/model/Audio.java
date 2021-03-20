/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import sgengine.logic.ResourceManager;

/**
 *
 * @author pi
 */
public class Audio {

    private String resource;
    private Clip soundClip;
    private AudioInputStream audioInputStream;

    public Audio(String audioResource) {
        setResource(audioResource);
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
        try {
            soundClip = ResourceManager.getInstance().loadAudio(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public synchronized void play() {
        try {
            soundClip.setMicrosecondPosition(0);
            soundClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean isPlaying() {
        return soundClip != null ? soundClip.isRunning() : false;
    }

    public void stop() {
        if (isPlaying()) {
            soundClip.stop();
        }
    }

}

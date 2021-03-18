/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgengine.logic;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/**
 *
 * @author pi
 */
public class ResourceManager {

    private static volatile ResourceManager instance;

    public static ResourceManager getInstance() {
        if (instance == null) {
            instance = new ResourceManager();
        }

        return instance;
    }

    private HashMap<String, BufferedImage> imagesMap = new HashMap();
    private HashMap<String, Clip> audioMap = new HashMap();

    private ResourceManager() {

    }

    public BufferedImage loadImage(String resource) {
        BufferedImage image = null;

        if (imagesMap.containsKey(resource)) {
            return imagesMap.get(resource);
        }

        if (resource != null) {
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/assets/" + resource));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (image != null) {
            imagesMap.put(resource, image);
        }

        return image;
    }

    public Clip loadAudio(String resource) {
        AudioInputStream audioStream = null;
        Clip clip = null;

        if (audioMap.containsKey(resource)) {
            return audioMap.get(resource);
        }

        if (resource != null) {
            try {
                audioStream = AudioSystem.getAudioInputStream(getClass().getResource("/assets/" + resource));
                DataLine.Info info = new DataLine.Info(Clip.class, audioStream.getFormat());
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(audioStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (clip != null) {
            audioMap.put(resource, clip);
        }

        return clip;
    }

    public void preloadAudio(String... audioArray) {
        for (String aRes : audioArray) {
            loadAudio(aRes);
        }
    }
}

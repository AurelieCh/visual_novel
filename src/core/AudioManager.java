package core;

import javafx.scene.media.AudioClip;

import java.io.File;

/**
 * Classe permettant de gérer l'ambiance sonore du jeu (musique, bruitages, etc...)
 */
public class AudioManager {
    private static double musicVolume = 1;
    private static double sfxVolume = 1;
    private static double voiceVolume = 1;

    // ==== Music ====
    public static void playMusic(String path, boolean loop) {
        try {
            AudioClip clip = new AudioClip(new File(path).toURI().toString());
            clip.setVolume(musicVolume);
            if (loop) clip.setCycleCount(AudioClip.INDEFINITE);
            clip.play();
        } catch (Exception e) {
            System.err.println("[AudioManager] Impossible de lire la musique : " + path);
        }
    }

    // ==== SFX / Hover etc ====
    public static void playSfx(String path) {
        try {
            AudioClip clip = new AudioClip(new File(path).toURI().toString());
            clip.setVolume(sfxVolume);
            clip.play();
        } catch (Exception e) {
            System.err.println("[AudioManager] Impossible de lire le SFX : " + path);
        }
    }

    // ==== Voix ====
    public static void playVoice(String path) {
        try {
            AudioClip clip = new AudioClip(new File(path).toURI().toString());
            clip.setVolume(voiceVolume);
            clip.play();
        } catch (Exception e) {
            System.err.println("[AudioManager] Impossible de lire la voix : " + path);
        }
    }

    // ==== Setters ====
    public static void setMusicVolume(double v) { musicVolume = clamp(v); }
    public static void setSfxVolume(double v) { sfxVolume = clamp(v); }
    public static void setVoiceVolume(double v) { voiceVolume = clamp(v); }

    private static double clamp(double v) {
        return Math.min(Math.max(v, 0.0), 1.0);
    }

    public static double getMusicVolume() {
        return musicVolume;
    }

    public static double getSfxVolume() {
        return sfxVolume;
    }

    public static double getVoiceVolume() {
        return voiceVolume;
    }
}


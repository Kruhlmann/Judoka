package com.kruhlmann.judoka.sound;

import java.applet.Applet;
import java.applet.AudioClip;

import com.kruhlmann.judoka.JudokaComponent;

public class Sound {
	public static final Sound MUSIC_MENU = new Sound("/sound/title.wav");
	public static final Sound HIT1 = new Sound("/sound/hit1.wav");
	public static final Sound HIT2 = new Sound("/sound/hit2.wav");
	public static final Sound HIT3 = new Sound("/sound/hit3.wav");
	public static final Sound HIT4 = new Sound("/sound/hit4.wav");
	public static final Sound HAJIME = new Sound("/sound/hajime.wav");
	public static final Sound MATTE = new Sound("/sound/matte.wav");
	public static final Sound WUSH = new Sound("/sound/wush.wav");
	public static final Sound YUKO = new Sound("/sound/yuko.wav");
	public static final Sound WAZA = new Sound("/sound/waza.wav");
	public static final Sound IPPON = new Sound("/sound/ippon.wav");
	public static final Sound SONEMATTE = new Sound("/sound/sonematte.wav");
	public static final Sound GRAB = new Sound("/sound/grab.wav");
	public static final Sound SHIDO = new Sound("/sound/shido.wav");
	public static final Sound HANSOKUMAKE = new Sound("/sound/hansokumake.wav");
	public static final Sound FIGHTING_MUSIC = new Sound("/sound/fight.wav");
	public static final Sound ALT_FIGHTING_MUSIC = new Sound("/sound/fightAlt.wav");

	public static boolean alternativeBattleMusic = false;
	
	private AudioClip audio;

	public Sound(String path) {
		loadSound(path);
	}

	private void loadSound(String path) {
		audio = Applet.newAudioClip(Sound.class.getResource(path));
	}

	public void play(boolean loop) {
		if(JudokaComponent.mute) return;
		if (loop) {
			audio.loop();
		} else {
			audio.play();
		}
	}

	public void stop() {
		audio.stop();
	}

	public static void stopAll() {
		MUSIC_MENU.stop();
		FIGHTING_MUSIC.stop();
		ALT_FIGHTING_MUSIC.stop();
	}

}
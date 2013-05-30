package com.kruhlmann.judoka.graphics;

import java.awt.image.BufferedImage;

public class Animation {
	
	public static Animation MOROTE_SEOI_NAGE;
	public static Animation morote_seoi_nage;
	
	public static Animation UCHI_MATA;
	public static Animation uchi_mata;
	
	public static Animation O_SOTO_GARI;
	public static Animation o_soto_gari;
	
	public static Animation O_UCHI_GARI;
	public static Animation o_uchi_gari;
	
	//////
	
	public int ID;
	public BufferedImage[] SPRITES;
	
	public Animation(BufferedImage step1, BufferedImage step2, BufferedImage step3){
		if(step1 == null || step2 == null || step3 == null) {
			if(step1 == null) System.err.print("Step 1 was NPE. Address: " + this + "\n");
			if(step2 == null) System.err.print("Step 2 was NPE. Address: " + this + "\n");
			if(step3 == null) System.err.print("Step 3 was NPE. Address: " + this + "\n");
			throw new RuntimeException("Animation NEP");
		}
		SPRITES = new BufferedImage[]{step1, step2, step3};
	}
	
	
}

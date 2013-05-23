package com.kruhlmann.judoka.graphics;

import java.awt.image.BufferedImage;

import com.kruhlmann.judoka.JudokaComponent;

public class Animation {
	
	public static final Animation MOROTE_SEOI_NAGE = new Animation(JudokaComponent.MOROTE1, JudokaComponent.MOROTE2, JudokaComponent.MOROTE3);
	public static final Animation morote_seoi_nage = new Animation(JudokaComponent.judokaStanding1, JudokaComponent.judokaStanding2, JudokaComponent.judokaStanding1);
	
	public int ID;
	public BufferedImage[] SPRITES;
	
	public static Animation UCHI_MATA = new Animation(JudokaComponent.uchiMata1, JudokaComponent.uchiMata2, JudokaComponent.uchiMata3);
	public static Animation uchi_mata = new Animation(JudokaComponent.judokaStanding1, JudokaComponent.judokaStanding2, JudokaComponent.judokaStanding1);

	public static Animation O_SOTO_GARI = new Animation(JudokaComponent.oSotoGari1, JudokaComponent.oSotoGari2, JudokaComponent.oSotoGari3);
	public static Animation o_soto_gari = new Animation(JudokaComponent.judokaStanding1, JudokaComponent.judokaStanding2, JudokaComponent.judokaStanding1);
	
	public Animation(BufferedImage step1, BufferedImage step2, BufferedImage step3){
		BufferedImage[] SPRITES = {step1, step2, step3};
		this.SPRITES = SPRITES;
	}
}

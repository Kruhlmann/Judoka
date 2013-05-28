package com.kruhlmann.judoka.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.kruhlmann.judoka.JudokaComponent;
import com.kruhlmann.judoka.input.InputHandler;
import com.kruhlmann.judoka.level.Level;
import com.kruhlmann.judoka.technique.Technique;

public class Entity {

	
	public Technique currentTechnique;
	public PlayerState playerState;
	public BufferedImage playerImage;	
	
	public boolean moving;
	public int ippons, wazaaris, yukos, shidos;
	public int energy = 400;
	public int x;
	public int y;
	public int cooldown = 0;
	
	//Techniques
	public Technique forward = Technique.O_SOTO_GARI;
	public Technique backward = Technique.UCHI_MATA;
	public Technique backwardUp = Technique.MOROTE_SEOI_NAGE;
	
	protected InputHandler input = JudokaComponent.input;
	protected Level level;
	
	//Timing
	protected int throwTimer;
	protected int standTimer;
	protected int moveTimer;
	protected int moveCount;
	protected int endThrowTimer;
	
	public Entity(BufferedImage playerImage, Level level){
		this.playerImage = playerImage;
		this.level = level;
		playerState = PlayerState.NOT_GRIPPING;
		y = level.getDojoYOffSet();
	}
	
	/**
	 * Get x position
	 * @return int x
	 */
	public int getX(){ return x; }
	
	/**
	 * get y position
	 * @return int y
	 */
	public int getY(){ return y; }
	
	public void render(Graphics g){
		g.setColor(new Color(255, 255, 158));
		if(!level.matchIsOver) g.fillRect(x + 120 / 4, y, cooldown, 20);
	}
	public void update(){}
}

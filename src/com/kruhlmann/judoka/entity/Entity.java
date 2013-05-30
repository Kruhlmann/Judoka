package com.kruhlmann.judoka.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.kruhlmann.judoka.JudokaComponent;
import com.kruhlmann.judoka.input.InputHandler;
import com.kruhlmann.judoka.level.Level;
import com.kruhlmann.judoka.technique.Technique;

public class Entity {

	
	public Technique currentTechnique;
	public PlayerState playerState;
	protected BufferedImage playerImage;	
	
	public boolean moving;
	public int ippons, wazaaris, yukos, shidos;
	public int energy = 400;
	public int x;
	public int y;
	public int cooldown = 0;
	
	public Technique[] techniques = new Technique[8];
	
	protected InputHandler input = JudokaComponent.input;
	protected Level level;
	
	//Timing
	protected int throwTimer;
	protected int standTimer;
	protected int moveTimer;
	protected int moveCount;
	protected int endThrowTimer;
	
	public Entity(BufferedImage playerImage, Level level, String name){
		this.playerImage = playerImage;
		this.level = level;
		try {
			this.techniques = JudokaComponent.propertyFileHandler.loadPlayerTechniques(name);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		this.playerState = PlayerState.NOT_GRIPPING;
		this.y = level.getDojoYOffSet();
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
	
	public void setPlayerImage(BufferedImage playerImage){
		if (playerImage == null) throw new RuntimeException("Set player image to null.");
		this.playerImage = playerImage;
	}
	
	public BufferedImage getPlayerImage(){
		return playerImage;
	}
	
	public void setTechniques(Technique[] techniques){
		this.techniques = techniques;
	}
	
}

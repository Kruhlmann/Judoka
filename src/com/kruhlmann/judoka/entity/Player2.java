package com.kruhlmann.judoka.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.kruhlmann.judoka.JudokaComponent;
import com.kruhlmann.judoka.input.InputHandler;
import com.kruhlmann.judoka.level.Level;
import com.kruhlmann.judoka.sound.Sound;
import com.kruhlmann.judoka.technique.Technique;

public class Player2{
	
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
	
	private InputHandler input = JudokaComponent.input;
	private Level level;
	
	//Timing
	private int throwTimer;
	private int standTimer;
	private int moveTimer;
	private int moveCount;
	private int endThrowTimer;
	
	public Player2(BufferedImage playerImage, Level level) {
		this.playerImage = playerImage;
		this.level = level;
		playerState = PlayerState.NOT_GRIPPING;
		
		x = level.getDojoBorder(false, level.dojo);
		y = level.getDojoYOffSet();
	}
	
	public void render(int x, int y, Graphics g){
		g.setColor(new Color(255, 255, 158));
		if(!level.matchIsOver) g.fillRect(x + 120 / 4, y, cooldown, 20);
		renderImage(x, y, g);
	}
	
	/**
	 * Update player logic
	 */
	public void update(){
		//Variable initialization
		if(playerState != PlayerState.BEING_THROWN && playerState != PlayerState.THROWING) throwTimer = 0;
		//END variable initialization
		
		//Energy loss by movement
		if(moveCount == 15) { energy --; moveCount = 0; }
		//END energy loss by movement
		
		//Gripping check
		if(playerState == PlayerState.NOT_GRIPPING && x - level.player1.getX() < level.getDojoScale(level.dojo) * 12) {
			playerState = PlayerState.GRIPPING;
			level.player1.playerState = PlayerState.GRIPPING;
			Sound.GRAB.play(false);
		}
		//END gripping check
		
		//Timing
		if(moving || (level.player1.moving && playerState == PlayerState.GRIPPING)) {
			moveCount ++;
			if(playerState == PlayerState.GRIPPING || playerState == PlayerState.NOT_GRIPPING) moveTimer++;
		}else{
			if(playerState == PlayerState.GRIPPING || playerState == PlayerState.NOT_GRIPPING) standTimer++;
			if(playerState == PlayerState.THROWING || playerState == PlayerState.BEING_THROWN) throwTimer++;
		}
		//END timing
		
		//Image determination
		if(moving){
			if(playerState == PlayerState.GRIPPING || playerState == PlayerState.NOT_GRIPPING) {
				if(moveTimer % 30 < 15) playerImage = JudokaComponent.judokaWalking1;
				else playerImage = JudokaComponent.judokaWalking2;
			}
		}else{
			if(playerState == PlayerState.GRIPPING || playerState == PlayerState.NOT_GRIPPING) {
				if(standTimer % 40 < 20) playerImage = JudokaComponent.judokaStanding1;
				else playerImage = JudokaComponent.judokaStanding2;
			}
			//Throw animation
			if(playerState == PlayerState.THROWING){
				if		(throwTimer % 120 < 40) playerImage = currentTechnique.ANIMATION.SPRITES[0];
				else if	(throwTimer % 120 < 80) playerImage = currentTechnique.ANIMATION.SPRITES[1];
				else if	(throwTimer % 120 < 120) playerImage = currentTechnique.ANIMATION.SPRITES[2];
			}else if(playerState == PlayerState.BEING_THROWN){
				if		(throwTimer % 120 < 40) playerImage = level.player1.currentTechnique.OPPONENT_ANIMATION.SPRITES[0];
				else if	(throwTimer % 120 < 80) playerImage = level.player1.currentTechnique.OPPONENT_ANIMATION.SPRITES[1];
				else if	(throwTimer % 120 < 120) playerImage = level.player1.currentTechnique.OPPONENT_ANIMATION.SPRITES[2];
			}
			if(throwTimer > 120 && playerState == PlayerState.THROWING){
				currentTechnique.performOnP1(level);
				throwTimer = 0;
				if(currentTechnique.SUCCESS){
					playerState = PlayerState.MATTE;
				}else{
					if(currentTechnique.BROKE_GRIB){
						playerState = PlayerState.NOT_GRIPPING;
						x += 20;
					}else{
						playerState = PlayerState.GRIPPING;
					}
				}
			}else if(throwTimer > 130 && playerState == PlayerState.BEING_THROWN){
				if(level.player1.currentTechnique.SUCCESS){
					playerState = PlayerState.THROWN;
				}else{
					if(level.player1.currentTechnique.BROKE_GRIB){
						playerState = PlayerState.NOT_GRIPPING;
						x += 20;
					}else{
						playerState = PlayerState.GRIPPING;
					}
				}
			}
			//END throw animation
			
			if(playerState == PlayerState.MATTE){
				playerImage = JudokaComponent.judokaMatte;
			}
			if(playerState == PlayerState.THROWN){
				playerImage = JudokaComponent.judokaFallen;
			}
			if(playerState == PlayerState.WINNER){
				playerImage = JudokaComponent.judokaMatte;
			}
			if(playerState == PlayerState.LOSER){
				playerImage = JudokaComponent.judokaLost;
			}
		}
		//END image determination
		
		//End-throw animation logic
		if(playerState == PlayerState.MATTE && level.player1.playerState == PlayerState.THROWN && endThrowTimer < 120) 
			endThrowTimer ++;
		else if(playerState == PlayerState.MATTE && level.player1.playerState == PlayerState.THROWN && endThrowTimer >= 120){
			endThrowTimer = 0;
			level.matte(false);
		}
		//END end-throw animation logic
		
		//Throw logic
		if(input.enter && playerState == PlayerState.GRIPPING && cooldown == 0){
			if(input.left && !input.d && !input.up && !input.down) {
				currentTechnique = forward;
				playerState = PlayerState.THROWING;
				level.player1.playerState = PlayerState.BEING_THROWN;
			}else if(input.right && !input.a && !input.up && !input.down) {
				currentTechnique = backward;
				playerState = PlayerState.THROWING;
				level.player1.playerState = PlayerState.BEING_THROWN;
			}else if(input.right && !input.a && input.up && !input.down) {
				currentTechnique = backwardUp;
				playerState = PlayerState.THROWING;
				level.player1.playerState = PlayerState.BEING_THROWN;
			}
		}
		//END throw logic
		
		cooldown --;
		
		//Overflow handler
		if(energy > 400) energy = 400;
		if(standTimer == Integer.MAX_VALUE) 	standTimer = 0;
		if(moveTimer == Integer.MAX_VALUE) 		moveTimer = 0;
		if(throwTimer == Integer.MAX_VALUE) 	throwTimer = 0;
		if(moveCount == Integer.MAX_VALUE) 		moveCount = 0;
		if(endThrowTimer == Integer.MAX_VALUE) 	endThrowTimer = 0;
		if(cooldown <= 0) 						cooldown = 0;
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

	/**
	 * Renders the player, flipped
	 * @param x : x position
	 * @param y : y position
	 * @param g : graphics to render with
	 */
	public void renderImage(int x, int y, Graphics g) {
		BufferedImage flippedPlayerImage = JudokaComponent.getFlippedImage(playerImage);
		g.drawImage(flippedPlayerImage, x, y, 16 * level.getDojoScale(level.dojo), 24 * level.getDojoScale(level.dojo), null);
	}

}

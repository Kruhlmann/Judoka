package com.kruhlmann.judoka.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.kruhlmann.judoka.JudokaComponent;
import com.kruhlmann.judoka.level.Level;
import com.kruhlmann.judoka.sound.Sound;

public class Player2 extends Entity{
	
	public Player2(BufferedImage playerImage, Level level, String name) {
		super(playerImage, level, name);
		x = level.getDojoBorder(false, level.dojo);
	}
	
	public void render(int x, int y, Graphics g){
		super.render(g);
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
				if(moveTimer % 30 < 15) setPlayerImage(JudokaComponent.judokaWalking1);
				else setPlayerImage(JudokaComponent.judokaWalking2);
			}
		}else{
			if(playerState == PlayerState.GRIPPING || playerState == PlayerState.NOT_GRIPPING) {
				if(standTimer % 40 < 20) setPlayerImage(JudokaComponent.judokaStanding1);
				else setPlayerImage(JudokaComponent.judokaStanding2);
			}
			//Throw animation
			if(playerState == PlayerState.THROWING){
				if		(throwTimer % 120 < 40) setPlayerImage(currentTechnique.ANIMATION.SPRITES[0]);
				else if	(throwTimer % 120 < 80) setPlayerImage(currentTechnique.ANIMATION.SPRITES[1]);
				else if	(throwTimer % 120 < 120) setPlayerImage(currentTechnique.ANIMATION.SPRITES[2]);
			}else if(playerState == PlayerState.BEING_THROWN){
				if		(throwTimer % 120 < 40) setPlayerImage(level.player1.currentTechnique.OPPONENT_ANIMATION.SPRITES[0]);
				else if	(throwTimer % 120 < 80) setPlayerImage(level.player1.currentTechnique.OPPONENT_ANIMATION.SPRITES[1]);
				else if	(throwTimer % 120 < 120) setPlayerImage(level.player1.currentTechnique.OPPONENT_ANIMATION.SPRITES[2]);
			}
			if(throwTimer > 120 && playerState == PlayerState.THROWING){
				currentTechnique.perform(level.player1, this);
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
				setPlayerImage(JudokaComponent.judokaMatte);
			}
			if(playerState == PlayerState.THROWN){
				setPlayerImage(JudokaComponent.judokaFallen);
			}
			if(playerState == PlayerState.WINNER){
				setPlayerImage(JudokaComponent.judokaMatte);
			}
			if(playerState == PlayerState.LOSER){
				setPlayerImage(JudokaComponent.judokaLost);
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
				currentTechnique = techniques[5];
				playerState = PlayerState.THROWING;
				level.player1.playerState = PlayerState.BEING_THROWN;
			}else if(input.right && !input.a && !input.up && !input.down) {
				currentTechnique = techniques[2];
				playerState = PlayerState.THROWING;
				level.player1.playerState = PlayerState.BEING_THROWN;
			}else if(input.right && !input.a && input.up && !input.down) {
				currentTechnique = techniques[3];
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

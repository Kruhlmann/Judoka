package com.kruhlmann.judoka.level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import com.kruhlmann.judoka.JudokaComponent;
import com.kruhlmann.judoka.entity.Player;
import com.kruhlmann.judoka.entity.Player2;
import com.kruhlmann.judoka.entity.PlayerState;
import com.kruhlmann.judoka.sound.Sound;

public class Level {

	public boolean paused = false;
	public boolean multiplayer;
	public boolean matchIsOver;
	public boolean stopUpdating;
	
	public int difficulty;
	public int dojo;
	public int clock = 240;
	public int pauseClock;
	
	public Player player1;
	public Player2 player2;
	
	public String endText;
	
	/**
	 * Level constructor
	 * @param multiplayer : Is game multiplayer
	 * @param difficulty : Difficulty of the AI
	 * @param dojo : Defines the dojo id used for background image
	 */
	public Level(boolean multiplayer, int difficulty, int dojo) {
		this.multiplayer = multiplayer;
		this.difficulty = difficulty;
		this.dojo = dojo;
		player1 = new Player(JudokaComponent.judokaStanding1, this);
		player2 = new Player2(JudokaComponent.judokaStanding1, this);
	}

	/**
	 * Gets the dojo image from the dojo integer
	 * @return Image dojo
	 */
	public Image getDojoImage() {
		if     (dojo == 1) return JudokaComponent.dojo1;
		else if(dojo == 2) return JudokaComponent.dojo2;
		return null;
	}
	
	/**
	 * Get the depth of the dojo
	 * @return Integer depth
	 */
	public int getDojoYOffSet() {
		if     (dojo == 1) return JudokaComponent.HEIGHT - 294;
		else if(dojo == 2) return JudokaComponent.HEIGHT - 294;
		return 0;
	}

	/**
	 * Renders the level content
	 */
	public void render(Graphics g){
		g.setColor(new Color(81, 217, 61));
		g.fillRect(50, 40, player1.energy, 30);
		g.fillRect(JudokaComponent.WIDTH - 50, 40, -player2.energy, 30);
		g.setColor(Color.RED);
		g.fillRect(450, 40, player1.energy - 400, 30);
		g.fillRect(JudokaComponent.WIDTH - 450, 40, 400 - player2.energy, 30);
		
		g.drawImage(JudokaComponent.flag, 140, 70, 40, 60, null);
		g.drawImage(JudokaComponent.flag, 200, 70, 40, 60, null);
		g.drawImage(JudokaComponent.flag, 260, 70, 40, 60, null);
		g.drawImage(JudokaComponent.flag, 320, 70, 40, 60, null);

		g.drawImage(JudokaComponent.getFlippedImage(JudokaComponent.flag), 590, 70, 40, 60, null);
		g.drawImage(JudokaComponent.getFlippedImage(JudokaComponent.flag), 650, 70, 40, 60, null);
		g.drawImage(JudokaComponent.getFlippedImage(JudokaComponent.flag), 710, 70, 40, 60, null);
		g.drawImage(JudokaComponent.getFlippedImage(JudokaComponent.flag), 770, 70, 40, 60, null);

		g.drawImage(JudokaComponent.getFlippedImage(JudokaComponent.timeFlag), 416, 103, 140, 70, null);
		
		g.setFont(JudokaComponent.hugeFont);
		g.setColor(Color.BLACK);
		g.fillRect(46, 36, 404, 4);
		g.fillRect(450, 74, 4, -38);
		g.fillRect(46, 70, 404, 4);
		g.fillRect(46, 74, 4, -38);
		if(paused) g.setColor(Color.RED);
		else g.setColor(new Color(81, 217, 61));
		g.drawString(clock + "", JudokaComponent.WIDTH / 2 - (clock + "").length() * 15, 154);

		g.setColor(Color.BLACK);
		g.fillRect(JudokaComponent.WIDTH - 450, 36, 400, 4);
		g.fillRect(JudokaComponent.WIDTH - 50, 74, 4, -38);
		g.fillRect(JudokaComponent.WIDTH - 450, 70, 400, 4);
		g.fillRect(JudokaComponent.WIDTH - 454, 74, 4, -38);
		
		g.setColor(Color.WHITE);
		g.drawString(player1.yukos + "", 144, 114);
		g.drawString(player1.wazaaris + "", 204, 114);
		g.drawString(player1.ippons + "", 264, 114);

		g.drawString(player2.yukos + "", 774, 114);
		g.drawString(player2.wazaaris + "", 714, 114);
		g.drawString(player2.ippons + "", 654, 114);
		
		g.setColor(Color.RED);
		g.drawString(player1.shidos + "", 324, 114);
		g.drawString(player2.shidos + "", 594, 114);
		
		g.setColor(Color.WHITE);
		if(player1.playerState == PlayerState.WINNER) g.drawString("WINNER", player1.getX(), JudokaComponent.HEIGHT - 350);
		if(player2.playerState == PlayerState.WINNER) g.drawString("WINNER", player2.getX(), JudokaComponent.HEIGHT - 350);
		if(player1.playerState == PlayerState.LOSER) g.drawString("LOSER", player1.getX(), JudokaComponent.HEIGHT - 350);
		if(player2.playerState == PlayerState.LOSER) g.drawString("LOSER", player2.getX(), JudokaComponent.HEIGHT - 350);
		
		if(player2.getX() <  JudokaComponent.WIDTH / 2) 
			g.drawImage(JudokaComponent.refereeLeft, JudokaComponent.WIDTH / 2 - 16 * (getDojoScale(dojo) - 2) / 2, getDojoYOffSet(), 16 * (getDojoScale(dojo) - 2), 24 * (getDojoScale(dojo) - 2), null);
		else
			g.drawImage(JudokaComponent.refereeRight, JudokaComponent.WIDTH / 2 - 16 * (getDojoScale(dojo) - 2) / 2, getDojoYOffSet(), 16 * (getDojoScale(dojo) - 2), 24 * (getDojoScale(dojo) - 2), null);
		
		player1.render(player1.getX(), player1.getY(), g);
		player2.render(player2.getX(), player2.getY(), g);
		
		g.setFont(JudokaComponent.bigFont);
		if(matchIsOver) g.drawString(endText, 210, 500);
	}
	
	/**
	 * Updates the game logic and everything contained inside the level
	 */
	public void update(){
		
		//Prevents interference from input while throwing
		if(player1.playerState != PlayerState.GRIPPING && player1.playerState != PlayerState.NOT_GRIPPING){
			JudokaComponent.input.flush();
		}
		
		//Checks if the match is over and it should stop updating
		if(!stopUpdating){
			player1.moving = false;
			player2.moving = false;
			
			if(player1.playerState == PlayerState.NOT_GRIPPING){
				int player1X = player1.x;
				int player2X = player2.x;
				if(JudokaComponent.input.a) player1.x -= 4;
				if(JudokaComponent.input.d) player1.x += 4;
				if(JudokaComponent.input.left) player2.x -= 4;
				if(JudokaComponent.input.right) player2.x += 4;
				if(player1.x != player1X) player1.moving = true;
				if(player2.x != player2X) player2.moving = true;
			}else if(player1.playerState == PlayerState.GRIPPING){
				if((JudokaComponent.input.a && !JudokaComponent.input.right) || (JudokaComponent.input.left && !JudokaComponent.input.d)){
					player1.moving = true;
					player2.moving = true;
					player1.x -= 4;
					player2.x -= 4;
				}else if((JudokaComponent.input.d && !JudokaComponent.input.left) || (JudokaComponent.input.right && !JudokaComponent.input.a)){
					player1.moving = true;
					player2.moving = true;
					player1.x += 4;
					player2.x += 4;
				}
			}
			
			player1.update();
			player2.update();
			if(JudokaComponent.input.escape) JudokaComponent.changeMenuFromLevel(JudokaComponent.MAIN);
		}else{
			if(JudokaComponent.input.escape || JudokaComponent.input.enter || JudokaComponent.input.space) JudokaComponent.changeMenuFromLevel(JudokaComponent.MAIN);
		}
		
		//Checks if the match is over and then ends the match
		if(!matchIsOver){
			if(player1.ippons > 0) endMatch(player1, false);
			else if(player2.ippons > 0) endMatch(player2, false);
			else if(player1.wazaaris > 1) endMatch(player1, false);
			else if(player2.wazaaris > 1) endMatch(player2, false);
			if(clock <= 0){
				clock = 0;		
			}
		}else{
			stopUpdating = true;
		}
		
		//Shido check
		if (player1.x < getDojoBorder(true,dojo) - (player1.getPlayerImage().getWidth() * (int) (getDojoScale(dojo) * 0.8))) {
			player1.shidos ++;
			if(player1.shidos > 3){
				Sound.HANSOKUMAKE.play(false);
				endMatch(player2, true);
			}else{
				matte(true);
				Sound.SHIDO.play(false);
			}
		}
		if(player2.x > getDojoBorder(false, dojo) + (player1.getPlayerImage().getWidth() * getDojoScale(dojo))) {
			player2.shidos ++;
			if(player2.shidos > 3){
				Sound.HANSOKUMAKE.play(false);
				endMatch(player1, true);
			}else{
				matte(true);
				Sound.SHIDO.play(false);
			}
		}
		//END shido check
	}

	
	/**
	 * Keeps track of the game time
	 */
	public void timer(){
		if(!paused) clock --;
		else {
			pauseClock ++;
			if(pauseClock > 3) hajime();
		}
		if(pauseClock == Integer.MAX_VALUE) pauseClock = 0;
	}
	
	/**
	 * Pauses match
	 * @param silent : decides whether the referee says matte
	 */
	public void matte(boolean silent) {
		if(!silent) Sound.MATTE.play(false);
		player1.playerState = PlayerState.MATTE;
		player2.playerState = PlayerState.MATTE;
		player1.x = getDojoBorder(true, dojo);
		player2.x = getDojoBorder(false, dojo);
		player1.energy += 5;
		player2.energy += 5;
		paused = true;
		pauseClock = 0;
	}
	
	/**
	 * Starts the match after hajime
	 */
	public void hajime() {
		if(matchIsOver) return;
		if(JudokaComponent.gameState != JudokaComponent.GameState.MENU) Sound.HAJIME.play(false);
		player1.playerState = PlayerState.NOT_GRIPPING;
		player2.playerState = PlayerState.NOT_GRIPPING;
		paused = false;
		pauseClock = 0;
	}
	
	/**
	 * Ends the match with player1 as winner
	 * @param winner : Player to win
	 * @param silent : announce silent
	 */
	public void endMatch(Player winner, boolean silent){
		if(!silent) Sound.SONEMATTE.play(false);
		matte(true);
		matchIsOver = true;
		endText = "Player 1 wins the match!";
		player1.playerState = PlayerState.WINNER;
		player2.playerState = PlayerState.LOSER;
		player1.x = getDojoBorder(true, dojo);
		player2.x = getDojoBorder(false, dojo);
	}

	/**
	 * Ends the match with player2 as winner
	 * @param winner : Player2 to win
	 * @param silent : announce silent
	 */
	public void endMatch(Player2 winner, boolean silent){
		if(!silent) Sound.SONEMATTE.play(false);
		matte(true);
		matchIsOver = true;
		endText = "Player 2 wins the match!";
		player2.playerState = PlayerState.WINNER;
		player1.playerState = PlayerState.LOSER;
		player1.x = getDojoBorder(true, dojo);
		player2.x = getDojoBorder(false, dojo);
	}

	/**
	 * Gets the border position of the dojo
	 * @param close : if true, then player 1 values are returned.
	 * @param dojo : id of the dojo
	 * @return Integer border
	 */
	public int getDojoBorder(boolean close, int dojo) {
		if(close){
			if     (dojo == 1) return 50;
			else if(dojo == 2) return 220;
		}else{
			if     (dojo == 1) return JudokaComponent.WIDTH - 250;
			else if(dojo == 2) return JudokaComponent.WIDTH - 350;
		}
		return 0;
	}

	/**
	 * Returns the scale of the player model for specified dojo
	 * @param dojo : id of dojo
	 * @return Integer scale
	 */
	public int getDojoScale(int dojo) {
		if     (dojo == 1) return 10;
		else if(dojo == 2) return 8;
		return 0;
	}
	
	/**
	 * Returns the walked speed of the dojo
	 * @param dojo : dojo id
	 * @return Integer speed
	 */
	public int getDojoSpeed(int dojo){
		if     (dojo == 1) return 4;
		else if(dojo == 2) return 3;
		return 0;
	}
	
}

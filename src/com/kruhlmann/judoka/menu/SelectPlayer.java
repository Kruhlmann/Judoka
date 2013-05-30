package com.kruhlmann.judoka.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.kruhlmann.judoka.JudokaComponent;
import com.kruhlmann.judoka.technique.Technique;

public class SelectPlayer extends Menu{
	
	private int timer = 0;
	private int dojo;
	
	private String player1Name = "", player2Name = "";
	
	/**
	 * Constructor of sub menu
	 */
	public SelectPlayer() {
		super();
	}

	/**
	 * Renders the menu elements
	 */
	public void render(Graphics g){
		g.setColor(Color.BLACK);
		g.setFont(JudokaComponent.bigFont);
		JudokaComponent.drawTextBox(75, 50, 500, 35, g);
		JudokaComponent.drawTextBox(75, 150, 500, 35, g);
		g.drawString("Player1 Character Name", 80, 40);
		g.drawString("Player2 Character Name", 80, 140);
		g.setColor(new Color(210, 210, 210));
		g.drawString("Player1 Character Name", 82, 42);
		g.drawString("Player2 Character Name", 82, 142);
		g.setColor(Color.BLACK);
		g.drawString(player1Name, 80, 80);
		g.drawString(player2Name, 80, 180);
		if(selectedItem == 0){
			if(timer % 60 < 30) g.fillRect(80 + player1Name.length() * 21, 54, 2, 30);
		}else if(selectedItem == 1){
			if(timer % 60 < 30) g.fillRect(80 + player2Name.length() * 21, 154, 2, 30);
		}else if(selectedItem == 2){
			g.setFont(JudokaComponent.hugeFont);
			g.drawString("> Start <", 175, 280);
		}
		if(selectedItem != 2){
			g.setFont(JudokaComponent.hugeFont);
			g.drawString("Start", 241, 280);
		}
	}
	

	/**
	 * Updates the menu logic
	 */
	public void update(){
		timer ++;
		int oldItem = selectedItem;
		if(JudokaComponent.input.up) selectedItem --;
		else if(JudokaComponent.input.down) selectedItem ++;
		
		if(selectedItem < 0) selectedItem = 0;
		if(selectedItem > 2) selectedItem = 2;
		
		if(JudokaComponent.input.enter && selectedItem == 2) {
			Technique[] player1Techniques = null;
			Technique[] player2Techniques = null;
			try {
				player1Techniques = JudokaComponent.propertyFileHandler.loadPlayerTechniques(player1Name);
				player2Techniques = JudokaComponent.propertyFileHandler.loadPlayerTechniques(player2Name);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JudokaComponent.changeLevel(false, dojo, 0, player1Techniques, player2Techniques);
			
		}
		if(JudokaComponent.input.escape) JudokaComponent.changeMenu(JudokaComponent.CREATEGAMEHUMAN);
		
		if(selectedItem == 0){
			player1Name += JudokaComponent.input.getTypedKey();
			if(JudokaComponent.input.backSpace && player1Name.length() > 0) player1Name = player1Name.substring(0, player1Name.length() - 1);
			if(player1Name.length() > 16) player1Name = player1Name.substring(0, player1Name.length() - 1);
		}else if(selectedItem == 1){
			player2Name += JudokaComponent.input.getTypedKey();
			if(JudokaComponent.input.backSpace && player2Name.length() > 0) player2Name = player2Name.substring(0, player2Name.length() - 1);
			if(player2Name.length() > 16) player2Name = player2Name.substring(0, player2Name.length() - 1);
		}
		
		if(timer == Integer.MAX_VALUE) timer = 0;
		if(oldItem != selectedItem) timer = 0;
	}
	
	/**
	 * Initializes the menu before use
	 */
	public void init(Object[] params){
		dojo = (Integer) params[0];
	}
	
}

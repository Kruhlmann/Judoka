package com.kruhlmann.judoka.menu;

import java.awt.Color;
import java.awt.Graphics;

import com.kruhlmann.judoka.JudokaComponent;

public class Singleplayer extends Menu{

	private String[] items = {
			"Against AI",
			"Against Human",
			"Back"
	};
	
	/**
	 * Constructor of sub menu
	 */
	public Singleplayer() {
		super();
	}
	
	/**
	 * Renders the menu elements
	 */
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(JudokaComponent.bigFont);

		for (int i = 0; i < items.length; i++) {
			if(i == selectedItem)
				g.drawString("> " + items[i] + " <" , JudokaComponent.WIDTH / 2 - (items[i].length() + 4) * 10 + 200, 50 + JudokaComponent.HEIGHT / 2 + i * 40);
			else
				g.drawString(items[i] , JudokaComponent.WIDTH / 2 - items[i].length() * 10 + 200, 50 + JudokaComponent.HEIGHT / 2 + i * 40);
		}
	}
	
	/**
	 * Updates the menu logic
	 */
	public void update(){
		if(JudokaComponent.input.up) selectedItem --;
		if(JudokaComponent.input.down) selectedItem ++;
		if(selectedItem > items.length - 1) selectedItem = items.length - 1;
		if(selectedItem < 1) selectedItem = 1;
		if(JudokaComponent.input.enter){
			if(selectedItem == 0) JudokaComponent.changeMenu(JudokaComponent.CREATEGAMEAI);
			else if(selectedItem == 1) JudokaComponent.changeMenu(JudokaComponent.CREATEGAMEHUMAN);
			else if(selectedItem == 2) JudokaComponent.changeMenu(JudokaComponent.MAIN);
		}
	}
}

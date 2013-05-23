package com.kruhlmann.judoka.menu;

import java.awt.Color;
import java.awt.Graphics;

import com.kruhlmann.judoka.JudokaComponent;

public class CreateGameHuman extends Menu {

	public int dojo = 1;
	private String[] items = {
			"Start Game",
			"Dojo",
			"Back"
	};

	/**
	 * Constructor of sub menu
	 */
	public CreateGameHuman() {
		super();
	}

	/**
	 * Renders the menu elements
	 */
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(JudokaComponent.bigFont);

		for (int i = 0; i < items.length; i++) {
			if(selectedItem == 1){
				if(i == selectedItem)
					g.drawString("> " + items[i] + " " + dojo + " <" , JudokaComponent.WIDTH / 2 - (items[i].length() + 6) * 10 + 200, 50 + JudokaComponent.HEIGHT / 2 + i * 40);
				else
					g.drawString(items[i] , JudokaComponent.WIDTH / 2 - (items[i].length() + 4) * 10 + 200, 50 + JudokaComponent.HEIGHT / 2 + i * 40);
			}else{
				if(i == selectedItem)
					g.drawString("> " + items[i] + " <" , JudokaComponent.WIDTH / 2 - (items[i].length() + 4) * 10 + 200, 50 + JudokaComponent.HEIGHT / 2 + i * 40);
				else
					g.drawString(items[i] , JudokaComponent.WIDTH / 2 - items[i].length() * 10 + 200, 50 + JudokaComponent.HEIGHT / 2 + i * 40);
			}
		}
	}
	

	/**
	 * Updates the menu logic
	 */
	public void update(){
		if(JudokaComponent.input.up) selectedItem --;
		if(JudokaComponent.input.down) selectedItem ++;
		if(selectedItem > items.length - 1) selectedItem = items.length - 1;
		if(selectedItem < 0) selectedItem = 0;
		if(JudokaComponent.input.enter && selectedItem == 0) JudokaComponent.changeLevel(false, dojo, 0);
		else if(JudokaComponent.input.enter && selectedItem == 1) dojo ++;
		else if(JudokaComponent.input.enter && selectedItem == 2) JudokaComponent.changeMenu(JudokaComponent.SINGLEPLAYER);
		
		if(dojo > 3) dojo = 1;
	}

}

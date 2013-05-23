package com.kruhlmann.judoka.menu;

import java.awt.Color;
import java.awt.Graphics;

import com.kruhlmann.judoka.JudokaComponent;

public class About extends Menu {

	/**
	 * Constructor of sub menu
	 */
	public About() {
		super();
	}

	/**
	 * Renders the menu elements
	 */
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(JudokaComponent.bigFont);
		g.drawString("> Back <" , JudokaComponent.WIDTH / 2 + 110, 220 + JudokaComponent.HEIGHT / 2);
	}

	/**
	 * Updates the menu logic
	 */
	public void update(){
		if(JudokaComponent.input.enter){
			JudokaComponent.changeMenu(JudokaComponent.MAIN);
		}
	}
	
}

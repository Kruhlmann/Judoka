package com.kruhlmann.judoka.menu;

import java.awt.Graphics;

public class Menu {
	protected int selectedItem;

	/**
	 * Menu constructor
	 */
	public Menu(){
	}
	
	public void update(){ }
	public void render(Graphics g){ }
	public void init(){ 
		selectedItem = 0;
	}
	
}

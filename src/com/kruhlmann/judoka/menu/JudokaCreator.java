package com.kruhlmann.judoka.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.kruhlmann.judoka.JudokaComponent;
import com.kruhlmann.judoka.technique.Technique;

public class JudokaCreator extends Menu{

	public String name = "Judoka #1"; // 16 max length
	public int timer;
	public String[] techniqueTitle = {
			"Up",
			"Down",
			"Back",
			"Back + Up",
			"Back + Down",
			"Forward",
			"Forward + Up",
			"Forward + Down"
	};
	
	public Technique[] techniques = {
		null,
		Technique.UCHI_MATA,
		null,
		null,
		null,
		null,
		null,
		null,
	};
	
	/**
	 * Constructor of sub menu
	 */
	public JudokaCreator() {
		super();
	}

	/**
	 * Renders the menu elements
	 */
	public void render(Graphics g){
		g.setColor(Color.BLACK);
		g.setFont(JudokaComponent.bigFont);
		if(selectedItem == -1)JudokaComponent.drawTextBox(170, 160, 350, 35, timer, name, true, g);
		else JudokaComponent.drawTextBox(170, 160, 350, 35, timer, name, false, g);
			
		g.drawImage(JudokaComponent.judokaMatte, 640, 120, 160, 240, null);
		
		g.setColor(Color.BLACK);
		g.drawString(name, 175, 187);
		if(timer % 60 < 30 && selectedItem == -1)g.drawRect(175 + name.length() * 21, 165, 1, 27);
		
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("Courier new", 1, 45));
		g.drawString("Techniques", 190, 235);
		g.drawString("Name", 190, 150);
		g.drawRect(185, 236, 280, 1);
		g.drawRect(185, 151, 116, 1);
		
		g.setColor(Color.BLACK);
		g.setFont(JudokaComponent.stdFont);
		for(int i = 0; i < techniqueTitle.length * 35; i += 35){
			g.setColor(Color.BLACK);
			g.setFont(JudokaComponent.stdFont);
			g.drawString(techniqueTitle[i / 35], 450, 270 + i);
			g.drawRect(150, 250 + i, 280, 24);
			g.drawRect(150, 250 + i, 24, 24);
			g.setColor(Color.WHITE);
			g.fillRect(175, 251 + i, 255, 22);
			g.setColor(Color.BLACK);
			g.drawImage(JudokaComponent.pen, 150, 250 + i, 24, 24, null);
			if(i / 35 == selectedItem && selectedItem != -1){
				g.drawString(">", 130, 270 + i);
			}
			g.setFont(JudokaComponent.bigFont);
			if(selectedItem == 8) g.drawString("> Save <", 640, 390);
			else g.drawString("Save", 682, 390);
			
			g.setFont(JudokaComponent.stdFont);
			if(techniques[i / 35] != null) g.drawString(techniques[i / 35].NAME, 177, 270 + i);
			else{
				g.setColor(Color.RED);
				g.drawString("NONE", 177, 270 + i);
				g.setColor(Color.BLACK);
			}
			
			
		}
	}
	

	/**
	 * Updates the menu logic
	 */
	public void update(){
		timer ++;
		
		if(JudokaComponent.input.up) selectedItem --;
		if(JudokaComponent.input.down) selectedItem ++;
		if(JudokaComponent.input.right) selectedItem  = 8;
		if(JudokaComponent.input.left) selectedItem = 0;
		if(JudokaComponent.input.escape) JudokaComponent.changeMenu(JudokaComponent.MAIN);
		
		if(selectedItem < -1) selectedItem = -1;
		if(selectedItem > 8) selectedItem = 8;
		
		if(selectedItem == -1) {
			name += JudokaComponent.input.getTypedKey();
			if(JudokaComponent.input.backSpace && name.length() > 0) name = name.substring(0, name.length() - 1);
			if(name.length() > 16) name = name.substring(0, name.length() - 1);
		}
		
		//Overflow handler
		if(timer == Integer.MAX_VALUE) timer = 0;
	}
	
	public void init(){
		selectedItem = -1;
		JudokaComponent.menuImage = JudokaComponent.createCharacterImage;
	}

}

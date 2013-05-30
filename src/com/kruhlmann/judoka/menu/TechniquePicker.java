package com.kruhlmann.judoka.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.kruhlmann.judoka.JudokaComponent;
import com.kruhlmann.judoka.technique.Technique;

public class TechniquePicker extends Menu{
	
	private int category; // 0 back, 1 forward
	private int index;
	
	/**
	 * Constructor of sub menu
	 */
	public TechniquePicker() {
		super();
	}

	/**
	 * Renders the menu elements
	 */
	public void render(Graphics g){
		JudokaComponent.drawTextBox(50, 50, JudokaComponent.WIDTH - 100, JudokaComponent.HEIGHT - 100, g);
		g.setFont(new Font("Courier", 1, 11));
		g.setColor(Color.BLACK);
		if(category == 0){
			int x = 75;
			int y = 75;
			for(int i = 0; i < Technique.techniquesBack.length; i++){
				if(Technique.techniquesBack[i] == null){
					if(selectedItem == i){
						g.setColor(Color.BLUE);
						g.drawRect(x, y, 100, 150);
						g.setFont(JudokaComponent.bigFont);
						g.drawString("^", x + 40, y + 180);
						g.setFont(new Font("Courier", 1, 11));
						g.setColor(Color.BLACK);
					}else g.drawRect(x, y, 100, 150);
					g.drawString("COMING SOON", x + 10, y - 5);
				}else{
					g.drawImage(Technique.techniquesBack[i].ANIMATION.SPRITES[0], x, y, 100, 150, null);
					if(selectedItem == i){
						g.setColor(Color.BLUE);
						g.drawRect(x, y, 100, 150);
						g.setFont(JudokaComponent.bigFont);
						g.drawString("^", x + 40, y + 180);
						g.setFont(new Font("Courier", 1, 11));
						g.setColor(Color.BLACK);
					}else g.drawRect(x, y, 100, 150);
					String name;
					if(Technique.techniquesBack[i].NAME.length() > 11) name = Technique.techniquesBack[i].NAME.substring(0, 9) + "...";
					else name = Technique.techniquesBack[i].NAME;
					g.drawString(name, x + 10 , y - 5);
				}
				if(x > 675) {
					x = 75;
					y += 220;
				}else x += 140;					
				
			}
		}else if(category == 1){
			int x = 75;
			int y = 75;
			for(int i = 0; i < Technique.techniquesForward.length; i++){
				if(Technique.techniquesForward[i] == null){
					if(selectedItem == i){
						g.setColor(Color.BLUE);
						g.drawRect(x, y, 100, 150);
						g.setFont(JudokaComponent.bigFont);
						g.drawString("^", x + 40, y + 180);
						g.setFont(new Font("Courier", 1, 11));
						g.setColor(Color.BLACK);
					}else g.drawRect(x, y, 100, 150);
					g.drawString("COMING SOON", x + 10, y - 5);
				}else{
					g.drawImage(Technique.techniquesForward[i].ANIMATION.SPRITES[0], x, y, 100, 150, null);
					if(selectedItem == i){
						g.setColor(Color.BLUE);
						g.drawRect(x, y, 100, 150);
						g.setFont(JudokaComponent.bigFont);
						g.drawString("^", x + 40, y + 180);
						g.setFont(new Font("Courier", 1, 11));
						g.setColor(Color.BLACK);
					}else g.drawRect(x, y, 100, 150);
					String name;
					if(Technique.techniquesForward[i].NAME.length() > 11) name = Technique.techniquesForward[i].NAME.substring(0, 9) + "...";
					else name = Technique.techniquesForward[i].NAME;
					g.drawString(name, x + 10 , y - 5);
				}
				if(x > 675) {
					x = 75;
					y += 220;
				}else x += 140;					
				
			}
		}else if(category == 3){
			int x = 75;
			int y = 75;
			for(int i = 0; i < Technique.techniquesUp.length; i++){
				if(Technique.techniquesUp[i] == null){
					if(selectedItem == i){
						g.setColor(Color.BLUE);
						g.drawRect(x, y, 100, 150);
						g.setFont(JudokaComponent.bigFont);
						g.drawString("^", x + 40, y + 180);
						g.setFont(new Font("Courier", 1, 11));
						g.setColor(Color.BLACK);
					}else g.drawRect(x, y, 100, 150);
					g.drawString("COMING SOON", x + 10, y - 5);
				}else{
					g.drawImage(Technique.techniquesUp[i].ANIMATION.SPRITES[0], x, y, 100, 150, null);
					if(selectedItem == i){
						g.setColor(Color.BLUE);
						g.drawRect(x, y, 100, 150);
						g.setFont(JudokaComponent.bigFont);
						g.drawString("^", x + 40, y + 180);
						g.setFont(new Font("Courier", 1, 11));
						g.setColor(Color.BLACK);
					}else g.drawRect(x, y, 100, 150);
					String name;
					if(Technique.techniquesUp[i].NAME.length() > 11) name = Technique.techniquesUp[i].NAME.substring(0, 9) + "...";
					else name = Technique.techniquesUp[i].NAME;
					g.drawString(name, x + 10 , y - 5);
				}
				if(x > 675) {
					x = 75;
					y += 220;
				}else x += 140;					
				
			}
		}else if(category == 4){
			int x = 75;
			int y = 75;
			for(int i = 0; i < Technique.techniquesDown.length; i++){
				if(Technique.techniquesDown[i] == null){
					if(selectedItem == i){
						g.setColor(Color.BLUE);
						g.drawRect(x, y, 100, 150);
						g.setFont(JudokaComponent.bigFont);
						g.drawString("^", x + 40, y + 180);
						g.setFont(new Font("Courier", 1, 11));
						g.setColor(Color.BLACK);
					}else g.drawRect(x, y, 100, 150);
					g.drawString("COMING SOON", x + 10, y - 5);
				}else{
					g.drawImage(Technique.techniquesDown[i].ANIMATION.SPRITES[0], x, y, 100, 150, null);
					if(selectedItem == i){
						g.setColor(Color.BLUE);
						g.drawRect(x, y, 100, 150);
						g.setFont(JudokaComponent.bigFont);
						g.drawString("^", x + 40, y + 180);
						g.setFont(new Font("Courier", 1, 11));
						g.setColor(Color.BLACK);
					}else g.drawRect(x, y, 100, 150);
					String name;
					if(Technique.techniquesDown[i].NAME.length() > 11) name = Technique.techniquesDown[i].NAME.substring(0, 9) + "...";
					else name = Technique.techniquesDown[i].NAME;
					g.drawString(name, x + 10 , y - 5);
				}
				if(x > 675) {
					x = 75;
					y += 220;
				}else x += 140;					
				
			}
		}
	}

	/**
	 * Updates the menu logic
	 */
	public void update(){
		int maxItem = 0;
		if(category == 0) maxItem = Technique.techniquesBack.length;
		else if(category == 1) maxItem = Technique.techniquesForward.length;
		else if(category == 3) maxItem = Technique.techniquesUp.length;
		else if(category == 4) maxItem = Technique.techniquesDown.length;
		
		if(JudokaComponent.input.escape) JudokaComponent.changeMenu(JudokaComponent.CREATE_JUDOKA);
		if(JudokaComponent.input.right) selectedItem ++;
		if(JudokaComponent.input.left) selectedItem --;
		if(JudokaComponent.input.down && selectedItem + 6 < maxItem) selectedItem += 6;
		if(JudokaComponent.input.up && selectedItem - 6 >= 0) selectedItem -= 6;
		
		if(JudokaComponent.input.enter){
			if(category == 0) JudokaComponent.CREATE_JUDOKA.setTechnique(index, Technique.techniquesBack[selectedItem]);
			if(category == 1) JudokaComponent.CREATE_JUDOKA.setTechnique(index, Technique.techniquesForward[selectedItem]);
			if(category == 3) JudokaComponent.CREATE_JUDOKA.setTechnique(index, Technique.techniquesUp[selectedItem]);
			if(category == 4) JudokaComponent.CREATE_JUDOKA.setTechnique(index, Technique.techniquesDown[selectedItem]);
			JudokaComponent.changeMenu(JudokaComponent.CREATE_JUDOKA);
		}
		
		if(selectedItem > maxItem - 1) selectedItem = maxItem - 1;
		if(selectedItem < 0) selectedItem = 0;
	}
	
	public void init(Object[] param){
		selectedItem = 0;
		category = (Integer) param[0];
		index = (Integer) param[1];
	}
}

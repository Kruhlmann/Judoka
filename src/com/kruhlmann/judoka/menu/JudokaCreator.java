package com.kruhlmann.judoka.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import com.kruhlmann.judoka.JudokaComponent;
import com.kruhlmann.judoka.menu.Menu;
import com.kruhlmann.judoka.technique.Technique;

public class JudokaCreator extends Menu{

	public String name = "Judoka #1"; // 16 max length
	public int timer, saveTimer;
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
	
	//public Technique[] techniques = new Technique[8];
	public Technique[] techniques = {
			Technique.UCHI_MATA,
			Technique.UCHI_MATA,
			Technique.UCHI_MATA,
			Technique.UCHI_MATA,
			Technique.UCHI_MATA,
			Technique.UCHI_MATA,
			Technique.UCHI_MATA,
			Technique.UCHI_MATA
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
		JudokaComponent.drawTextBox(170, 160, 350, 35, g);
			
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
		if(saveTimer < 180) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(700, JudokaComponent.HEIGHT - 100, 145, 200);
			g.setColor(Color.WHITE);
			g.setFont(JudokaComponent.stdFont);
			g.drawString("Saved!", 730, JudokaComponent.HEIGHT - 60);
			g.setColor(Color.BLACK);
		}
	}
	

	/**
	 * Updates the menu logic
	 */
	public void update(){
		if(saveTimer < 300) saveTimer ++;
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
		
		if(JudokaComponent.input.enter) {
			if(selectedItem > 1 && selectedItem < 5)JudokaComponent.changeMenu(JudokaComponent.TECHNIQUE_PICKER, new Object[]{0, selectedItem});
			else if(selectedItem > 4 && selectedItem != 8)JudokaComponent.changeMenu(JudokaComponent.TECHNIQUE_PICKER, new Object[]{1, selectedItem});
			else if(selectedItem == 0)JudokaComponent.changeMenu(JudokaComponent.TECHNIQUE_PICKER, new Object[]{3, selectedItem});
			else if(selectedItem == 1)JudokaComponent.changeMenu(JudokaComponent.TECHNIQUE_PICKER, new Object[]{4, selectedItem});
			else if(selectedItem == 8){
				boolean notReadyToSave = false;
				for(Technique t: techniques) {
					if(t == null ) notReadyToSave = true;
				}
				if (!notReadyToSave){
					try {
						JudokaComponent.propertyFileHandler.saveJudoka(techniques, name);
						displaySuccessfulSave();
					} catch (FileNotFoundException e) { e.printStackTrace();
					} catch (ParserConfigurationException e) { e.printStackTrace();
					} catch (IOException e) { e.printStackTrace(); 
					}
				}
			}
		}
		
		
		
		
		//Overflow handler
		if(timer == Integer.MAX_VALUE) timer = 0;
	}
	
	public void init(){
		selectedItem = -1;
		JudokaComponent.menuImage = JudokaComponent.createCharacterImage;
	}
	
	public void setTechnique(int index, Technique technique){
		techniques[index] = technique;
	}
	
	public void displaySuccessfulSave(){
		saveTimer = 0;
	}

}

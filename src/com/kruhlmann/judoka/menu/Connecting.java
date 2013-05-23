package com.kruhlmann.judoka.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class Connecting {
	
	public static ArrayList<String> connectingConsole = new ArrayList<String>();
	public static ArrayList<Integer> warningStates = new ArrayList<Integer>();
	
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier", 1, 20));
		for(int i = 0; i < connectingConsole.size(); i ++){
			if(warningStates.get(i) == 1) g.setColor(Color.RED);
			g.drawString(connectingConsole.get(i), 15, 35 + (i * g.getFont().getSize()));
			g.setColor(Color.WHITE);
		}
		g.drawString("Press ESC to return", 320, 460);
	}

	public static void console(String string, int type) {
		connectingConsole.add(string);
		warningStates.add(type);
	}
}

package com.kruhlmann.judoka.input;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.kruhlmann.judoka.JudokaComponent;

public class InputHandler implements KeyListener, FocusListener{

	public boolean[] keys;
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	public boolean space;
	public boolean enter;
	public boolean period;
	public boolean backSpace;
	public boolean escape;
	
	public boolean q,w,e,r,t,y,u,i,o,p,a,s,d,f,g,h,j,k,l,z,x,c,v,b,n,m;
	public boolean k1,k2,k3,k4,k5,k6,k7,k8,k9,k0;
	
	public boolean menuDown;
	public boolean menuUp;
	public boolean focused;
	public boolean menuRight;
	public boolean menuLeft;
	
	public InputHandler(){
		keys = new boolean[65536];
	}
	
	public void update() {
		up 		= keys[KeyEvent.VK_UP];
		down 	= keys[KeyEvent.VK_DOWN];
		right 	= keys[KeyEvent.VK_RIGHT];
		left 	= keys[KeyEvent.VK_LEFT];
		
		space	 	= toggle(KeyEvent.VK_SPACE);
		enter 		= toggle(KeyEvent.VK_ENTER);
		escape 		= toggle(KeyEvent.VK_ESCAPE);
		if(JudokaComponent.gameState == JudokaComponent.GameState.MENU){
			menuUp  	= toggle(KeyEvent.VK_UP);
			menuDown	= toggle(KeyEvent.VK_DOWN);
			menuLeft 	= toggle(KeyEvent.VK_LEFT);
			menuRight 	= toggle(KeyEvent.VK_RIGHT);
		}
		d = keys[KeyEvent.VK_D];
		a = keys[KeyEvent.VK_A];
		w = keys[KeyEvent.VK_W];
		s = keys[KeyEvent.VK_S];
		
		q = toggle(KeyEvent.VK_Q);
		e = toggle(KeyEvent.VK_E);
		r = toggle(KeyEvent.VK_R);
		t = toggle(KeyEvent.VK_T);
		y = toggle(KeyEvent.VK_Y);
		u = toggle(KeyEvent.VK_U);
		i = toggle(KeyEvent.VK_I);
		o = toggle(KeyEvent.VK_O);
		p = toggle(KeyEvent.VK_P);
		f = toggle(KeyEvent.VK_F);
		g = toggle(KeyEvent.VK_G);
		h = toggle(KeyEvent.VK_H);
		j = toggle(KeyEvent.VK_J);
		k = toggle(KeyEvent.VK_K);
		l = toggle(KeyEvent.VK_L);
		z = toggle(KeyEvent.VK_Z);
		x = toggle(KeyEvent.VK_X);
		c = toggle(KeyEvent.VK_C);
		v = toggle(KeyEvent.VK_V);
		b = toggle(KeyEvent.VK_B);
		n = toggle(KeyEvent.VK_N);
		m = toggle(KeyEvent.VK_M);

		k1 = toggle(KeyEvent.VK_1);
		k2 = toggle(KeyEvent.VK_2);
		k3 = toggle(KeyEvent.VK_3);
		k4 = toggle(KeyEvent.VK_4);
		k5 = toggle(KeyEvent.VK_5);
		k6 = toggle(KeyEvent.VK_6);
		k7 = toggle(KeyEvent.VK_7);
		k8 = toggle(KeyEvent.VK_8);
		k9 = toggle(KeyEvent.VK_9);
		k0 = toggle(KeyEvent.VK_0);
		
		backSpace = toggle(KeyEvent.VK_BACK_SPACE);
		period = toggle(KeyEvent.VK_PERIOD);
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void focusGained(FocusEvent arg0) {
		focused = true;
	}
	
	public void flush(){
		for(int i = 0; i < keys.length; i++){
			keys[i] = false;
		}
	}

	public void focusLost(FocusEvent arg0) {
		focused = false;
		for(int i = 0; i < keys.length; i++){
			keys[i] = false;
		}
	}
	
	private boolean toggle(int key) {
		if(keys[key]){
			keys[key] = false;
			return true;
		}
		return false;
	}

	public void flushPlayerInput() {
		w = false;
		s = false;
		d = false;
		a = false;
		enter = false;
		space = false;
		up = false;
		down = false;
		left = false;
		right = false;
	}

}

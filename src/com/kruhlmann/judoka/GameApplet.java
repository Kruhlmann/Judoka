package com.kruhlmann.judoka;

import java.applet.Applet;
import java.awt.BorderLayout;

public class GameApplet extends Applet {
	private static final long serialVersionUID = 1L;

	private JudokaComponent game = new JudokaComponent();

	public void init() {
		setLayout(new BorderLayout());
		add(game);
	}

	public void start() {
		JudokaComponent.start();
	}

	public void stop() {
		JudokaComponent.stop();
	}

}

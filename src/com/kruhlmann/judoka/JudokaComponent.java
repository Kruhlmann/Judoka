package com.kruhlmann.judoka;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.kruhlmann.judoka.input.InputHandler;
import com.kruhlmann.judoka.level.Level;
import com.kruhlmann.judoka.menu.About;
import com.kruhlmann.judoka.menu.CreateGameAI;
import com.kruhlmann.judoka.menu.CreateGameHuman;
import com.kruhlmann.judoka.menu.Exit;
import com.kruhlmann.judoka.menu.JudokaCreator;
import com.kruhlmann.judoka.menu.Main;
import com.kruhlmann.judoka.menu.Menu;
import com.kruhlmann.judoka.menu.Multiplayer;
import com.kruhlmann.judoka.menu.Singleplayer;
import com.kruhlmann.judoka.sound.Sound;

public class JudokaComponent extends Canvas implements Runnable{
	
	public static enum GameState {
		MENU, SINGLE, MULTI
	}
	
	///Final variables///
	public static final String VERSION = "Alpha 0.4.1";

	public static final long serialVersionUID = 3250072112674679916L;
	public static final boolean mute = true;
	public static final int HEIGHT = 540;
	public static final int WIDTH = 960;

	///Object creation///
	public static BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	public static GameState gameState;
	public static BufferStrategy bs;
	public static InputHandler input;
	public static Thread thread;
	public static Level level;
	public static Menu menu;

		///Fonts///
		public static Font tinyFont = new Font("Courier new", 1, 15);
		public static Font smallFont = new Font("Courier new", 1, 20);
		public static Font stdFont = new Font("Courier new", 1, 25);
		public static Font bigFont = new Font("Courier new", 1, 35);
		public static Font hugeFont = new Font("Courier new", 1, 55);

		///Menus///
		public static final Menu CREATEGAMEHUMAN = new CreateGameHuman();
		public static final Menu CREATEGAMEAI = new CreateGameAI();
		public static final Menu SINGLEPLAYER = new Singleplayer();
		public static final Menu MULTIPLAYER = new Multiplayer();	
		public static final Menu ABOUT = new About();
		public static final Menu EXIT = new Exit();
		public static final Menu MAIN = new Main();
		public static final Menu CREATE_JUDOKA = new JudokaCreator();
	
	///Public non final variables///
	public static boolean running;
	public static boolean updated;
	public static int techniqueTimer;
	public static int[] pixels;
	public static int updates;
	public static int seconds;
	public static int frames;
	public static int fps;
	public static int ups;
	public static int timer;
	
	///BufferedImages///
	public static BufferedImage characterSheet;
	public static BufferedImage menuImage;
	public static BufferedImage mainMenuImage;
	public static BufferedImage createCharacterImage;
	public static BufferedImage dojo1;
	public static BufferedImage dojo2;
	public static BufferedImage flag;
	public static BufferedImage timeFlag;
	public static BufferedImage pen;
	
		///Character///
		public static BufferedImage judokaWalking1;
		public static BufferedImage judokaWalking2;
		public static BufferedImage judokaStanding1;
		public static BufferedImage judokaStanding2;
		public static BufferedImage judokaMatte;
		public static BufferedImage judokaFallen;
		public static BufferedImage judokaLost;
		public static BufferedImage refereeLeft;
		public static BufferedImage refereeRight;
	
		///Techniques///
		public static BufferedImage uchiMata1;
		public static BufferedImage uchiMata2;
		public static BufferedImage uchiMata3;
		public static BufferedImage UCHIMATA1;
		public static BufferedImage UCHIMATA2;
		public static BufferedImage UCHIMATA3;
		
		public static BufferedImage oSotoGari1;
		public static BufferedImage oSotoGari2;
		public static BufferedImage oSotoGari3;
	
		public static BufferedImage MOROTE1;
		public static BufferedImage MOROTE2;
		public static BufferedImage MOROTE3;
	
	/**
	 * Main constructor for Judoka game
	 */
	public JudokaComponent(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		gameState = GameState.MENU;
		thread = new Thread(this, "Judoka");
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		input = new InputHandler();
		menu = new Menu();
		menu = MAIN;
		
		try {
			pen = ImageIO.read(JudokaComponent.class.getResource("/img/pen.png"));
			mainMenuImage = ImageIO.read(JudokaComponent.class.getResource("/img/main.png"));
		    createCharacterImage = ImageIO.read(JudokaComponent.class.getResource("/img/createCharacterImage.png"));
		    dojo1 = ImageIO.read(JudokaComponent.class.getResource("/img/dojo01.png"));
		    dojo2 = ImageIO.read(JudokaComponent.class.getResource("/img/dojo02.png"));
		    characterSheet = ImageIO.read(JudokaComponent.class.getResource("/img/character.png"));
		    flag = ImageIO.read(JudokaComponent.class.getResource("/img/flag.png"));
		    timeFlag = ImageIO.read(JudokaComponent.class.getResource("/img/timeFlag.png"));
		    refereeLeft = ImageIO.read(JudokaComponent.class.getResource("/img/refereeLeft.png"));
		    refereeRight = ImageIO.read(JudokaComponent.class.getResource("/img/refereeRight.png"));
		    
		    //Character
		    judokaWalking1 = characterSheet.getSubimage(64, 0, 16, 24);
		    judokaWalking2 = characterSheet.getSubimage(160, 0, 16, 24);
		    judokaStanding1 = characterSheet.getSubimage(16, 0, 16, 24);
		    judokaStanding2 = characterSheet.getSubimage(80, 0, 16, 24);
		    judokaMatte = characterSheet.getSubimage(208, 72, 16, 24);
		    judokaFallen = characterSheet.getSubimage(80, 48, 20, 24);
		    judokaLost = characterSheet.getSubimage(144, 72, 16, 24);
		    
		    //Throws
		    uchiMata1 = characterSheet.getSubimage(176, 48, 16, 24);
		    uchiMata2 = characterSheet.getSubimage(144, 48, 16, 24);
		    uchiMata3 = getFlippedImage(characterSheet.getSubimage(112, 48, 16, 24));
		    UCHIMATA1 = characterSheet.getSubimage(128, 72, 16, 24);
		    UCHIMATA2 = characterSheet.getSubimage(32, 72, 16, 24);
		    UCHIMATA3 = characterSheet.getSubimage(16, 48, 16, 24);
		    
		    oSotoGari1 = characterSheet.getSubimage(64, 24, 16, 24);
		    oSotoGari2 = characterSheet.getSubimage(144, 24, 16, 24);
		    oSotoGari3 = characterSheet.getSubimage(112, 48, 16, 24);

		    MOROTE1 = characterSheet.getSubimage(192, 24, 16, 24);
		    MOROTE2 = characterSheet.getSubimage(64, 24, 16, 24);
		    MOROTE3 = getFlippedImage(characterSheet.getSubimage(16, 24, 16, 24));
		} catch (IOException e) {
			System.out.println("[Warning] Unable to load image files");
		}
		addKeyListener(input);	
		menuImage = mainMenuImage;
	}
	
	/**
	 * Main method for starting game Main class: "JudokaComponent"
	 * @param args : arguments from command line
	 */
	public static void main(String[] args) {
		System.out.println("[Info] Starting Judoka " + VERSION);
		JudokaComponent judoGame = new JudokaComponent();
		JFrame frame = new JFrame("Judoka by Andreas Kruhlmann - " + VERSION);
		JPanel panel = new JPanel(new BorderLayout());
		frame.setContentPane(panel);
		frame.add(judoGame, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JudokaComponent.start();
	}

	/**
	 * Main game loop
	 */
	public void run() {
		long timer = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		double delta = 0;
		final double NS = 1000000000. / 60.;
		requestFocus();
		Sound.MUSIC_MENU.play(true);
		
		while(running){
			updated = false;
			long now = System.nanoTime();
			delta += (now - lastTime) / NS;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
				updated = true;
			}
			if(updated){
				render();
				frames++;
			}
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				if(level != null) level.timer();
				fps = frames;
				ups = updates;
				frames = 0;
				updates = 0;
				seconds ++;
				if(seconds > 100000) seconds = 0;
			}			
		}
	}

	/**
	 * Starts the main game loop thread
	 */
	public static void start() {
		if(running) return;
		running = true;
		thread.start();
	}

	/**
	 * Stops the game loop by joining the thread
	 */
	public static void stop() {
		if(!running) return;
		running = false;
		Sound.stopAll();
		/* What the hell is up with this?!
		try {
			thread.join();
		} catch (InterruptedException e) { }
		*/
		System.exit(0);
	}
	
	/**
	 * Renders the screen if the game logic has been updated
	 */
	public void render(){
		//BS
		bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		//END BS
		Graphics g = bs.getDrawGraphics();
 		if(gameState == GameState.MENU) g.drawImage(menuImage, 0, 0, getWidth(), getHeight(), null);
 		else g.drawImage(level.getDojoImage(), 0, 0, getWidth(), getHeight(), null);
 		
		if(gameState == GameState.MENU){
			if(input.menuUp || input.menuDown) Sound.WUSH.play(false);
			menu.render(g);
		}else if(gameState == GameState.SINGLE){
			level.render(g);
		}else if(gameState == GameState.MULTI){
			level.render(g);
		}
		
		g.dispose();
		bs.show();
	}

	/**
	 * Updates the game logic at the rate of MAX_FRAMES_PER_SECOND
	 */
	public void update(){
		timer ++;
		input.update();
		
		if(gameState == GameState.MENU){
			menu.update();
		}else if(gameState == GameState.SINGLE){
			level.update();
		}else if(gameState == GameState.MULTI){
			level.update();
		}
	}

	/**
	 * Changes the current menu shown and updated
	 * @param m : menu to be changed
	 */
	public static void changeMenu(Menu m) {
		Sound.HIT1.play(false);
		menu = m;
		m.init();
		input.flush();
	}
	
	/**
	 * Changes from level to menu
	 * @param m : menu to return to
	 */
	public static void changeMenuFromLevel(Menu m) {
		gameState = GameState.MENU;
		Sound.FIGHTING_MUSIC.stop();
		Sound.ALT_FIGHTING_MUSIC.stop();
		Sound.MUSIC_MENU.play(true);
		Sound.HIT1.play(false);
		changeMenu(m);
	}

	/**
	 * Changes current level to new instance of the Level class with following parameters
	 * @param multiplayer : is the level multiplayer
	 * @param dojo : dojo background id
	 * @param difficulty : AI difficulty
	 */
	public static void changeLevel(boolean multiplayer, int dojo, int difficulty){
		Sound.MUSIC_MENU.stop();
		if(Sound.alternativeBattleMusic) Sound.ALT_FIGHTING_MUSIC.play(true);
		else Sound.FIGHTING_MUSIC.play(true);
		Sound.HAJIME.play(false);
		if(!multiplayer)gameState = GameState.SINGLE;
		else gameState = GameState.MULTI;
		level = new Level(multiplayer, difficulty, dojo);
	}
	
	/**
	 * Flips a BufferedImage horizontally
	 * @param img : image to be flipped
	 * @return BufferedImage : flipped image
	 */
	public static BufferedImage getFlippedImage(BufferedImage img) {
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
	    tx.translate(-img.getWidth(null), 0);
	    AffineTransformOp op = new AffineTransformOp(tx,
	        AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	    img = op.filter(img, null);
	    return img;
	}

	public static void drawTextBox(int x, int y, int width, int height, int timer, String name, boolean focus, Graphics g) {
		g.drawRect(x, y, width, height);
		g.drawRect(x + 1, y + 1, width, height);
		g.drawRect(x + 2, y + 2, width, height);
		g.setColor(Color.WHITE);
		g.fillRect(x + 3, y + 3, width - 3, height - 3);
	}
	
}

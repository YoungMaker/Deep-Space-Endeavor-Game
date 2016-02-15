
package main;

import mech.Exception.SwingExceptionManager;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer; 
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException; 
import org.newdawn.slick.AppGameContainer;
//import org.newdawn.slick.Image;
//import org.newdawn.slick.geom.Vector2f;
//import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class GameStates extends StateBasedGame{
		
		public static int currentState = -1; //stores current game state
		FadeInTransition trans = null; //generic fade in state
		static boolean isPaused = false;
		protected static float masterVolume = 1.0f; //master volume
		protected static int difficulty = 0; //future use
		public static boolean fullScrn = false; //full screen bool
		public static Color TransparentCol = new Color(255,0,220); //color that will be seen as transparent 
		//version and build numbers
		public static final String version = "1.1_B"; //version of the game
		public static final String build = "1.1 BETA"; //build of the game
		//scrn parameters
		public static final int scrnHeight = 600;//screen height and with
		public static final int scrnWidth = 800;
		
		static AngelCodeFont fnt = null; //the font
	//static AngelCodeFont fntBig = null; //font scaled
		static GameStates gamer = null;
		static AppGameContainer window = null; 

		public static final int RESOURCE_LOADING_STATE = 1;
		public static final int INTRO_TEXT_STATE = 2;
		public static final int MAIN_MENU_STATE = 3;
		public static final int INTRO_VIDEO_STATE = 4;
		public static final int GAME_PLAY_STATE = 5;
		public static final int OPTIONS_MENU_STATE = 6;
		public static final int FINAL_CREDITS_STATE = 7;
		public static final int DEATH_STATE = 8;
	
	public GameStates() {
		super("Deep Space Endeavor - The Escape");
			this.addState(ResourceFactory.getInstance());
			this.addState(new IntroTextState(INTRO_TEXT_STATE));
			this.addState(new OptionsMenu(OPTIONS_MENU_STATE));
			this.addState(new MainMenu(MAIN_MENU_STATE));
			this.addState(new Game(GAME_PLAY_STATE));
			this.addState(new DeathState(DEATH_STATE));
		}
	
	public static float getVol() {
		return masterVolume;
	}
	
	public static void changeVol(boolean which) { //dis crap code. Fix
		if(which == true) {
			if(masterVolume < 100){
				masterVolume += 1.5f;
			}
		}
		else {
			if(masterVolume > 0.50f) {
				masterVolume -= 0.5f;
			}
		}
	}
	

	public static void killGame() { //kill the appGameContainer and clean up the mess
		window.destroy();
		//window.exit();
		System.exit(0);
	}
	
	public static void killGameWindow() { //kill the appGameContainer and clean up the mess
		window.exit();
	}

	
	public static void pauseGame(boolean b) { 
		window.setPaused(b);
	}
	
	public static int getFPS() {
		return window.getFPS();
	}
	
	@Override
	public void initStatesList(GameContainer gc) {
		Input.disableControllers();
		try {
			fnt = new AngelCodeFont("/resources/fonts/txt.fnt", new Image("/resources/fonts/txt_0.png"));
		} catch (SlickException e) {
			SwingExceptionManager.throwNewException(e);
		}
	}
	
	public static void setFullscreen(boolean full) {
		fullScrn = false;
		try {
			window.setDisplayMode(800,600, fullScrn);
			window.reinit();
		} catch (SlickException e) {
			SwingExceptionManager.throwNewException(e);
		}
	}
	
	public static void resetFont(Graphics g) {
		g.setFont(fnt);
	}
	
	public static boolean isFullscreen() {
		return window.isFullscreen();
	}
	
	public void changeState(int states, Color fadeColor) {
		currentState = states;
		
			trans =	new FadeInTransition(fadeColor);
			this.enterState(states, new FadeOutTransition(fadeColor), trans);
	}
	
	public void changeState(int states, int fadeTime, Color fadeColor) {
		currentState = states;
		
			trans =	new FadeInTransition(fadeColor, fadeTime);
			this.enterState(states, new FadeOutTransition(fadeColor, fadeTime), trans);
	}
	
	public static void main(String args[]) {
	 if(args.length != 0) {
		if(args[0].equalsIgnoreCase("-d")) {
			Game.setDebug(true);
			System.out.println("Game: debug mode active");
		}
	 }
			try {
				gamer = new GameStates();
				window = new AppGameContainer(gamer);	
				window.setDisplayMode(800,600, fullScrn);
				window.setMouseGrabbed(fullScrn);
				window.setTargetFrameRate(60);
				window.setIcons(new String[] {"resources/img/icons/icon.png", "resources/img/icons/icon16.png"});
				window.setMinimumLogicUpdateInterval(24);
				window.setMaximumLogicUpdateInterval(24);
				window.setAlwaysRender(true);
				window.setShowFPS(false);
				window.setForceExit(false);
				window.start();
			} catch (SlickException e) {
				SwingExceptionManager.throwNewException(e);
			}
		}

	public static boolean isPaused() {
		return isPaused;
	}

	public static String getVersion() {
		return new String("Slick Build #" + GameContainer.getBuildVersion() + " DSE version: " + version + " DSE build #" + build);
	}
}

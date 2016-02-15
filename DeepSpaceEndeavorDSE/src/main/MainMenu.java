package main;

import level.LevelLoader;
import mech.GameSys;
import mech.SoundManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entity.GunShip;

/*
 *  Add support for fullscreen/windowed
 */

public class MainMenu extends BasicGameState {
	int stateID = 0;
	Sound fx = null;
	//UI images
	private Image MenuBack = null;
	private Image menuOptions = null;
	private Image menuCursor = null;
	private Image menuTitle = null;
	private Image highScore = null;
	private long lastTime = 0;
	
	private int saturation = 255;
	Input in = null;
	private int selected = 0;
	private final int menuX = 520;
	private final int menuY = 280;
	private boolean satDown = true;
	private long lastspawn = 0;

	
	public MainMenu(int stateID) {
		this.stateID = stateID;
	}
	
	public void enter(GameContainer container, StateBasedGame game)
	           					throws SlickException {
		SoundManager.getInstance().setMusic("TITLE", true, 0.45f);
		lastspawn = GameSys.getMills();
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	 MenuBack =  ResourceFactory.getInstance().getImage("MENU_BACK");
	menuOptions = ResourceFactory.getInstance().getImage("MENU_OPTIONS");
	menuCursor = ResourceFactory.getInstance().getImage("ORB_LARGE");
	menuTitle = ResourceFactory.getInstance().getImage("MENU_TITLE");
	highScore = ResourceFactory.getInstance().getImage("HIGH_SCORE");
	//Image exitOptions = ResourceFactory.getInstance().getImage("EXIT_MENU");
		
	//		fx = ResourceFactory.getInstance().getSound("BLIP");
	if(!LevelLoader.getInstance().isHighScoreLoaded()) {
		LevelLoader.getInstance().loadHighscore();
	}	
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g)
			throws SlickException {
		MenuBack.draw(0,0); 
		Game.bullets.render(gc, sb, g);
		Game.enemies.render(gc, sb, g);
		menuTitle.draw(346,26);
		highScore.draw(450,470);
		g.setFont(GameStates.fnt);
		g.drawString("" + LevelLoader.getInstance().getHighscore(), 605, 478);
		g.drawString("Version: " + GameStates.version, 10, 15);
			if(satDown) {
				if(saturation <= 100){
					satDown = false;
				}
				saturation -= 2;
			}
			else if(!satDown){
			  if(saturation >= 255){
					satDown = true;
				}
			  saturation += 2;
			}
				g.setColor(new Color(255,255,255,saturation));
			g.drawString("Press ENTER to select.", 440, 226);
			g.drawString("UP/DOWN or W/S to move", 440, 248);
			menuOptions.draw(menuX, menuY, new Color(255,255,255, 220));
		g.drawRoundRect(menuX - 40, menuY + ((64*selected)-4), (45+ menuOptions.getWidth()), 38, 10);
		menuCursor.draw(menuX - 35, menuY + ((64*selected)), 0.5f);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta)
			throws SlickException {
		in = gc.getInput();
		SoundManager.getInstance().update(gc,sb,delta);
		Game.bullets.update(gc, sb, delta);
		Game.enemies.update(gc, sb, delta);
		if(in.isKeyPressed(Input.KEY_S)  || in.isKeyPressed(Input.KEY_DOWN)) {
			//fx.play();
			if(selected < 2){
				selected++;
			}
			else {
				selected = 0;
			}
		}
		
	if(in.isKeyPressed(Input.KEY_W) || in.isKeyPressed(Input.KEY_UP)) {
			//fx.play();
			if(selected > 0){
				selected--;
			}
			else {
				selected = 2;
			}
		}
		
	if(in.isKeyPressed(Input.KEY_ENTER) || in.isKeyDown(Input.KEY_SPACE)){
				switch (selected) {
				case 0:
				
					GameStates.gamer.changeState(GameStates.GAME_PLAY_STATE, Color.black); //in game!
				break;
				case 1:
					GameStates.gamer.changeState(GameStates.OPTIONS_MENU_STATE, Color.black); //options menu
				break;
				case 2:
					GameStates.killGame();
				break;
				
				}
			}
		if(GameSys.getMills() - lastspawn  > 3000) {	
			lastspawn = GameSys.getMills();
				new GunShip("Enem-" + GameSys.random.nextInt(), 520, -10);
				//TODO: add more enemies?
		}
	}
	
	public void leave(GameContainer gc, StateBasedGame sb)
		throws SlickException {
		SoundManager.getInstance().stopMusic();
		Game.enemies.clearLayer();
		Game.bullets.clearLayer();
	}
	
	@Override
	public int getID() {
		return this.stateID;
	}

}

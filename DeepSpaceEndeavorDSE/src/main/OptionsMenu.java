package main;

import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.BasicGameState;

import entity.background;

public class OptionsMenu extends BasicGameState {
	private int stateID;
	Input in = null;
	Image slider = null;
	Image back = null;
	private HashMap<String, Boolean> options = new HashMap<String,Boolean>();

	private final int MenuX = 160;
	private final int MenuY = 200;
	
	Image menuBack = null;
	private int selected = 0;
	private Image menuOptions;
	private Image menuCursor;
	
	public OptionsMenu(int state) {
		this.stateID = state;
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sb) {
		back = ResourceFactory.getInstance().getImage("OPTIONS_MENU_BK");
		menuOptions = ResourceFactory.getInstance().getImage("OPTIONS_MENU_ITEMS");
		menuCursor = ResourceFactory.getInstance().getImage("ORB_LARGE");
		setUpOptions();
	}

	private void setUpOptions() {
		options.put("mute", true);
		options.put("full", false);
	}
	
	private void setOption(String key, boolean value) {
		options.put(key, value);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g) {
	 if(back != null) {
		back.draw(0,0);
	 }
	 if(menuOptions != null && menuCursor != null) {
		menuOptions.draw(MenuX, MenuY, 1.0f, new Color(255,255,255, 220));
		g.drawRoundRect(MenuX - 60, MenuY + ((64*selected)-4), (35+ menuOptions.getWidth()), 35, 10);
		menuCursor.draw(MenuX - 20, MenuY + ((64*selected)), 0.5f);
		drawOptions();
	 }
		
	}

	private void drawOptions() {
		if(options.get("mute")) {
			menuCursor.draw(MenuX + 120, MenuY+15, 0.5f, Color.green);
		}
		if(options.get("full")) {
			menuCursor.draw(MenuX + 120, MenuY + 79, 0.5f, Color.green);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		in = gc.getInput();
		if(in.isKeyDown(Input.KEY_ESCAPE) ) {
			GameStates.gamer.changeState(GameStates.MAIN_MENU_STATE, Color.black);
		}
		
		if(in.isKeyPressed(Input.KEY_S)  || in.isKeyPressed(Input.KEY_DOWN)) {
			//fx.play();
			if(selected < 1){
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
				selected = 1;
			}
		}
	
	}

	@Override
	public int getID() {
		return stateID;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

}

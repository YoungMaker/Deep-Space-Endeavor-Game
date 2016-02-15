package main;

import java.io.BufferedReader;
//import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import mech.GameSys;
import mech.SoundManager;
import mech.Exception.SwingExceptionManager;
import mech.effect.EffectLayer;
import mech.effect.FlashEffect;
import mech.effect.star;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class IntroTextState extends BasicGameState {
	int stateID;
	boolean scaling = true;
	float norm = 3.0f;
	float thresh = 5.0f;
	float scale = norm;
	Input in = null;
	long lastMills;
	Image orbimg = null;
	int indx = 0;
	int lineCount = 0;
	int reset = 205;
	//int xPoint = reset;
	ArrayList<String> file;
	String currentLine = "";
	String thisLine = "";
	private int yPoint = 275;
	
	private EffectLayer layer = new EffectLayer(0);
	private int amtFlashes = 0;
	
	public IntroTextState(int id) {
		this.stateID = id;
	}
	
	
	public void enter(GameContainer container, StateBasedGame game)  throws SlickException{
		orbimg = ResourceFactory.getInstance().getImage("ORB_LARGE");
		try {
			String line;
			file = new ArrayList<String>();
			BufferedReader in = new BufferedReader(new FileReader(ResourceFactory.getInstance().getTextFile("STORY")));
				while((line = in.readLine()) != null) {
					file.add(line);
				}
				in.close();
		} catch (FileNotFoundException e) {
			//throw new SlickException("Cannot Load Story File" + e);
			SwingExceptionManager.throwNewException(e);
		} catch (IOException e) {
			//throw new SlickException("Cannot Load Story File" + e);
			SwingExceptionManager.throwNewException(e);
		} 
		
	}
	
	
	@Override
	public void init(GameContainer gc, StateBasedGame sb)
			throws SlickException {}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr)
			throws SlickException {
		layer.render(gc, sb, gr);
		if(!scaling) {
		}
		if(orbimg != null) {
			orbimg.draw(235,125, scale, new Color(1,1,1,0.6f));
		}
		
		if(!scaling && thisLine != null) {
			GameStates.fnt.drawString(reset, yPoint, thisLine);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta)
			throws SlickException {
		layer.update(gc, sb, delta);
		SoundManager.getInstance().update(gc,sb,delta);
		if(!scaling && GameSys.getMills() - lastMills >= 80) {
			 spawnStar();
			if(amtFlashes < 2 && (lineCount == 1 || lineCount == 7 || lineCount == 12 || lineCount == 9)) {
				new FlashEffect(0,0, 600,800, 3, layer);
				amtFlashes++;
			}
			if(!SoundManager.getInstance().isPlaying() && !SoundManager.getInstance().isMuted()) {
				SoundManager.getInstance().setMusic("ORB", true, 0.3f);
			}
			if(indx < currentLine.length()) {
				thisLine += currentLine.charAt(indx);
				//xPoint-= 5;
			}
			else if(lineCount < file.size() && indx > currentLine.length()+4) {
				currentLine = file.get(lineCount);
				thisLine += '\n'; //not sure what that does
				indx = 0; //resets line character index
				lineCount++; //increases level line count
				 yPoint -= 8; //subtracts line spacing
				 amtFlashes = 0; //resets thunder counter
				
			}
			else if(lineCount == file.size()) {
				//GameStates.gamer.changeState(GameStates.MAIN_MENU_STATE, Color.black);
			}
			
			lastMills = GameSys.getMills();
			indx++;
		}
		
		if(scaling) {
			if(scale <= thresh){
				scale += 0.02f;
			}
			else {
				scaling = false;
			}
		}
		in = gc.getInput();
		if(in.isKeyDown(Input.KEY_SPACE)){
			GameStates.gamer.changeState(GameStates.MAIN_MENU_STATE, Color.black); //skips intro
		}

	}
	
	private void spawnStar() {
			new star(GameSys.random.nextInt(800),-10, layer, 0.02f);
			new star(GameSys.random.nextInt(800),-10, layer, 0.02f);
	}

	@Override
	public int getID() {
		return this.stateID;
	}

}

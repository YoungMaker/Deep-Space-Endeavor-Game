package mech;

import level.LevelLoader;
import main.Game;
import main.ResourceFactory;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import entity.Iship;

public class Hud {
private Image hudUI = null;
private int amtShots = 0;
private Color yellow = new Color(255,255,60,215);
private Color white = new Color(255,255,255,215);
private Color red = new Color(255,50,50 ,215);
private boolean[] icons = {false,false,false};
protected static Hud _instance = new Hud();



	public Hud() {
		hudUI = ResourceFactory.getInstance().getImage("HUD");
	}
	
	public void resetHud() {
		amtShots = 0;
		for(int i= 0; i < icons.length; i++) {
			icons[i]  = false;
		}
	}
	
	
	public void update(GameContainer gc, StateBasedGame sb, int delta)
	throws SlickException {}
	
	public void render(GameContainer gc, StateBasedGame sb, Graphics g)
	throws SlickException {
		hudUI.draw(0,376); //draws UI image
		g.setColor(new Color(255,255,255,210)); //resets color
		g.drawString("" + Game.getScore(), 6, 570); //draws score value 
		g.drawString("" + Game.getLives(), 559, 580); //draws lives remaning
		if(LevelLoader.getInstance().getLevelProperty("name") != null) {
				g.drawString(LevelLoader.getInstance().getLevelProperty("name"), 640, 575 ); //draws level name
		}
		else {
			g.drawString("LOADING. . .", 640, 580); //while levels are loading displays this instead of nondescript 'null'
		}

		ResourceFactory.getInstance().getSheetImage("PUPSUI", 0,Game.getPup()).draw(658,495, 2); //draws pup UI icon (unused)

		int hlth = Game.getPlayer().getHealth(); 
		if(hlth <= 25) {
			g.setColor(red); //red DANGER
		}
		else if(hlth > 25 && hlth <= 50) {
			g.setColor(yellow);
			 //warning color
		}
		else if(hlth > 50) {
			g.setColor(white); //ok color
		}
		float indx = ((float)(hlth)/(float)(Iship.PLAYER_MAX_HEALTH));
		//System.out.println("health: " + hlth + "multiplier" +  (indx));
		g.fillArc(731f, 412f, 51f, 49f, (175 - (indx*360)), 175f);
		//shots remaining/ammo counter
		
			g.setColor(white);//reset color
		g.drawRoundRect(216, 579, 100, 18, 8); //draw outline
		if(amtShots >= 42) {
			g.setColor(red);
		}
		else if(amtShots > 38 && amtShots <= 42) {
			g.setColor(yellow);
		}
		else if(amtShots < 20) {
			g.setColor(white);
		}
		if(amtShots >= 50) {
			g.drawString("RELOADING", 320 ,580); //out of ammo indicator
//			if(amtShots == 50) {
			//TODO: stop spamming!
//				TextController.getInstance().addMessage("Sktih: Reloading main cannon!");
//			}
		}
		g.fillRoundRect(216, 579, 100-(((float)(amtShots)/(float)(50))*100), 18, 8);
		
		//draws active pup icons:
		for(int i = 0; i < icons.length; i++) {
			if(icons[i]) {
				ResourceFactory.getInstance().getSheetImage("PUPICONS", 0,i).draw(430+(i*32),580);
			}
		}
		if(Game.getBoss() != null) {
			g.setColor(Color.white);
			g.drawLine(Game.getBoss().getCenter().x, Game.getBoss().getCenter().y, 650, Game.getBoss().getCenter().y);
			g.drawLine(650,380, 650, Game.getBoss().getCenter().y); //TODO: finish boss hud
			g.drawRect(640, 380, 20, 60);
			g.setColor(new Color(255,105,0,185));
			g.fillRect(641, 440, 19, -((float)(Game.getBoss().getHealth())/(float)(Game.getBoss().getBaseHealth()))*59.0f);
			g.setColor(Color.white);
		}
		if(Game.getPlayer().isInvincible()) {
			Image overlay = ResourceFactory.getInstance().getSheetImage("SHIELD", Game.getPlayer().getInvinceHits(),0);
			overlay.draw(Game.getPlayer().getPos().x, Game.getPlayer().getPos().y, 5.0f);
		}
		if(Game.getPlayer().isArmored()) {
			Image overlay = ResourceFactory.getInstance().getImage("ARMOR");
			overlay.draw(Game.getPlayer().getPos().x, Game.getPlayer().getPos().y, 5.0f);
		}
	}
	
	public static Hud getInstance() {
		return _instance;
	}

	public void setShotsFired(int shots) {
		amtShots = shots;
	}
	
	public void setIcon(int id, boolean state) {
		icons[id] = state;
	}
	
	
}
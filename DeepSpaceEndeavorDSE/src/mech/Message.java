package mech;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Message {
	private String contents = "";
	private int ticks = 0;
	private boolean fading = false; //has the message timed out and are we fading?
	private int saturation = 255; //fade constant, used in the color object
	private int timeOut = 200; //ticks before fade
	private Color col = Color.white;
	
	public Message(String contents) {
		this.contents = contents;
	}
	public Message(String contents, Color col) {
		this.contents = contents;
		this.col = col;
	}
	
	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
	
	public void printText(String text) {
		contents = text;
	}
	
	public void clearText() {
		contents = "";
		ticks = 0;
	}
	public void fadeText() {
		fading = true;
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta)
	throws SlickException {
	 if(contents.length() > 0) {
		if(ticks > timeOut) {
			if(!fading) {
				fading = true;
			}
		}
		else {
			ticks++;
		}
		if(fading) {
			//System.out.println("Fade Saturation: " + saturation);
			if(saturation > 20) {
				saturation-=2;
			}
			else {
				saturation = 255;
				fading= false;
				clearText();
			 }
		}
	 }
	}
	//TODO: word wrap
	public void render(GameContainer gc, StateBasedGame sb, Graphics g, int x, int y)
													throws SlickException {
		g.setColor(new Color(255,255,255,saturation));
		g.drawString(contents, x, y);
		//g.setFont()
	}

	public int length() {
		return contents.length();
	}

}
package mech;

import java.awt.Font;
import java.util.ArrayList;

import main.GameStates;
import mech.Exception.SwingExceptionManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

//TODO: control story/instruction text on the side of the GUI
public class TextController {
	private static TextController _instance = new TextController();
	private Vector2f loc = new Vector2f(20,140); //base pos
	private ArrayList<Message> messages = new ArrayList<Message>();
	public int offset =0;
	private UnicodeFont font;
	
	private TextController() {
		font = new UnicodeFont(new java.awt.Font
				("Small Fonts", Font.PLAIN, 14));
				font.getEffects().add(new ColorEffect(java.awt.Color.white));
				font.addNeheGlyphs();
				try {
					font.loadGlyphs();
				} catch (SlickException e) {
					SwingExceptionManager.throwNewException(e, "Font Load Error");                                    
				}
	}
	
	public static TextController getInstance() {
		return _instance;
	}

	public void addMessage(String message) {
		if(message.length() > 35) {
			messages.add(new Message(message.substring(0, indexOfSpace(message.length()/2, message.length(), message) ) ) );
			messages.add(new Message(message.substring(indexOfSpace(message.length()/2, message.length(), message) )) );
		}
		else {
			messages.add(new Message(message));
		}
	}
	
	private int indexOfSpace(int start, int end, String str) {
		for(int i=start; i<end; i++) {
			if(str.charAt(i) == ' ') {
				return i;
			}
		}
		return -1;
	}
	
	
	public void update(GameContainer gc, StateBasedGame sb, int delta)
													throws SlickException {
		for(int i = 0; i<messages.size(); i++) {
			if(messages.get(i).length() > 0) {
				messages.get(i).update(gc, sb, delta);
			}
			else {
				messages.remove(i);
			}
		}
	}
	
	public void render(GameContainer gc, StateBasedGame sb, Graphics g)
													throws SlickException {
		g.setFont(font);
		for(int i = 0; i<messages.size(); i++) {
			messages.get(i).render(gc, sb, g, (int)loc.x, (int)loc.y+offset);
			offset += 16;
		}
		offset = 0;
		GameStates.resetFont(g);
	}

	public void clearMessages() {
		messages.clear();
	}
}

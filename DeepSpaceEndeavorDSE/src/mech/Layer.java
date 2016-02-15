package mech;

import java.util.ArrayList;
import java.util.Iterator;

import main.Game;
import main.GameStates;
import mech.effect.Effect;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import entity.AnimatedEntity;
import entity.Entity;

/*
 * This class handles entites in an arraylist for each of this object. 
 * it updates and renders them
 */

public class Layer {
	boolean isActive = true;
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	int layerID;
	
	public Layer(int layerid) {
		this.layerID = layerid;
	}
	
	public Layer(int layerID, ArrayList<Entity> entites){
		this.layerID = layerID;
		for(Entity e: entites) {
			this.entities.add(e);
		}
	}
	
	public void add(Entity entity) {
		entities.add(entity);
	}
	
	public void remove(Entity entity) {
		entities.remove(entity);
	}
	
	public int getLayerID(){
		return this.layerID;
	}
	
	public void clearLayer() {
	//	this.setActive(false);
		entities.clear();
		//this.setActive(true);
	}
	
	public void setActive(boolean active) {
		this.isActive = active;
	}
	
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr) 
														throws SlickException {
	if(isActive) {	
		if(entities.size() > 0) {
			for(Entity e : entities) {
			if(e instanceof AnimatedEntity) {
				((AnimatedEntity)e).render(gc, sb, gr);
			}
				else {
					e.render(gc, sb, gr);
				}
			}
		}
	}
	}
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) 
												throws SlickException{
		if(isActive) {
		if(entities.size() > 0) {
			for(int i = 0; i< entities.size(); i++) {	
				Entity current = entities.get(i);
				if(current instanceof AnimatedEntity) {
					if(((AnimatedEntity)current).isDone()){
						entities.remove(current);
					}
					else {
						((AnimatedEntity)current).update(gc, sb, delta);
					}
				}
				else {
					current.update(gc, sb, delta);
				}
				if((current.getPos().x + current.getWidth()-50) > GameStates.scrnWidth || 
						(current.getPos().y + current.getHeight()-50) > GameStates.scrnHeight ||
						current.getPos().x < -50 || current.getPos().y < -50) {
					if(current.isTarget()) {
						Game.decPup();
					}
					if(!current.getId().equalsIgnoreCase("Target")){
						current.setAsTarget(false);
						entities.remove(current); 
					}
				}
			}
		}
	}
	}
}

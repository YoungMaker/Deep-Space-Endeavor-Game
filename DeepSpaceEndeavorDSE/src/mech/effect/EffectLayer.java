package mech.effect;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;



public class EffectLayer {
 
 	private ArrayList<Effect> effects = new ArrayList<Effect>();
	private int layerID;	
	
	public EffectLayer(int layerID){
		this.layerID = layerID;
	}
	
	public EffectLayer(int layerID, ArrayList<Effect> effects){
		this.layerID = layerID;
		for(Effect e: effects) {
			this.effects.add(e);
		}
	}
	
	public void add(Effect effect) {
		effects.add(effect);
	}
	
	public int size() {
		return this.effects.size();
	}
	
	public void remove(Effect effect) {
		effects.remove(effect);
	}
	
	public int getLayerID(){
		return this.layerID;
	}
	
	public void clearLayer() {
		effects.clear();
	}
	

	public void render(GameContainer gc, StateBasedGame sb, Graphics g){
		for(int i=0; i<effects.size(); i++ ) {
			if(!effects.get(i).isFinished()) {
				effects.get(i).render(gc, sb, g);
			}
			else {
				effects.remove(i);
			}
		}
	}
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		for(int i=0; i<effects.size(); i++ ) {
			if(effects.get(i).getPos().y < 650) {
				effects.get(i).update(gc, sb, delta);
			}
			else {
				effects.remove(i);
			}
		}
	}
	
}

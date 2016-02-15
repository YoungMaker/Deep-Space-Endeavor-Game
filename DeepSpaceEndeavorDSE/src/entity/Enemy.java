package entity;

import java.util.ArrayList;

import org.newdawn.slick.Image;

import abilities.AbilTypes;
import abilities.DmgAbil;
import abilities.JetEffect;

import mech.Layer;

public abstract class Enemy extends Entity implements Iship {

	int health = 0;
	int type = 0;
	//ENEMY SCALE:
	public static final float ENEM_SCALE = 3.0f;
	private ArrayList<Entity> attachedEffects = new ArrayList<Entity>();
	
	public Enemy(String id, Image img, int x, int y, Layer layer, float scale, int type) {
		super(id, img, x, y, layer, scale);
		this.type = type;
		this.addAbility(new DmgAbil(AbilTypes.DAMIND));
	}
	
	public void takeDamage() {
		DmgAbil abil = (DmgAbil)(this.getAbility(AbilTypes.DAMIND));
		abil.setDamaging();
	}
	
	public int getType() {
		return this.type;
	}

	public abstract void spawnEnemy();
	public abstract void die();
	
	public abstract void fire();
	public abstract int getValue();
	public abstract int getHealth();
	
	@Override
	public void delete() {
		clearEffects();
		super.delete();
	}

	public boolean addEffect(Entity arg0) {
		return attachedEffects.add(arg0);
	}

	public Entity getEffect(int arg0) {
		return attachedEffects.get(arg0);
	}
	
	public void clearEffects() {
		for(Entity e: attachedEffects) {
			e.delete();
		}
		attachedEffects.clear();
	}

}
package entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Vector2f;

import abilities.AbilTypes;
import abilities.BulletAbil;
import main.ResourceFactory;
import mech.Layer;

public class Bullet extends Entity {
boolean isHyperGun = false;
private int value = 10;
private boolean team = false;
	
	public Bullet(String id, int x, int y, Layer layer, boolean team, int value, float scale, boolean hypergun) {
		super(id, ResourceFactory.getInstance().getSheetImage("SHIP_SPRITES", getTeamColor(team) ), x, y, layer, scale);
		
		this.isHyperGun = hypergun;
		if(isHyperGun) {
			this.setColor(new Color(255,149,63,255));
			this.addAbility(new BulletAbil(AbilTypes.BULLET_ABIL, 0.21f, team));
		}
		else {
			this.addAbility(new BulletAbil(AbilTypes.BULLET_ABIL, 0.198f, team));
		}
		this.team = team;
		this.spawn();
		if(value > 0) {
			this.value = value;
		}
	}
	
	public Bullet(String id, int x, int y, Layer layer, boolean team, int value, float scale, boolean hypergun, int time) {
		super(id, ResourceFactory.getInstance().getSheetImage("SHIP_SPRITES", getTeamColor(team) ), x, y, layer, scale);
		
		this.isHyperGun = hypergun;
		if(isHyperGun) {
			this.setColor(new Color(255,149,63,255));
			this.addAbility(new BulletAbil(AbilTypes.BULLET_ABIL, 0.21f, team, time));
		}
		else {
			this.addAbility(new BulletAbil(AbilTypes.BULLET_ABIL, 0.198f, team, time));
		}
		this.team = team;
		this.spawn();
		if(value > 0) {
			this.value = value;
		}
	}
	
	public Bullet(String id, int x, int y, Layer layer, boolean team, int value, float scale, boolean hypergun, float angle) {
		super(id, ResourceFactory.getInstance().getSheetImage("SHIP_SPRITES", getTeamColor(team) ), x, y, layer, scale);
		this.rotate(angle);
		this.addAbility(new BulletAbil(AbilTypes.BULLET_ABIL, 0.198f, team));
		this.isHyperGun = hypergun;
		this.team = team;
		this.spawn();
		if(value > 0) {
			this.value = value;
		}
	}
	public boolean isHyperGun() {
		return isHyperGun;
		
	}
	

	private static Vector2f getTeamColor(boolean team) {
		if(team) {
			return new Vector2f(0,1);
		}
		else {
			return new Vector2f(1,1);
		}
	}
	
	public boolean getTeam() {
		return team;
	}
	
	public int getValue() {
		return this.value;
	}

}

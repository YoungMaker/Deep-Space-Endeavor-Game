package entity;


import abilities.AbilTypes;
import abilities.GunShipAbil;
import abilities.PongBounceAbil;
import main.Game;
import main.ResourceFactory;
import mech.GameSys;
import mech.Layer;

public class Starship extends Ship implements Iship{
	public int health = 5000;
	
	public Starship(String id, int x, int y, Layer thelayer) {
		super(id, ResourceFactory.getInstance().getSheetImage("MOTHERSHIP", 0,0), x, y, thelayer);
		this.addAbility(new GunShipAbil(AbilTypes.GUN_SHIP_ABIL, 2068));
		this.addAbility(new PongBounceAbil(AbilTypes.SHIP_ABIL, 0, 300, 0.72f, 0.3f));
		this.spawn();
		new Jet("Starsip JET", 0,2.6f, this, 22,140);
		new Jet("Starsip JET", 0,2.6f, this, 176,140);
	}
	
	
	public void fireBullet() { 
		new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+getGunpoints()[0], (int) this.getPos().y+70, Game.bullets, true, Iship.PLAYER_BULLET_VAL, 1.2f, false);
		new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+getGunpoints()[1], (int) this.getPos().y+70, Game.bullets, true, Iship.PLAYER_BULLET_VAL, 1.2f ,false);
		new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+getGunpoints()[2], (int) this.getPos().y+60, Game.bullets, true, Iship.PLAYER_BULLET_VAL, 1.2f, false);
		new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+getGunpoints()[3], (int) this.getPos().y+60, Game.bullets, true, Iship.PLAYER_BULLET_VAL, 1.2f ,false);
		new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+getGunpoints()[4], (int) this.getPos().y+20, Game.bullets, true, Iship.PLAYER_BULLET_VAL, 1.2f, false);
		new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+getGunpoints()[5], (int) this.getPos().y+20, Game.bullets, true, Iship.PLAYER_BULLET_VAL, 1.2f ,false);
	}

	@Override
	public int[] getGunpoints() {
		return Iship.MOTHERSHIP_GUNPOINTS;
	}
	
	public boolean isInvincible(){
		return false;
	}
	
	public boolean isArmored(){
		return false;
	}
	

	public boolean isHyperCannon(){
		return false;
	}
	
	public void setPowerUp(int id) {}
	
	public void removePowerUp(int id) {}

	public boolean isSped() {
		return false;
	}

	@Override
	public int getHealth() {
		return this.health;
	}
	
	public void takeDamage() {}
	
	public boolean isDamaging() {
		return false;
	}

	@Override
	public void setHealth(int newHealth) {
			this.health = newHealth;
	}

	public void updateInvinc() {}

	public int getInvinceHits() {return -1;}
	

}

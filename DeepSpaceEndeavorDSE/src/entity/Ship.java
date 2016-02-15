package entity;
import org.newdawn.slick.Image;

import main.Game;
import main.ResourceFactory;
import mech.GameSys;
import mech.Hud;
import mech.Layer;
import mech.TextController;

import abilities.*;

public class Ship extends Entity implements Iship{
boolean isHyperGun = false;
boolean isSped = false;
boolean isPupTimed = false;
boolean isInvincible = false;
boolean isArmored = false;
boolean isPushed = false;
//HEALTH VARIABLE
public int health = Iship.PLAYER_MAX_HEALTH;
private Ability shp = null;
private int invinceHits = 0;

	public Ship(String id, int x, int y, Layer theLayer) {
		super(id,ResourceFactory.getInstance().getSheetImage("SHIP_SPRITES", 0,0), x, y, theLayer, 5.0f);
		this.addAbility(new ShipAbil(AbilTypes.SHIP_ABIL, 0.20f, true));
		this.addAbility(new DmgAbil(AbilTypes.DAMIND));
		this.spawn();
		shp = getAbility(AbilTypes.SHIP_ABIL);
		//this.health = Iship.PLAYER_MAX_HEALTH;
		new Jet("SHIP JET", 0,1.0f, this, 35,55);
	}
	
	public Ship(String id, Image image, int x, int y, Layer Thelayer) {
		super(id, image, x, y, Thelayer, 7.0f);
		//this.addAbility(new ShipAbil(AbilTypes.SHIP_ABIL, 0.20f, true));
		//this.addAbility(new DmgAbil(AbilTypes.DAMIND));
		this.spawn();
	//	shp = getAbility(AbilTypes.SHIP_ABIL);
	}
	
	public void fireBullet() { 
		new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+Iship.PLAYER_GUNPOINTS[0], (int) this.getPos().y, Game.bullets, true, Iship.PLAYER_BULLET_VAL, 1.2f, false, 50);
		new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+Iship.PLAYER_GUNPOINTS[1], (int) this.getPos().y, Game.bullets, true, Iship.PLAYER_BULLET_VAL, 1.2f ,false, 50);
		new GunFire("gf-" + GameSys.random.nextInt(),(int)this.getPos().x+Iship.PLAYER_GUNPOINTS[0],(int) this.getPos().y, Game.effects, this, Iship.PLAYER_GUNPOINTS, false);
		new GunFire("gf-" + GameSys.random.nextInt(),(int)this.getPos().x+Iship.PLAYER_GUNPOINTS[1],(int) this.getPos().y, Game.effects, this, Iship.PLAYER_GUNPOINTS, true);
		if(isHyperGun) {
			new Bullet("hg-" + GameSys.random.nextInt(), (int) ((int)this.getPos().x + 5*this.getScale()), (int) ((int) this.getPos().y-5*this.getScale()), Game.bullets, true, Iship.PLAYER_BULLET_VAL, 1.7f, true);
		}
	}

	@Override
	public int[] getGunpoints() {
		return Iship.PLAYER_GUNPOINTS;
	}
	
	public boolean isInvincible(){
		return isInvincible;
	}
	
	public boolean isArmored(){
		return isArmored;
	}
	

	public boolean isHyperCannon(){
		return isHyperGun;
	}
	
	public void setPowerUp(int id) {
		switch (id) {
		case 0:
			//System.out.println("Ship(Powerup Controller): health applied");
			TextController.getInstance().addMessage("Computer: Ship repaired. ");
			if(getHealth()+(Iship.COLLISION_DAMAGE/2) < Iship.PLAYER_MAX_HEALTH) {
				setHealth(getHealth() + (Iship.COLLISION_DAMAGE));
			}
			else {
				setHealth(Iship.PLAYER_MAX_HEALTH);
			}
		break;
		case 1:
			//System.out.println("Ship(Powerup Controller): speed applied");
			TextController.getInstance().addMessage("Computer: Increasing menuvering engine power");
			if(shp instanceof ShipAbil) 
			((ShipAbil) shp).setSpeed(((ShipAbil) shp).getSpeed()+0.025f);
		break;
		case 2:
			//System.out.println("Ship(Powerup Controller): armor applied");
			TextController.getInstance().addMessage("Computer: Activating armor plate ");
			isArmored = true;
			Hud.getInstance().setIcon(0, true);
			((ShipAbil) shp).setSpeed(((ShipAbil) shp).getSpeed()-0.075f);
		break;
		case 3:
			//System.out.println("Ship(Powerup Controller): hyper gun applied");
			TextController.getInstance().addMessage("Computer: Frontal cannon loaded");
			isHyperGun = true;
			Hud.getInstance().setIcon(1, true);
			((ShipAbil) shp).resetFiretimes();
			 Hud.getInstance().setShotsFired(0);
			((ShipAbil) shp).setSpeed(((ShipAbil) shp).getSpeed()-0.046f);
		break;
		case 4:
			//System.out.println("Ship(Powerup Controller): full restore applied");
			TextController.getInstance().addMessage("Computer: Ship fully repaired. ");
			setHealth(Iship.PLAYER_MAX_HEALTH);
		break;
		case 5:
			//System.out.println("Ship(Powerup Controller): invincible applied");
			TextController.getInstance().addMessage("Computer: Main sheild generators online");
			isInvincible = true;
			Hud.getInstance().setIcon(2, true);
			((ShipAbil) shp).setSpeed(((ShipAbil) shp).getSpeed()-0.055f);
		break;
		}
	}
	
	public void removePowerUp(int id) {
		switch (id) {
		case 0:
			//N/A
		break;
		case 1:
			//Ability shp = getAbility(AbilTypes.SHIP_ABIL);
			//if(shp instanceof ShipAbil) 
			//((ShipAbil) shp).setSpeed(0.2f);
			//isSped = false;
			//Hud.getInstance().setIcon(0, false);
		break;
		case 2:
			isArmored = false;
			Hud.getInstance().setIcon(0, false);
			((ShipAbil) shp).setSpeed(Iship.SHIP_SPEED_NORMAL);
		break;
		case 3:
			isHyperGun = false;
			Hud.getInstance().setIcon(1, false);
			((ShipAbil) shp).setSpeed(Iship.SHIP_SPEED_NORMAL);
		break;
		case 4:
		
		break;
		case 5:
			isInvincible = false;
			Hud.getInstance().setIcon(2, false);
			((ShipAbil) shp).setSpeed(Iship.SHIP_SPEED_NORMAL);
		break;
		}
	}

	public boolean isSped() {
		return isSped;
	}

	@Override
	public int getHealth() {
		return this.health;
	}
	
	public void takeDamage() {
		DmgAbil abil = (DmgAbil)(this.getAbility(AbilTypes.DAMIND));
		abil.setDamaging();
	}
	
	public boolean isDamaging() {
		DmgAbil abil = (DmgAbil)(this.getAbility(AbilTypes.DAMIND));
		return abil.getDamaging();
	}

	@Override
	public void setHealth(int newHealth) {
			this.health = newHealth;
	}

	public void updateInvinc() {
		if(invinceHits  >= 4) {
			this.removePowerUp(5) ;
		}
		else {
			invinceHits++;
		}
	}

	public int getInvinceHits() {
		return invinceHits;
	}

	public boolean isBeingPushed() {
		return isPushed;
	}
	public void setPushed(boolean push) {
		isPushed = push;
	}

}
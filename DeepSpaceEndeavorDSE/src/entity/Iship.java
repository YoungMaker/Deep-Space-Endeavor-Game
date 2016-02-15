package entity;

//import org.newdawn.slick.geom.Vector2f;

/*
 * this class is used to store information
 * for any ship-type entity
 */

public interface Iship {	
	
//gunpoints, places where the cannons stick out of a model, cannot have more than 2?	
	public static final int[] PLAYER_GUNPOINTS = {3,60};
	public static final int[] GUNSHIP_GUNPOINTS = {8, 40};
	public static final int[] SIDEWAYS_GUNPOINTS = {12, 36};
	public static final int[] ADVANCED_GUNPOINTS = {0};
	public static final int[] MOTHERSHIP_GUNPOINTS = {36, 190, 56, 168, 84, 140};
	public static final int[] SUIBOSS_GUNPOINTS = {10, 200, 145, 60};
	
//bullet destruction vals
public static final int PLAYER_BULLET_VAL = 50;

//ship score vals
public static final int SUICIDE_SCORE_VAL = 15;
public static final int SHOOTER_SCORE_VAL = 25;
public static final int SIDEWAYS_SCORE_VAL = 50;

//health values:
public static final int PLAYER_MAX_HEALTH = 100;

//standard collision health value
public static final int COLLISION_DAMAGE = 10;
public static final int BULLET_DAMAGE = 15;

//enemy type ids 
public static final int SUICIDE_ID = 0;
public static final int GUNSHIP_ID = 1;

//enemy health
public static final int REGULAR_ENEM_HEALTH = 1;
public static final int ELEVATED_ENEM_HEALTH = 2;

//speeds 
public static final float SHIP_SPEED_NORMAL = 0.2f;

//methods
	
	public int[] getGunpoints(); //returns gun-barrel pixels
	public int getHealth(); //gets health
	public void setHealth(int newHealth); //sets the health
}

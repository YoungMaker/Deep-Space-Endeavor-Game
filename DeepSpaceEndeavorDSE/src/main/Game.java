package main;

import java.util.ArrayList;

import level.LevelLoader;
import mech.Colision;
import mech.GameSys;
import mech.Hud;
import mech.Layer;
import mech.SoundManager;
import mech.TextController;
import mech.Exception.SwingExceptionManager;
import mech.effect.FlashEffect;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entity.*;
import generator.BackgroundGenerator;
import generator.PupGenerator;

public class Game extends BasicGameState {
	int stateID = 0; //what state
	protected static Ship you = null; //original player
	Input in = null; //input ids
	//boolean done = false; //WTF is this for?
	private static boolean fps = false; //debug mode 
	int delta = 0; //updates per second
	private static int plrScore = 0; //the players current score for the level
	//protected static final int BACKGROUND_GENERATOR = 0;
	
	//layer IDs
	protected static final int BACK_LAYER = 0;
	protected static final int BULLET_LAYER = 1;
	protected static final int SHIP_LAYER = 2;
	protected static final int ENEMY_LAYER = 3;
	protected static final int EFFECTS_LAYER = 4;
	protected static final int PUPS_LAYER = 5;
	long lastUpdate = 0; //spawn tick storage
	long lastPowerupTimer = 0; //powerup Timer last tick (fix!)
	protected static int Powerups = 0; //saves powerup ticks
	protected static int lastPup = 0; //last powerup given
	//static layer constructors (perhaps move to layer controller?)
	public static Layer bullets = new Layer(Game.BULLET_LAYER);
	public static Layer effects = new Layer(Game.BULLET_LAYER);
	public static Layer ships = new Layer(Game.SHIP_LAYER);
	public static Layer enemies = new Layer(Game.ENEMY_LAYER);
	public static Layer pups = new Layer(Game.PUPS_LAYER);
	public static Layer background = new BackgroundGenerator(Game.BACK_LAYER);
	//level information
	private static int lives = 5;
	
	private static int level = 4 ; //0 = cut scene
	private int levelLine = 0; //current level spawn line in level file
	private static int cPoint[] = {0,0,0}; //checkpoint line
	private int comLine = 0; //current comment file line
	boolean pause = false;
	private boolean ended = false;
	private boolean entered = false;
	//private int ticks = 0; // stores last amout of ticks since last esc event
	private static Boss boss = null;
	
	
	//TEST
	long lastUpdateDelta;
	
	public Game(int stateID) {
		this.stateID = stateID; //returns game state ID
	}
	
	public void enter(GameContainer container,StateBasedGame game) {
		plrScore = 0; //resets score
		if(level == 0) {
			you =	new Starship("Mothership", 288, 276, ships); //spawns mothership
		}
		else {
			you = new Ship("you!", 350, 485, ships); //spawns player
		}
		new target("Target", -35 , -35, null); //spawns powerup target entity
		//back = new BackgroundGen(BACKGROUND_GENERATOR); //background generator (fix)
		lastUpdate = GameSys.getMills(); //reset spawn tick value
		LevelLoader.getInstance().LoadLevel(level); //loads current level
		Hud.getInstance().resetHud();
	//	new powerUp("pup-" + GameSys.random.nextInt(), ResourceFactory.getInstance().getSheetImage("PUPS", 0,1), 400,200, 3);//smart bomb?
		LoadMusic(LevelLoader.getInstance().getLevelProperty("music"));
		if(cPoint[0] > 0) {
			new CheckPointIndicatior("CPOINT indicator", 188,64); 
		}
		if(!LevelLoader.getInstance().isHighScoreLoaded()) {
			LevelLoader.getInstance().loadHighscore();
		}
	//	new background("BG 1", 2);
	//	new TeleportEnem("TelTest1", 400, 200);
		System.out.println("Game: Levels: " +  LevelLoader.getInstance().amtLevels());
		levelLine = cPoint[0] + 1;
		plrScore = cPoint[1];
		comLine = cPoint[2];
		pause = true;
		entered = true;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		//nothing in init, its called at game startup
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr){ 
			gr.setFont(GameStates.fnt);
		try {
			//renders all layers and objects
			if(ships != null) {
				background.render(gc, sb, gr);
				bullets.render(gc, sb, gr);
				enemies.render(gc, sb, gr);
				pups.render(gc, sb, gr);
				ships.render(gc, sb, gr);
				effects.render(gc, sb, gr);
				TextController.getInstance().render(gc, sb, gr);
			}
			} catch (SlickException e) {
				SwingExceptionManager.throwNewException(e);
			}
			if(entered && level != 0) {
				try {
					Hud.getInstance().render(gc,sb,gr);
				} catch (SlickException e) {
					SwingExceptionManager.throwNewException(e);
				}
			}
			
			if(pause) {
				if(fps) { //draws debug screen if its toggled
					GameStates.fnt.drawString(3, 15, "fps: " + GameSys.getFPS());
					GameStates.fnt.drawString(3, 40, "d: " + this.delta);
					GameStates.fnt.drawString(3, 60, GameSys.getAllocatedMem());
					GameStates.fnt.drawString(3, 80, GameSys.getUsedMem());
					GameStates.fnt.drawString(3, 110, "entites: " + getEntities()); //fix
					GameStates.fnt.drawString(600, 15, "v: " + GameStates.version );
					GameStates.fnt.drawString(600, 35, "level:" + level);//displays level #
					GameStates.fnt.drawString(3, 128, "level Line "  + levelLine);
					GameStates.fnt.drawString(3, 152, "true d:" + (GameSys.getMills()-lastUpdateDelta));
					Input input = gc.getInput();
					GameStates.fnt.drawString(3, 168, "mouse loc: (" + input.getAbsoluteMouseX() + "," + input.getAbsoluteMouseY()+")");
					lastUpdateDelta = GameSys.getMills();
				}
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if(pause) { //if were runnin'
			this.delta = delta; //saves delta
			isColiding(); //checks collsions
		//	powerupTimers(); //checks pup timers (fix)
			in = gc.getInput();
			
		try {
			TextController.getInstance().update(gc, sb, delta);
			SoundManager.getInstance().update(gc,sb,delta);
			background.update(gc, sb, delta);
			bullets.update(gc, sb, delta);
			enemies.update(gc, sb, delta);
			pups.update(gc, sb, delta);
			ships.update(gc, sb, delta);
			effects.update(gc, sb, delta);
		} catch (SlickException e) {
			SwingExceptionManager.throwNewException(e);
		}
		if(in.isKeyPressed(Input.KEY_F3)) {
			if(fps) {
				fps = false;
			}
			else {
				fps = true;
				System.out.println("Engine: entity object dump string:" + '\n' + dumpString());
			}
		}
		
		if(in.isKeyPressed(Input.KEY_X)) {
		if(lastPup != 1) {
			you.setPowerUp(lastPup - 1);
			lastPup = 0;
		}
			if(pups.entities.size() > 0) {
				PupGenerator.changePup(lastPup,pups.entities.get(0));
			}
			if(lastPup - 1 == 5 || lastPup - 1 == 1)
			lastPowerupTimer = GameSys.getMills();
		}
		if(GameSys.getMills() - lastUpdate >= Integer.parseInt(LevelLoader.getInstance().getLevelProperty("spawninterval")) 
				|| lastUpdate == 0) {
				lastUpdate = GameSys.getMills();
				loadNextLine();
				((BackgroundGenerator) background).fillRow();
		}
		
		}
		if(in.isKeyDown(Input.KEY_ESCAPE)) { //TODO perhaps a pause method/UI?
			//TODO: set pause to false, debounce key and show menu
			GameStates.gamer.changeState(GameStates.MAIN_MENU_STATE, Color.black);
			level = 1;
		}
		if(level == 0 && in.isKeyDown(Input.KEY_SPACE)) {
			level ++;
			GameStates.gamer.changeState(GameStates.GAME_PLAY_STATE, Color.black);
		}
	}
	
	private void loadNextLine() {
		if(LevelLoader.getInstance().getLevelProperty("type").equals("random")) {
		//random spawner, use the updated spawnEnem function
			if(ships.entities.size() > 0) {
					lastUpdate = GameSys.getMills();
						for(int i =0; i < GameSys.random.nextInt(6); i++) {					//temp compatiblitiy
							spawnEnem((char)GameSys.random.nextInt(6), GameSys.random.nextInt(9));
					}
			}
		}
		//normal type level
		else {
			if(!(LevelLoader.getInstance().amtLines()-1 < levelLine)) {
				LoadStrip(LevelLoader.getInstance().getLevelLine(levelLine));
				levelLine ++;
			}
			else {
				if(!ended && boss == null) {
					//one-time ending things, before the entities are gone
					if(level != 0) {
						new LevelEndedIndicator("Level end indicator", 230,66);
					}
					else {
						((BackgroundGenerator) background).addEffect(new FlashEffect(0,0, 600,800, 5, ((BackgroundGenerator) background).getLayer()));
						new StarshipDeath("DEATH ANIMATION", (int)(you.getPos().x), (int) (you.getPos().y));
						new DummyShip("dmy ship", (int)(you.getCenter().x), (int)(you.getCenter().y));
						ships.entities.remove(you);
						you.delete();
						((BackgroundGenerator) background).fillRow();
					}
					if(plrScore > LevelLoader.getInstance().getHighscore()) {
						LevelLoader.getInstance().updateHighscore(plrScore);
					}
					
					ended  = true; //this does nothing?
					enemies.clearLayer();
				}
				//END OF LEVEL, check if all enemies\special effects destroyed and exit the current level, load another one.
				if(enemies.entities.isEmpty() && effects.entities.size() < 4) { 
				   if(((LevelLoader.getInstance().amtLevels()-1) > level)) { //-1 for level 0, random level (credits?)
					   level++;
					   cPoint[0] = 0;
					   cPoint[1] = 0;
					   cPoint[2] = 0;//clears checkpoint
					 System.out.println("Level Loader: total levels: " +(LevelLoader.getInstance().amtLevels() - 1) + " Current level "  + level);
					 GameStates.gamer.changeState(GameStates.GAME_PLAY_STATE, Color.black);
				   }
				   else {
						level = 1; 
						GameStates.gamer.changeState(GameStates.MAIN_MENU_STATE, Color.black);	
				   	}
				}
			}
		}
	}
	
	private void LoadStrip(String line) {
		//TODO: boss type parsing and other special things such as comment files.
		if(line.charAt(0) == 'b') {
			spawnBoss(Integer.parseInt(line.substring(1,2)));
			LoadMusic(LevelLoader.getInstance().getLevelProperty("bossmusic"));
		}
		else {
		for(int i = 0; i < line.length(); i++) {
			if(Character.toString(line.charAt(i)).equalsIgnoreCase("c")) {
				//	TODO: update text controller.
				TextController.getInstance().addMessage(LevelLoader.getInstance().getCommentLine(comLine));
				System.out.println("Game: Comment @" + levelLine + " " +
						LevelLoader.getInstance().getCommentLine(comLine));
				comLine++;
			}
			if(Character.toString(line.charAt(i)).equalsIgnoreCase("p")) {
				cPoint[0] = levelLine;
				cPoint[1] = plrScore;
				cPoint[2] = comLine;
				new CheckPointIndicatior("CPOINT indicator", 188,64); 
			}	
				else if(!Character.toString(line.charAt(i)).equalsIgnoreCase("p") && !Character.toString(line.charAt(i)).equalsIgnoreCase("c")){
					spawnEnem(line.charAt(i), i);
				}
			}
		}
	}
	
	private void spawnBoss(int id) {
		System.out.println("level " + level + " Boss id: " + id);
		switch(id) {
		case 0:
			boss = new suicideBoss("Boss for lev 1", 0,0);
		break;
		case 1:
			boss = new GunBoss("Boss for lev 2", 0,0);
		break;
		case 2:
			boss = new triGunBoss("Boss for lev 3", 0,0);
		break;
		}
	}
	
	private void LoadMusic(String id) {
		if(id != null && id != "") {
			SoundManager.getInstance().setMusic(id, true, 0.35f);
		}
		else {
			System.out.println("Level control: no music");
		}
	}
	
	private void spawnEnem(char type, int pos) {
		switch (type) {
			case '1'://TODO: add more for more enemies
				new suicideEnemy("Enem-"  + GameSys.random.nextInt(), 90*pos, -10);
			break;
			case '2':
				new GunShip("Enem-"  + GameSys.random.nextInt(), 90*pos, -10);
			break;
			case '3':
				new SidewaysEnemy("Enem-"  + GameSys.random.nextInt(), 90*pos, -10);
			break;
			case '4':
				new FlipEnemy("Enem-"  + GameSys.random.nextInt(), 90*pos, -10 );
			break;
			case '5':
				new AdvancedEnem("Enem-"  + GameSys.random.nextInt(), 90*pos, -10);
		   break;
			case '6':
				new Mine("Enem(M)-"  + GameSys.random.nextInt(), 90*pos, -10);
			break;
			case '7':
				new ShotgunEnem("Enem-"  + GameSys.random.nextInt(), 90*pos, -10);
			break;
			case '8':
				new CloakedEnem("Enem-"  + GameSys.random.nextInt(), 90*pos, -10);
			break;
			case '9':
				new RapidFireEnem("Enem-"  + GameSys.random.nextInt(), 90*pos, -10);
			break;
			case 'a':
				new StationTurret("Station-"  + GameSys.random.nextInt(), 90*pos, -10);
			break;
			case 'k':
				new staticStation("Station-"  + GameSys.random.nextInt(), 90*pos, -10, 2,0);
			break;
			case 'd':
				new staticStation("Station-"  + GameSys.random.nextInt(), 90*pos, -10, 3,0);
			break;
			case 'e':
				new staticStation("Station-"  + GameSys.random.nextInt(), 90*pos, -10, 0,1);
			break;
			case 'f':
				new staticStation("Station-"  + GameSys.random.nextInt(), 90*pos, -10, 1,1);
			break;
			case 'g':
				new staticStation("Station-"  + GameSys.random.nextInt(), 90*pos, -10, 2,1);
			break;
			case 'h':
				new StationSpawner("Station-"  + GameSys.random.nextInt(), 90*pos, -10, true, '1');
			break;
			case 'i':
				new StationSpawner("Station-"  + GameSys.random.nextInt(), 90*pos, -10, false, '5');
			break;
			case 'z':
				new EndGameObject("End-"  + GameSys.random.nextInt(), 0, -580);
			break;
		}
	}
	
	
	private void isColiding() {
	//checks all collisions
	ArrayList<Entity> ShipArray = ships.entities;
	ArrayList<Entity> enemyArray = enemies.entities;
	ArrayList<Entity> bulletArray = bullets.entities;
	ArrayList<Entity> pupArray = pups.entities;
	//System.out.println(bulletArray.size() + " " + enemyArray.size() + " " + ShipArray.size());
	Entity currentEnemy = null;
	//goodguys with da badguys
	if(ShipArray.size() > 0 && enemyArray.size() > 0) { //if not empty
		for(int j = 0; j < enemyArray.size(); j++) { //loop enemies
			//for(int i = 0; i < ShipArray.size(); i++) { //loop ships
				Entity currentShip = getPlayer(); //get current ship
				currentEnemy = enemyArray.get(j); //get current enemy
				for(int c = 0; c < pupArray.size(); c++) { //pup check loop
					Entity e = pupArray.get(c); //get current power up
					if(Colision.colideBoxes(you, e)) { //collide current player and pup
					if(e instanceof powerUp) { //check if its really a pup
						plrScore += 10;
						if(((powerUp) e).getType() != 6) { //cast it and check if its not a blank one
							lastPup = ((powerUp) e).getType()+1; //add current pup id
						}
						else {
							if(lastPup != 5) {
								lastPup += 1;
							}
						}
						if(((powerUp) e).getType() == 0) { //if its a health, give it anyway
							you.setPowerUp(0); //give health
						}
							e.delete(); //delete the powerup object
							if(pupArray.size() > 0) {   //if the pups array isnt empty
								PupGenerator.changePup(lastPup,pupArray.get(0)); //change the pup to the next one
							}
						}
					}
				}
				if(Colision.colideBoxes(currentShip,currentEnemy)) {
				  if(!(currentEnemy instanceof Boss)) {
					if(!(((Ship) currentShip).isInvincible()) && !((Ship) currentShip).isArmored() && !((Ship) currentShip).isDamaging()) {
						updateHealth(Iship.COLLISION_DAMAGE,currentShip); //standard value
						currentEnemy.delete();
						plrScore += (((Enemy)currentEnemy).getValue()* new Float(LevelLoader.getInstance().getLevelProperty("pointmutiplier")));
					}
					else if((((Ship) currentShip).isArmored())) {
						currentEnemy.delete();
						plrScore += (((Enemy)currentEnemy).getValue()* new Float(LevelLoader.getInstance().getLevelProperty("pointmutiplier")));
						((Ship)currentShip).removePowerUp(2);
					}
					else if((((Ship) currentShip).isInvincible())) {
						currentEnemy.delete();
						plrScore += (((Enemy)currentEnemy).getValue()* new Float(LevelLoader.getInstance().getLevelProperty("pointmutiplier")));
						((Ship)currentShip).updateInvinc();
					}
				  }
				  else {
					  updateHealth(Iship.PLAYER_MAX_HEALTH,currentShip); //delete on contact with ship
				  }
				}
			//}
	//bullets with da badguys & good guys
	if(bulletArray.size() > 0 && enemyArray.size() > 0) {
		for(int l = 0; l < bulletArray.size(); l++) {
			Entity currentBullet = bulletArray.get(l);
			if(Colision.colideBoxes(currentBullet, currentEnemy)) {
			if(((Bullet) currentBullet).getTeam()) {
				if(!((Bullet) currentBullet).isHyperGun()) {
					currentBullet.delete();// wtf why twice works?? kinda cool (but weird)...
					currentBullet.delete();
				}
				if(currentEnemy.isTarget() && !(currentEnemy instanceof Boss) ) {
					plrScore += 5;
					checkPowerups(currentEnemy.getPos());
				}
				updateHealth(1, currentEnemy);
				//TODO: smoke/trash particles. explosion looks great
			}
			}
		}
	}
	}
		if(ShipArray.size() > 0) {
			for(int i = 0; i < ShipArray.size(); i++) { //loop ships
				for(int l = 0; l < bulletArray.size(); l++) {
					Entity currentBullet = bulletArray.get(l);
					Entity currentShip = ShipArray.get(i);
					if(Colision.colideBoxes(currentBullet, currentShip)) {
						if(!((Bullet) currentBullet).getTeam()) {
							if(!(((Ship) currentShip).isInvincible()) && !((Ship) currentShip).isArmored()) {
								updateHealth(Iship.BULLET_DAMAGE,currentShip);
								new Explosion("Ex-" + GameSys.random.nextInt(), 
										(int)currentBullet.getPos().x, 
										(int)currentBullet.getPos().y, 
										ResourceFactory.getInstance().getSheet("EXPLOSION"),
										2.0f);
								currentBullet.delete();
								currentBullet.delete();
								return;
							}
							else if((((Ship) currentShip).isArmored())) {
								currentBullet.delete();
								((Ship)currentShip).removePowerUp(2);
							}
							else if((((Ship) currentShip).isInvincible())) {
								currentBullet.delete();
								((Ship)currentShip).updateInvinc();
							}
						}
					}
				}
			}
		}
	}
}
	
	
	public static void updateHealth(int bulletHealth, Entity ent) {
		if(ent instanceof Iship) {
			if(ent instanceof Ship) {
				Ship ent1 = ((Ship) ent);
				if(ent1.getHealth() - bulletHealth > 0) {
					//System.out.println(ent1.getHealth());
					ent1.setHealth(ent1.getHealth() - bulletHealth);
					//new DamageIndicator(ent.getId() + "DAMAGEIND", (int)(ent.getPos().x), (int)(ent.getPos().y), ent.getScale(), ent);
					ent1.takeDamage();
				}
				else {
					//TODO: make you die in here. done, but make it do something after
					ent1.setHealth(0);
					ent1.delete();
					plrScore = cPoint[1]; //sets score to cpoint score to prevent windup
					lives --; //subtracts lives
					if(lives == -1) {
						cPoint[0] = 0;
						cPoint[1] = 0;
						cPoint[2] = 0;
						lives = 5;
						level = 1;
					GameStates.gamer.changeState(GameStates.MAIN_MENU_STATE, 1500, new Color(0,0,0 ,215));
					}
					else {
						if(level == 0) {
							new StarshipDeath("DEATH ANIMATION", (int)(you.getPos().x), (int) (you.getPos().y));
							you.delete();
						}
						else{
							GameStates.gamer.changeState(GameStates.DEATH_STATE, 1500, new Color(0,0,0 ,215));
						}
					}
				}
			}
			
			else if(ent instanceof Enemy) {
				Enemy ent2 = (Enemy)ent;
				if(ent2.getHealth() - bulletHealth > 0) {
					ent2.setHealth(ent2.getHealth() - bulletHealth);
					//new DamageIndicator(ent.getId() + "DAMAGEIND", (int)(ent.getPos().x), (int)(ent.getPos().y), ent.getScale(), ent);
					ent2.takeDamage();
				}
				else {
					ent2.setHealth(0);
					ent2.die();
					ent2.delete();
						plrScore += (((Enemy)ent2).getValue()* new Float(LevelLoader.getInstance().getLevelProperty("pointmutiplier")));
				}
			}
		}
	}
		
private void die() {
	
}
	public void checkPowerups(Vector2f loc) {
		//System.out.println(Powerups);
		if(Powerups > 5) {
			Powerups = 0;
			PupGenerator.spawnPup((int)loc.x, (int)loc.y, lastPup); 
		}
		else {
			Powerups++;
		}
	}
	
	public void leave(GameContainer gc, StateBasedGame sb)
	       							throws SlickException {
//		TODO: remove everything, reset all values
		LevelLoader.getInstance().UnloadLevel();
		bullets.clearLayer();
		enemies.clearLayer();
		pups.clearLayer();
		effects.clearLayer();
		ships.clearLayer();
		((BackgroundGenerator) background).clearLayer();
		TextController.getInstance().clearMessages();
		boss = null;
		ended = false;
		lastPup = 0;
		Powerups = 0;
		plrScore = 0;
		levelLine = 0;
		comLine = 0;
		SoundManager.getInstance().stopMusic();
	}

	@Override
	public int getID() {
		return this.stateID;
	}
	
	public static Ship getPlayer() {
			return you;
	}
	
	public static boolean isDebug() {
		return fps;
	}
	public static void decPup() {
		if(Powerups != 0) {
			Powerups = 0;
		}
		plrScore -= 5;
	}
	public static int getScore() {
		return plrScore;
	}
	
	public static int getLives() {
		return lives;
	}
	
	public static int getPup() {
		return lastPup;
	}
	
	public static int getEntities() {
		return 
		bullets.entities.size() + 
		effects.entities.size() + 
		ships.entities.size() + 
		pups.entities.size() +
		enemies.entities.size();
	}
	
	public static String dumpString() {
		String output ="";
		output += "Ships:" + '\n';
		for(Entity e: ships.entities) {
			output += "		" + e + '\n';
		}
		output += "Enemies:" + '\n';
		for(Entity e: enemies.entities) {
			output += "		" + e + '\n';
		}
		output += "Effects: "+ '\n';
		for(Entity e: effects.entities) {
			output += "		" + e + '\n';
		}
		output += "Powerups:"+ '\n';
		for(Entity e: pups.entities) {
			output += "		" + e + '\n';
		}
		output += "Bullets:"+ '\n';
		for(Entity e: bullets.entities) {
			output += "		" + e + '\n';
		}
		output += "Background:"+ '\n';
		for(Entity e: background.entities) {
			output += "		" + e + '\n';
		}
		return output;
	}
	
	public static void setDebug(boolean tf) {
		fps = tf;
	}

	public static Boss getBoss() {
		return boss;
	}

	public static void clearBoss() {
		boss = null;
	}

}
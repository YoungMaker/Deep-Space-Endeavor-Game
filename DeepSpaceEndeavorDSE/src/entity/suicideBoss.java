package entity;

import main.Game;
import main.ResourceFactory;
import mech.GameSys;

import org.newdawn.slick.Image;

import abilities.AbilTypes;
import abilities.SuiBossAbil;

public class suicideBoss extends Boss {

	public suicideBoss(String id, int x, int y) {
		super(id, ResourceFactory.getInstance().getSheetImage("BOSS", 0,0), x, y, 75, 1);
		this.addAbility(new SuiBossAbil(AbilTypes.BOSS_ABIL, 178));
		this.spawnEnemy();
	}

	@Override
	public void fire() {
		new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+Iship.SUIBOSS_GUNPOINTS[0], (int) this.getPos().y+this.getHeight(), Game.bullets, false, Iship.PLAYER_BULLET_VAL, 1.2f, false);
		new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+Iship.SUIBOSS_GUNPOINTS[1], (int) this.getPos().y+this.getHeight(), Game.bullets, false, Iship.PLAYER_BULLET_VAL, 1.2f ,false);
		new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+Iship.SUIBOSS_GUNPOINTS[2], (int) this.getPos().y+this.getHeight(), Game.bullets, false, Iship.PLAYER_BULLET_VAL, 1.2f, false);
		new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+Iship.SUIBOSS_GUNPOINTS[3], (int) this.getPos().y+this.getHeight(), Game.bullets, false, Iship.PLAYER_BULLET_VAL, 1.2f ,false);
		
	}

	@Override
	public int[] getGunpoints() {
		return Iship.SUIBOSS_GUNPOINTS;
	}

	@Override
	public void spawnNewEnem() {
		new suicideEnemy("Enem-"  + GameSys.random.nextInt(), (int)(this.getCenter().x), (int)(this.getCenter().y));
	}
	

}

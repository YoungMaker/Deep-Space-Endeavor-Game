package entity;

import main.Game;
import main.ResourceFactory;
import mech.GameSys;

import org.newdawn.slick.Image;

import abilities.AbilTypes;
import abilities.GunShipAbil;
import abilities.LungeAbil;
import abilities.PongBounceAbil;
import abilities.SuiBossAbil;

public class GunBoss extends Boss {

	public GunBoss(String id, int x, int y) {
		super(id, ResourceFactory.getInstance().getSheetImage("BOSS", 1,0), x, y, 110, 2);
		this.addAbility(new SuiBossAbil(AbilTypes.BOSS_ABIL, 180));
		this.spawnEnemy();
	}

	@Override
	public int[] getGunpoints() {
		return null;
	}

	@Override
	public void spawnNewEnem() {
		new GunShip("Enem-"  + GameSys.random.nextInt(), (int)getCenter().x, (int) getCenter().y);
		
	}

	@Override
	public void fire() {
		new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+42, (int) this.getPos().y+(this.getHeight()-42), Game.bullets, false, Iship.PLAYER_BULLET_VAL, 1.2f, false);
		new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+220, (int) this.getPos().y+(this.getHeight()-42), Game.bullets, false, Iship.PLAYER_BULLET_VAL, 1.2f ,false);

	}

}

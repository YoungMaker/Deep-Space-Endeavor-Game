package entity;

import abilities.AbilTypes;
import abilities.PongBounceAbil;
import abilities.SuiBossAbil;
import main.Game;
import main.ResourceFactory;
import mech.GameSys;

public class triGunBoss extends Boss{

	public triGunBoss(String id, int x, int y) {
		super(id, ResourceFactory.getInstance().getSheetImage("BOSS", 2,0), x, y, 80, 2);
		this.addAbility(new SuiBossAbil(AbilTypes.BOSS_ABIL, 185));
		this.spawnEnemy();
	}

	@Override
	public int[] getGunpoints() {
		return null;
	}

	@Override
	public void spawnNewEnem() {
		new ShotgunEnem("Enem-"  + GameSys.random.nextInt(), (int)getCenter().x, (int) getCenter().y);
		
	}

	@Override
	public void fire() {
		Bullet b1 = new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+91, (int) this.getPos().y+this.getHeight(), Game.bullets, false, Iship.PLAYER_BULLET_VAL, 0.9f, false,35);
		b1.rotate(-45);
	Bullet b2 = new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+154, (int) this.getPos().y+this.getHeight(), Game.bullets, false, Iship.PLAYER_BULLET_VAL, 0.9f ,false,35);
		b2.rotate(45);
	new Bullet("b-" + GameSys.random.nextInt(), (int)this.getPos().x+119, (int) this.getPos().y+this.getHeight(), Game.bullets, false, Iship.PLAYER_BULLET_VAL, 1.0f, false);

	}

}

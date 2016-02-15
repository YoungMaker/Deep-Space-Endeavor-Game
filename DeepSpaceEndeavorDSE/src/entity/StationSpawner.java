package entity;

import main.ResourceFactory;
import mech.GameSys;

import org.newdawn.slick.Image;

import abilities.AbilTypes;
import abilities.SpawnerAbil;

public class StationSpawner extends StationPart {
private char type;
	public StationSpawner(String id, int x, int y, boolean LR, char type) {
		super(id, ResourceFactory.getInstance().getSheetImage("STATION_PARTS", 3,1), x, y);
		this.addAbility(new SpawnerAbil(AbilTypes.ADVANCED, 150));
		if(!LR) {
			setTexture(ResourceFactory.getInstance().getSheetImage("STATION_PARTS", 0,2));
		}
		this.spawn();
		this.type = type;
	}

	@Override
	public void action() {
		switch (type) {
		case '1'://TODO: add more for more enemies
			new suicideEnemy("Enem-"  + GameSys.random.nextInt(), (int) getCenter().x, (int) getCenter().y);
		break;
		case '2':
			new GunShip("Enem-"  + GameSys.random.nextInt(), (int) getCenter().x, (int) getCenter().y);
		break;
		case '3':
			new SidewaysEnemy("Enem-"  + GameSys.random.nextInt(), (int) getCenter().x, (int) getCenter().y);
		break;
		case '4':
			new FlipEnemy("Enem-"  + GameSys.random.nextInt(), (int) getCenter().x, (int) getCenter().y );
		break;
		case '5':
			new AdvancedEnem("Enem-"  + GameSys.random.nextInt(), (int) getCenter().x, (int) getCenter().y);
	   break;
		case '6':
			new Mine("Enem(M)-"  + GameSys.random.nextInt(), (int) getCenter().x, (int) getCenter().y);
		break;
		case '7':
			new ShotgunEnem("Enem-"  + GameSys.random.nextInt(), (int) getCenter().x, (int) getCenter().y);
		break;
		case '8':
			new CloakedEnem("Enem-"  + GameSys.random.nextInt(), (int) getCenter().x,(int) getCenter().y);
		break;
		case '9':
			new RapidFireEnem("Enem-"  + GameSys.random.nextInt(), (int) (int) getCenter().x, (int) getCenter().y);
		break;
	}

	}

}

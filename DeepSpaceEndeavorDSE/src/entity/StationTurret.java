package entity;

import main.ResourceFactory;

public class StationTurret extends StationPart {
private TurretEnem turret = null;
	public StationTurret(String id, int x, int y) {
		super(id, ResourceFactory.getInstance().getSheetImage("STATION_PARTS", 0,0), x, y);
		this.spawn();
		turret = new TurretEnem("TURRET", (int)getPos().x, (int)getPos().y);
	}

	@Override
	public void action() {
		// fire turret

	}

}

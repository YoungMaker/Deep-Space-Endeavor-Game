package entity;

import main.ResourceFactory;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class staticStation extends StationPart {

	public staticStation(String id, int x, int y, int xImage, int yImage) {
		super(id, ResourceFactory.getInstance().getSheetImage("STATION_PARTS", xImage,yImage), x, y);
		this.spawn();
	}
	
	@Override
	public void action() {
		// TODO Auto-generated method stub

	}

}

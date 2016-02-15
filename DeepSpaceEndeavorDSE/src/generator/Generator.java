package generator;

import java.util.Random;

import mech.Layer;

public abstract class Generator extends Layer {
private Random r = new Random();
	
	public Generator(int layerid) {
		super(layerid);
	}
	
	public abstract void GenerateEntity();

}

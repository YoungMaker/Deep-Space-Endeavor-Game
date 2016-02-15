package mech.effect;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class FlashEffect extends Effect {
	private int width = 0, height = 0;
	private float saturation = 0;
	private int duration = 200;
	private boolean state = false;
	private float offset = ((duration/2)/180);
	
	public FlashEffect(int x, int y, int height, int width, int duration, EffectLayer layer) {
		super(x, y, layer);
		this.width = width;
		this.height = height;
		this.duration = duration;
		this.offset = 50.0f/(float)(duration/2.0f);
		//System.out.println("FlashEffect: Offset " + offset );
		this.spawn();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g) {
		g.setColor(new Color(255,255,255, (int)(Math.floor(saturation)))); //round! round! round to int goddammit
		g.fillRect(this.getPos().x,this.getPos().y, width, height);
		g.setColor(Color.white);//reset color.
	}



	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if(!state) {
			if(saturation < 180) {
				saturation+= offset;
			}
			else {
				state = true;
			}
		}
		else {
			if(saturation > 1 ) {
				saturation -= offset;
			}
			else {
				this.setFinished(true);
			}
		}
	}

}

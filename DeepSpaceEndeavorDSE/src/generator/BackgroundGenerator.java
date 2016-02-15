package generator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import level.LevelLoader;
import mech.GameSys;
import mech.Layer;
import mech.effect.Effect;
import mech.effect.EffectLayer;
import mech.effect.star;

public class BackgroundGenerator extends Layer{
EffectLayer layer = new EffectLayer(0);
	
	public BackgroundGenerator(int layerid) {
		super(layerid);
	}
			
	
	public void update(GameContainer gc, StateBasedGame sb, int delta) 
													throws SlickException{
		for(int i=0; i< entities.size(); i++ ) {
			if(entities.get(i).getPos().y > 800) {
				entities.remove(i);
			}else {
				//System.out.println("Background layer: " + entities.get(i).getPos());
				entities.get(i).update(gc, sb, delta);	
			}
		}
		
		layer.update(gc, sb, delta);
	}
	
	public void render(GameContainer gc, StateBasedGame sb, Graphics g) 
													throws SlickException {
		layer.render(gc, sb, g);
		super.render(gc, sb, g);	
		
		
	}
	
	public void addEffect(Effect e) {
		layer.add(e);
	}
	
	public void fillRow() {
		for(int i=0; i<GameSys.random.nextInt(5) + 5; i++) {
			new star(GameSys.random.nextInt(800),-10, layer, 
					new Float(LevelLoader.getInstance().getLevelProperty("backspeed")) );
		}
	}

	public void clearLayer() {
		super.clearLayer();
		layer.clearLayer();
	}
	
	public EffectLayer getLayer() {
		// TODO Auto-generated method stub
		return layer;
	}
	
	

}

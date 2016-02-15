package mech;

import main.GameStates;
import main.ResourceFactory;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

public class SoundManager {
	private static SoundManager _instance =  new SoundManager();
	private Music mus = null;
	private Music cached = null;
	private boolean muted = false;
	
	public SoundManager() {
		//init something?
	}

	public static SoundManager getInstance() {
		return _instance;
	}
	
	//1.0 = default volume
	public void setMusic(String id, boolean loop, float volume) {
		Music mus = ResourceFactory.getInstance().getMusic(id);
		if(mus != null) {
	  if(!muted) {
		 
		if(this.mus != null && this.mus.playing()) {
			this.mus.fade(200, 0, true); 
			this.cached = mus;
			this.cached.setVolume(volume);
			System.out.println("SoundManager: Music stream " + id + " deffered, music already playing");
		}
		else {
			 System.out.println("SoundManager: Music stream " + id + " loaded");
			this.mus = mus;
			this.mus.play(1.0f, volume);
			if(loop) {
				this.mus.loop(1.0f, volume);
			}
		}
	  }
	}
	}
	
	public boolean isPlaying() {
		if(mus != null) {
			return mus.playing();
		}
		else {
			return false;
		}
	}
	public void stopMusic() {
		if(mus != null) 
			this.mus.fade(500, 0, true);
	}
	
	public boolean isMuted() {
		return muted;
	}
	
	public void setMuted(boolean playing) {
		muted = playing;
	}

	public void update(GameContainer gc, StateBasedGame sb, int delta) {
		if(cached != null) {
		   if(!mus.playing()) {
			   mus.stop();
			   mus = cached; //transfer
			   mus.play(1.0f, cached.getVolume()); //play
			   System.out.println("SoundManager:  Playing deffered music ");
			   cached = null; //clear cached music
		   }
	   }
		
	}
	
}

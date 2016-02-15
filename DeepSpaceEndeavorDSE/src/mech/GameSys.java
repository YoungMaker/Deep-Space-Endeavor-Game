package mech;

import java.text.NumberFormat;
import org.lwjgl.Sys;
import org.newdawn.slick.geom.Vector2f;

import main.GameStates;

import java.util.Random;

public class GameSys {

	public static Random random = new Random(GameSys.getMills());
	
	public static String getAllocatedMem() {
		NumberFormat format = NumberFormat.getInstance();
		StringBuilder sb = new StringBuilder();
		long total = Runtime.getRuntime().totalMemory();
		sb.append("Allocated RAM = " + format.format((total/1024)/1024) + "MB");
		return sb.toString();
	}
	
	public static String getUsedMem() {
		NumberFormat format = NumberFormat.getInstance();
		StringBuilder sb = new StringBuilder();
		long used  = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		sb.append("Used RAM = " + format.format((used/1024)/1024) + "MB");
		return sb.toString();
	}
	
	public static int getFPS() {
		return GameStates.getFPS();
	}
	
	public static long getMills() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public static String getVersionInformation() {
		return GameStates.getVersion() + " OS: " + System.getProperty("os.name") + "." + System.getProperty("os.version");
	}
	
	public static float distance(int x1, int y1, int x2, int y2) {
		return (float) Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2));
	}
	public static float distance(Vector2f v1, Vector2f v2) {
		return (float) Math.sqrt(Math.pow((v2.x-v1.x), 2) + Math.pow((v2.y-v1.y), 2));
	}

}

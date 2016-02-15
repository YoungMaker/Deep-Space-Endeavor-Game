package level;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;
//this loads level files, lev.dat (for spawning) and lev.properties (for properties)
//its a singleton

import mech.Exception.SwingExceptionManager;

import org.newdawn.slick.SlickException;
public class LevelLoader {
	Properties prop = new Properties(); //new properties object
	Properties highScore = new Properties();
	FileInputStream strm; 
	File dir = new File("resources/levels/");
	protected static LevelLoader _instance = new LevelLoader(); //singleton!
	ArrayList<String> file = null; //stores file lines
	private ArrayList<String> comFile = null;
	
	public void LoadLevel(int levelID) {
		try {
			strm = new FileInputStream("resources/levels/" + levelID + "/lev.properties"); //loads file
			prop.load(strm); //stream -> to properties
			String line; //current line
			file = new ArrayList<String>(); //construct arraylist
			BufferedReader in = new BufferedReader(new FileReader("resources/levels/" + levelID + "/lev.dat")); //get level dat file
				while((line = in.readLine()) != null) { //while were not at the end of the file
					file.add(line); //add the line to the array
				}
				File f = new File("resources/levels/" + levelID + "/com.dat");
				if(f.exists()) {
					String comLine; //current line
					comFile = new ArrayList<String>(); //construct arraylist
					BufferedReader io = new BufferedReader(new FileReader(f)); //get comment dat file
					while((comLine = io.readLine()) != null) { //while were not at the end of the file
						comFile.add(comLine); //add the line to the array
					}
				io.close();//close the buffered stream (and the file)
			}
		} catch (FileNotFoundException e) { 
			SwingExceptionManager.throwNewException(e, "DEFAULT LEVEL LOAD ERROR");
		} catch (IOException e) {
			SwingExceptionManager.throwNewException(e, "LEVEL LOAD ERROR");
		}
		
		System.out.println("Level Loader: Loaded level: " + levelID + " with name: "  + prop.getProperty("name"));
		System.out.println("Level Loader: Level description: " +  prop.getProperty("goal"));
	}
	
	
	
	public void LoadLevel(File dir) 
		throws IllegalArgumentException{
		try {
			strm = new FileInputStream(dir  + "/lev.properties"); //loads file
			prop.load(strm); //stream -> to properties
			String line; //current line
			file = new ArrayList<String>(); //construct arraylist
			BufferedReader in = new BufferedReader(new FileReader(dir + "/lev.dat")); //get level dat file
				while((line = in.readLine()) != null) { //while were not at the end of the file
					file.add(line); //add the line to the array
				}
				File f = new File(dir + "/com.dat");
				if(f.exists()) {
					String comLine; //current line
					comFile = new ArrayList<String>(); //construct arraylist
					BufferedReader io = new BufferedReader(new FileReader(f)); //get comment dat file
					while((comLine = io.readLine()) != null) { //while were not at the end of the file
						comFile.add(comLine); //add the line to the array
					}
				io.close();//close the buffered stream (and the file)
			}
		} catch (FileNotFoundException e) { 
			throw new IllegalArgumentException();
		} catch (IOException e) {
			SwingExceptionManager.throwNewException(e, "LEVEL LOAD ERROR");
		}
		
		System.out.println("Level Loader: Loaded level: " + dir.getName() + " with name: "  + prop.getProperty("name"));
		System.out.println("Level Loader: Level description/goal: " +  prop.getProperty("goal"));
	}
	
	public void loadHighscore() {
		try {
			FileInputStream strm = new FileInputStream("resources/levels/highscore.properties");
			highScore.load(strm);
			strm.close();
		} catch (FileNotFoundException e) {
			SwingExceptionManager.throwNewException(e, "HIGHSCORE LOAD ERROR");
		} catch (IOException e) {
			SwingExceptionManager.throwNewException(e, "HIGHSCORE LOAD ERROR");
		}
	}
	
	public boolean isHighScoreLoaded() {
		return !(highScore.isEmpty());
	}
	
	public void updateHighscore(int newHigh) {
		highScore.setProperty("highscore", "" +newHigh);
		try {
			FileOutputStream out = new FileOutputStream("resources/levels/highscore.properties");
			highScore.store(out, "");
		} catch (FileNotFoundException e) {
			SwingExceptionManager.throwNewException(e, "HIGHSCORE SAVE ERROR");
		} catch (IOException e) {
			SwingExceptionManager.throwNewException(e, "HIGHSCORE SAVE ERROR");
		}
	}
	
	public int getHighscore() {
		return Integer.parseInt(highScore.getProperty("highscore"));
	}
	
	public String getOptionProperty(String key) {
		return highScore.getProperty(key);
	}
	
	public void setOptionProperty(String key, String value) {
		highScore.setProperty(key, value);
		try {
			FileOutputStream out = new FileOutputStream("resources/levels/highscore.properties");
			highScore.store(out, "");
		} catch (FileNotFoundException e) {
			SwingExceptionManager.throwNewException(e, "OPTION SAVE ERROR");
		} catch (IOException e) {
			SwingExceptionManager.throwNewException(e, "OPTION SAVE ERROR");
		}
	}
	
	public void UnloadLevel() { //unloads CURRENTLY loaded level
		prop.clear();
		try {
			strm.close();
		} catch (IOException e) {
			SwingExceptionManager.throwNewException(e, "LEVEL UNLOAD ERROR");
		}
		file.clear();
		comFile.clear();
		//TODO: perhaps move to beginning of load level?
	}
	
	public int amtLevels() { //counts levels by number.
		int total = 0;
		for (int i=0; i < dir.listFiles().length; i++) {
		    File child = dir.listFiles()[i];
			if(child.isDirectory() && child.getName().equals("" + i)) {
		    	total ++;
		    	//System.out.println("Level Loader: dir: " + child.getName() + " i: " + i);
		    }
		  }
		//System.out.println("Level Loader: Levels: " + total);
		return total;
	}
	
	public String getLevelProperty(String key) {
		if(strm != null) return prop.getProperty(key); //if we've loaded a level
		else return null;
	}
	
	public String getLevelLine(int line) {
	 if(file != null) return file.get(line); //if we've loaded a level
	 else return null;
	}
	
	public int amtLines() {
	 if(file != null)  return file.size(); //if we've loaded a level
		else return 0; 
	}
	
	
	public static LevelLoader getInstance() {
		return _instance; //give singleton instance to accessing class 
	}

	public String getCommentLine(int comLine) {
			 if(comFile  != null) {
				 if(comLine < comFile.size()) {
					 return comFile.get(comLine); //if we've loaded a level
			 	}
				 else {return "";}
			 }
			 else  {return "";}
	}
}

package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
//import java.util.Properties;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import mech.Exception.SwingExceptionManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.loading.DeferredResource; 
import org.newdawn.slick.loading.LoadingList;  

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ResourceFactory extends BasicGameState {
	int stateID;
	private int barX = 482;
	private final int barY = 261;
	Image logo = null;
    private DeferredResource nextResource;  
    int times = 0;
    
    float alpha = 0;
    //resource maps
	private Map<String, Sound> soundMap;
	private Map<String, Image> imageMap;
	private Map<String, File> textMap;
	private Map<String, SpriteSheet> SheetMap;
	private Map<String, Music> MusMap;
	private static ResourceFactory _instance = new ResourceFactory(GameStates.RESOURCE_LOADING_STATE);
	//resource amount variables
	private int total;
	private int loaded;
	
	
	public ResourceFactory(int state) {
		this.stateID = state;
		soundMap 	 = new HashMap<String, Sound>();
		imageMap 	 = new HashMap<String, Image>();
		textMap 	 = new HashMap<String, File>();
		SheetMap 	 = new HashMap<String, SpriteSheet>();
		MusMap 		 = new HashMap<String, Music>();
	}	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) {
		LoadingList.setDeferredLoading(true);
		File f = new File("resources/resources.xml");
		if(f.exists()) {
			loadResourceList(f);
		}
		else {
			unpack();
			if(f.exists()) {
				loadResourceList(f);
			}
			else {
				SwingExceptionManager.throwNewException(new SlickException("Failled to unzip properly"), "Resource Unpack failed.");
			}
		}
	}
	
	private void unpack() {
		//TODO: unpack zip file
	}

	private void loadResourceList(File res) {
		InputStream in = null;
		try {
		in = new FileInputStream(res);
		} catch (FileNotFoundException e1) {
			SwingExceptionManager.throwNewException(e1, "Could not load Resources");
		}
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = null;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch(ParserConfigurationException e) {
			SwingExceptionManager.throwNewException(e, "Could not load Resources");
		}
		Document doc = null;
		try {
			doc = docBuilder.parse (in);
		} catch (SAXException e) {
			SwingExceptionManager.throwNewException(e, "Could not load Resources");
		} catch (IOException e) {
			SwingExceptionManager.throwNewException(e, "Could not load Resources");
		}
		doc.getDocumentElement().normalize();
		NodeList imgs = doc.getElementsByTagName("img");
		NodeList sheets = doc.getElementsByTagName("sheet");
		NodeList sounds = doc.getElementsByTagName("sound");
	  	NodeList textData = doc.getElementsByTagName("text");
	   	NodeList musicData = doc.getElementsByTagName("mus");
	   
	   	for(int idx = 0; idx < sheets.getLength(); idx++) {
	   		Node resourceNode = sheets.item(idx);
			  if(resourceNode.getNodeType() == Node.ELEMENT_NODE) {
				  Element resourceElement = (Element)resourceNode;
				  try {
					loadSheet(resourceElement);
				} catch (SlickException e) {
					SwingExceptionManager.throwNewException(e, "Could not load Resources");
				}
			  }
		   }
	   
	   for(int idx = 0; idx < imgs.getLength(); idx++) {
		  Node resourceNode = imgs.item(idx);
		  if(resourceNode.getNodeType() == Node.ELEMENT_NODE) {
			  Element resourceElement = (Element)resourceNode;
			  try {
				loadImg(resourceElement);
			} catch (SlickException e) {
				SwingExceptionManager.throwNewException(e, "Could not load Resources");
			}
		  }
	   }
		  for(int idx = 0; idx < sounds.getLength(); idx++) {
			  Node resourceNode = sounds.item(idx);
			  if(resourceNode.getNodeType() == Node.ELEMENT_NODE) {
					 Element resourceElement = (Element)resourceNode;
					 try {
						loadSound(resourceElement);
					} catch (SlickException e) {
						SwingExceptionManager.throwNewException(e, "Could not load Resources");
					}
				 }
			  }
		  for(int idx = 0; idx < textData.getLength(); idx++) {
			 Node resourceNode = textData.item(idx);
		  if(resourceNode.getNodeType() == Node.ELEMENT_NODE) {
			  Element resourceElement = (Element)resourceNode;
			  try {
				loadText(resourceElement);
			} catch (SlickException e) {
				SwingExceptionManager.throwNewException(e, "Could not load Resources");
			}
			 }
		  }
	  
	  	for(int idx = 0; idx < musicData.getLength(); idx++) {
			 Node resourceNode = musicData.item(idx);
		  if(resourceNode.getNodeType() == Node.ELEMENT_NODE) {
			  Element resourceElement = (Element)resourceNode;
			  try {
				loadMusic(resourceElement);
			} catch (SlickException e) {
				SwingExceptionManager.throwNewException(e, "Could not load Resources");
			}
			 }
		  }
		}	  


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
	       total = LoadingList.get().getTotalResources(); 
	      loaded = LoadingList.get().getTotalResources() - LoadingList.get().getRemainingResources();
	     // barX = 400-(total*20/2);
	      if(logo != null) {
			logo.draw(0,0); 
	      }
	      g.drawString("Version: " + GameStates.version, 10, 15);
	      g.setColor(new Color(0,171,239, 150));
	      g.fillRoundRect(barX, barY, ((float)(loaded)/(float)(total)*280), 20, 30);
	      g.setColor(new Color(1f,1f,1f,1f));
	      g.drawRoundRect(barX, barY, 280, 20, 30);
	     if(nextResource != null) {
	      g.drawString("" + nextResource.getDescription(), 475, 295);
	      g.drawString("" + loaded + "/" + total, 584, 315);
	     }
	}

	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		  if (nextResource != null) { 
	            try { 
	                nextResource.load(); 
	              try { Thread.sleep(10); } catch (Exception e) {e.printStackTrace();} 
	            } catch (IOException e) { 
	                throw new SlickException("Failed to load: "+ nextResource.getDescription(), e); 
	            }  
		  }
		  
	            if (total != loaded) { 
	                nextResource = LoadingList.get().getNext(); 
	            }
	      else if(total == loaded ) {
	    	  if(times != 20) {
		    	  times++;
		      }
		      else {
		    	  GameStates.gamer.changeState(GameStates.INTRO_TEXT_STATE, Color.black); 
		      }
	      }
	}

	
	private void loadSheet(Element resource) throws SlickException {
			try {
			if(!Game.isDebug()) {
				if(resource.getAttribute("spacey").toString().equals("")) {
					SheetMap.put(resource.getAttribute("id").toString(), new SpriteSheet(resource.getTextContent(), Integer.parseInt(resource.getAttribute("space").toString()), Integer.parseInt(resource.getAttribute("space").toString()) ,GameStates.TransparentCol));
				}
				else {
					SheetMap.put(resource.getAttribute("id").toString(), new SpriteSheet(resource.getTextContent(), Integer.parseInt(resource.getAttribute("space").toString()), Integer.parseInt(resource.getAttribute("spacey").toString()) ,GameStates.TransparentCol));
				}
			}
			else {
				if(resource.getAttribute("spacey").toString().equals("")) {
					SheetMap.put(resource.getAttribute("id").toString(), new SpriteSheet(resource.getTextContent(), Integer.parseInt(resource.getAttribute("space").toString()), Integer.parseInt(resource.getAttribute("space").toString()) ));
				}
				else {
					SheetMap.put(resource.getAttribute("id").toString(), new SpriteSheet(resource.getTextContent(), Integer.parseInt(resource.getAttribute("space").toString()), Integer.parseInt(resource.getAttribute("spacey").toString()) ));
				}
			}
			}  catch (RuntimeException e) {
				throw new SlickException("Could not load resources", e);
			} catch (SlickException e) {
				throw new SlickException("Could not load resources", e);
			}
		}
	
	private void loadImg(Element resource) throws SlickException {
		if(resource.getAttribute("id").equalsIgnoreCase("logo")) {
			logo = new Image(resource.getTextContent());
		}
		else {
			try {
				Image img =new Image(resource.getTextContent());
				img.setFilter(Image.FILTER_NEAREST);
				imageMap.put(resource.getAttribute("id").toString(), img);
			}  catch (RuntimeException e) {
				throw new SlickException("Could not load resources", e);
			} catch (SlickException e) {
				throw new SlickException("Could not load resources", e);
			}
		}
	}
	
	private void loadSound(Element resource) throws SlickException {
		try {
			soundMap.put(resource.getAttribute("id").toString(), new Sound(resource.getTextContent()));
		}  catch (RuntimeException e) {
			throw new SlickException("Could not load resources", e);
		} catch (SlickException e) {
			throw new SlickException("Could not load resources", e);
		}
	}
	
	
	private void loadText(Element resource) throws SlickException {
		try {
			textMap.put(resource.getAttribute("id").toString(), new File(resource.getTextContent()));
		}  catch (RuntimeException e) {
			throw new SlickException("Could not load resources", e);
		}
	}
	
	private void loadMusic(Element resource) throws SlickException {
		try {
			MusMap.put(resource.getAttribute("id").toString(), new Music(resource.getTextContent(), true));
		}  catch (RuntimeException e) {
			throw new SlickException("Could not load resources", e);
		} catch (SlickException e) {
			throw new SlickException("Could not load resources", e);
		}
		
	}
	
	@Override
	public int getID() {
		return stateID;
	}
	
	 public final static ResourceFactory getInstance(){
	     return _instance;
	  }

	public Image getImage(String id) {
		return imageMap.get(id);
	}

	public Image getSheetImage(String id, int x, int y) {
		return SheetMap.get(id).getSprite(x, y);
	}
	public Image getSheetImage(String id, Vector2f coords) {
		return SheetMap.get(id).getSprite((int)coords.x, (int) coords.y);
	}
	
	public SpriteSheet getSheet(String id) {
		return SheetMap.get(id);
	}
	
	public Sound getSound(String id) {
		return soundMap.get(id);
	}
	
	public File getTextFile(String id) {
		return textMap.get(id);
	}

	public Music getMusic(String id) {
		return MusMap.get(id);
	}
}

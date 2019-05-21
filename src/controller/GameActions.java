package controller;

import javafx.scene.canvas.GraphicsContext;
import misc.Difficulty;
import misc.GameObjectFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import gameObjects.DangerousBomb;
import gameObjects.GameObject;
import gameObjects.RegularFruit;
import gameObjects.SpecialFruit;
import interfaces.IGameActions;

public class GameActions implements IGameActions {
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private int score = 0;
	private int lives = 3;
	private int time = 0;
	private static Difficulty difficulty = Difficulty.EASY;
	ArrayList<GameObject> toBeDeleted = new ArrayList<GameObject>();
	
	// Singleton
	private static GameActions gameActions; 											
	private GameActions() {
	} 
	public static synchronized GameActions getInstance() {
		if (gameActions == null) 
			gameActions = new GameActions(); 
		return gameActions; 
	} 

	@Override
	public void createGameObject() {
		gameObjects.add(GameObjectFactory.createObject());
	}

	@Override
	public void updateObjectsLocations(GraphicsContext gc) {
		for (GameObject x : gameObjects) {
			x.move(0,difficulty);
			x.render(gc);
		}

	}

	@Override
	public void ResetGame() {
		lives = 3;
		score = 0;
		time = 0;
		gameObjects.clear();
	}

	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public void incrementTime() {
		time++;
	}

	public void scorePlusOne() {
		score++;

	}

	public void scorePlusFive() {
		score = score + 5;
	}

	public void loseLife() {
		lives--;
	}
	public static Difficulty getDifficulty() {
		return difficulty;
	}
	public static void setDifficulty(Difficulty difficulty) {
		GameActions.difficulty = difficulty;
	}
	public void sliceObject(GameObject gameObject) {
		gameObject.slice();
		if(gameObject instanceof RegularFruit) scorePlusOne();
		else if(gameObject instanceof SpecialFruit) scorePlusFive();
		else if(gameObject instanceof DangerousBomb) loseLife();
	}
	public void checkFallingObjects() {
		for (GameObject x : gameObjects) {
			if (x.hasMovedOffScreen()) {
				toBeDeleted.add(x);
				if (!x.isSliced())
					if (x instanceof RegularFruit || x instanceof SpecialFruit) {
						loseLife();
					}
			}
		}
		gameActions.getGameObjects().removeAll(toBeDeleted);
		toBeDeleted.clear();
	}
	
	public void SaveGame() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            StringBuilder xmlStringBuilder = new StringBuilder();
            xmlStringBuilder.append("<?xml version=?> <class> </class>");
            new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
            Element Diff = document.createElement("Difficulty");
            Element Score = document.createElement("Score");
            Element Lives = document.createElement("Lives");
            Element Time = document.createElement("Time");
            document.appendChild(Diff);
 
            //Diff.appendChild();
 
            Diff.appendChild(document.createTextNode(difficulty +" "));
            Diff.appendChild(Score);
            Score.appendChild(document.createTextNode(String.valueOf(score) + " "));
            Diff.appendChild(Lives);
            Lives.appendChild(document.createTextNode(String.valueOf(lives) + " "));
            Diff.appendChild(Time);
            Time.appendChild(document.createTextNode(String.valueOf(time) + " "));
 
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("Resources/SavedGame.xml"));
            transformer.transform(domSource, streamResult);
            System.out.println("Done creating XML File");
 
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LoadGame();
 
    }
 
    public void LoadGame(){
 
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            document = builder.parse("Resources/SavedGame.xml");
            String Data = document.getElementsByTagName("Difficulty").item(0).getTextContent();
            System.out.println(Data);
            ArrayList<String> DataList = new ArrayList<String>();
            for (String val : Data.split(" ")) {
                System.out.println(val);
                DataList.add(val);
            }
 
            String ldiff = DataList.get(0);
            
            if(ldiff.equals("EASY")) difficulty = Difficulty.EASY;
            else if(ldiff.equals("MEDIUM")) difficulty = Difficulty.MEDIUM;
            else difficulty = Difficulty.HARD;
            
            score = Integer.parseInt(DataList.get(1));
            
            lives = Integer.parseInt(DataList.get(2));
            
 
            time = Integer.parseInt(DataList.get(3));
            
        }
        catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
 
 
    }

}

//reset game
//labels
//special fruit
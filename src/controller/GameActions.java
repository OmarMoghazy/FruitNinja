package controller;

import javafx.scene.canvas.GraphicsContext;
import misc.Difficulty;
import misc.GameObjectFactory;

import java.util.ArrayList;


import command.Command;
import command.Remote;
import command.SaveLoad;
import command.*;
import gameObjects.DangerousBomb;
import gameObjects.GameObject;
import gameObjects.RegularFruit;
import gameObjects.SpecialFruit;
import interfaces.IGameActions;

public class GameActions implements IGameActions {
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	static int score = 0;
	static int lives = 3;
	static int time = 0;
	static Difficulty difficulty = Difficulty.EASY;
	ArrayList<GameObject> toBeDeleted = new ArrayList<GameObject>();
	
	SaveLoad saveLoad = new SaveLoad();
	Remote remote = new Remote();
	Command SaveCommand = new SaveCommand(saveLoad);
	Command loadCommand = new LoadCommand(saveLoad);
	
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

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		GameActions.score = score;
	}

	public static int getTime() {
		return time;
	}

	public static void setTime(int time) {
		GameActions.time = time;
	}

	public static int getLives() {
		return lives;
	}

	public static void setLives(int lives) {
		GameActions.lives = lives;
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
       remote.setCommand(SaveCommand);
       remote.pressButton();
    }
 
    public void LoadGame(){
    	remote.setCommand(loadCommand);
    	remote.pressButton();
    }

}

//reset game
//labels
//special fruit
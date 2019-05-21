package controller;

import javafx.scene.canvas.GraphicsContext;
import misc.Difficulty;
import misc.GameObjectFactory;
import java.util.ArrayList;

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
	public void createGameObject() { gameObjects.add(GameObjectFactory.createObject()); }

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

	public ArrayList<GameObject> getGameObjects() { return gameObjects; }

	public int getScore() { return score; }

	public void setScore(int score) { this.score = score; }

	public int getTime() { return time; }

	public void setTime(int time) { this.time = time; }

	public int getLives() { return lives; }

	public void setLives(int lives) { this.lives = lives; }

	public void incrementTime() { time++; }

	private void scorePlusOne() { score++; }

	private void scorePlusFive() { score = score + 5; }

	private void loseLife() { lives--; }

	public static Difficulty getDifficulty() { return difficulty; }

	public static void setDifficulty(Difficulty difficulty) { GameActions.difficulty = difficulty; }

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
}
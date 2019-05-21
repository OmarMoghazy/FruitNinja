package controller;

import javafx.scene.canvas.GraphicsContext;
import misc.Difficulty;
import misc.GameObjectFactory;
import misc.SaveMemento;

import java.util.ArrayList;

import command.*;
import gameObjects.DangerousBomb;
import gameObjects.GameObject;
import gameObjects.RegularFruit;
import gameObjects.SpecialFruit;
import interfaces.Command;
import interfaces.IGameActions;

public class GameActions implements IGameActions {
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private static int score = 0;
	private static int lives = 3;
	private static int time = 0;
	private static Difficulty difficulty;
	private ArrayList<GameObject> toBeDeleted = new ArrayList<GameObject>();
	private SaveLoad saveLoad = new SaveLoad();
	private Remote remote = new Remote();
	private Command SaveCommand = new SaveCommand(saveLoad);
	private Command loadCommand = new LoadCommand(saveLoad);
	private GameObjectFactory gameObjectFactory = new GameObjectFactory();

	// Singleton
	private static GameActions gameActions;

	private GameActions() { }

	public static synchronized GameActions getInstance() {
		if (gameActions == null)
			gameActions = new GameActions();
		return gameActions;
	}

	@Override
	public void createGameObject() {
		gameObjects.add(gameObjectFactory.createObject());
	}

	@Override
	public void updateObjectsLocations(GraphicsContext gc) {
		for (GameObject x : gameObjects) {
			x.move(0, difficulty);
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

	public void scorePlusOne() { score++; }

	public void combo2() { score = score * 2; }

	public void combo3() { score = score * 3; }

	public void combo4() { score = score * 4; }

	public void scorePlusFive() {
		score = score + 5;
	}

	public void loseLife() {
		lives--;
	}

	public void timeBonus(){ if (time >=10 ) time = time -10; }

	public void lifeBonus(){ if(lives < 3) lives = lives + 1;}

	public static Difficulty getDifficulty() {
		return difficulty;
	}

	public static void setDifficulty(Difficulty difficulty) {
		GameActions.difficulty = difficulty;
	}

	public void sliceObject(GameObject gameObject) {
		gameObject.slice();
		if (gameObject instanceof RegularFruit)
			scorePlusOne();
		else if (gameObject instanceof SpecialFruit)
			scorePlusFive();
		else if (gameObject instanceof DangerousBomb)
			loseLife();
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

	public void saveGame() {
		SaveMemento saveMemento = new SaveMemento(GameActions.getScore(), GameActions.getLives(), GameActions.getTime(), GameActions.getDifficulty());
		saveLoad.setMemento(saveMemento);
		remote.setCommand(SaveCommand);
		remote.pressButton();
	}

	public void loadGame() {
		remote.setCommand(loadCommand);
		remote.pressButton();
	}
}
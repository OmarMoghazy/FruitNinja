package controller;

import interfaces.IGameObject;
import javafx.scene.canvas.GraphicsContext;
import misc.GameObjectFactory;

import java.util.ArrayList;

import gameObjects.GameObject;
import interfaces.IGameActions;

public class GameActions implements IGameActions {
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	// Singleton
	//////////////////////////////////////////////////////////////////////////////////
	private static GameActions gameActions;											//
																					//
	private GameActions() {}														//
																					//
	public static synchronized GameActions getInstance() {							//
		if (gameActions == null)													//
			gameActions = new GameActions();										//
		return gameActions;															//
	}																				//
																					//
	//////////////////////////////////////////////////////////////////////////////////							

	@Override
	public void createGameObject() {
		gameObjects.add(GameObjectFactory.createObject());
	}

	@Override
	public void updateObjectsLocations(GraphicsContext gc) {
		for(GameObject x : gameObjects) {
			x.move(0);
			x.render(gc);
		}

	}

	@Override
	public void ResetGame() {
		// TODO Auto-generated method stub

	}
	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}

}

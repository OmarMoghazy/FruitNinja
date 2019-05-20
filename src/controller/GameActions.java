package controller;

import interfaces.IGameObject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import misc.GameObjectFactory;

import java.io.IOException;
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
	public void ResetGame(MouseEvent e) throws IOException {
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
		Scene scene = new Scene(root, 1000, 552);
		window.setScene(scene);
		window.show();
	}
	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}

}

//reset game
//labels
//special fruit
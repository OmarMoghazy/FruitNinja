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
	private int score = 0;
	private int lives = 3;
	private int time = 0;

	// Singleton
	//////////////////////////////////////////////////////////////////////////////////
	private static GameActions gameActions; //
											//

	private GameActions() {
	} //
		//

	public static synchronized GameActions getInstance() { //
		if (gameActions == null) //
			gameActions = new GameActions(); //
		return gameActions; //
	} //
		//
	//////////////////////////////////////////////////////////////////////////////////

	@Override
	public void createGameObject() {
		gameObjects.add(GameObjectFactory.createObject());
	}

	@Override
	public void updateObjectsLocations(GraphicsContext gc) {
		for (GameObject x : gameObjects) {
			x.move(0);
			x.render(gc);
		}

	}

	@Override
	public void ResetGame(MouseEvent e) throws IOException {
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
		lives = 3;
		score = 0;
		time = 0;
		gameObjects.clear();
		Scene scene = new Scene(root, 800, 600);
		window.setScene(scene);
		window.show();
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

}

//reset game
//labels
//special fruit
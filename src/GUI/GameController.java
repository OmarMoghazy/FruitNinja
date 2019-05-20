package GUI;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.GameActions;
import gameObjects.GameObject;
import interfaces.IGameObject;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameController implements Initializable {

	@FXML Canvas canvas;
	GraphicsContext gc;
	ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	long lastUpdate;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		GameActions gameActions = GameActions.getInstance();
		gc = canvas.getGraphicsContext2D();
		for(int i = 0; i < 20 ; i++) {
			gameObjects.add(gameActions.createGameObject());
		}
		AnimationTimer x = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				gc.clearRect(0, 0, 800, 600);
				for(int i = 0; i <gameObjects.size();i++) {
					gameObjects.get(i).move(arg0);
					gameObjects.get(i).render(gc);
				}
			}
		};
		x.start();
	}
}

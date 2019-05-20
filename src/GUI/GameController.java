package GUI;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.GameActions;
import gameObjects.GameObject;
import interfaces.IGameObject;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class GameController implements Initializable {

	@FXML Canvas canvas;
	GraphicsContext gc;
	ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		GameActions gameActions = GameActions.getInstance();
		gc = canvas.getGraphicsContext2D();
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent->{
			ArrayList<GameObject> toBeDeleted = new ArrayList<GameObject>();
			for(GameObject x : gameObjects) {
				if(x.hasMovedOffScreen()) toBeDeleted.add(x);
			}
			gameObjects.removeAll(toBeDeleted);
			gameObjects.add(gameActions.createGameObject());
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		AnimationTimer x = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				gc.clearRect(0, 0, 800, 600);
				gc.drawImage(new Image(new File("Resources/bg.png").toURI().toString()), 0, 0, 800, 600);
				for(int i = 0; i <gameObjects.size();i++) {
					gameObjects.get(i).move(arg0);
					gameObjects.get(i).render(gc);
				}
			}
		};
		x.start();
	}
	@FXML
	public void onMouseDragged(ActionEvent e) {
		System.out.println("mouse dragged");
	}
}

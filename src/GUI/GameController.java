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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import misc.ObjectType;

public class GameController implements Initializable {

	@FXML Canvas canvas;
	@FXML Label scoreLabel;
	@FXML Label highscoreLabel;
	@FXML Label timeLabel;
	@FXML Label livesLabel;
	
	GraphicsContext gc;
	ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	double mouseX;
	double mouseY;
	private int score = 0;
	private int lives = 3;
	
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
			System.out.println(score + " " + lives);
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		AnimationTimer x = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				gc.clearRect(0, 0, 800, 600);
				gc.drawImage(new Image(new File("Resources/bg.png").toURI().toString()), 0, 0, 800, 600);
				for(int i = 0; i <gameObjects.size();i++) {
					if((mouseX >= gameObjects.get(i).getXlocation() && mouseX <= gameObjects.get(i).getXlocation() + 75) && (mouseY >= gameObjects.get(i).getYlocation() && mouseY <= gameObjects.get(i).getYlocation() + 75) ) {
						gameObjects.get(i).slice();
						if(gameObjects.get(i).getObjectType().equals(ObjectType.REGULAR_FRUIT)) score++;
						else if(gameObjects.get(i).getObjectType().equals(ObjectType.SPECIAL_FRUIT)) score = score + 5;
						else if(gameObjects.get(i).getObjectType().equals(ObjectType.DANGEROUS_BOMB)) lives--; 
						else loseGame();
					}
					gameObjects.get(i).move(arg0);
					gameObjects.get(i).render(gc);
				}
			}
		};
		x.start();
	}
	protected void loseGame() {
	}
	@FXML
	public void onDrag(MouseEvent event) {
		mouseX = event.getX();
		mouseY = event.getY();
	}
}

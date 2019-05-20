package GUI;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.GameActions;
import gameObjects.DangerousBomb;
import gameObjects.GameObject;
import gameObjects.RegularFruit;
import gameObjects.SpecialFruit;
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

	@FXML
	Canvas canvas;
	@FXML
	Label scoreLabel;
	@FXML
	Label highscoreLabel;
	@FXML
	Label timeLabel;
	@FXML
	Label livesLabel;

	GraphicsContext gc;
	double mouseX;
	double mouseY;
	private int score = 0;
	private int lives = 3;

	GameActions gameActions;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gameActions = GameActions.getInstance();
		gc = canvas.getGraphicsContext2D();
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
			gameActions.createGameObject();
			System.out.println(score + " " + lives);
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		AnimationTimer x = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				gc.clearRect(0, 0, 800, 600);
				gc.drawImage(new Image(new File("Resources/bg.png").toURI().toString()), 0, 0, 800, 600);
				
				ArrayList<GameObject> toBeDeleted = new ArrayList<GameObject>();
				
				for (int i = 0; i < gameActions.getGameObjects().size(); i++) {
					if ((mouseX >= gameActions.getGameObjects().get(i).getXlocation()
							&& mouseX <= gameActions.getGameObjects().get(i).getXlocation() + 75)
							&& !gameActions.getGameObjects().get(i).isSliced()
							&& (mouseY >= gameActions.getGameObjects().get(i).getYlocation()
									&& mouseY <= gameActions.getGameObjects().get(i).getYlocation() + 75)) {
						gameActions.getGameObjects().get(i).slice();
						if (gameActions.getGameObjects().get(i) instanceof RegularFruit)
							score++;
						else if (gameActions.getGameObjects().get(i) instanceof SpecialFruit)
							score = score + 5;
						else if (gameActions.getGameObjects().get(i) instanceof DangerousBomb)
							lives--;
						else
							loseGame();

					}
					for (GameObject x : gameActions.getGameObjects()) {
						if (x.hasMovedOffScreen()) {
							toBeDeleted.add(x);
						if(!x.isSliced()) 
							if(x instanceof RegularFruit || x instanceof SpecialFruit) {
								lives--;
							}}
					}
					gameActions.getGameObjects().removeAll(toBeDeleted);
					gameActions.updateObjectsLocations(gc);
				}
			}
		};
		x.start();
	}

	protected void loseGame() {
	}

	@FXML
	public void onMouseMoved(MouseEvent event) {
		mouseX = event.getX();
		mouseY = event.getY();
	}
}

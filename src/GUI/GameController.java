package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import controller.GameActions;
import gameObjects.FatalBomb;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import misc.Alerts;

public class GameController implements Initializable {

	@FXML
	Canvas canvas;
	@FXML
	Label scorelabel;
	@FXML
	Label highscorelabel;
	@FXML
	Label gamediflabel;
	@FXML
	Label timeLabel;
	@FXML
	ImageView life1;
	@FXML
	ImageView life2;
	@FXML
	ImageView life3;
	@FXML
	Button resetButton;

	double mouseX;
	double mouseY;

	GraphicsContext gc;
	Timeline timeline;
	GameActions gameActions = GameActions.getInstance();
	AnimationTimer x;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gc = canvas.getGraphicsContext2D();
		timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
			gameActions.createGameObject();
			scorelabel.setText(Integer.toString(gameActions.getScore()));
			timeLabel.setText(Integer.toString(gameActions.getTime()));
			gameActions.incrementTime();
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		x = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				
				gc.clearRect(0, 0, 800, 600);
				gc.drawImage(new Image(new File("Resources/bg.png").toURI().toString()), 0, 0, 800, 600);
				
				life1.setVisible(true);
				life2.setVisible(true);
				life3.setVisible(true);
				
				if (gameActions.getLives() < 3)	life1.setVisible(false);
				if (gameActions.getLives() < 2)	life2.setVisible(false);	
				if (gameActions.getLives() < 1)	life3.setVisible(false);
				
				for (int i = 0; i < gameActions.getGameObjects().size(); i++) {
				
					if ((mouseX >= gameActions.getGameObjects().get(i).getXlocation()
					
							&& mouseX <= gameActions.getGameObjects().get(i).getXlocation() + 75)
							&& !gameActions.getGameObjects().get(i).isSliced()
							&& (mouseY >= gameActions.getGameObjects().get(i).getYlocation()
									&& mouseY <= gameActions.getGameObjects().get(i).getYlocation() + 75)) {
						if(gameActions.getGameObjects().get(i) instanceof FatalBomb) loseGame();
						else gameActions.sliceObject(gameActions.getGameObjects().get(i));
						if(gameActions.getLives() == 0) loseGame();
					}
					gameActions.checkFallingObjects();
					if(gameActions.getLives() == 0) loseGame();
					gameActions.updateObjectsLocations(gc);	
				}
			}
		};
		x.start();
	}

	private void loseGame() {
		System.out.println("the snooze u loose");
		x.stop();
		timeline.stop();
		Alerts.textAlert("u kiding meeeee", "u loose");
		// resetButton.fire();
	}

	@FXML
	public void onDrag(MouseEvent event) {
		mouseX = event.getX();
		mouseY = event.getY();
	}

	@FXML
	public void reset(ActionEvent e) throws IOException {
		gameActions.ResetGame();
		gameActions.updateObjectsLocations(gc);
		x.start();
		timeline.play();
	}

	// alertBox

}

//moved to be deleted array out of the handle function
//moved gameActions too so it can be used in functions
//moved animation timer out too so it can be stopped on loss
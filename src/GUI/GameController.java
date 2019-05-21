package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import controller.GameActions;
import gameObjects.FatalBomb;
import misc.Alerts;
import misc.Difficulty;

public class GameController implements Initializable {

	@FXML private Canvas canvas;
	@FXML private Label scorelabel;
	@FXML private Label highscorelabel;
	@FXML private Label gamediflabel;
	@FXML private Label timeLabel;
	@FXML private ImageView life1;
	@FXML private ImageView life2;
	@FXML private ImageView life3;
	@FXML private Button resetButton;

	private double mouseX;
	private double mouseY;

	private GraphicsContext gc;
	private Timeline timeline;
	private GameActions gameActions = GameActions.getInstance();
	private AnimationTimer x;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gameActions.ResetGame();
		if(GameActions.getDifficulty().equals(Difficulty.EASY)) gamediflabel.setText("Easy");
		else if(GameActions.getDifficulty().equals(Difficulty.MEDIUM)) gamediflabel.setText("Medium");
		else gamediflabel.setText("Hard");
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
					}
					gameActions.checkFallingObjects();
					if(gameActions.getLives() == 0) loseGame();
					gameActions.updateObjectsLocations(gc);	
				}
			}
		};
		x.start();
	}
	
	public void back(ActionEvent e) {
		x.stop();
		timeline.stop();
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene(root);
			window.setScene(scene);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			try {
				reset(null);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
	}

	private void loseGame() {
		System.out.println("the snooze u loose");
		x.stop();
		timeline.stop();
		GameActions.setDifficulty(Difficulty.HARD);
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
}
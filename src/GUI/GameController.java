package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import controller.GameActions;
import gameObjects.DangerousBomb;
import gameObjects.GameObject;
import gameObjects.RegularFruit;
import gameObjects.SpecialFruit;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameController implements Initializable {

	@FXML Canvas canvas;
	@FXML Label scorelabel;
	@FXML Label highscorelabel;
	@FXML Label gamediflabel;
	@FXML Label timeLabel;
	@FXML ImageView life1;
	@FXML ImageView life2;
	@FXML ImageView life3;
	@FXML Button resetButton;

	private double mouseX;
	private double mouseY;
	private int score = 0;
	private int lives = 3;
	private int time = 0;

	private GraphicsContext gc;
	private Timeline timeline;
	private ArrayList<GameObject> toBeDeleted = new ArrayList<>();
	private GameActions gameActions = GameActions.getInstance();
	private AnimationTimer x;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gc = canvas.getGraphicsContext2D();
		timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
			gameActions.createGameObject();
			scorelabel.setText(Integer.toString(score));
			timeLabel.setText(Integer.toString(time));
			if(lives < 3)
				life1.setVisible(false);
			if(lives < 2)
				life2.setVisible(false);
			if(lives < 1)
				life3.setVisible(false);
			time++;
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		x = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				gc.clearRect(0, 0, 800, 600);
				gc.drawImage(new Image(new File("Resources/bg.png").toURI().toString()), 0, 0, 800, 600);

				for (int i = 0; i < gameActions.getGameObjects().size(); i++) {
					if ((mouseX >= gameActions.getGameObjects().get(i).getXlocation()
							&& mouseX <= gameActions.getGameObjects().get(i).getXlocation() + 75)
							&& !gameActions.getGameObjects().get(i).isSliced()
							&& (mouseY >= gameActions.getGameObjects().get(i).getYlocation()
								&& mouseY <= gameActions.getGameObjects().get(i).getYlocation() + 75)) {
						gameActions.getGameObjects().get(i).slice();
						if (gameActions.getGameObjects().get(i) instanceof RegularFruit)
							score++;
						else if (gameActions.getGameObjects().get(i) instanceof SpecialFruit){
							//special fruit slices all fruits
							/*for (int j = 0; j < gameActions.getGameObjects().size(); j++) {
								if (gameActions.getGameObjects().get(i) instanceof RegularFruit)
									gameActions.getGameObjects().get(i).slice();
							}*/
							score = score+5;
						}
						else if (gameActions.getGameObjects().get(i) instanceof DangerousBomb){
							lives--;
							if(lives ==0 )
								loseGame();
						}
						else
							loseGame();
					}
					for (GameObject x : gameActions.getGameObjects()) {
						if (x.hasMovedOffScreen()) {
							toBeDeleted.add(x);
						if(!x.isSliced()) 
							if(x instanceof RegularFruit || x instanceof SpecialFruit) {
								lives--;
								if(lives ==0 )
									loseGame();
							}}
					}
					gameActions.getGameObjects().removeAll(toBeDeleted);
					gameActions.updateObjectsLocations(gc);
				}
			}
		};
		x.start();
	}

	private void loseGame() {
		System.out.println("the snooze u loose");
		x.stop();
		//alertBox("u kidding meeeeeeeee", "Resources/gaemo.png");
		//resetButton.fire();
	}

	@FXML
	public void onDrag(MouseEvent event) {
		mouseX = event.getX();
		mouseY = event.getY();
	}

	@FXML
	public void reset(ActionEvent e) throws IOException{
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
		Scene scene = new Scene(root, 800, 600);
		window.setScene(scene);
		window.show();
	}

	// alertBox
	public static void alertBox(String title, String FileName) {
		Stage alert = new Stage();
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.setTitle(title);
		alert.setMinHeight(500);
		alert.setMinWidth(250);

		Image image = new Image(new File(FileName).toURI().toString());
		ImageView imageView = new ImageView(image);
		//Label label = new Label(FileName);
		VBox layout = new VBox();
		//layout.getChildren().add(label);
		layout.getChildren().add(imageView);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		alert.setScene(scene);
		alert.showAndWait();
	}
}

//moved to be deleted array out of the handle function
//moved gameActions too so it can be used in functions
//moved animation timer out too so it can be stopped on loss
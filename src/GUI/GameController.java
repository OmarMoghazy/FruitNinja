package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import controller.GameActions;
import gameObjects.FatalBomb;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import misc.Alerts;
import misc.Difficulty;

public class GameController implements Initializable {

	Media slice = new Media(new File("Resources/slice.mp3").toURI().toString());
	MediaPlayer SliceSound = new MediaPlayer(slice);

	Media special = new Media(new File("Resources/special.mp3").toURI().toString());
	MediaPlayer SpecialSound = new MediaPlayer(special);

	Media Bombsound = new Media(new File("Resources/bomb.mp3").toURI().toString());
	MediaPlayer BombSound = new MediaPlayer(Bombsound);

	Media highscoresound = new Media(new File("Resources/highscore.mp3").toURI().toString());
	MediaPlayer billie = new MediaPlayer(highscoresound);

	Media wii = new Media(new File("Resources/wii.mp3").toURI().toString());
	MediaPlayer wiiPlayer = new MediaPlayer(wii);

	Media lose = new Media(new File("Resources/gameover.mp3").toURI().toString());
	MediaPlayer evilmorty = new MediaPlayer(lose);
	@FXML
	private Canvas canvas;
	@FXML
	private Label scorelabel, highscorelabel, gamediflabel, timeLabel, lives;
	@FXML
	private ImageView life1, life2, life3;
	@FXML
	private Button resetButton;

	private double mouseX, mouseY;

	private GraphicsContext gc;
	private Timeline timeline;
	private GameActions gameActions = GameActions.getInstance();
	private AnimationTimer animationTimer;
	private int highscore = 0;
	public static int flag = 1;
	private int c = 0;
	public static String gameMode;

	private Image life = new Image(new File("Resources/lives2.png").toURI().toString());

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		billie.stop();
		evilmorty.stop();
		wiiPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		wiiPlayer.stop();
		wiiPlayer.play();

		life1.setImage(life);
		life2.setImage(life);
		life3.setImage(life);

		File file = new File("Highscore.txt");
		try {
			Scanner scanner = new Scanner(file);
			highscore = Integer.parseInt(scanner.nextLine());
			scanner.close();
		} catch (FileNotFoundException e) {
		}
		highscorelabel.setText(Integer.toString(highscore));

		if (flag == 1)
			gameActions.ResetGame();
		else {
			gameActions.ResetGame();
			gameActions.loadGame();
		}

		if (gameMode.equals("arcade"))
			gamediflabel.setText("Arcade");
		else if (GameActions.getDifficulty().equals(Difficulty.EASY))
			gamediflabel.setText("Easy");
		else if (GameActions.getDifficulty().equals(Difficulty.MEDIUM))
			gamediflabel.setText("Medium");
		else if (GameActions.getDifficulty().equals(Difficulty.HARD))
			gamediflabel.setText("Hard");

		gc = canvas.getGraphicsContext2D();
		timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
			gameActions.createGameObject();
			gameActions.incrementTime();
			if (GameActions.getTime() % 20 == 0)
				GameObject.speed += 1.5;
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		animationTimer = new AnimationTimer() {
			@Override
			public void handle(long arg0) {

				gc.clearRect(0, 0, 800, 600);
				gc.drawImage(new Image(new File("Resources/bg.png").toURI().toString()), 0, 0, 800, 600);

				if (gameMode.equals("arcade")) {
					life1.setVisible(false);
					life2.setVisible(false);
					life3.setVisible(false);

					gameActions.checkFallingObjects2();

					if (GameActions.getTime() == 60)
						loseGame();
				} else {
					// has to be there for life bonus
					life1.setVisible(true);
					life2.setVisible(true);
					life3.setVisible(true);

					if (GameActions.getLives() < 3)
						life1.setVisible(false);
					if (GameActions.getLives() < 2)
						life2.setVisible(false);
					if (GameActions.getLives() < 1)
						life3.setVisible(false);

					gameActions.checkFallingObjects();
					if (GameActions.getLives() == 0)
						loseGame();
				}
				gameActions.updateObjectsLocations(gc);
				scorelabel.setText(Integer.toString(GameActions.getScore()));
				timeLabel.setText(Integer.toString(GameActions.getTime()));
				if (GameActions.getScore() > highscore)
					highscorelabel.setText(Integer.toString(GameActions.getScore()));
			}
		};
		animationTimer.start();
	}

	public void save(ActionEvent e) {
		gameActions.saveGame();
	}

	public void back(ActionEvent e) {
		billie.stop();
		evilmorty.stop();
		wiiPlayer.stop();
		animationTimer.stop();
		timeline.stop();
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene(root);
			window.setScene(scene);
		} catch (IOException e1) {
			e1.printStackTrace();
			try {
				reset(null);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}

	private void loseGame() {
		wiiPlayer.stop();

		animationTimer.stop();
		timeline.stop();
		Alerts.imageAlert("game over", "Resources/gaemo.png");
		if (GameActions.getScore() > highscore) {
			billie.setCycleCount(MediaPlayer.INDEFINITE);
			billie.stop();
			billie.play();
			highscore = GameActions.getScore();
			highscorelabel.setText(Integer.toString(highscore));
			FileWriter fw;
			try {
				fw = new FileWriter("Highscore.txt");
				fw.write(Integer.toString(GameActions.getScore()));
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Alerts.textAlert("Congratulations!", "New Highsore!");
		} else {
			evilmorty.setCycleCount(MediaPlayer.INDEFINITE);
			evilmorty.stop();
			evilmorty.play();
		}
	}

	@FXML
	public void onDrag(MouseEvent event) {
		mouseX = event.getX();
		mouseY = event.getY();

		for (int i = 0; i < gameActions.getGameObjects().size(); i++) {
			if ((mouseX >= gameActions.getGameObjects().get(i).getXlocation()
					&& mouseX <= gameActions.getGameObjects().get(i).getXlocation() + 75)
					&& !gameActions.getGameObjects().get(i).isSliced()
					&& (mouseY >= gameActions.getGameObjects().get(i).getYlocation()
							&& mouseY <= gameActions.getGameObjects().get(i).getYlocation() + 75)) {

				if (gameActions.getGameObjects().get(i) instanceof RegularFruit) {
					SliceSound.stop();
					SliceSound.play();
				} else if (gameActions.getGameObjects().get(i) instanceof SpecialFruit) {
					SpecialSound.stop();
					SpecialSound.play();
				} else {
					BombSound.stop();
					BombSound.play();
				}

				if (gameMode.equals("arcade")) {
					gameActions.sliceObject2(gameActions.getGameObjects().get(i));
				} else {
					if (gameActions.getGameObjects().get(i) instanceof FatalBomb)
						loseGame();
					else if (gameActions.getGameObjects().get(i) instanceof SpecialFruit) {
						gameActions.sliceObject(gameActions.getGameObjects().get(i));
						/*
						 * for (int j = 0; j < gameActions.getGameObjects().size(); j++){ if
						 * (gameActions.getGameObjects().get(i) instanceof RegularFruit)
						 * gameActions.sliceObject(gameActions.getGameObjects().get(i)); }
						 */
					} else
						gameActions.sliceObject(gameActions.getGameObjects().get(i));
				}
				c++;
			}
		}
		if (c >= 4)
			gameActions.combo4();
		else if (c >= 3)
			gameActions.combo3();
		else if (c >= 2)
			gameActions.combo2();
		c = 0;
	}

	@FXML
	public void reset(ActionEvent e) throws IOException {
		billie.stop();
		evilmorty.stop();
		highscorelabel.setText(Integer.toString(highscore));
		gameActions.ResetGame();
		gameActions.updateObjectsLocations(gc);
		animationTimer.start();
		timeline.play();
	}
}
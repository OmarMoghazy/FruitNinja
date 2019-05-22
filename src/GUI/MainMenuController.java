package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.GameActions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import misc.Difficulty;

public class MainMenuController implements Initializable {

	private MediaPlayer Mainmenu = new MediaPlayer(new Media(new File("Resources/mainmenu.mp3").toURI().toString()));

	@FXML private ImageView mainMenu;

	Image image = new Image(new File("Resources/MainMenu.png").toURI().toString());

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainMenu.setImage(image);
		Mainmenu.setCycleCount(MediaPlayer.INDEFINITE);
		Mainmenu.stop();
		Mainmenu.play();
	}

	@FXML
	public void exitbutton() { System.exit(0); }

    @FXML
	public void easybutton(ActionEvent event) throws IOException {
    	Mainmenu.stop();
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		GameActions.setDifficulty(Difficulty.EASY);
		GameController.gameMode = "normal";
		Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
		Scene scene = new Scene(root, 800, 600);
		window.setScene(scene);
		window.show();
	}

	@FXML
	public void mediumbutton(ActionEvent event) throws IOException {
		Mainmenu.stop();
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		GameActions.setDifficulty(Difficulty.MEDIUM);
		GameController.gameMode = "normal";
		Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
		Scene scene = new Scene(root, 800, 600);
		window.setScene(scene);
		window.show();
	}
	@FXML
	public void hardbutton(ActionEvent event) throws IOException {
		Mainmenu.stop();
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		GameActions.setDifficulty(Difficulty.HARD);
		GameController.gameMode = "normal";
		Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
		Scene scene = new Scene(root, 800, 600);
		window.setScene(scene);
		window.show();
	}
	@FXML
	public void randombutton(ActionEvent event) throws IOException  {
		int x = (int) (Math.random() * 3);
		if (x==0)
			hardbutton(event);
		else if (x==1)
			mediumbutton(event);
		else if (x==2)
			easybutton(event);
	}

	@FXML
	public void arcadeButton(ActionEvent event) throws IOException  {
		Mainmenu.stop();
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		GameActions.setDifficulty(Difficulty.MEDIUM);
		GameController.gameMode = "arcade";
		Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
		Scene scene = new Scene(root, 800, 600);
		window.setScene(scene);
		window.show();
	}
	
	public void loadGame(ActionEvent event) throws IOException {
		Mainmenu.stop();
		GameController.flag = 0;
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
		GameController.flag = 1;
		Scene scene = new Scene(root, 800, 600);
		window.setScene(scene);
		window.show();
	}
}
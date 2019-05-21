package GUI;

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
import javafx.stage.Stage;
import misc.Difficulty;

public class MainMenuController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	@FXML private Button exitbutton;
	@FXML private Button hardbutton;
	@FXML private Button mediumbutton;
	@FXML private Button easybutton;

	@FXML
	public void exitbutton(ActionEvent event) throws IOException{

		System.exit(0);
	}

    @FXML
	public void easybutton(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		GameActions.setDifficulty(Difficulty.EASY);
		Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
		Scene scene = new Scene(root, 800, 600);
		window.setScene(scene);
		window.show();
	}

	@FXML
	public void mediumbutton(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		GameActions.setDifficulty(Difficulty.MEDIUM);
		Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
		Scene scene = new Scene(root, 800, 600);
		window.setScene(scene);
		window.show();
	}
	@FXML
	public void hardbutton(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		GameActions.setDifficulty(Difficulty.HARD);
		Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
		Scene scene = new Scene(root, 800, 600);
		window.setScene(scene);
		window.show();
	}
	@FXML
	public void randombutton(ActionEvent event) throws IOException  {
		System.out.println("Random");
		int x = (int) (Math.random() * 3);
		if (x==0)
			hardbutton(event);
		else if (x==1)
			mediumbutton(event);
		else if (x==2)
			easybutton(event);
	}
	
	public void loadGame(ActionEvent event) throws IOException {
		GameController.flag = 0;
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
		GameController.flag = 1;
		Scene scene = new Scene(root, 800, 600);
		window.setScene(scene);
		window.show();
	}


}

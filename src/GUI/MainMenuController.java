package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
		Parent root = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
		Scene scene = new Scene(root, 1000, 552);
		window.setScene(scene);
		window.show();
	}

	@FXML
	public void mediumbutton(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
		Scene scene = new Scene(root, 1000, 552);
		window.setScene(scene);
		window.show();
	}
	@FXML
	public void hardbutton(ActionEvent event) throws IOException {
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
		Scene scene = new Scene(root, 1000, 552);
		window.setScene(scene);
		window.show();
	}
	@FXML
	public void randombutton(ActionEvent event) throws IOException  {
		System.out.println("Random");
		double x=Math.random();
		if (x<=1/3)
			hardbutton(event);
		else if (x<=2/3)
			mediumbutton(event);
		else if (x<=1)
			easybutton(event);
	}


}

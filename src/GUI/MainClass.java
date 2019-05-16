package GUI;

import java.io.File;

import gameObjects.SpecialFruit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainClass extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		primaryStage.setTitle("Welcome to Fruit Ninja!");
		primaryStage.setScene(new Scene(root,713,563));
		primaryStage.show();
		
		/*VBox borderpane = new VBox();
		SpecialFruit x = new SpecialFruit();
		Image image = x.getImages()[0];
		ImageView imv = new ImageView();
		imv.setImage(image);
		borderpane.getChildren().add(imv);
		Scene scene = new Scene(borderpane);
		primaryStage.setScene(scene);
		primaryStage.show();*/
	}
	

}

package misc;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Alerts {
	public static void textAlert(String title, String message) {
		Stage window2 = new Stage();
		window2.setMinWidth(250);
		window2.setTitle(title);
		window2.initModality(Modality.APPLICATION_MODAL);
		VBox layout2 = new VBox(20);
		layout2.setPadding(new Insets(10, 10, 10, 10));
		Button closeAlert = new Button("OK");
		closeAlert.setOnAction(e -> window2.close());
		Label alertLabel = new Label(message);
		layout2.getChildren().addAll(alertLabel, closeAlert);
		layout2.setAlignment(Pos.CENTER);
		Scene alertScene = new Scene(layout2, 200, 200);
		window2.setScene(alertScene);
		window2.show();
	}

}

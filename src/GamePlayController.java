package GUI;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;



public class GamePlayController implements Initializable {
    @FXML private Label gamediflabel;
    @FXML private Label scorelabel;
    @FXML private Label highscorelabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gamediflabel.setText("TODO");
        scorelabel.setText("TODO");
        highscorelabel.setText("TODO");
    }

}

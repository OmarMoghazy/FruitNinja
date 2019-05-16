package GUI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class Controller implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void ExitPressed() {
		System.exit(0);
	}
	
	public void EasyPressed() {
		System.out.println("Easy");
	}
	
	public void MediumPressed() {
		System.out.println("Medium");

	}

	public void HardPressed() {
		System.out.println("Hard");

	}
	
	public void RandomPressed() {
		System.out.println("Random");
		double x=Math.random();
		if (x<=0.25)
			EasyPressed();
		else if (x<=0.5)
			MediumPressed();
		else if (x<=0.75)
			HardPressed();
		else RandomPressed();

	}
}

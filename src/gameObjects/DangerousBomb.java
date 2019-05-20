package gameObjects;

import java.io.File;

import interfaces.IGameObject;
import javafx.scene.image.Image;
import misc.ObjectType;

public class DangerousBomb extends GameObject implements IGameObject{
	
	public DangerousBomb() {
		images[0] = new Image(new File("Resources/bomb1.png").toURI().toString());
		images[1] = new Image(new File("Resources/sbomb1.png").toURI().toString());
		this.Xlocation = (int) (Math.random() * 600 + 75);
		this.Ylocation = 0;
	}

	@Override
	public int getMaxHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getInitialVelocity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFallingVelocity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void slice() {
		isSliced = true;
	}
}

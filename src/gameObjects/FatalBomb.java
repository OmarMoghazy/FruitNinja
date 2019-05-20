package gameObjects;

import java.io.File;

import interfaces.IGameObject;
import javafx.scene.image.Image;
import misc.ObjectType;

public class FatalBomb extends GameObject implements IGameObject{
	
	public FatalBomb() {
		images[0] = new Image(new File("Resources/bomb2.png").toURI().toString());
		images[1] = new Image(new File("Resources/sbomb1.png").toURI().toString());
		this.Xlocation = (int) (Math.random() * 600 +75);
		this.Ylocation = 550;
		maxHeight = Math.random() * 600 * 0.2;
		initialVelocity = Math.random() * 6 + 5;
	}
	
	@Override
	public double getMaxHeight() {
		return maxHeight;
	}

	@Override
	public int getInitialVelocity() {
		return 0;
	}

	@Override
	public int getFallingVelocity() {
		return 0;
	}
	
	@Override
	public void slice() {
		isSliced = true;
		
	}

}

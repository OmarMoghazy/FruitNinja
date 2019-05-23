package gameObjects;

import java.io.File;

import interfaces.IGameObject;
import javafx.scene.image.Image;
import misc.ObjectType;

public class FatalBomb extends GameObject implements IGameObject{
	ObjectType objectType = ObjectType.FATAL_BOMB;
	public FatalBomb() {
		images[0] = new Image("/bomb2.png");
		images[1] = new Image("/sbomb1.png");
		this.Xlocation = (int) (Math.random() * 600 +75);
		this.Ylocation = 550;
		maxHeight = Math.random() * 600 * 0.2;
	}
	
	@Override
	public double getMaxHeight() {
		return maxHeight;
	}

	@Override
	public ObjectType getObjectType() {
		return objectType;
	}

}

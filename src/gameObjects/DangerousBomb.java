package gameObjects;

import java.io.File;

import interfaces.IGameObject;
import javafx.scene.image.Image;
import misc.ObjectType;

public class DangerousBomb extends GameObject implements IGameObject {
	ObjectType objectType = ObjectType.DANGEROUS_BOMB;

	public DangerousBomb() {
		images[0] = new Image("/bomb1.png");
		images[1] = new Image("/sbomb1.png");
		this.Xlocation = Math.random() * 600 + 75;
		this.Ylocation = 550;
		maxHeight = Math.random() * 600 * 0.2;
	}

	@Override
	public double getMaxHeight() {return maxHeight;}

	@Override
	public ObjectType getObjectType() {return ObjectType.DANGEROUS_BOMB;}
}

package gameObjects;

import java.io.File;

import interfaces.IGameObject;
import javafx.scene.image.Image;
import misc.ObjectType;

public class SpecialFruit extends GameObject implements IGameObject{
	private ObjectType objectType = ObjectType.SPECIAL_FRUIT;
	public SpecialFruit() {
		int x = (int)(Math.random() * 2);
		switch(x) {
		case 0 : {
			images[0] = new Image("/pepper.png");
			images[1] = new Image("/spepper.png");
		}
		break;
		case 1 : {
			images[0] = new Image("/pepper2.png");
			images[1] = new Image("/spepper2.png");
		}
		break;
		}
		this.Xlocation = (int) (Math.random() * 600 +75 );
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

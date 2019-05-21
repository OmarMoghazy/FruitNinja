package gameObjects;

import java.io.File;

import interfaces.IGameObject;
import javafx.scene.image.Image;
import misc.ObjectType;

public class RegularFruit extends GameObject implements IGameObject {
	ObjectType objectType = ObjectType.REGULAR_FRUIT;
	public RegularFruit() {
		int x = (int)(Math.random() * 3);
		switch(x) {
		case 0 : {
			images[0] = new Image(new File("Resources/onion.png").toURI().toString());
			images[1] = new Image(new File("Resources/sonion.png").toURI().toString());
		}
		break;
		case 1 : {
			images[0] = new Image(new File("Resources/potato.png").toURI().toString());
			images[1] = new Image(new File("Resources/spotato.png").toURI().toString());
		}
		break;
		case 2 : {
			images[0] = new Image(new File("Resources/cabbage.png").toURI().toString());
			images[1] = new Image(new File("Resources/scabbage.png").toURI().toString());
		}
		break;
		}
		this.Xlocation = Math.random() * 600 + 75;
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

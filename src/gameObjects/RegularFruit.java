package gameObjects;

import java.io.File;

import interfaces.IGameObject;
import javafx.scene.image.Image;
import misc.ObjectType;

public class RegularFruit implements IGameObject {
	private final ObjectType objectType = ObjectType.DANGEROUS_BOMB;
	int Xlocation;
	int Ylocation;

	@Override
	public ObjectType getObjectType() {
		// TODO Auto-generated method stub
		return objectType;
	}

	@Override
	public int getXlocation() {
		// TODO Auto-generated method stub
		return Xlocation;
	}

	@Override
	public int getYlocation() {
		// TODO Auto-generated method stub
		return Ylocation;
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
	public Boolean isSliced() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean hasMovedOffScreen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void slice() {
		// TODO Auto-generated method stub
	}

	@Override
	public void move(double time) {
		// TODO Auto-generated method stub
	}

	@Override
	public Image[] getImages() {
		Image [] images = new Image[2];
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
		return images;
	}

}

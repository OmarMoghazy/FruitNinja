package gameObjects;

import java.io.File;

import interfaces.IGameObject;
import javafx.scene.image.Image;
import misc.ObjectType;

public class RegularFruit implements IGameObject {
	private final ObjectType objectType = ObjectType.DANGEROUS_BOMB;
	int Xlocation;
	int Ylocation;
	Image [] images = new Image[2];
	
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
		this.Xlocation = (int) (Math.random() * 600 + 75);
		this.Ylocation = 0;
	}

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
		return Xlocation >= 800 || Ylocation >= 50 || Xlocation <= -50 || Ylocation <= -650;
	}

	@Override
	public void slice() {
		// TODO Auto-generated method stub
	}

	@Override
	public void move(double time) {
		this.Ylocation -= 5;
	}

	@Override
	public Image[] getImages() {
		
		return images;
	}

}

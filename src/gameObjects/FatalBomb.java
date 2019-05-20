package gameObjects;

import java.io.File;

import interfaces.IGameObject;
import javafx.scene.image.Image;
import misc.ObjectType;

public class FatalBomb implements IGameObject{
	private final ObjectType objectType = ObjectType.DANGEROUS_BOMB;
	int Xlocation;
	int Ylocation;
	Image [] images = new Image[2];
	
	public FatalBomb() {
		images[0] = new Image(new File("Resources/bomb2.png").toURI().toString());
		images[1] = new Image(new File("Resources/sbomb1.png").toURI().toString());
		this.Xlocation = (int) (Math.random() * 600 +75);
		this.Ylocation = 0;
	}

	@Override
	public ObjectType getObjectType() {
		// TODO Auto-generated method stub
		return objectType;
	}

	@Override
	public int getXlocation() {
		return Xlocation;
	}

	@Override
	public int getYlocation() {
		return Ylocation;
	}

	@Override
	public int getMaxHeight() {
		return 0;
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

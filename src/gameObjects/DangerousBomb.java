package gameObjects;

import interfaces.IGameObject;
import javafx.scene.image.Image;
import misc.ObjectType;

public class DangerousBomb implements IGameObject{
	private final ObjectType objectType = ObjectType.DANGEROUS_BOMB;
	int Xlocation;
	int Ylocation;

	@Override
	public ObjectType getObjectType() {
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
		// TODO Auto-generated method stub
		return null;
	}
}

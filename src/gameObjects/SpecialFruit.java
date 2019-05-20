package gameObjects;

import java.io.File;

import interfaces.IGameObject;
import javafx.scene.image.Image;
import misc.ObjectType;

public class SpecialFruit extends GameObject implements IGameObject{
	
	public SpecialFruit() {
		int x = (int)(Math.random() * 2);
		switch(x) {
		case 0 : {
			images[0] = new Image(new File("Resources/pepper.png").toURI().toString());
			images[1] = new Image(new File("Resources/spepper.png").toURI().toString());
		}
		break;
		case 1 : {
			images[0] = new Image(new File("Resources/pepper2.png").toURI().toString());
			images[1] = new Image(new File("Resources/pepper2.png").toURI().toString());
		}
		break;
		}
		this.Xlocation = (int) (Math.random() * 600 +75 );
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

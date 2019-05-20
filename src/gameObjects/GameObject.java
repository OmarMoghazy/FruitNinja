package gameObjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import misc.ObjectType;

public class GameObject {
	protected final ObjectType objectType = ObjectType.DANGEROUS_BOMB;
	boolean isSliced = false;
	int Xlocation;
	int Ylocation;
	Image [] images = new Image[2];
	
	public ObjectType getObjectType() {
		return objectType;
	}

	public int getXlocation() {
		return Xlocation;
	}

	public int getYlocation() {
		return Ylocation;
	}
	
	public Boolean hasMovedOffScreen() {
		return Xlocation >= 800 || Ylocation >= 50 || Xlocation <= -50 || Ylocation <= -650;
	}

	public Boolean isSliced() {
		return isSliced;
	}

	public Image[] getImages() {
		return images;
	}
	
	public void render(GraphicsContext gc) {
		if(isSliced) 
			gc.drawImage(images[1], Xlocation, Ylocation + 550, 50, 50);
		else
			gc.drawImage(images[0], Xlocation, Ylocation + 550, 50, 50);
	}

	public void move(long arg0) {
		this.Ylocation -= 3;
		
	}
}

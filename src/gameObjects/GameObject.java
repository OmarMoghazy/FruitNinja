package gameObjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import misc.ObjectType;

public class GameObject {
	protected final ObjectType objectType = ObjectType.DANGEROUS_BOMB;
	boolean isSliced = false;
	double Xlocation;
	double Ylocation;
	Image[] images = new Image[2];
	boolean up = true;
	double maxHeight;
	double initialVelocity;
	double acceleration = -1;
	double currentVelocity;

	public ObjectType getObjectType() {
		return objectType;
	}

	public double getXlocation() {
		return Xlocation;
	}

	public double getYlocation() {
		return Ylocation;
	}

	public Boolean hasMovedOffScreen() {
		return  Ylocation >= 600;
	}

	public Boolean isSliced() {
		return isSliced;
	}

	public Image[] getImages() {
		return images;
	}

	public void render(GraphicsContext gc) {
		if (isSliced)
			gc.drawImage(images[1], Xlocation, Ylocation, 75, 75);
		else
			gc.drawImage(images[0], Xlocation, Ylocation, 75, 75);
	}

	public void move(long time) {
		double mtime = (double)time* Math.pow(10, -6);
		  double newCurrentVelocity = currentVelocity + acceleration * mtime;
		  double newYLocation = Ylocation + currentVelocity * mtime + (1/2) * acceleration * mtime * mtime;
		  Ylocation = newYLocation; 
		  currentVelocity = newCurrentVelocity;
		  
		 /*
		if (up) {
			double newYLocation = Ylocation - 7;
			if (newYLocation < maxHeight)
				up = false;
			else
				Ylocation = newYLocation;
		} else  Ylocation += 7;
*/
	}
}

package gameObjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameObject {
	boolean isSliced = false;
	double Xlocation;
	double Ylocation;
	Image[] images = new Image[2];
	boolean up = true;
	double maxHeight;
	double initialVelocity;
	double acceleration = -0.00001;
	double currentVelocity;
	public boolean hasBeenSliced = false;

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
		/*double mtime = (double)time* Math.pow(10, -6);
		  double newCurrentVelocity = currentVelocity + acceleration * mtime;
		  double newYLocation = Ylocation + currentVelocity * mtime + (1/2) * acceleration * mtime * mtime;
		  Ylocation = newYLocation; 
		  currentVelocity = newCurrentVelocity;
		  
		 */
		if (up) {
			double newYLocation = Ylocation - 7;
			if (newYLocation < maxHeight)
				up = false;
			else
				Ylocation = newYLocation;
		} else  Ylocation += 7;
	}
	public void slice() {
		isSliced =true;
	}

	public boolean hasBeenSliced() {
		return hasBeenSliced;
	}
}

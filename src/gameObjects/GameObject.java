package gameObjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import misc.Difficulty;

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
	public static int speed;

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

	public void move(long time,Difficulty difficulty) {
		/*double mtime = (double)time* Math.pow(10, -6);
		  double newCurrentVelocity = currentVelocity + acceleration * mtime;
		  double newYLocation = Ylocation + currentVelocity * mtime + (1/2) * acceleration * mtime * mtime;
		  Ylocation = newYLocation; 
		  currentVelocity = newCurrentVelocity;
		  
		 */
		
		if(difficulty.equals(Difficulty.EASY)) speed = 4;
		else if(difficulty.equals(Difficulty.MEDIUM)) speed = 8;
		else speed = 12;
		if (up) {
			double newYLocation = Ylocation - speed;
			if (newYLocation < maxHeight)
				up = false;
			else
				Ylocation = newYLocation;
		} else  Ylocation += speed;
	}
	public void slice() {
		isSliced =true;
	}

	public boolean hasBeenSliced() {
		return hasBeenSliced;
	}
}

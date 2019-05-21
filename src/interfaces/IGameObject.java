package interfaces;
import javafx.scene.image.Image;
import misc.ObjectType;;

public interface IGameObject {
	/**
	*@return the type of game object
	*/
	public ObjectType getObjectType();
	/*
	*@return X location of game object
	*/
	public double getXlocation();
	/*
	*@return Y location of game object
	*/
	public double getYlocation();
	/*
	*@return max Y location that the object can reach on the screen
	*/
	public double getMaxHeight();
	/*
	turn whether the object is sliced or not
	*/
	public Boolean isSliced();
	/*
	*@return whether the object is dropped off the screen or not
	*/
	public Boolean hasMovedOffScreen();
	/*
	*it is used to slice the object
	*/
	public void slice();
	/*
	*@return at least two images of the object, one when it is
	sliced and one when it is not.
	*/
	public Image[] getImages();

}

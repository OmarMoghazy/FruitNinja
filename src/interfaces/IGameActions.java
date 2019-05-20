package interfaces;

import gameObjects.GameObject;
import javafx.scene.canvas.GraphicsContext;

public interface IGameActions {
	/*
	 * @return created game object
	 */
	public void createGameObject();

	/*
	 * update moving objects locations
	 */
	void updateObjectsLocations(GraphicsContext gc);
	/*
	* it is used to slice fruits located in your swiping region
	This method can take your swiping region as parameters (they
	depend on how you calculate it).
	*/
	public void sliceObject(GameObject gameObject);
	public void ResetGame();
}

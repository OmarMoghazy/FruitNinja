package interfaces;

public interface IGameActions {
	/*
	 * @return created game object
	 */
	public IGameObject createGameObject();

	/*
	 * update moving objects locations
	 */
	public void updateObjectsLocations();
	/*
	* it is used to slice fruits located in your swiping region
	This method can take your swiping region as parameters (they
	depend on how you calculate it).
	*/
	public void sliceObjects();
	/*
	*resets the game to its initial state
	*/
	public void ResetGame();
}

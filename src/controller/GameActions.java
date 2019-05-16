package controller;

import interfaces.IGameObject;
import misc.GameObjectFactory;
import interfaces.IGameActions;

public class GameActions implements IGameActions {
	
	// Singleton
	//////////////////////////////////////////////////////////////////////////////////
	private static GameActions gameActions;											//
																					//
	private GameActions() {}														//
																					//
	public static synchronized GameActions getInstance() {							//
		if (gameActions == null)													//
			gameActions = new GameActions();										//
		return gameActions;															//
	}																				//
																					//
	//////////////////////////////////////////////////////////////////////////////////
	
	// Factory																		
	GameObjectFactory factory = new GameObjectFactory();

	@Override
	public IGameObject createGameObject() {
		return GameObjectFactory.createObject();
	}

	@Override
	public void updateObjectsLocations() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sliceObjects() {
		// TODO Auto-generated method stub
	}

	@Override
	public void ResetGame() {
		// TODO Auto-generated method stub

	}

}

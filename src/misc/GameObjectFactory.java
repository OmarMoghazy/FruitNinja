package misc;

import gameObjects.*;
import interfaces.IGameObject;

public class GameObjectFactory {
	public static IGameObject createObject() {
		int x  = (int)( Math.random() * 20 );
		if(x < 14) return new RegularFruit();
		else if(x < 15) return new SpecialFruit();
		else if(x < 18) return new DangerousBomb();
		else if(x < 20) return new FatalBomb();
		return null;
	}
	
	public static IGameObject createObject(ObjectType type) {
		if(type.equals(ObjectType.REGULAR_FRUIT)) return new RegularFruit();
		else if(type.equals(ObjectType.SPECIAL_FRUIT)) return new SpecialFruit();
		else if(type.equals(ObjectType.DANGEROUS_BOMB)) return new DangerousBomb();
		else if(type.equals(ObjectType.FATAL_BOMB)) return new FatalBomb();
		return null;
	}
}
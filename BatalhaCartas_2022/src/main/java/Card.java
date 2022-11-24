import java.util.*;

enum Type {
	FIRE,
	WATER,
	PLANT,

}

public class Card extends Observable {
	private String id;
	private String imageId;
	private int value;
	private Type type;
	private boolean faceUp;

	public Card(String anId, String anImageId, int val, Type type) {
		id = anId;
		imageId = anImageId;
		value = val;
		this.type = type;
		faceUp = true;
	}

	public String getId() {
		return (id);
	}

	public String getImageId() {
		return (imageId);
	}

	public int getValue() {
		return (value);
	}

	public Type getType() {
		return (type);
	}

	public boolean isFacedUp() {
		return (faceUp);
	}

	public void flip() {
		if (faceUp == true) {
			faceUp = false;
		} else {
			faceUp = true;
		}
		setChanged();
		notifyObservers();
	}
}
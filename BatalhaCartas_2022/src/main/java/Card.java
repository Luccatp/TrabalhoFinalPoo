import java.util.*;

enum Type {
	FIRE,
	WATER,
	PLANT,
	AIR,
	ROCK,

}

public class Card extends Observable {
	private String id;
	private String imageId;
	private int attack;
	private int health;
	private Type type;
	private boolean faceUp;

	public Card(String anId, String anImageId, int attack, int health, Type type) {
		id = anId;
		imageId = anImageId;
		this.attack = attack;
		this.health = health;
		this.type = type;
		faceUp = true;
	}

	public String getId() {
		return (id);
	}

	public String getImageId() {
		return (imageId);
	}

	public void setImagem(String imageId) {
		this.imageId = imageId;
	}

	public int getAttack() {
		return (attack);
	}

	public int getHealth() {
		return (health);
	}

	public void setHealth(int health) {
		this.health = health;
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
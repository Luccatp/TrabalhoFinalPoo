import java.util.*;

// Esta classe tem de ser um container de cartas observavel ...
public class CardDeck extends Observable {
	public static final int NCARDS = 6;
	private List<Card> cartas;
	private Card selected;

	public CardDeck() {
		cartas = new ArrayList<Card>(NCARDS);
		selected = null;
		Random r = new Random();
		Type type;
		for (int i = 0; i < NCARDS; i++) {
			int randomType = r.nextInt(9) + 1;
			int value;
			switch (randomType) {
				case 1:
					value = 1;
					break;
				case 2:
					value = 2;
					break;
				case 3:
					value = 3;
					break;
				case 4:
					value = 1;
					break;
				case 5:
					value = 2;
					break;
				case 6:
					value = 3;
					break;
				case 7:
					value = 1;
					break;
				case 8:
					value = 2;
					break;
				case 9:
					value = 3;
					break;
				default:
					value = 0;
					break;
			}
			if (randomType <= 3 && randomType >= 1) {
				type = Type.FIRE;
			} else if (randomType > 3 && randomType <= 6) {
				type = Type.PLANT;
			} else {
				type = Type.WATER;
			}
			Card c = new Card("C" + randomType, "img" + randomType, value, type);
			c.flip();
			cartas.add(c);
		}
	}

	public List<Card> getCards() {
		return (cartas);
	}

	public int getNumberOfCards() {
		return (cartas.size());
	}

	public void removeSel() {
		if (selected == null) {
			return;
		}
		cartas.remove(selected);
		selected = null;
		GameEvent gameEvent = new GameEvent(GameEvent.Target.DECK, GameEvent.Action.REMOVESEL, "");
		setChanged();
		notifyObservers(gameEvent);
	}

	public void setSelectedCard(Card card) {
		selected = card;
	}

	public Card getSelectedCard() {
		return (selected);
	}
}
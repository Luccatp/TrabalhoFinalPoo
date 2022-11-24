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
			int n = r.nextInt(10) + 1;
			int randomType = r.nextInt(3) + 1;
			if (randomType == 1) {
				type = Type.FIRE;
			} else if (randomType == 2) {
				type = Type.PLANT;
			} else if (randomType == 3) {
				type = Type.WATER;
			} else if (randomType == 4) {
				type = Type.PLANT;
			} else if (randomType == 5) {
				type = Type.PLANT;
			} else {
				type = Type.PLANT;
			}
			Card c = new Card("C" + n, "img" + n, n, type);
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
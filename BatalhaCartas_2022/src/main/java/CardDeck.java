import java.util.*;

import org.apache.commons.lang3.ObjectUtils.Null;

// Esta classe tem de ser um container de cartas observavel ...
public class CardDeck extends Observable {
	public static final int NCARDS = 12;
	private List<Card> cartas;
	private Card selected;

	public CardDeck() {
		cartas = new ArrayList<Card>(NCARDS);
		selected = null;
		Random r = new Random();
		Type type;
		for (int i = 0; i < NCARDS; i++) {
			int randomType = r.nextInt(15) + 1;
			int randomHealth = r.nextInt(3) + 1;
			String teste = "" + randomType + randomHealth;
			int stringValor = Integer.parseInt(teste);
			int attack;
			int health;
			switch (stringValor) {
				case 11:
					attack = 1;
					health = 1;
					break;
				case 12:
					attack = 1;
					health = 2;
					break;
				case 13:
					attack = 1;
					health = 3;
					break;
				case 21:
					attack = 2;
					health = 1;
					break;
				case 22:
					attack = 2;
					health = 2;
					break;
				case 23:
					attack = 2;
					health = 3;
					break;
				case 31:
					attack = 3;
					health = 1;
					break;
				case 32:
					attack = 3;
					health = 2;
					break;
				case 33:
					attack = 3;
					health = 3;
					break;
				case 41:
					attack = 1;
					health = 1;
					break;
				case 42:
					attack = 1;
					health = 2;
					break;
				case 43:
					attack = 1;
					health = 3;
					break;
				case 51:
					attack = 2;
					health = 1;
					break;
				case 52:
					attack = 2;
					health = 2;
					break;
				case 53:
					attack = 2;
					health = 3;
					break;
				case 61:
					attack = 3;
					health = 1;
					break;
				case 62:
					attack = 3;
					health = 2;
					break;
				case 63:
					attack = 3;
					health = 3;
					break;
				case 71:
					attack = 1;
					health = 1;
					break;
				case 72:
					attack = 1;
					health = 2;
					break;
				case 73:
					attack = 1;
					health = 3;
					break;
				case 81:
					attack = 2;
					health = 1;
					break;
				case 82:
					attack = 2;
					health = 2;
					break;
				case 83:
					attack = 2;
					health = 3;
					break;
				case 91:
					attack = 3;
					health = 1;
					break;
				case 92:
					attack = 3;
					health = 2;
					break;
				case 93:
					attack = 3;
					health = 3;
					break;
				case 101:
					attack = 1;
					health = 1;
					break;
				case 102:
					attack = 1;
					health = 2;
					break;
				case 103:
				 	attack = 1;
					health = 3;
					break;
				case 111:
					attack = 2;
					health = 1;
					break;
				case 112:
					attack = 2;
					health = 2;
					break;
				case 113:
					attack = 2;
					health = 3;
					break;
				case 121:
					attack = 3;
					health = 1;
					break;
				case 122:
				    attack = 3;
					health = 2;
					break;
				case 123:
					attack = 3;
					health = 3;
					break;
				case 131:
					attack = 1;
					health = 1;
					break;
				case 132:
					attack = 1;
					health = 2;
					break;
				case 133:
				 	attack = 1;
					health = 3;
					break;
				case 141:
					attack = 2;
					health = 1;
					break;
				case 142:
					attack = 2;
					health = 2;
					break;
				case 143:
					attack = 2;
					health = 3;
					break;
				case 151:
					attack = 3;
					health = 1;
					break;
				case 152:
				    attack = 3;
					health = 2;
					break;
				case 153:
					attack = 3;
					health = 3;
					break;
				default:
					attack = 0;
					health = 0;
					break;
			}
			if (randomType <= 3 && randomType >= 1) {
				type = Type.FIRE;
			} else if (randomType > 3 && randomType <= 6) {
				type = Type.PLANT;
			} else if (randomType > 6 && randomType <= 9) {
				type = Type.WATER;
			} else if (randomType > 9 && randomType <= 12) {
				type = Type.AIR;
		    } else if (randomType > 12 && randomType <= 15){
				type = Type.ROCK;
			}
			
			Card c = new Card("C" + randomType, "img" + stringValor, attack, health, type);
			c.flip();
			cartas.add(c);
		}
	}

	public List<Card> getCards() {
		return (cartas);
	}

	public void flipAll() {
		for (Card carta : cartas) {
			carta.flip();
		}
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
import java.util.*;

import javafx.stage.Stage;

public class Game extends Observable {
	private static Game game = new Game();
	private int ptsJ1, ptsJ2;
	private CardDeck deckJ1, deckJ2;
	private int player;
	private int jogadas;

	public static Game getInstance() {
		return (game);
	}

	private Game() {
		ptsJ1 = 0;
		ptsJ2 = 0;
		deckJ1 = new CardDeck();
		deckJ2 = new CardDeck();
		player = 1;
		jogadas = CardDeck.NCARDS;
	}

	private void nextPlayer() {
		GameEvent gameEvent = null;
		player++;
		if (player <= 2) {
			gameEvent = new GameEvent(GameEvent.Target.GWIN, GameEvent.Action.TURN, Integer.toString(game.player));
			setChanged();
			notifyObservers((Object) gameEvent);
		} else if (player == 4) {
			player = 1;
		} else if (player == 3) {
			gameEvent = new GameEvent(GameEvent.Target.GWIN, GameEvent.Action.MUSTCLEAN, "");
			setChanged();
			notifyObservers((Object) gameEvent);
		}
	}

	public int getPtsJ1() {
		return (ptsJ1);
	}

	public int getPtsJ2() {
		return (ptsJ2);
	}

	public CardDeck getDeckJ1() {
		return (deckJ1);
	}

	public CardDeck getDeckJ2() {
		return (deckJ2);
	}

	public void play(CardDeck deckAcionado) {
		GameEvent gameEvent = null;

		if (player == 4) {
			gameEvent = new GameEvent(GameEvent.Target.GWIN, GameEvent.Action.MUSTCLEAN, "");
			setChanged();
			notifyObservers((Object) gameEvent);
			return;
		}
		if (deckAcionado == deckJ1) {
			if (player != 1) {
				gameEvent = new GameEvent(GameEvent.Target.GWIN, GameEvent.Action.INVPLAY, "2");
				setChanged();
				notifyObservers((Object) gameEvent);
			} else {
				// Vira a carta
				deckJ1.getSelectedCard().flip();
				// Proximo jogador

				nextPlayer();
			}
		} else if (deckAcionado == deckJ2) {
			if (player != 2) {
				gameEvent = new GameEvent(GameEvent.Target.GWIN, GameEvent.Action.INVPLAY, "2");
				setChanged();
				notifyObservers((Object) gameEvent);
			} else {
				// Vira a carta
				deckJ2.getSelectedCard().flip();
				// Verifica quem ganhou a rodada
				if (deckJ1.getSelectedCard().getType().equals(Type.FIRE)
						&& deckJ2.getSelectedCard().getType().equals(Type.PLANT)
						||
						deckJ1.getSelectedCard().getType().equals(Type.WATER)
								&& deckJ2.getSelectedCard().getType().equals(Type.FIRE)
						||
						deckJ1.getSelectedCard().getType().equals(Type.PLANT)
								&& deckJ2.getSelectedCard().getType().equals(Type.WATER)) {

					deckJ2.getSelectedCard()
							.setHealth(0);
					ptsJ1++;

				} else if (deckJ2.getSelectedCard().getType().equals(Type.FIRE)
						&& deckJ1.getSelectedCard().getType().equals(Type.PLANT)
						||
						deckJ2.getSelectedCard().getType().equals(Type.WATER)
								&& deckJ1.getSelectedCard().getType().equals(Type.FIRE)
						||
						deckJ2.getSelectedCard().getType().equals(Type.PLANT)
								&& deckJ1.getSelectedCard().getType().equals(Type.WATER)) {

					deckJ1.getSelectedCard()
							.setHealth(0);

					ptsJ2++;

				} else {

					deckJ1.getSelectedCard()
							.setHealth(deckJ1.getSelectedCard().getHealth() - deckJ2.getSelectedCard().getAttack());

					deckJ2.getSelectedCard()
							.setHealth(deckJ2.getSelectedCard().getHealth() - deckJ1.getSelectedCard().getAttack());

					String imgName1 = deckJ1.getSelectedCard().getImageId();
					imgName1 = imgName1.substring(0, imgName1.length() - 1) + deckJ1.getSelectedCard().getHealth();
					deckJ1.getSelectedCard().setImagem(imgName1);

					String imgName2 = deckJ2.getSelectedCard().getImageId();
					imgName2 = imgName2.substring(0, imgName2.length() - 1) + deckJ2.getSelectedCard().getHealth();
					deckJ2.getSelectedCard().setImagem(imgName2);

					if (deckJ1.getSelectedCard().getHealth() <= 0) {
						ptsJ2++;
					}
					if (deckJ2.getSelectedCard().getHealth() <= 0) {
						ptsJ1++;
					}
				}
				setChanged();
				notifyObservers((Object) gameEvent);
				// Próximo jogador
				nextPlayer();
			}
		}
	}

	// Acionada pelo botao de limpar
	public void removeSelected() {
		GameEvent gameEvent = null;

		if (player != 3) {
			return;
		}
		jogadas--;
		if (deckJ1.getNumberOfCards() == 1 | deckJ2.getNumberOfCards() == 1) {
			gameEvent = new GameEvent(GameEvent.Target.GWIN, GameEvent.Action.ENDGAME, "");
			setChanged();
			notifyObservers((Object) gameEvent);
			// return;
		}
		if (deckJ1.getSelectedCard().getHealth() <= 0) {
			deckJ1.removeSel();
		} else {
			deckJ1.getSelectedCard().flip();
		}
		if (deckJ2.getSelectedCard().getHealth() <= 0) {
			deckJ2.removeSel();
		} else {
			deckJ2.getSelectedCard().flip();
		}
		nextPlayer();
	}
}
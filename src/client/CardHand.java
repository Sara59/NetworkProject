package client;

import gui.ImageLabel;

public class CardHand {
	private String[] cards;
	private int amountOfCards;
	private int cardIndex;
	private ImageLabel[][] field;

	public CardHand(ImageLabel[][] field) {
		this.field = field;
		cards = new String[5];
		amountOfCards = 0;
		cardIndex = 0;
	}

	public boolean addCard(String newCard) {
		if (amountOfCards < 5) {
			cards[cardIndex] = newCard;
			field[0][cardIndex].setImage(newCard);
			amountOfCards++;
			cardIndex++;
			return true;
		}
		return false;

	}

	public String removeCard(int index) {
		if (amountOfCards > 4) {
			cardIndex = index;
			String drawn = cards[cardIndex];
			cards[cardIndex] = null;
			field[0][cardIndex].setImage("5 1");
			amountOfCards--;
			return drawn;
		}
		return null;
	}

	public void newGameHand() {
		for (int i = 0; i < cards.length; i++) {
			cards[i] = null;
		}
		amountOfCards = 0;
	}

	public boolean bubblanHand() {

		if (amountOfCards == 4) {
			if (getRank(0) != getRank(1)) {
				return false;
			}
			if (getRank(1) != getRank(2)) {
				return false;
			}
			if (getRank(2) != getRank(3)) {
				return false;
			}
			return true;

		}
		return false;
	}

	private int getRank(int i) {
		return Integer.parseInt(cards[i].substring(2));
	}
}

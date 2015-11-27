package game.playingcard.attribute;

import game.core.util.StringUtil;

public enum Rank {
	ACE('A', 13, 11),
	KING('K', 12, 10),
	QUEEN('Q', 11, 10),
	JACK('J', 10, 10),
	TEN('T', 9, 10),
	NINE('9', 8, 9),
	EIGHT('8', 7, 8),
	SEVEN('7', 6, 7),
	SIX('6', 5, 6),
	FIVE('5', 4, 5),
	FOUR('4', 3, 4),
	THREE('3', 2, 3),
	TWO('2', 1, 2);

	private char symbol;
	private int weight;
	private int value;

	private Rank(char symbol, int weight, int value) {
		this.symbol = symbol;
		this.weight = weight;
		this.value = value;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String formattedName() {
		return StringUtil.toTitleCase(name());
	}
}

package game.playingcard.attribute;

public enum Suit {
	HEART('♥'),
	SPADE('♠'),
	DIAMOND('♦'),
	CLUB('♣');
	
	private char symbol;
	
	private Suit(char symbol) {
		this.symbol = symbol;
	}
	
	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
}

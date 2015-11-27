package game.playingcard;
import game.core.Card;
import game.playingcard.attribute.Rank;
import game.playingcard.attribute.Suit;

public class PlayingCard implements Card<PlayingCard> {
	private Rank rank;
	private Suit suit;

	public PlayingCard(Rank rank, Suit suit) {
		super();
		this.rank = rank;
		this.suit = suit;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public int compareTo(PlayingCard other) {
		int diff = rank.getWeight() - other.getRank().getWeight();
		
		if (diff == 0) {
			return suit.getSymbol() - other.getSuit().getSymbol();
		}

		return diff;
	}

	public int compare(PlayingCard card1, PlayingCard card2) {
		return card1.compareTo(card2);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayingCard other = (PlayingCard) obj;
		if (rank != other.rank)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}
	
	public String formattedName() {
		return String.format("|%c %c|", rank.getSymbol(), suit.getSymbol());
	}
	
	@Override
	public String toString() {
		return "Card [suit=" + suit.getSymbol() + ", rank=" + rank.getSymbol() + "]";
	}
}

package game.playingcard.condition;

import java.util.List;
import java.util.Map;

import game.core.util.StringUtil;
import game.playingcard.PlayingCardHand;
import game.playingcard.attribute.Rank;
import game.playingcard.formatter.PlayingCardHandFormatter;

public enum Rating {
	ROYAL_FLUSH(10, new PlayingCardHandFormatter() {
		@Override
		public String format(PlayingCardHand hand) {
			return String.format("Royal Flush with %s High", highCard(hand));
		}
	}),
	STRAIGHT_FLUSH(9, new PlayingCardHandFormatter() {
		@Override
		public String format(PlayingCardHand hand) {
			return String.format("Stright Flush with %s High", highCard(hand));
		}
	}),
	FOUR_OF_A_KIND(8, new PlayingCardHandFormatter() {
		@Override
		public String format(PlayingCardHand hand) {
			Map<Integer, List<Rank>> frequency = getFrequency(hand);
			return String.format("Four %ss", lookup(frequency, 4, 0));
		}
	}),
	FULL_HOUSE(7, new PlayingCardHandFormatter() {
		@Override
		public String format(PlayingCardHand hand) {
			Map<Integer, List<Rank>> frequency = hand.frequencyByRank();
			return String.format("Full House of %ss and %ss", lookup(frequency, 3, 0), lookup(frequency, 2, 0));
		}
	}),
	FLUSH(6, new PlayingCardHandFormatter() {
		@Override
		public String format(PlayingCardHand hand) {
			return String.format("Flush with %s High", highCard(hand));
		}
	}),
	STRAIGHT(5, new PlayingCardHandFormatter() {
		@Override
		public String format(PlayingCardHand hand) {
			return String.format("Stright with %s High", highCard(hand));
		}
	}),
	THREE_OF_A_KIND(4, new PlayingCardHandFormatter() {
		@Override
		public String format(PlayingCardHand hand) {
			Map<Integer, List<Rank>> frequency = getFrequency(hand);
			return String.format("Three %ss", lookup(frequency, 3, 0));
		}
	}),
	TWO_PAIR(3, new PlayingCardHandFormatter() {
		@Override
		public String format(PlayingCardHand hand) {
			Map<Integer, List<Rank>> frequency = hand.frequencyByRank();
			return String.format("Two pairs of %ss and %ss", lookup(frequency, 2, 0), lookup(frequency, 2, 1));
		}
	}),
	PAIR(2, new PlayingCardHandFormatter() {
		@Override
		public String format(PlayingCardHand hand) {
			Map<Integer, List<Rank>> frequency = hand.frequencyByRank();
			return String.format("Pair of %ss", lookup(frequency, 2, 0));
		}
	}),
	HIGH_CARD(1, new PlayingCardHandFormatter() {
		@Override
		public String format(PlayingCardHand hand) {
			return String.format("%s high", highCard(hand));
		}
	}),
	NOTHING(0, new PlayingCardHandFormatter() {
		@Override
		public String format(PlayingCardHand hand) {
			return null;
		}
	});

	private int weight;
	private PlayingCardHandFormatter formatter;
	
	private Rating(int weight, PlayingCardHandFormatter formatter) {
		this.weight = weight;
		this.formatter = formatter;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public PlayingCardHandFormatter getFormatter() {
		return formatter;
	}
	
	public void setFormatter(PlayingCardHandFormatter formatter) {
		this.formatter = formatter;
	}
	
	public String formmattedName() {
		return StringUtil.toTitleCase(name().replace('_', ' '));
	}
	
	@Deprecated
	public String formattedName(PlayingCardHand hand) {
		StringBuffer buff = new StringBuffer();
		
		String highCard = hand.max().getRank().formattedName() + " High";
		
		if (equals(HIGH_CARD)) { 
			buff.append(highCard);
		} else {
			buff.append(formmattedName());
			
			Map<Integer, List<Rank>> frequency = hand.frequencyByRank();
			
			if (equals(STRAIGHT_FLUSH)) {
				buff.append(" of ").append(highCard);
			} else if (equals(FOUR_OF_A_KIND)) {
				buff.append(" of ").append(frequency.get(4).get(0).formattedName());
			} else if (equals(FULL_HOUSE)) {
				buff.append(" of ").append(frequency.get(3).get(0).formattedName()).append(" and ").append(frequency.get(2).get(0).formattedName());
			} else if (equals(FLUSH)) {
				buff.append(" of ").append(highCard);
			} else if (equals(STRAIGHT)) {
				buff.append(" of ").append(highCard);
			} else if (equals(THREE_OF_A_KIND)) {
				buff.append(" of ").append(frequency.get(3).get(0).formattedName());
			} else if (equals(TWO_PAIR)) {
				buff.append(" of ").append(frequency.get(2).get(0).formattedName()).append(" and ").append(frequency.get(2).get(1).formattedName());
			} else if (equals(PAIR)) {
				buff.append(" of ").append(frequency.get(2).get(0).formattedName());
			}
		}
		
		return buff.toString();
	}
}

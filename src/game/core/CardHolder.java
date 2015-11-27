package game.core;

import java.util.Collection;

public interface CardHolder<CARDS extends Collection<CARD>, CARD extends Card<CARD>> {
	CARDS getCards();

	void setCards(CARDS cards);
	
	boolean add(CARD card);

	boolean addAll(Collection<CARD> cards);
	
	CARD remove();

	int size();
	
	boolean hasCards();
	
	boolean discard(CARD target, CardHolder<CARDS, CARD> destination);

	<PILE extends Collection<CARD>> void discardAll(CardHolder<PILE, CARD> destination);
}

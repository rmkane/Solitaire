package game.card;

import java.util.List;

public interface CardHolder<T extends Card<T>> {
	List<T> getCards();

	void setCards(List<T> cards);
	
	boolean hasCards();

	boolean add(T card);
	
	T remove();

	boolean discard(T target, Deck<T> destination);
}

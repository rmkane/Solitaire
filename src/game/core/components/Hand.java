package game.core.components;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import game.core.Card;
import game.core.CardHolder;
import game.core.collections.Function;
import game.core.collections.list.OrderedLinkedList;
import game.core.util.CollectionUtil;
import game.core.util.StringUtil;

public class Hand<CARD extends Card<CARD>> implements CardHolder<OrderedLinkedList<CARD>, CARD> {
	@SuppressWarnings("rawtypes")
	private static final Function<Card, String> FORMATTED_NAME = new Function<Card, String>() {
		public String apply(Card card) {
			return card.formattedName();
		}
	};
	
	private OrderedLinkedList<CARD> cards;

	@Override
	public OrderedLinkedList<CARD> getCards() {
		return cards;
	}

	@Override
	public void setCards(OrderedLinkedList<CARD> cards) {
		this.cards = cards;
	}

	public Hand() {
		this(new OrderedLinkedList<CARD>());
	}

	public Hand(OrderedLinkedList<CARD> cards) {
		this.cards = cards;
	}

	@Override
	public boolean add(CARD card) {
		return cards.add(card);
	}

	@Override
	public boolean addAll(Collection<CARD> cards) {
		return cards.addAll(cards);
	}

	@Override
	public CARD remove() {
		return cards.remove(cards.size() - 1);
	}

	@Override
	public int size() {
		return cards.size();
	}

	@Override
	public boolean hasCards() {
		return !cards.isEmpty();
	}

	@Override
	public boolean discard(CARD target, CardHolder<OrderedLinkedList<CARD>, CARD> destination) {
		return CollectionUtil.move(cards, destination.getCards(), target);
	}

	@Override
	public <CARDS extends Collection<CARD>> void discardAll(CardHolder<CARDS, CARD> destination) {
		CollectionUtil.moveAll(cards, destination.getCards());
	}

	public CARD max() {
		return Collections.max(cards);
	}

	protected static <CARDS extends Card<CARDS>, U> Map<U, List<CARDS>> groupBy(Hand<CARDS> hand, Function<CARDS, U> groupBy) {
		return CollectionUtil.groupBy(hand.getCards(), groupBy);
	}

	protected static <CARDS extends Card<CARDS>, U> Map<Integer, List<U>> frequency(Hand<CARDS> hand, Function<CARDS, U> groupBy) {
		return CollectionUtil.frequency(hand.getCards(), groupBy);
	}

	@Override
	public String toString() {
		return StringUtil.join(CollectionUtil.transform(getCards(), FORMATTED_NAME), " ");
	}
}

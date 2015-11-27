package game.core.formatter;

import game.core.Card;
import game.core.components.Hand;

public interface HandFormatter<HAND extends Hand<CARD>, CARD extends Card<CARD>> {
	String format(HAND hand);
}

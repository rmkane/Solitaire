package game.core;
import java.util.Comparator;

public interface Card<T> extends Comparable<T>, Comparator<T> {
	String formattedName();
}

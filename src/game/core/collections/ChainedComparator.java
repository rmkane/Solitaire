package game.core.collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ChainedComparator<E extends Comparator<E>> implements Comparator<E> {
	private List<Comparator<E>> comparators;

	@SafeVarargs
	public ChainedComparator(Comparator<E>... comparators) {
		this.comparators = Arrays.asList(comparators);
	}

	public ChainedComparator(List<Comparator<E>> comparators) {
		this.comparators = comparators;
	}

	@Override
	public int compare(E obj1, E obj2) {
		for (Comparator<E> comparator : comparators) {
			int result = comparator.compare(obj1, obj2);

			if (result != 0) {
				return result;
			}
		}

		return 0;
	}
}

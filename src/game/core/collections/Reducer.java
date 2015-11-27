package game.core.collections;

import java.util.Collection;

public interface Reducer<E, V> {
    V reduce(V result, E item, int index, Collection<E> collection);
}

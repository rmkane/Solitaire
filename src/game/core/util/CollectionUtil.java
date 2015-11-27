package game.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import game.core.collections.Function;
import game.core.collections.Predicate;
import game.core.collections.Reducer;
import game.core.collections.list.OrderedLinkedList;

public abstract class CollectionUtil {
	public static void main(String[] args) {
		List<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3));
		
		int sum = CollectionUtil.reduce(intList, new Reducer<Integer, Integer>() {
			@Override
			public Integer reduce(Integer result, Integer item, int index, Collection<Integer> collection) {
				return result + item.intValue();
			}
		}, 0);
		
		System.out.println(sum);
		
		List<String> result = CollectionUtil.reduce(intList, new Reducer<Integer, List<String>>() {
			@Override
			public List<String> reduce(List<String> result, Integer item, int index, Collection<Integer> collection) {
				result.add("{" + item.toString() + "}");
				return result;
			}
		}, new ArrayList<String>());
		
		System.out.println(result);
	}
	
	public static <E> Collection<E> filter(Collection<E> collection, Predicate<E> predicate) {
		Collection<E> copy = new ArrayList<E>();

		for (Iterator<E> i = collection.iterator(); i.hasNext();) {
			E obj = i.next();
			if (predicate.filter(obj)) {
				copy.add(obj);
			}
		}

		return copy;
	}

	public static <E, V> V reduce(Collection<E> collection, Reducer<E, V> reducer, V result) {
		int index = 0;
		Iterator<E> it = collection.iterator();

		while (it.hasNext()) {
			result = reducer.reduce(result, it.next(), index++, collection);
		}

		return result;
	}

	public static <E> boolean every(Collection<E> collection, Predicate<E> predicate) {
		for (Iterator<E> i = collection.iterator(); i.hasNext();) {
			if (!predicate.filter(i.next())) {
				return false;
			}
		}

		return true;
	}

	public static <E> boolean some(Collection<E> collection, Predicate<E> predicate) {
		for (Iterator<E> i = collection.iterator(); i.hasNext();) {
			if (predicate.filter(i.next())) {
				return true;
			}
		}

		return false;
	}

	public static <F, T> Collection<T> transform(Collection<F> collection, Function<? super F, T> function) {
		Collection<T> copy = new ArrayList<T>();

		for (Iterator<F> i = collection.iterator(); i.hasNext();) {
			copy.add(function.apply(i.next()));
		}

		return copy;
	}

	public static <T, U> Map<U, List<T>> groupBy(Collection<T> collection, Function<T, U> groupingKey) {
		Map<U, List<T>> map = new HashMap<U, List<T>>();

		for (T item : collection) {
			U key = groupingKey.apply(item);

			List<T> list = map.get(key);

			if (list == null) {
				list = new LinkedList<T>();
			}

			list.add(item);

			map.put(key, list);
		}

		return map;
	}

	public static <T, U> Map<U, Integer> countBy(Collection<T> collection, Function<T, U> groupingKey) {
		Map<U, Integer> map = new HashMap<U, Integer>();

		for (T item : collection) {
			U key = groupingKey.apply(item);

			if (map.containsKey(key)) {
				map.put(key, map.get(key) + 1);
			} else {
				map.put(key, 1);
			}
		}

		return map;
	}

	public static <U, T> Map<U, List<T>> transpose(Map<T, U> map) {
		Map<U, List<T>> m = new HashMap<U, List<T>>();

		for (Entry<T, U> entry : map.entrySet()) {
			U index = entry.getValue();
			List<T> list = m.get(index);
			
			if (list == null) {
				list = new LinkedList<T>();
			}
			
			list.add(entry.getKey());
			m.put(index, list);
		}

		return m;
	}

	public static <T, U, V> Map<Integer, List<V>> frequency(Collection<T> collection, Function<T, V> groupingKey) {
		return transpose(countBy(collection, groupingKey));
	}

	public static <E extends Comparable<E>> boolean moveLast(List<E> source, List<E> destination) {
		if (source.isEmpty()) {
			return false;
		}

		E topCard = null;

		if (Stack.class.isInstance(source)) {
			topCard = ((Stack<E>) source).pop();
		} else if (LinkedList.class.isInstance(source)) {
			topCard = ((LinkedList<E>) source).pop();
		} else {
			topCard = source.get(source.size() - 1);
			source.remove(topCard);
		}

		return move(topCard, destination);
	}

	public static <E extends Comparable<E>> boolean move(Collection<E> source, Collection<E> destination, E target) {
		boolean present = source.remove(target);

		if (!present) {
			return false;
		}

		return move(target, destination);
	}

	public static <E extends Comparable<E>> boolean move(E target, Collection<E> destination) {
		if (OrderedLinkedList.class.isInstance(destination)) {
			((OrderedLinkedList<E>) destination).orderedAdd(target);
			return true;
		} else if (LinkedList.class.isInstance(destination)) {
			((LinkedList<E>) destination).push(target);
			return true;
		} else {
			return destination.add(target);
		}
	}

	public static <E extends Comparable<E>> void moveAll(Collection<E> source, Collection<E> destination) {
		while (!source.isEmpty()) {
			E target = null;

			if (List.class.isInstance(source)) {
				target = ((List<E>) source).get(0);
			} else if (Stack.class.isInstance(source)) {
				target = ((Stack<E>) source).firstElement();
			}

			move(source, destination, target);
		}
	}
}

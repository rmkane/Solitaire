package game.core.util;

import java.util.Iterator;

public class StringUtil {
	public static <E> String join(Iterable<E> input, String delimiter) {
		StringBuffer buff = new StringBuffer();
		Iterator<E> it = input.iterator();
		while (it.hasNext()) {
			buff.append(it.next());
			
			if (it.hasNext()) {
				buff.append(delimiter);
			}
		}
		return buff.toString();
	}

	public static String toTitleCase(String input) {
		StringBuffer buff = new StringBuffer();
		boolean nextTitleCase = true;

		for (char c : input.toCharArray()) {
			if (Character.isSpaceChar(c)) {
				nextTitleCase = true;
			} else {
				if (nextTitleCase) {
					c = Character.toTitleCase(c);
					nextTitleCase = false;
				} else {
					c = Character.toLowerCase(c);
				}
			}

			buff.append(c);
		}

		return buff.toString();
	}
}

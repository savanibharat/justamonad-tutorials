package com.justamonad.tutorials.optional;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

public class OptionalClassOrElseMethod {

	public static void main(String[] args) {

		BigInteger b1 = BigInteger.ONE;
		BigInteger b2 = BigInteger.TEN;

		Optional<BigInteger> opt1 = Optional.of(b1);
		Optional<BigInteger> opt2 = Optional.of(b2);

		BigInteger worstUsageEver = opt1.orElse(BigInteger.ZERO).add(opt2.orElse(BigInteger.ZERO));
		System.out.println(worstUsageEver);

		BigInteger result = BigInteger.ZERO;

		if (b1 != null && b2 != null) {
			result = b1.add(b2);
		}
		System.out.println(result);

	}

	public static <T extends Object & Comparable<? super T>> Optional<T> max(Collection<? extends T> coll) {

		if (coll.isEmpty())
			return Optional.empty();

		Iterator<? extends T> i = coll.iterator();
		T candidate = i.next();

		while (i.hasNext()) {
			T next = i.next();
			if (next.compareTo(candidate) > 0)
				candidate = next;
		}
		return Optional.of(candidate);
	}

}

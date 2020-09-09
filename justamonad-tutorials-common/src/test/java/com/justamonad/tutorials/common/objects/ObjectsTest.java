package com.justamonad.tutorials.common.objects;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import com.justamonad.tutorials.common.Name;

public class ObjectsTest {

	@Test
	public void testEqualsTrue() {
		boolean isEqual1 = Objects.equals("Jon", "Jon");
		Assert.assertTrue(isEqual1);

		boolean isEqual2 = Objects.equals("Jon", "Arya");
		Assert.assertFalse(isEqual2);
	}

	@Test
	public void testEqualsNull() {
		boolean isEqual = Objects.equals("", null);
		Assert.assertFalse(isEqual);
	}

	@Test
	public void testEqualsBothNull() {
		boolean isEqual = Objects.equals(null, null);
		Assert.assertTrue(isEqual);
	}

	@Test
	public void deepEqualsTest1() {
		boolean isEqual = Objects.deepEquals(null, null);
		Assert.assertTrue(isEqual);
	}

	@Test
	public void deepEqualsTest2() {
		int[] a = new int[] { 1, 2, 3 };
		int[] b = new int[] { 1, 2, 3 };
		boolean isEqual = Objects.deepEquals(a, b);
		Assert.assertTrue(isEqual);
	}

	@Test
	public void deepEqualsTest3() {
		int[] a = new int[] { 1, 2, 3 };
		int[] b = new int[] { 1, 2, 3, 5 };
		boolean isEqual = Objects.deepEquals(a, b);
		Assert.assertFalse(isEqual);
	}

	@Test
	public void deepEqualsTest4() {
		int[] a = new int[] { 1, 2, 3 };
		int[] b = new int[] { 34, 21, 89 };
		boolean isEqual = Objects.deepEquals(a, b);
		Assert.assertFalse(isEqual);
	}

	@Test
	public void deepEqualsTest5() {
		int[] a = new int[] { 1, 2, 3 };
		long[] b = new long[] { 1, 2, 3 };
		boolean isEqual = Objects.deepEquals(a, b);
		Assert.assertFalse(isEqual);
	}

	@Test
	public void deepEqualsTest6() {
		int[][] a = new int[][] { { 1, 2, 3 }, { 1, 2, 3 } };
		int[][] b = new int[][] { { 1, 2, 3 }, { 1, 2, 3 } };
		boolean isEqual = Objects.deepEquals(a, b);
		Assert.assertTrue(isEqual);
	}

	@Test
	public void deepEqualsTest7() {
		Object[] a = new Object[] { 1, 2, 3, new Object[] { 1, 2 } };
		Object[] b = new Object[] { 1, 2, 3, new Object[] { 1, 2 } };
		boolean isEqual = Objects.deepEquals(a, b);
		Assert.assertTrue(isEqual);
	}

	@Test
	public void hashCode1() {
		int hash = Objects.hashCode(null);
		Assert.assertEquals(0, hash);
	}

	@Test
	public void hashCode2() {
		int hash = Objects.hashCode(Integer.valueOf(42));
		Assert.assertTrue(hash != 0);
	}

	@Test
	public void hash1() {
		int hash = Objects.hash(null);
		Assert.assertEquals(0, hash);
	}

	@Test
	public void hash2() {
		int hash = Objects.hash(null, null);
		Assert.assertEquals(961, hash);
	}

	@Test
	public void hash3() {
		Integer iVal = Integer.valueOf(42);
		String str = "Jon";
		Float fVal = Float.valueOf(3.14f);
		int hash = Objects.hash(iVal, str, fVal);
		Assert.assertTrue(hash != 0);
	}

	@Test
	public void toString1() {
		String str = Objects.toString(null);
		Assert.assertEquals("null", str);
	}

	@Test
	public void toString2() {
		String str = Objects.toString(Long.valueOf(123456789123456789L));
		Assert.assertEquals("123456789123456789", str);
	}
	
	@Test
	public void toString3() {
		String str = Objects.toString(Name.of("Jon", "Snow"));
		System.out.println(str);
	}

	@Test
	public void toStringDefaultValue1() {
		String nullDefault = "empty";
		String str = Objects.toString(null, nullDefault);
		Assert.assertEquals("empty", str);
	}

	@Test
	public void toStringDefaultValue2() {
		String nullDefault = "empty";
		String str = Objects.toString(Long.valueOf(123456789123456789L), nullDefault);
		Assert.assertEquals("123456789123456789", str);
	}

	@SuppressWarnings("unused")
	@Test
	public void compare1() {

		final Comparator<Name> NAME_COMP = new Comparator<Name>() {
			@Override
			public int compare(Name n1, Name n2) {
				int result = n1.firstName().compareTo(n2.firstName());
				if (result != 0) {
					result = n1.lastName().compareTo(n2.lastName());
				}
				return result;
			}
		};

		Comparator<Name> firstNameComp = (n1, n2) -> n1.firstName().compareTo(n2.firstName());
		Comparator<Name> lastNameComp = (n1, n2) -> n1.lastName().compareTo(n2.lastName());

		Comparator<Name> nameComp = firstNameComp.thenComparing(lastNameComp);

		Name n1 = Name.of("Brandon", "Stark");
		Name n2 = Name.of("Brandon", "Stark");

		int result = Objects.compare(n1, n2, nameComp);
		Assert.assertEquals(0, result);

	}

	@SuppressWarnings("unused")
	@Test
	public void compare2() {

		final Comparator<Name> NAME_COMP = new Comparator<Name>() {
			@Override
			public int compare(Name n1, Name n2) {
				int result = n1.firstName().compareTo(n2.firstName());
				if (result != 0) {
					result = n1.lastName().compareTo(n2.lastName());
				}
				return result;
			}
		};

		Comparator<Name> firstNameComp = (n1, n2) -> n1.firstName().compareTo(n2.firstName());
		Comparator<Name> lastNameComp = (n1, n2) -> n1.lastName().compareTo(n2.lastName());

		Comparator<Name> nameComp = firstNameComp.thenComparing(lastNameComp);

		Name n1 = Name.of("Brandon", "Stark");
		Name n2 = Name.of("Brandon", "Greyjoy");

		int result = Objects.compare(n1, n2, nameComp);
		Assert.assertTrue(result > 0);

		int resultReverse = Objects.compare(n2, n1, nameComp);
		Assert.assertTrue(resultReverse < 0);

	}

	@Test(expected = NullPointerException.class)
	public void requireNonNull1() {
		Objects.requireNonNull(null);
	}

	@Test
	public void requireNonNull2() {
		@SuppressWarnings("unused")
		/**
		 * Show constructor of name class.
		 */
		Name name = Objects.requireNonNull(Name.of("Jon", "Snow"));
		Assert.assertNotNull(name);
	}

	@Test
	public void requireNonNullMsg1() {
		String message = "null parameter is disallowed";
		try {
			Objects.requireNonNull(null, message);
		} catch (NullPointerException npe) {
			Assert.assertEquals(message, npe.getMessage());
		}
	}

	@Test
	public void requireNonNullMsg2() {
		@SuppressWarnings("unused")
		/**
		 * Show constructor of name class.
		 */
		Name name = Objects.requireNonNull(Name.of("Jon", "Snow"));
	}

	@Test
	public void requireNonNullSupplierMsg1() {
		Supplier<String> messageSupplier = () -> "null parameter is disallowed";
		try {
			Objects.requireNonNull(null, messageSupplier);
		} catch (NullPointerException npe) {
			Assert.assertEquals(messageSupplier.get(), npe.getMessage());
		}
	}

	@Test
	public void requireNonNullSupplierMsg2() {
		Supplier<String> messageSupplier = () -> "null parameter is disallowed";
		/**
		 * Show constructor of name class.
		 */
		Name input = Name.of("Jon", "Snow");
		Name name = Objects.requireNonNull(input, messageSupplier);
		Assert.assertEquals(input, name);
	}

	@Test
	public void isNull1() {
		String input = null;
		boolean isNull = Objects.isNull(input);

		Assert.assertTrue(isNull);
	}

	@Test
	public void isNull2() {
		String input = null;

		Predicate<String> pred = (str) -> Objects.isNull(str);
		boolean isNull = pred.test(input);
		Assert.assertTrue(isNull);

		pred = Objects::isNull;
		isNull = pred.test(input);
		Assert.assertTrue(isNull);

	}

	@Test
	public void isNull3() {
		long count = Stream.of("a", "b", null, "c").filter(Objects::isNull).count();
		Assert.assertEquals(1, count);

		boolean isNull = Stream.of("a", "b", null, "c").anyMatch(Objects::isNull);
		Assert.assertTrue(isNull);

	}

	@Test
	public void isNonNull1() {
		String input = "abc";
		boolean isNonNull = Objects.nonNull(input);

		Assert.assertTrue(isNonNull);
	}

	@Test
	public void isNonNull2() {
		String input = "abc";

		Predicate<String> pred = (str) -> Objects.nonNull(str);
		boolean isNonNull = pred.test(input);
		Assert.assertTrue(isNonNull);

		pred = Objects::nonNull;
		isNonNull = pred.test(input);
		Assert.assertTrue(isNonNull);
	}

	@Test
	public void isNonNull3() {
		long count = Stream.of("a", "b", null, "c").filter(Objects::nonNull).count();
		Assert.assertEquals(3, count);

		Stream.of("a", "b", null, "c").filter(Objects::nonNull).forEach(System.out::println);

		List<String> result = Stream.of("a", "b", null, "c").filter(Objects::nonNull).collect(Collectors.toList());
		System.out.println(result);
	}

	@Test
	public void requireNonNullElse1() {
		Name input = Name.of("Jon", "Snow");
		Name defaultName = Name.of("default", "default");
		Name result = Objects.requireNonNullElse(input, defaultName);
		Assert.assertEquals(input, result);
	}
	
	@Test
	public void requireNonNullElse2() {
		LocalDate input = null;
		LocalDate now = LocalDate.now();
		LocalDate result = Objects.requireNonNullElse(input, now);
		Assert.assertEquals(now, result);
	}

	@Test
	public void requireNonNullElse3() {
		Name input = null;
		Name defaultName = Name.of("default", "default");
		Name result = Objects.requireNonNullElse(input, defaultName);
		Assert.assertEquals(defaultName, result);
	}

	@Test
	public void requireNonNullElse4() {
		Name input = null;
		Name defaultName = null;
		try {
			Objects.requireNonNullElse(input, defaultName);
		} catch (NullPointerException npe) {
			Assert.assertEquals("defaultObj", npe.getMessage());
		}
	}

	@Test
	public void requireNonNullElseGet1() {
		Name input = Name.of("Jon", "Snow");
		Supplier<Name> defaultSupplier = () -> Name.of("default", "default");
		Name result = Objects.requireNonNullElseGet(input, defaultSupplier);
		Assert.assertEquals(input, result);
	}

	@Test
	public void requireNonNullElseGet2() {
		Name input = null;
		Supplier<Name> defaultSupplier = () -> Name.of("default", "default");
		Name result = Objects.requireNonNullElseGet(input, defaultSupplier);
		Assert.assertEquals(Name.of("default", "default"), result);
	}

	@Test
	public void requireNonNullElseGet3() {
		Name input = null;
		Supplier<Name> defaultSupplier = null;
		try {
			Objects.requireNonNullElseGet(input, defaultSupplier);
		} catch (NullPointerException npe) {
			Assert.assertEquals("supplier", npe.getMessage());
		}
	}

	@Test
	public void requireNonNullElseGet4() {
		Name input = null;
		Supplier<Name> defaultSupplier = () -> null;
		try {
			Objects.requireNonNullElseGet(input, defaultSupplier);
		} catch (NullPointerException npe) {
			Assert.assertEquals("supplier.get()", npe.getMessage());
		}
	}

	@Test
	public void checkIndex1() {
		int[] arr = { 4, 54, 8, 5 };
		int result = Objects.checkIndex(2, arr.length);
		Assert.assertEquals(2, result);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void checkIndex2() {
		int[] arr = { 4, 54, 8, 5 };
		Objects.checkIndex(-1, arr.length);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void checkIndex3() {
		int[] arr = { 4, 54, 8, 5 };
		Objects.checkIndex(arr.length, arr.length);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void checkIndex4() {
		int[] arr = { 4, 54, 8, 5 };
		Objects.checkIndex(20, arr.length);
	}

	@Test
	public void checkFromToIndex1() {
		int[] arr = { 4, 54, 8, 5 };
		int result = Objects.checkFromToIndex(0, 3, arr.length);
		Assert.assertEquals(0, result);
	}

	@Test
	public void checkFromToIndex2() {
		int[] arr = { 4, 54, 8, 5 };
		int result = Objects.checkFromToIndex(0, 0, arr.length);
		Assert.assertEquals(0, result);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void checkFromToIndex3() {
		int[] arr = { 4, 54, 8, 5 };
		Objects.checkFromToIndex(0, 20, arr.length);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void checkFromToIndex4() {
		int[] arr = { 4, 54, 8, 5 };
		Objects.checkFromToIndex(3, 0, arr.length);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void checkFromToIndex5() {
		int[] arr = { 4, 54, 8, 5 };
		Objects.checkFromToIndex(0, 3, -1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void checkFromToIndex6() {
		Objects.checkFromToIndex(-1, 3, 4);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void checkFromToIndex7() {
		Objects.checkFromToIndex(1, -3, 4);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void checkFromToIndex8() {
		Objects.checkFromToIndex(1, 3, -1);
	}

	@Test
	public void checkFromIndexSize1() {
		int[] arr = { 4, 54, 8, 5, 62, 84, 535 };
		int fromIndex = Objects.checkFromIndexSize(0, 5, arr.length);
		Assert.assertEquals(0, fromIndex);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void checkFromIndexSize2() {
		int[] arr = { 4, 54, 8, 5, 62, 84, 535 };
		Objects.checkFromIndexSize(-1, 5, arr.length);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void checkFromIndexSize3() {
		int[] arr = { 4, 54, 8, 5, 62, 84, 535 };
		Objects.checkFromIndexSize(1, -5, arr.length);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void checkFromIndexSize4() {
		int[] arr = { 4, 54, 8, 5, 62, 84, 535 };
		Objects.checkFromIndexSize(1, 5, -arr.length);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void checkFromIndexSize5() {
		int[] arr = { 4, 54, 8, 5, 62, 84, 535 };
		Objects.checkFromIndexSize(1, 15, arr.length);
	}

}

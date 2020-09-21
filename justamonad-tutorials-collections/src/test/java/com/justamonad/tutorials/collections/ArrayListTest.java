package com.justamonad.tutorials.collections;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class ArrayListTest {

	@Test
	public void test() {
	}

//	@Test
	public void arrayListNoArgs() {
		List<String> names = new ArrayList<>();
		for (int i = 0; i < 1_000_000_000; i++) {
			try {
				names.add("John");
			} catch (Throwable e) {
				System.out.println("index : " + i);
				names.clear();
				e.printStackTrace();
			}
		}
//		names.add("John");
		names.add("Jane");
	}

	@Test
	public void arrayListInitialCapacity() {
		List<String> names = new ArrayList<>(20);
		names.add("John");
		names.add("Jane");
	}

	@Test
	public void arrayListCollection() {
		List<String> names = new ArrayList<>();
		names.add("John");
		names.add("Arya");

//		List<String> moreNames = new ArrayList<>(names);
//		moreNames.add("Ned");
//		moreNames.add("Catelyn");

		Set<String> moreNames = new HashSet<>(names);
		moreNames.add("Ned");
		moreNames.add("Catelyn");

		Assert.assertEquals(4, moreNames.size());
	}

	@Test
	public void add() {
		List<String> names = new ArrayList<>();
		names.add("John");
		names.add("Jane");

		// add allows nulls
		names.add(null);
		Assert.assertEquals(3, names.size());
	}

	@Test
	public void addWithIndex() {
		List<String> names = new ArrayList<>();
		names.add("John");
		names.add("Jane");
		names.add(null);

		names.add(3, "Sansa");

		System.out.println(names);

		Assert.assertEquals(4, names.size());
	}

	@Test
	public void remove1() {
		List<String> names = new ArrayList<>();
		names.add("John");
		names.add("Jane");
		names.add(null);
		names.add(null);

		// remove nulls
		names.remove(null);

		Assert.assertEquals(3, names.size());
		System.out.println(names);
	}

	@Test
	public void remove2() {
		List<String> names = new ArrayList<>();
		names.add("John");
		names.add("Jane");
		names.add("Jack");

		boolean isJaneRemoved = names.remove("Jane");
		Assert.assertTrue(isJaneRemoved);

		boolean isPikaRemoved = names.remove("Pikachu");
		Assert.assertFalse(isPikaRemoved);
	}

	@Test
	public void removeWithIndex() {
		List<String> names = new ArrayList<>();
		names.add("John");
		names.add("Jane");
		names.add("Jack");

		String str = names.remove(2);
		Assert.assertEquals("Jack", str);
	}

	@Test
	public void addAll() {
		List<String> names1 = new ArrayList<>();
		names1.add("John");
		names1.add("Jane");

		List<String> names2 = new ArrayList<>();
		names2.add("Doe");
		names2.add("Dane");

		// names2 is appended. throws NPE if names2 is null.
		names1.addAll(names2);
		System.out.println(names1);
		Assert.assertEquals(4, names1.size());
	}

	@Test
	public void addAllWithIndex() {
		List<String> names1 = new ArrayList<>();
		names1.add("John");
		names1.add("Jane");

		List<String> names2 = new ArrayList<>();
		names2.add("Doe");
		names2.add("Dane");

		names1.addAll(0, names2);
		Assert.assertEquals(4, names1.size());
		// [Doe, Dane, John, Jane]
		System.out.println(names1);
	}

	@Test
	public void get() {
		List<String> names = new ArrayList<>();
		names.add("John");
		names.add("Jane");

		int index = 1;
		String str = names.get(index);
		System.out.println("element at index " + index + " is " + str);
	}

	@Test
	public void sublist() {
		List<String> names = new ArrayList<>();
		names.add("Jon");
		names.add("Sansa");
		names.add("Arya");
		names.add("Rickon");
		names.add("Bran");

		List<String> sublist = names.subList(1, 3);
		System.out.println(sublist);
	}

	@Test
	public void set() {
		List<String> names = new ArrayList<>();
		names.add("John");
		names.add("Jane");

		int index = 1;
		names.set(index, "Arya");
		System.out.println(names);
	}

	@Test
	public void removeAll1() {
		List<String> names = new ArrayList<>();
		names.add("John");
		names.add("Jane");
		names.add("John");
		names.add("Jack");
		names.add("John");

		// remove John and Jack
		List<String> removeNames = new ArrayList<>();
		removeNames.add("Jack");
		removeNames.add("John");
		names.removeAll(removeNames);

		Assert.assertEquals(1, names.size());
		System.out.println(names);
	}

	@Test
	public void removeAll2() {
		List<String> names = new ArrayList<>();
		names.add("John");
		names.add("Jane");
		names.add(null);
		names.add(null);

		// remove all nulls
		names.removeAll(Collections.singletonList(null));

		Assert.assertEquals(2, names.size());
	}

	@Test
	public void contains() {
		List<String> names = new ArrayList<>();
		names.add("John");
		names.add("Jane");
		names.add("Jack");

		boolean containsJack = names.contains("Jack");
		boolean containsSansa = names.contains("Sansa");

		Assert.assertTrue(containsJack);
		Assert.assertFalse(containsSansa);
	}

	@Test
	public void indexOf1() {
		List<String> names = new ArrayList<>();
		names.add("John");
		names.add("Jane");
		names.add("Jack");

		int index = names.indexOf("John");
		Assert.assertEquals(0, index);
	}

	@Test
	public void indexOf2() {
		List<String> names = new ArrayList<>();
		names.add("John");
		names.add("Jane");
		names.add("Jack");

		int index = names.indexOf("Sansa");

		Assert.assertEquals(-1, index);
	}

	@Test
	public void indexOf3() {
		List<String> names = new ArrayList<>();
		names.add("John");
		names.add("Jane");
		names.add("John");

		int index = names.indexOf("John");
		Assert.assertEquals(0, index);
	}

	@Test
	public void lastIndexOf1() {
		List<String> names = new ArrayList<>();
		names.add("John");
		names.add("Jane");
		names.add("Jack");

		int index = names.lastIndexOf("John");
		Assert.assertEquals(0, index);
	}

	@Test
	public void lastIndexOf2() {
		List<String> names = new ArrayList<>();
		names.add("John");
		names.add("Jane");
		names.add("Jack");

		int index = names.lastIndexOf("Sansa");

		Assert.assertEquals(-1, index);
	}

	@Test
	public void lastIndexOf3() {
		List<String> names = new ArrayList<>();
		names.add("John");
		names.add("Jane");
		names.add("John");

		int index = names.lastIndexOf("John");
		Assert.assertEquals(2, index);
	}

	@Test
	public void containsAll() {
		List<String> names1 = new ArrayList<>();
		names1.add("John");
		names1.add("Jane");
		names1.add("Jack");

		List<String> names2 = new ArrayList<>();
		names2.add("Jack");
		names2.add("Jack");
		names2.add("Jack");
		names2.add("Jack");

		Assert.assertTrue(names1.containsAll(names2));
		Assert.assertFalse(names2.containsAll(names1));
	}

	@Test
	public void retainAll1() {
		List<String> names1 = new ArrayList<>();
		names1.add("John");
		names1.add("Jane");
		names1.add("Jack");

		List<String> names2 = new ArrayList<>();
		names2.add("Jack");
		names2.add("Jack");
		names2.add("Jack");

		names1.retainAll(names2);
		System.out.println(names1);
	}

	@Test
	public void retainAll2() {
		List<String> names1 = new ArrayList<>();
		names1.add("John");
		names1.add("Jane");
		names1.add("Jack");

		List<String> names2 = new ArrayList<>();
		names2.add("Jack");
		names2.add("Jane");

		names1.retainAll(names2);
		System.out.println(names1);
	}

	@Test
	public void retainAll3() {
		List<String> names1 = new ArrayList<>();
		names1.add("John");
		names1.add("Jane");
		names1.add("Jack");

		List<String> names2 = new ArrayList<>();
		names2.add("Sansa");
		names2.add("Arya");

		names1.retainAll(names2);
		System.out.println(names1);
	}

	@Test
	public void clear() {
		List<String> names = new ArrayList<>();
		names.add("John");
		names.add("Jane");
		names.add("John");

		names.clear();

		Assert.assertTrue(names.isEmpty());

	}

	@Test
	public void equals1() {
		List<String> names1 = new ArrayList<>();
		names1.add("John");
		names1.add("Jane");
		names1.add("Jack");

		List<String> names2 = new ArrayList<>();
		names2.add("John");
		names2.add("Jane");
		names2.add("Jack");

		Assert.assertTrue(names1.equals(names2));
		Assert.assertTrue(names2.equals(names1));
	}

	@Test
	public void equals2() {
		List<String> names1 = new ArrayList<>();
		names1.add("John");
		names1.add("Jane");
		names1.add("Jack");

		List<String> names2 = new ArrayList<>();
		names2.add("John");
		names2.add("Jack");
		names2.add("Jane");

		Assert.assertFalse(names1.equals(names2));
	}

	@Test
	public void equals3() {
		List<String> names1 = new ArrayList<>();
		names1.add("John");
		names1.add("Jane");
		names1.add("Jack");

		List<String> names2 = new ArrayList<>();
		names2.add("Sansa");
		names2.add("Arya");

		Assert.assertFalse(names1.equals(names2));
	}

	@Test
	public void hashCodeDemo() {
		List<String> names = new ArrayList<>();
		names.add("John");
		names.add("Jane");
		names.add("Jack");

		System.out.println(names.hashCode());

	}

	@Test
	public void trimToSize() {
		ArrayList<String> names = new ArrayList<>();
		names.add("John");
		names.add("Jane");
		names.add("Jack");

		names.trimToSize();
	}

	@Test
	public void serializeDeserialize() {
		final String serializedFileName = "namesSerialized";
		List<String> names = new ArrayList<>();
		names.add("John");
		names.add("Jane");
		names.add("Jack");
		serialize(names, serializedFileName);

		List<String> deserializedNames = deserialize(serializedFileName);
		Assert.assertTrue(names.equals(deserializedNames));
	}
	
	@Test
	public void serializeDeserializeNamesClass() {
		final String serializedFileName = "namesClassSerialized";
		List<Name> names = new ArrayList<>();
		names.add(new Name("Arya", "Stark"));
		names.add(new Name("Ned", "Stark"));
		names.add(new Name("Jon", "Snow"));
		serializeNames(names, serializedFileName);

		List<Name> deserializedNames = deserializeNames(serializedFileName);
		Assert.assertTrue(names.equals(deserializedNames));
	}
	
	private void serializeNames(List<Name> names, String serilizedFileName) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(serilizedFileName);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(names);
			objectOutputStream.close();
			fileOutputStream.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	private List<Name> deserializeNames(String serilizedFileName) {
		try {
			FileInputStream fileInputStream = new FileInputStream(serilizedFileName);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			@SuppressWarnings("unchecked")
			ArrayList<Name> namesDes = (ArrayList<Name>) objectInputStream.readObject();
			objectInputStream.close();
			fileInputStream.close();
			return namesDes;
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	private void serialize(List<String> names, String serilizedFileName) {

		try {
			FileOutputStream fileOutputStream = new FileOutputStream(serilizedFileName);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(names);
			objectOutputStream.close();
			fileOutputStream.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	private List<String> deserialize(String serilizedFileName) {
		try {
			FileInputStream fileInputStream = new FileInputStream(serilizedFileName);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			@SuppressWarnings("unchecked")
			ArrayList<String> namesDes = (ArrayList<String>) objectInputStream.readObject();
			objectInputStream.close();
			fileInputStream.close();
			return namesDes;
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

}

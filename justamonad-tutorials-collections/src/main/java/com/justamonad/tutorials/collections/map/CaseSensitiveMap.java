package com.justamonad.tutorials.collections.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CaseSensitiveMap<V> implements Map<BackingString, V> {

	private Map<BackingString, V> backingMap = null;
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;

	public CaseSensitiveMap(int initialCapacity, float loadFactor) {
		backingMap = new HashMap<>(initialCapacity, loadFactor);
	}

	public CaseSensitiveMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
	}

	public CaseSensitiveMap() {
		this(0, DEFAULT_LOAD_FACTOR);
	}

	public CaseSensitiveMap(Map<? extends BackingString, ? extends V> m) {
		this(0, DEFAULT_LOAD_FACTOR);
		for (java.util.Map.Entry<? extends BackingString, ? extends V> entry : m.entrySet()) {
			BackingString backString = new BackingString(
					entry.getKey().getString().toUpperCase(entry.getKey().getLocale()), entry.getKey().getLocale());
			backingMap.put(backString, entry.getValue());
		}
	}

	@Override
	public int size() {
		return backingMap.size();
	}

	@Override
	public boolean isEmpty() {
		return backingMap.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return backingMap.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return backingMap.containsValue(value);
	}

	@Override
	public V get(Object key) {
		return backingMap.get(key);
	}

	@Override
	public V put(BackingString key, V value) {
		BackingString backString = new BackingString(key.getString().toUpperCase(key.getLocale()), key.getLocale());
		backingMap.put(backString, value);
		return backingMap.put(backString, value);
	}

	@Override
	public V remove(Object key) {
		return backingMap.remove(key);
	}

	@Override
	public void putAll(Map<? extends BackingString, ? extends V> m) {
		for (java.util.Map.Entry<? extends BackingString, ? extends V> entry : m.entrySet()) {
			BackingString backString = new BackingString(
					entry.getKey().getString().toUpperCase(entry.getKey().getLocale()), entry.getKey().getLocale());
			backingMap.put(backString, entry.getValue());
		}
	}

	@Override
	public void clear() {
		backingMap.clear();
	}

	@Override
	public Set<BackingString> keySet() {
		return backingMap.keySet();
	}

	@Override
	public Collection<V> values() {
		return backingMap.values();
	}

	@Override
	public Set<java.util.Map.Entry<BackingString, V>> entrySet() {
		return backingMap.entrySet();
	}

}

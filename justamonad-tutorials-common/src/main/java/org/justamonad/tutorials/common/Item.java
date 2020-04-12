package org.justamonad.tutorials.common;

import static org.justamonad.tutorials.common.JsonConverter.toJsonString;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import org.joda.money.Money;

public class Item {

	private final String itemName;
	private final String itemId;
	private final Money price;

	private Item(final String itemName, final Money price, String itemId) {
		this.itemName = itemName;
		this.price = price;
		this.itemId = itemId;
	}

	public static final Item of(final String itemName, final Money price, String itemId) {
		return new Item(Objects.requireNonNull(itemName), Objects.requireNonNull(price),
				Objects.requireNonNull(itemId));
	}

	public String itemName() {
		return itemName;
	}

	public Money price() {
		return price;
	}

	public String itemId() {
		return itemId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

	@Override
	public String toString() {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("item_name", itemName);
		map.put("item_id", itemId);
		map.put("price", price.getAmount());
		return toJsonString(map);
	}

}

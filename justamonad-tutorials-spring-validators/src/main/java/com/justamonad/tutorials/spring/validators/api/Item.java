package com.justamonad.tutorials.spring.validators.api;

import org.joda.money.Money;

public final class Item {

	private final String itemName;
	private final Money price;

	private Item(final String itemName, final Money price) {
		this.itemName = itemName;
		this.price = price;
	}

	public static final Item of(final String itemName, final Money price) {
		return new Item(itemName, price);
	}

	public String itemName() {
		return itemName;
	}

	public Money price() {
		return price;
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
		StringBuilder builder = new StringBuilder();
		builder.append("Item [itemName=");
		builder.append(itemName);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}

}

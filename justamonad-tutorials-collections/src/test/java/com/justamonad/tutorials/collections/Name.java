package com.justamonad.tutorials.collections;

import java.io.Serializable;
import java.util.Objects;

public class Name implements Serializable, Comparable<Name> {

	private static final long serialVersionUID = -7470957962267746723L;

	private final String firstName;
	private final String lastName;

	private static final String FIRST_NAME_ERR = "invalid firstName";
	private static final String LAST_NAME_ERR = "invalid lastName";

	public Name(String firstName, String lastName) {
		Objects.requireNonNull(firstName, FIRST_NAME_ERR);
		Objects.requireNonNull(lastName, LAST_NAME_ERR);

		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String firstName() {
		return firstName;
	}

	public String lastName() {
		return lastName;
	}
	
	@Override
	public int compareTo(Name that) {
		int compare = this.firstName.compareTo(that.firstName);
		if(compare == 0) {
			compare = this.lastName.compareTo(that.lastName);
		}
		return compare;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		Name other = (Name) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Name [firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append("]");
		return builder.toString();
	}

}

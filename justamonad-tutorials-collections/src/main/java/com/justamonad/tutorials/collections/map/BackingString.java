package com.justamonad.tutorials.collections.map;

import java.util.Locale;

class BackingString {
	private String s;
	private Locale locale;

	public BackingString(String s, Locale locale) {
		this.s = s;
		this.locale = locale;
	}

	public Locale getLocale() {
		return locale;
	}
	
	public String getString() {
		return s;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		result = prime * result + ((s == null) ? 0 : s.hashCode());
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
		BackingString other = (BackingString) obj;
		if (locale == null) {
			if (other.locale != null)
				return false;
		} else if (!locale.equals(other.locale))
			return false;
		if (s == null) {
			if (other.s != null)
				return false;
		} else if (!s.equals(other.s))
			return false;
		return true;
	}
	
	
	
}

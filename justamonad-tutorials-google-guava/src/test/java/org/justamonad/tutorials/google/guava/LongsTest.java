package org.justamonad.tutorials.google.guava;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.primitives.Longs;

public class LongsTest {

	@Test
	public void testLongBytes() {
		int longBytes = Longs.BYTES;
		Assert.assertTrue(longBytes == 8);
	}
	
	

}

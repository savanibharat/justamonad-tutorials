package com.justamonad.tutorials.functional.lists.tailcall;

import static com.justamonad.tutorials.functional.lists.tailcall.TailCall.ret;
import static com.justamonad.tutorials.functional.lists.tailcall.TailCall.suspend;
import org.junit.Assert;
import org.junit.Test;

public class TailCallTest {

	@Test
	public void recusrsionSuccess() {
		int res = addRec(10000, 3);
		Assert.assertEquals(10003, res);
	}

	@Test
	public void recursionThrowsStackOverflowError() {
//		try {
//			addRec(3, 20500);
//			Assert.fail();
//		} catch (StackOverflowError error) {
//			Assert.assertTrue(true);
//		}
	}

	@Test
	public void recursionUsingTailCall() {

//		TailCall<Integer> tailCall = addRecTailCall(3, 5);
//		while (tailCall.isSuspend()) {
//			tailCall = tailCall.resume();
//		}
//		System.out.println(tailCall.eval());
	}

	static int addRec(int x, int y) {
		return y == 0 ? x : addRec(++x, --y);
	}

	static TailCall<Integer> addRecTailCall(int x, int y) {
		if (y == 0) {
			System.out.println("ret");
			return ret(x);
		} else {
			System.out.println("suspend");
			return suspend(() -> addRecTailCall(x + 1, y - 1));
		}
		// return y == 0
		// ? ret(x)
		// : suspend(() -> addRecTailCall(x + 1, y - 1));
	}

}

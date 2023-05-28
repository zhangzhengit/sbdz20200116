package com.example.demo.p;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * 随机数
 *
 * @author zhangzhen
 * @date 2020年2月6日
 *
 */
public class ZR {

	private static final java.util.Random RANDOM = ThreadLocalRandom.current();

	public static double nextDouble() {
		return RANDOM.nextDouble();
	}

	public static double nextDouble(final double endInclusive) {
		final double startInclusive = 0;
		if (startInclusive == endInclusive) {
			return startInclusive;
		}
		return startInclusive + ((endInclusive - startInclusive) * RANDOM.nextDouble());
	}

	public static int nextInt(final int bound) {
		return RANDOM.nextInt(bound);
	}

	public static int nextInt(final int startInclusive, final int endExclusive) {
		return startInclusive + RANDOM.nextInt(endExclusive - startInclusive);
	}

}

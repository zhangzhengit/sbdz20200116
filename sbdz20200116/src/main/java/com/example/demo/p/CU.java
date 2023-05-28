package com.example.demo.p;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.collect.Maps;

/**
 * 生成54张一副扑克牌
 *
 * @author zhangzhen
 * @date 2020年2月6日
 *
 */
public class CU {

	private static final Random RANDOM = new SecureRandom();

	static public final Map<String, Integer> SIMAP = Maps.newHashMap();

	/**
	 * 值/10表示3 4 5 6 7 8 9 10 J Q K A 2。160表示小王，170表示大王
	 */
	private static final int[] A = {
			31, 32, 33, 34, 41, 42, 43, 44, 51, 52, 53, 54,
			61, 62, 63, 64, 71, 72, 73, 74, 81, 82, 83, 84,
			91, 92, 93, 94, 101, 102, 103, 104, 111, 112, 113,
			114, 121, 122, 123, 124, 131, 132, 133, 134,
			141, 142, 143, 144, 151, 152, 153, 154, 160, 170 };

	/**
	 * 生成打乱顺序的54张一副扑克牌
	 *
	 * @return
	 */
	public static int[] g() {
		final int[] copy = Arrays.copyOf(A, A.length);
		ArrayUtils.shuffle(copy, RANDOM);
		return copy;
	}


	static{
		SIMAP.put("3", 31);
		SIMAP.put("4", 41);
		SIMAP.put("5", 51);
		SIMAP.put("6", 61);
		SIMAP.put("7", 71);
		SIMAP.put("8", 81);
		SIMAP.put("9", 91);
		SIMAP.put("10", 101);
		SIMAP.put("J", 111);
		SIMAP.put("Q", 121);
		SIMAP.put("K", 131);
		SIMAP.put("A", 141);
		SIMAP.put("2", 151);
		SIMAP.put("小王", 160);
		SIMAP.put("大王", 170);
	}


}

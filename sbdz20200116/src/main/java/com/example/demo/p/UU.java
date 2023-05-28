package com.example.demo.p;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

/**
 *
 * 把54张牌分给三个玩家
 *
 * @author zhangzhen
 * @date 2020年2月6日
 *
 */
public class UU {

	public static List<PU> wj(final int[] array) {
		final PU pu1 = new PU(1, "A", URE.L, Arrays.copyOfRange(array, 0, 20), null, null, null);
		final PU pu2 = new PU(2, "B", URE.F, Arrays.copyOfRange(array, 20, 37), null, null, null);
		final PU pu3 = new PU(3, "C", URE.F, Arrays.copyOfRange(array, 37, 54), null, null, null);

		pu1.setNext(pu2);
		pu1.setPrevious(pu3);

		pu2.setNext(pu3);
		pu2.setPrevious(pu1);

		pu3.setNext(pu1);
		pu3.setPrevious(pu2);

		final ArrayList<PU> ulist = Lists.newArrayList(pu1, pu2, pu3);
		for (final PU pu : ulist) {
			Arrays.sort(pu.getCArray());
		}
		return ulist;

	}

}

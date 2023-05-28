package com.example.demo.p;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 *
 *
 * @author zhangzhen
 * @date 2020年1月16日
 *
 */
public class PX20Test1 {

	public static void main(final String[] args) {
		test_2020年1月16日_20_1();
	}

	public static void test_2020年1月16日_20_1() {
		System.out.println(
				Thread.currentThread().getName() + "\t" + LocalTime.now() + "\t" + "PX20Test1.test_2020年1月16日_20_1()");
		System.out.println();

		final int n = 10000 * 10;
		final int cs = 20;
		final PXE[] ps = PXE.values();
		final Map<PXE, Integer> map = Maps.newHashMap();
		for (int i = 1; i <= n; i++) {
			final int[] cA = CU.g();
			final int[] array = Arrays.copyOfRange(cA, 0, cs);
			Arrays.sort(array);
			for (final PXE pxe : ps) {
				final List<PX> pxlist = G.g(array, pxe);
				final Integer maxV = map.get(pxe);
				if (maxV == null || maxV < pxlist.size()) {
					 map.put(pxe, pxlist.size());
				}
			}
		}

		System.out.println(map.size());
		for (final PXE pxe : ps) {
			System.out.println(pxe + "\t" + map.get(pxe));
		}
		System.out.println(map.size());


	}

}

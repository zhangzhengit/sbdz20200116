package com.example.demo.p;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.Lists;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableCallable;

/**
 *
 *
 * @author zhangzhen
 * @date 2020年1月15日
 *
 */
public class Gif1 {

	public static void main(final String[] args) {
		test_2020年1月15日_1();
	}

	@Test
	public static void test_2020年1月15日_1() {
		System.out
				.println(Thread.currentThread().getName() + "\t" + LocalTime.now() + "\t" + "Gif1.test_2020年1月15日_1()");
		System.out.println();

		// 1 100000	671
		final int n = 10000 * 10;
		final long t1 = System.currentTimeMillis();
		for (int i = 1; i <= n; i++) {
			final int[] array = CU.g();
			final int csize = 20;
			final int[] c2 = Arrays.copyOfRange(array, 0, csize);
			Arrays.sort(c2);
//			final List<PX> pxlist = side(c2, 40);
			final List<PX> pxlist = side2(c2, 40);
//			System.out.println(i + "\t" + pxlist.size());
		}
		final long t2 = System.currentTimeMillis();
		System.out.println(n + "\t" + (t2 - t1));
	}

	public static List<PX> side2(final int[] array, final int size) {
		if (array.length < 6) {
			return G.el();
		}
		final List<PX> bmlist = G.zhadan(array);
		if (bmlist.isEmpty()) {
			return G.el();
		}

		final List<PX> list = Lists.newArrayListWithCapacity(size);
		final List<PX> dzlist = G.duizi(array);
		// 1
//		for (final PX b : bmlist) {
//			for (final PX d : dzlist) {
//				if (b.getCArray()[0] / 10 == d.getCArray()[0] / 10) {
//					continue;
//				}
//				final int[] rA = { b.getCArray()[0], b.getCArray()[1], b.getCArray()[2], b.getCArray()[3], d.getCArray()[0],
//						d.getCArray()[1] };
//				list.add(new PX(PXE.LD, rA));
//			}
//		}
		// 2
		final List<PX> collect = bmlist.parallelStream().flatMap(bm -> {
			final List<PX> dlist = dzlist.parallelStream()
			.filter(d -> bm.getCArray()[0] / 10 != d.getCArray()[0] / 10)
			.map(d -> {
				final int[] rA = { bm.getCArray()[0], bm.getCArray()[1], bm.getCArray()[2], bm.getCArray()[3], d.getCArray()[0],
						d.getCArray()[1] };
				final PX px = new PX(PXE.Liandui, rA);
//				list.add(px);
				return px;
			}).collect(Collectors.toList());

			return dlist.parallelStream();
		}).collect(Collectors.toList());

		return collect;
	}

	public static List<PX> side(final int[] array, final int size) {
		if (array.length < 6) {
			return G.el();
		}
		final List<PX> bmlist = G.zhadan(array);
		if (bmlist.isEmpty()) {
			return G.el();
		}

		final List<PX> list = Lists.newArrayListWithCapacity(size);
		final List<PX> dzlist = G.duizi(array);
		for (final PX b : bmlist) {
			for (final PX d : dzlist) {
				if (b.getCArray()[0] / 10 == d.getCArray()[0] / 10) {
					continue;
				}
				final int[] rA = { b.getCArray()[0], b.getCArray()[1], b.getCArray()[2], b.getCArray()[3], d.getCArray()[0],
						d.getCArray()[1] };
				list.add(new PX(PXE.Liandui, rA));
			}
		}

		return list;
	}

}

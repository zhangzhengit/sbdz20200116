package com.example.demo.p;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

/**
 *
 *
 * @author zhangzhen
 * @date 2020年1月15日
 *
 */
public class GTest {


	@Test
	public void test_2020年1月17日_vl1() {

		final int[] array = { 31, 32, 41, 42, 51, 52, 61, 62, 71, 72, 81, 82, 91, 92, 101 };
		for (final PXE pxe : PXE.VL) {
			final List<PX> g = G.g(array, pxe);
			final Optional<PX> a = g.parallelStream().filter(px -> px.getCArray().length == array.length).findAny();
			if (a.isPresent()) {
				System.out.println("a=" + a.get());
				return;
			}
		}
		final boolean anyMatch = PXE.VL.parallelStream().anyMatch(pxe -> G.g(array, pxe).stream()
				.filter(px -> px.getCArray().length == array.length).findAny().isPresent());

		System.out.println(anyMatch);
	}

	@Test
	public void test_2020年1月16日_d1() {

		final int[] cA = new int[] { 41, 51, 52, 53, 54 };
		System.out.println(Arrays.toString(cA));

		final List<PX> bmlist = G.dan(cA);
		System.out.println(bmlist.size());
		for (final PX px : bmlist) {
			System.out.println(px);
		}
		System.out.println(bmlist.size());

	}

	@Test
	public void test_2020年1月16日_bm2() {

		final int[] cA = new int[] { 41, 51, 52, 53, 54 };
		System.out.println(Arrays.toString(cA));

		final List<PX> bmlist = G.zhadan(cA);
		System.out.println(bmlist.size());
		for (final PX px : bmlist) {
			System.out.println(px);
		}
		System.out.println(bmlist.size());
	}

	@Test
	public void test_2020年1月15日_jb1() {

		final int[] g = CU.g();
		final int size = RandomUtils.nextInt(54, 54);
		final int[] c2 = Arrays.copyOf(g, size);
		Arrays.sort(c2);
		System.out.println(size);
		System.out.println(Arrays.toString(c2));

		final int n = 4004;
		final long t1 = System.currentTimeMillis();
		final List<PX> pList = G.wangzha(c2);
		final long t2 = System.currentTimeMillis();
		System.out.println(pList.size());
		for (final PX px : pList) {
			System.out.println(px);
		}
		System.out.println(pList.size());
		System.out.println("ms:" + (t2 - t1));

	}

	@Test
	public void test_2020年1月15日_fjc1() {

		final int nxx = 5000;
		for (int i = 1; i <= nxx; i++) {

			final int[] g = CU.g();
			final int cs = 17;
			final int size = RandomUtils.nextInt(cs, cs);
			final int[] c2 = Arrays.copyOf(g, size);
			// final int[] c2 = { 31, 32, 34, 41, 42, 43, 51, 52, 53, 71, 72,
			// 101, 102, 141, 142};
			Arrays.sort(c2);
			// System.out.println(size);
			// System.out.println(Arrays.toString(c2));

			final int n = 4004;
			final long t1 = System.currentTimeMillis();
			final List<PX> pList = G.feijichibang(c2, n);
			final long t2 = System.currentTimeMillis();
			if (!pList.isEmpty()) {
				System.out.println(i + " 次：" + cs + " zhang,ms = " + (t2 - t1) + "\t" + "pxsize=" + pList.size());
			}
			// System.out.println(pList.size());
			// for (final PX px : pList) {
			// System.out.println(px);
			// }
			// System.out.println(pList.size());
		}

	}

	@Test
	public void test_2020年1月15日_side1() {

		final int[] g = CU.g();
		final int size = RandomUtils.nextInt(10, 54);
		final int[] c2 = Arrays.copyOf(g, size);
		Arrays.sort(c2);
		System.out.println(size);
		System.out.println(Arrays.toString(c2));

		final int n = 4004;
		final long t1 = System.currentTimeMillis();
		final List<PX> pList = G.sidaier(c2, n);
		final long t2 = System.currentTimeMillis();
		System.out.println(pList.size());
		for (final PX px : pList) {
			System.out.println(px);
		}
		System.out.println(pList.size());

	}

	@Test
	public void test_2020年1月15日_ld1() {

		final int[] g = CU.g();
		final int size = RandomUtils.nextInt(6, 54);
		final int[] c2 = Arrays.copyOf(g, size);
		Arrays.sort(c2);
		System.out.println(size);
		System.out.println(Arrays.toString(c2));

		final int n = 4004;
		final long t1 = System.currentTimeMillis();
		final List<PX> pList = G.liandui(c2, n);
		final long t2 = System.currentTimeMillis();
		System.out.println(pList.size());
		for (final PX px : pList) {
			System.out.println(px);
		}
		System.out.println(pList.size());

	}

	@Test
	public void test_2020年1月15日_sde1() {

		final int[] g = CU.g();
		final int size = 34;
		final int[] c2 = Arrays.copyOf(g, size);
		Arrays.sort(c2);
		System.out.println(size);
		System.out.println(Arrays.toString(c2));

		final int n = 4004;
		final long t1 = System.currentTimeMillis();
		final List<PX> pList = G.sandaier(c2, n);
		final long t2 = System.currentTimeMillis();
		System.out.println(pList.size());
		for (final PX px : pList) {
			System.out.println(px);
		}
		System.out.println(pList.size());

	}

	@Test
	public void test_2020年1月15日_sdy1() {

		final int[] g = CU.g();
		final int size = 54;
		final int[] c2 = Arrays.copyOf(g, size);
		Arrays.sort(c2);
		System.out.println(size);
		System.out.println(Arrays.toString(c2));

		final int n = 4004;
		final long t1 = System.currentTimeMillis();
		final List<PX> pList = G.sandaiyi(c2, n);
		final long t2 = System.currentTimeMillis();
		System.out.println(pList.size());
		for (final PX px : pList) {
			System.out.println(px);
		}
		System.out.println(pList.size());
	}

	@Test
	public void test_2020年1月15日_fj1() {

		final int nx = 1;
		int fjc = 0;
		long sum = 0L;
		for (int i = 1; i <= nx; i++) {
			final int[] g = CU.g();
			final int size = 54;
			final int[] c2 = Arrays.copyOf(g, size);
			Arrays.sort(c2);
			System.out.println(size);
			System.out.println(Arrays.toString(c2));

			final int n = 4004;
			final long t1 = System.currentTimeMillis();
			final List<PX> pList = G.feiji(c2, n);
			final long t2 = System.currentTimeMillis();
			sum += (t2 - t1);
			if (!pList.isEmpty()) {
				fjc++;
			}
			System.out.println(pList.size());
			for (final PX px : pList) {
				System.out.println(px);
			}
		}

		System.out.println(nx + "\t" + "ms: " + (sum));
		System.out.println(nx + "\t" + fjc + "\t" + ((fjc + 0.0) / nx));
	}

	private static List<PX> el() {
		return Collections.emptyList();
	}

	@Test
	public void test_2020年1月15日_shz1() {

		final int[] g = CU.g();
		final int size = 8;
		final int[] c2 = Arrays.copyOf(g, size);
		// final int[] c2 = {31,32,43,52,61,71,82,91,101,111,121,131,142};
		// final int[] c2 = {34, 42, 43, 44, 51, 53, 61, 72, 73, 81, 92, 93,
		// 103, 111, 121, 131, 134, 141, 144, 153};
		Arrays.sort(c2);
		System.out.println(size);
		System.out.println(Arrays.toString(c2));

		final int n = 4004;
		final List<PX> pList = G.shunzi(c2);
		System.out.println(pList.size());
		for (final PX px : pList) {
			System.out.println(px);
		}
		System.out.println(pList.size());

	}

	@Test
	public void test_2020年1月15日_bm1() {

		final int[] g = CU.g();
		final int size = 40;
		final int[] c2 = Arrays.copyOf(g, size);
		Arrays.sort(c2);
		System.out.println(size);
		System.out.println(Arrays.toString(c2));

		final int n = 4004;
		final List<PX> pList = G.zhadan(c2, n);
		System.out.println(pList.size());
		for (final PX px : pList) {
			System.out.println(px);
		}
	}

	@Test
	public void test_2020年1月15日_sz1() {

		final int[] g = CU.g();
		final int size = 54;
		final int[] c2 = Arrays.copyOf(g, size);
		// final int[] c2 = { 31, 32, 33, 41, 43, 44 };

		Arrays.sort(c2);
		System.out.println(size);
		System.out.println(Arrays.toString(c2));
		final int n = 400;
		final List<PX> pList = G.sanzhang(c2, n);
		System.out.println(pList.size());
		for (final PX px : pList) {
			System.out.println(px);
		}
		System.out.println(pList.size());
	}

	@Test
	public void test_2020年1月15日_dz1() {

		final int nnnn = 5000;
		for (int i = 1; i <= nnnn; i++) {

			final int[] g = CU.g();
			final int size = RandomUtils.nextInt(1, 55);
			final int[] c2 = Arrays.copyOf(g, size);
			Arrays.sort(c2);
			System.out.println(Arrays.toString(c2));

			final int dzSize = 2000;
			final List<PX> dz = G.duizi(c2, dzSize);
			System.out.println(i + "\t" + dz.size());
			for (final PX px : dz) {
				System.out.println(px);
			}
			System.out.println(i + "\t" + dz.size());
		}

	}

}

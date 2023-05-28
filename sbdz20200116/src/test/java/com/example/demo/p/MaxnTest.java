package com.example.demo.p;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 *
 * @author zhangzhen
 * @date 2020年1月19日
 *
 */
public class MaxnTest {

	@Test
	public void test_2020年1月19日_zhiding1() {

		final int n = 10000 * 111;
		final int size = 20;
		final PXE pxe = PXE.Zhadan;

		final RT rt = getmax(pxe, size, n);
		System.out.println(size + "\t" + pxe + "\t" + rt.getMax());
		final List<PX> pxlist = rt.getMaxpxlist();
		final Set<Integer> nset = Sets.newHashSet();
		for (final PX px : pxlist) {
			final int[] cArray = px.getCArray();
			for (final int i : cArray) {
				nset.add(i);
			}
		}
		final List<Integer> nList = Lists.newArrayList(nset);
		nList.sort(Comparator.comparing(Integer::valueOf));
		System.out.println("\t" + nList);

	}

	@Test
	public  void test_2020年1月19日_1() {

		final int n = 10000 * 111;
		final int size = 20;
		final ImmutableList<PXE> pxes = PXE.VL;
		for (final PXE pxe : pxes) {
			final RT rt = getmax(pxe, size, n);
			System.out.println(size + "\t" + pxe + "\t" + rt.getMax());
			final List<PX> pxlist = rt.getMaxpxlist();
			final Set<Integer> nset = Sets.newHashSet();
			for (final PX px : pxlist) {
				final int[] cArray = px.getCArray();
				for (final int i : cArray) {
					nset.add(i);
				}
			}
			final List<Integer> nList = Lists.newArrayList(nset);
			nList.sort(Comparator.comparing(Integer::valueOf));
			System.out.println("\t" + nList);

		}

	}

	private static RT getmax(final PXE pxe, final int size, final int n) {
		int max = -1;
		List<PX> maxpxlist = null;
		for (int i = 1; i <= n; i++) {
			final int[] g = CU.g();
			final int[] c1a = Arrays.copyOfRange(g, 0, size);
			Arrays.sort(c1a);
			final List<PX> m = m(c1a, pxe);
			if (m.size() > max) {
				max = m.size();
				maxpxlist = m;
				// System.out.println(size + "\t" + pxe + "\t" + max + "\t" +
				// m);
			}
		}
		final RT rt = new RT(max, maxpxlist);
		return rt;
	}

	@Data
	@AllArgsConstructor
	static class RT {
		int max = -1;
		List<PX> maxpxlist = null;

	}

	public static List<PX> m(final int[] array, final PXE pxe) {
		final List<PX> g = G.g(array, pxe);
		return g;
	}

}

package com.example.demo;

import java.time.LocalTime;
import java.util.List;

import org.junit.Test;

import com.example.demo.p.CU;
import com.example.demo.p.PU;
import com.example.demo.p.RR;
import com.example.demo.p.RRPU;
import com.example.demo.p.UU;

/**
 *
 *
 * @author zhangzhen
 * @date 2020年1月19日
 *
 */
public class ABCTest1 {

	@Test
	public void test_d_orderN1() {

		final int[] l1 = new int[] { 31, 51, 62, 72, 91, 101, 121 };
		final int[] f1 = new int[] { 42 };
		final int[] f2 = new int[] { 41 };
		final List<PU> puList = p3(l1, f1, f2);
		final int ms = 1000 * 2;
		print(puList, ms, 0);
	}

	// @Test
	public void test_shz1() {

		final int[] l1 = new int[] { 31, 41, 42, 51, 52, 61, 62, 71, 72, 81, 82, 91, 101, 111, 121 };
		final int[] f1 = new int[] { 170 };
		final int[] f2 = new int[] { 41 };
		final List<PU> puList = p3(l1, f1, f2);
		final int ms = 1000 * 2;
		print(puList, ms, 0);
	}

	// @Test
	public void test_l1dz_1() {
		System.out.println(Thread.currentThread().getName() + "\t" + LocalTime.now() + "\t" + "ABCTest1.test_l1dz_1()");
		System.out.println();

		final int[] l1 = new int[] { 31, 41, 42, 61, 62, 81, 82, 101, 102 };
		final int[] f1 = new int[] { 170 };
		final int[] f2 = new int[] { 41 };
		final List<PU> puList = p3(l1, f1, f2);

		final PU p1 = puList.parallelStream().filter(p -> p.getId() == 1).findAny().get();
		System.out.println("----------p1-----------");
		System.out.println(p1);

		final int ms = 1000 * 2;
		final List<RRPU> rrpulist = RR.rrrrrrrrrrrr(p1, ms);
		System.out.println("rrpulist.size=" + rrpulist.size());

	}

	// @Test
	public void test_cdzf2_1() {
		System.out
				.println(Thread.currentThread().getName() + "\t" + LocalTime.now() + "\t" + "ABCTest1.test_cdzf2_1()");
		System.out.println();

		final int[] l1 = new int[] { 41, 42, 43, 61, 62, 63, 81, 82, 83, 101, 102, 103 };
		final int[] f1 = new int[] { 31, 42, 51, 61, 62, 63, 64, 71, 81 };
		final int[] f2 = new int[] { 41 };
		final List<PU> puList = p3(l1, f1, f2);

		final PU p1 = puList.parallelStream().filter(p -> p.getId() == 1).findAny().get();
		System.out.println("----------p1-----------");
		System.out.println(p1);

		final int ms = 1000 * 2;
		final List<RRPU> rrpulist = RR.rrrrrrrrrrrr(p1, ms);
		System.out.println("rrpulist.size=" + rrpulist.size());

	}

	private List<PU> p3(final int[] l1, final int[] f1, final int[] f2) {
		final int[] g = CU.g();
		final List<PU> puList = UU.wj(g);
		puList.get(0).setCArray(l1);
		puList.get(1).setCArray(f1);
		puList.get(2).setCArray(f2);
		for (final PU pu : puList) {
			System.out.println(pu);
		}
		return puList;
	}

	// @Test
	public void testf1() {
		System.out.println(Thread.currentThread().getName() + "\t" + LocalTime.now() + "\t" + "ABCTest1.testf1()");
		System.out.println();

		final int[] g = CU.g();
		final List<PU> puList = UU.wj(g);
		puList.get(0).setCArray(new int[] { 51, 52, 61, 62, 81, 82, 101, 102, 121, 123 });
		puList.get(1).setCArray(new int[] { 31, 41, 42, 121, 122, 123 });
		puList.get(2).setCArray(new int[] { 41 });
		for (final PU pu : puList) {
			System.out.println(pu);
		}

		final PU p1 = puList.parallelStream().filter(p -> p.getId() == 1).findAny().get();
		System.out.println("----------p1-----------");
		System.out.println(p1);

		final int ms = 1000 * 2;
		final List<RRPU> rrpulist = RR.rrrrrrrrrrrr(p1, ms);
		System.out.println("rrpulist.size=" + rrpulist.size());
	}

	private void print(final List<PU> puList, final int ms, final int pi) {
		final PU p1 = puList.get(pi);
		System.out.println("----------p1-----------");
		System.out.println(p1);

		final List<RRPU> rrpulist = RR.rrrrrrrrrrrr(p1, ms);
		System.out.println("rrpulist.size=" + rrpulist.size());
	}

	@Test
	public void test_f1yasiff2z_1() {

		final int[] l1 = new int[] { 31, 51, 62, 72, 91, 101, 121 };
		final int[] f1 = new int[] { 31, 41, 160 };
		final int[] f2 = new int[] { 41 };
		final List<PU> puList = p3(l1, f1, f2);
		final int ms = 1000 * 2;
		print(puList, ms, 1);
	}

}

package com.example.demo;

import java.util.List;

import org.junit.Test;

import com.example.demo.p.G;
import com.example.demo.p.PX;

/**
 *
 *
 * @author zhangzhen
 * @date 2020年1月19日
 *
 */
public class PXETest1 {

	// 20 FJC 66
	// [61, 63, 72, 73, 83, 84, 102, 103, 104, 111, 113, 114, 122, 123, 124,
	// 131, 133, 134, 151, 154]

	@Test
	public void testfjc1() {
		// final int[] array = { 61, 63, 72, 73, 83, 84, 102, 103, 104, 111,
		// 113, 114, 122, 123, 124, 131, 133, 134, 151, 154 };
		final int[] array = { 32, 33, 62, 64, 82, 84, 93, 94, 111, 113, 114, 121, 122, 124, 131, 133, 134, 142, 143,
				144 };
		System.out.println(array.length);
		final List<PX> dz = G.feijichibang(array);
		System.out.println(dz.size());
	}

	@Test
	public void testfj1() {
		final int[] array = { 31, 32, 33, 42, 43, 44, 52, 53, 54, 62, 63, 64, 72, 73, 74, 81, 82, 83, 91, 92 };
		System.out.println(array.length);
		final List<PX> dz = G.feiji(array);
		System.out.println(dz.size());
	}

	@Test
	public void testsz1() {
		final int[] array = { 31, 32, 33, 42, 43, 44, 52, 53, 54, 62, 63, 64, 72, 73, 74, 81, 82, 83, 91, 92 };
		System.out.println(array.length);
		final List<PX> dz = G.sanzhang(array);
		System.out.println(dz.size());
	}

	@Test
	public void testbm1() {
		final int[] array = { 31, 32, 33, 34, 41, 42, 43, 44, 51, 52, 53, 54, 61, 62, 63, 64, 71, 72, 73, 74 };
		System.out.println(array.length);
		final List<PX> dz = G.zhadan(array);
		System.out.println(dz.size());
	}

	@Test
	public void testdz1() {
		final int[] array = { 31, 32, 41, 42, 51, 52, 61, 62, 71, 72, 81, 82, 91, 92, 101, 102, 111, 112, 121, 121 };
		System.out.println(array.length);
		final List<PX> dz = G.duizi(array);
		System.out.println(dz.size());
	}
}

package com.example.demo.p;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.example.demo.p.CU;
import com.example.demo.p.PU;
import com.example.demo.p.RR;
import com.example.demo.p.RRPU;
import com.example.demo.p.UU;

/**
 *
 *
 * @author zhangzhen
 * @date 2020年1月21日
 *
 */
public class Testp1 {

	public static void main(String[] args) {
		test_2020年1月21日_1();
	}

	public static void test_2020年1月21日_1() {

		final int[] g = CU.g();
		final List<PU> puList = UU.wj(g);
		final int ucs = 2;
		for (final PU pu : puList) {
			ArrayUtils.shuffle(pu.getCArray());
			final int[] c2 = Arrays.copyOfRange(pu.getCArray(), 0, ucs);
			Arrays.sort(c2);
			pu.setCArray(c2);
		}

		puList.get(0).setCArray(new int[] { 31, 32, 83, 84 });
		puList.get(1).setCArray(new int[] { 31, 41, 52, 62, 72, 81, 82 });
//		puList.get(2).setCArray(new int[] { 31, 32, 34, 51, 61, 111 });
		puList.get(2).setCArray(new int[] { 111 });
		for (final PU pu : puList) {
			System.out.println(pu);
		}

		final PU p1 = puList.stream().filter(p -> p.getId() == 1).findAny().get();
		final int[] aaaa = p1.getCArray();
		System.out.println(Arrays.toString(aaaa));
		final int maxms = 500;
		RRPU pp = RR.pp(p1, maxms);
		System.out.println("pp=" + pp);

	}

}

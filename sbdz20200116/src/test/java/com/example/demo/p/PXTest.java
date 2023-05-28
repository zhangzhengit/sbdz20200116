package com.example.demo.p;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.annotation.Annotation;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.checkerframework.checker.units.qual.Prefix;
import org.checkerframework.checker.units.qual.radians;
import org.junit.Test;

/**
 *
 * 牌型测试
 *
 * @author zhangzhen
 * @date 2020年2月6日
 *
 */
public class PXTest {

	Random random = new Random();

	/**
	 * 随机选一个非炸弹和王炸的牌型A，找出所有要得起A的牌型，这些牌型必须是同牌型或王炸或炸弹
	 */
	@Test
	public void testpx1() {
		System.out.println(LocalTime.now() + "\t" + Thread.currentThread().getName() + "\t" + "PXTest.testpx1()");

		// 测试次数
		final int n = 10000 * 50;
		for (int i = 1; i <= n; i++) {
			final int[] g = CU.g();
			final List<PU> wj = UU.wj(g);
			final PU p1 = wj.get(0);

			final List<PX> p1pxlist = G.g(p1.getCArray());
			PX px1 = p1pxlist.get(random.nextInt(p1pxlist.size()));
			while (px1.getPxe() == PXE.Zhadan || px1.getPxe() == PXE.Wangzha) {
				px1 = p1pxlist.get(random.nextInt(p1pxlist.size()));
			}
			final PXE px1pxe = px1.getPxe();
			// System.out.println("px1=" + px1.getPxe() + "\t" +
			// Arrays.toString(px1.getCArray()));

			final PU p2 = wj.get(1);
			final List<PX> p2list = GU.ydq(px1, p2.getCArray());
			final boolean allMatch = p2list.stream().allMatch(
					px -> px.getPxe() == px1pxe || px.getPxe() == PXE.Zhadan || px.getPxe() == PXE.Wangzha);
			assertThat(allMatch);
		}

	}




	/**
	 * 	测试需要个数相同才能比较的牌型（顺子、飞机、飞机翅膀、连对）
	 */
	@Test
	public void testliandui1(){
		System.out.println(LocalTime.now() + "\t" + Thread.currentThread().getName() + "\t" + "PXTest.testliandui1()");

		final EnumSet<PXE> set = EnumSet.of(PXE.Shunzi,PXE.Feiji,PXE.Feijichibang,PXE.Liandui);

		final int n = 10000 * 5;
		for (int i = 1; i <= n; i++) {
			final int[] g = CU.g();
			final List<PU> wj = UU.wj(g);
			final PU p1 = wj.get(0);

			final List<PX> p1pxlist = G.g(p1.getCArray());
			PX px1 = p1pxlist.get(random.nextInt(p1pxlist.size()));
			while (!set.contains(px1.getPxe())) {
				px1 = p1pxlist.get(random.nextInt(p1pxlist.size()));
			}
			final PXE px1pxe = px1.getPxe();
			final int px1size = px1.size();
			final PU p2 = wj.get(1);
			final List<PX> p2list = GU.ydq(px1, p2.getCArray());
			final boolean allMatch = p2list.stream().allMatch(px -> {
				if (px.getPxe() == px1pxe) {
					return px.size() == px1size;
				}
				return px.getPxe() == PXE.Zhadan || px.getPxe() == PXE.Wangzha;
			});
			assertThat(allMatch);
			final Set<PXE> pxeset = p2list.stream().map(PX::getPxe).collect(Collectors.toSet());
			System.out.println(i + "\t" + px1pxe + "\t" + pxeset);
		}



	}





























}

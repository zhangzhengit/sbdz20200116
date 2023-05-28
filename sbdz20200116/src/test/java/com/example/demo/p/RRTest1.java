package com.example.demo.p;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import com.google.common.collect.Lists;

/**
 *
 *
 * @author zhangzhen
 * @date 2020年1月16日
 *
 */
public class RRTest1 {

	@Test
	public void  test_2020年1月17日_gg1() {
		System.out.println(
				Thread.currentThread().getName() + "\t" + LocalTime.now() + "\t" + "RRTest1.test_2020年1月17日_gg1()");
		System.out.println();

		final int[] g = CU.g();
		final List<PU> puList = UU.wj(g);
		puList.get(0).setCArray(new int[] { 83, 84 }); // 31, 32
		puList.get(1).setCArray(new int[] { 31, 41, 62, 72, 81, 82 });
		puList.get(2).setCArray(new int[] { 31 });
		for (final PU pu : puList) {
			System.out.println(pu);
		}

		final PU p2 = puList.stream().filter(p -> p.getId() == 2).findAny().get();
		final int[] aaaa = p2.getCArray();
		System.out.println(Arrays.toString(aaaa));
		final int maxms = 1000 * 2;
		final PX p1c = new PX(PXE.Duzi, new int[] { 31, 32 });
		final RRPU gg = RR.gg(p2, p1c, maxms);
		System.out.println("gg=" + gg);

	}

	public void  test_2020年1月19日_r_ysfz1() {
		System.out.println(
				Thread.currentThread().getName() + "\t" + LocalTime.now() + "\t" + "RRTest1.test_2020年1月19日_r_ysfz1()");
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
		final List<RRPU> rrpulist = rrrrrrrrrrrr(p1, ms);
		System.out.println("rrpulist.size=" + rrpulist.size());

	}

	@Test
	public void  test_2020年1月19日_r_f2yasi_1() {
		System.out.println(Thread.currentThread().getName() + "\t" + LocalTime.now() + "\t"
				+ "RRTest1.test_2020年1月19日_r_f2yasi_1()");
		System.out.println();

		final int[] g = CU.g();
		final List<PU> puList = UU.wj(g);
		puList.get(0).setCArray(new int[] { 51, 52, 61, 62, 81, 82, 101, 111, 121, 131, 141 });
		puList.get(1).setCArray(new int[] { 123 });
		puList.get(2).setCArray(new int[] { 31, 32, 41, 42, 51, 61, 71, 111, 112 });
		for (final PU pu : puList) {
			System.out.println(pu);
		}

		final PU p1 = puList.parallelStream().filter(p -> p.getId() == 1).findAny().get();
		System.out.println("----------p1-----------");
		System.out.println(p1);

		final int ms = 1000 * 2;
		final List<RRPU> rrpulist = rrrrrrrrrrrr(p1, ms);
		System.out.println("rrpulist.size=" + rrpulist.size());

	}

	@Test
	public void  test_2020年1月19日_r_czd1() {
		System.out.println(
				Thread.currentThread().getName() + "\t" + LocalTime.now() + "\t" + "RRTest1.test_2020年1月19日_r_czd1()");
		System.out.println();

		final int[] g = CU.g();
		final List<PU> puList = UU.wj(g);
		puList.get(0).setCArray(new int[] { 31, 42, 51, 64, 71, 72, 73, 74, 84 });
		puList.get(1).setCArray(new int[] { 81, 82, 83 });
		puList.get(2).setCArray(new int[] { 31 });
		for (final PU pu : puList) {
			System.out.println(pu);
		}

		final PU p1 = puList.parallelStream().filter(p -> p.getId() == 1).findAny().get();
		System.out.println("----------p1-----------");
		System.out.println(p1);

		final int ms = 1000 * 2;
		final List<RRPU> rrpulist = rrrrrrrrrrrr(p1, ms);
		System.out.println("rrpulist.size=" + rrpulist.size());

	}

	@Test
	public void  test_2020年1月19日_r_zhiding_d_l1() {
		System.out.println(Thread.currentThread().getName() + "\t" + LocalTime.now() + "\t"
				+ "RRTest1.test_2020年1月19日_r_zhiding_d_l1()");
		System.out.println();

		final int[] g = CU.g();
		final List<PU> puList = UU.wj(g);
		Arrays.sort(g);
		puList.get(0).setCArray(Arrays.copyOfRange(g, 17 + 17, 54));

		for (final PU pu : puList) {
			System.out.println(pu);
		}

		final PU p1 = puList.parallelStream().filter(p -> p.getId() == 1).findAny().get();
		System.out.println("----------p1-----------");
		System.out.println(p1);
		final int maxms = 1000 * 2;
		final List<RRPU> rrrrrrrrrrrr = rrrrrrrrrrrr(p1, maxms);
		System.out.println(rrrrrrrrrrrr.size());

	}

	/**
	 *
	 */
	@Test
	public void  test_2020年1月19日_p_zhiding_1() {
		System.out.println(Thread.currentThread().getName() + "\t" + LocalTime.now() + "\t"
				+ "RRTest1.test_2020年1月19日_p_zhiding_1()");
		System.out.println();

		final int[] g = CU.g();
		final List<PU> puList = UU.wj(g);
		// puList.get(0).setCArray(new int[] { 31, 42j, 51, 52, 61, 62 });
		// puList.get(1).setCArray(new int[] { 31, 42, 51, 52, 71, 72 });
		// puList.get(2).setCArray(new int[] { 33 });
		for (final PU pu : puList) {
			System.out.println(pu);
		}

		final PU p1 = puList.parallelStream().filter(p -> p.getId() == 1).findAny().get();
		System.out.println("----------p1-----------");
		System.out.println(p1);
		final int maxms = 1000 * 5;
		final RRPU pp = RR.pp(p1, maxms);
		System.out.println(pp);

	}

	@Test
	public void  test_2020年1月19日_r_zhiding1() {
		System.out.println(Thread.currentThread().getName() + "\t" + LocalTime.now() + "\t"
				+ "RRTest1.test_2020年1月19日_r_zhiding1()");
		System.out.println();

		final int[] g = CU.g();
		final List<PU> puList = UU.wj(g);
		puList.get(0).setCArray(new int[] { 31, 42, 51, 52, 61, 62 });
		puList.get(1).setCArray(new int[] { 31, 42, 51, 52, 71, 72 });
		puList.get(2).setCArray(new int[] { 33 });
		for (final PU pu : puList) {
			System.out.println(pu);
		}

		final PU p1 = puList.parallelStream().filter(p -> p.getId() == 1).findAny().get();
		System.out.println("----------p1-----------");
		System.out.println(p1);

		final int ms = 1000 * 5;
		final List<RRPU> rrpulist = rrrrrrrrrrrr(p1, ms);
		System.out.println("rrpulist.size=" + rrpulist.size());

	}

	@Test
	public void  test_2020年1月19日_r_2() {
		System.out.println(
				Thread.currentThread().getName() + "\t" + LocalTime.now() + "\t" + "RRTest1.test_2020年1月19日_r_2()");
		System.out.println();

		final int[] g = CU.g();
		final List<PU> puList = UU.wj(g);
		final int s = 4;
		for (final PU pu : puList) {
			final int[] cArray = pu.getCArray();
			ArrayUtils.shuffle(cArray);
			final int[] c1 = Arrays.copyOf(cArray, s);
			Arrays.sort(c1);
			pu.setCArray(c1);
			System.out.println(pu);
		}
		final PU p1 = puList.stream().filter(p -> p.getId() == 1).findAny().get();
		System.out.println("----------p1-----------");
		System.out.println(p1);

		final int ms = 1000 * 2;
		final List<RRPU> rrpulist = rrrrrrrrrrrr(p1, ms);
		System.out.println("rrpulist.size=" + rrpulist.size());

	}

	@Test
	public void  test_2020年1月19日_r_1() {
		System.out.println(
				Thread.currentThread().getName() + "\t" + LocalTime.now() + "\t" + "RRTest1.test_2020年1月19日_r_1()");
		System.out.println();

		final int[] g = CU.g();
		final List<PU> puList = UU.wj(g);
		// puList.get(0).setCArray(new int[] { 31, 32, 34, 41, 42, 43, 51, 52,
		// 53, 83, 84, 91, 92, 101, 102 });
		puList.get(0).setCArray(new int[] { 31, 32, 83, 84, 91, 92, 101, 102 });
		puList.get(1).setCArray(new int[] { 31, 41, 52, 62, 72, 81, 82 });
		puList.get(2).setCArray(new int[] { 31, 32, 34, 51, 61, 111 });
		for (final PU pu : puList) {
			System.out.println(pu);
		}

		final PU p1 = puList.stream().filter(p -> p.getId() == 1).findAny().get();
		System.out.println("----------p1-----------");
		System.out.println(p1);

		final int ms = 400;
		final List<RRPU> rrpulist = rrrrrrrrrrrr(p1, ms);
		System.out.println("rrpulist.size=" + rrpulist.size());

	}

	@Test
	public void  test_2020年1月17日_pp1() {
		System.out.println(
				Thread.currentThread().getName() + "\t" + LocalTime.now() + "\t" + "RRTest1.test_2020年1月17日_pp1()");
		System.out.println();

		final int[] g = CU.g();
		final List<PU> puList = UU.wj(g);
		puList.get(0).setCArray(new int[] { 31, 32, 41, 42, 51, 52, 61, 62, 71, 72, 81, 82, 91, 92 });
		puList.get(1).setCArray(new int[] { 31, 41, 52, 62, 72, 81, 82 });
		puList.get(2).setCArray(new int[] { 31, 32, 34, 51, 61, 111 });
		// puList.get(2).setCArray(new int[] { 33 });
		for (final PU pu : puList) {
			System.out.println(pu);
		}

		final PU p1 = puList.stream().filter(p -> p.getId() == 1).findAny().get();
		final int[] aaaa = p1.getCArray();
		System.out.println(Arrays.toString(aaaa));
		final int maxms = 1000;
		RR.pp(p1, maxms);
	}

	@Test
	public void  test_2020年1月16日_pp1() {
		System.out.println(
				Thread.currentThread().getName() + "\t" + LocalTime.now() + "\t" + "RRTest1.test_2020年1月16日_pp1()");
		System.out.println();

		final int[] g = CU.g();
		final List<PU> puList = UU.wj(g);
		final int ucs = 2;
		for (final PU pu : puList) {
			ArrayUtils.shuffle(pu.getCArray());
			final int[] c2 = Arrays.copyOfRange(pu.getCArray(), 0, ucs);
			Arrays.sort(c2);
			pu.setCArray(c2);
		}

		// puList.get(0).setCArray(new int[] { 41, 42, 43,51,52 });
		//// puList.get(0).setCArray(new int[] { 31,41,51,61,71,91});
		// puList.get(1).setCArray(new int[] { 81, 82, 83, 84 });
		// puList.get(2).setCArray(new int[] { 51, 52, 53, 54});

		// puList.get(0).setCArray(new int[] { 91,101 });
		// puList.get(1).setCArray(new int[] { 31,33, 41,42, 43, 44, 112 });
		// puList.get(2).setCArray(new int[] { 31, 32, 34, 51, 61, 111 });

		 puList.get(0).setCArray(new int[] { 31, 32, 83, 84 });
		 puList.get(1).setCArray(new int[] { 31, 41, 52, 62, 72, 81, 82 });
		 puList.get(2).setCArray(new int[] { 31, 32, 34, 51, 61, 111 });
		 puList.get(2).setCArray(new int[] { 111 });
		for (final PU pu : puList) {
			System.out.println(pu);
		}

		final PU p1 = puList.stream().filter(p -> p.getId() == 1).findAny().get();
		final int[] aaaa = p1.getCArray();
		System.out.println(Arrays.toString(aaaa));
		final int maxms = 500;
		final RRPU pp = RR.pp(p1, maxms);
		System.out.println("pp=" + pp);

	}

	@Test
	public void  test_2020年1月16日_1() {
		System.out.println(
				Thread.currentThread().getName() + "\t" + LocalTime.now() + "\t" + "RRTest1.test_2020年1月16日_1()");
		System.out.println();

		final int[] g = CU.g();
		final List<PU> puList = UU.wj(g);
		final PU p1 = puList.stream().filter(p -> p.getId() == 1).findAny().get();
		rrrrrrrrrrrr(p1, 1000);

	}

	List<RRPU> rrrrrrrrrrrr(final PU p1, final int ms) {
		final List<RRPU> rrpulist = Lists.newArrayList();
		PU currentP = p1;
		final long t11 = System.currentTimeMillis();
		RRPU currentRRPU = RR.pp(currentP, ms);
		final long t22 = System.currentTimeMillis();
		currentP.remove(currentRRPU.getPx());
		currentP.setPrPX(currentRRPU.getPx());
		System.out.println(
				"###########P" + "\t" + "ms:" + (t22 - t11) + "\t" + currentP.getId() + "\tP " + currentRRPU.getPx());
		rrpulist.add(currentRRPU);
		while (!currentP.w()) {
			currentP = currentP.getNext();
			if (rrpulist.size() >= RR.PS
					&& Objects.isNull(currentP.getPrevious().getPrPX())
					&& Objects.isNull(currentP.getPrevious().getPrevious().getPrPX())) {
				final long t1 = System.currentTimeMillis();
				currentRRPU = RR.pp(currentP, ms);
				final long t2 = System.currentTimeMillis();
				System.out.println("###########P" + "\t" + "ms:" + (t2 - t1) + "\t" + currentP.getId() + "\tP "
						+ currentRRPU.getPx());
			} else {
				final PX gPX = currentRRPU.getPx() == null ? getnotnull(rrpulist) : currentRRPU.getPx();
				final long t1 = System.currentTimeMillis();
				currentRRPU = RR.gg(currentP, gPX, ms);
				final long t2 = System.currentTimeMillis();
				System.out.println("###########G" + "\t" + "ms:" + (t2 - t1) + "\t" + currentP.getId() + "\tG "
						+ currentRRPU.getPx());
			}
			if (currentP.w()) {
				break;
			}
			currentP.remove(currentRRPU.getPx());
			currentP.setPrPX(currentRRPU.getPx());
			rrpulist.add(currentRRPU);
		}

		System.out.println("@@@W=" + currentP.getId());
		return rrpulist;
	}

	PX getnotnull(final List<RRPU> rrpulist) {
		final int size = rrpulist.size();
		for (int i = size; i-- > 0;) {
			if (rrpulist.get(i).getPx() != null) {
				return rrpulist.get(i).getPx();
			}
		}
		return null;
	}

	@Test
	public void testBBUgenfangczou1(){
		final int[] g = CU.g();
		final List<PU> puList = UU.wj(g);
		puList.get(0).setCArray(new int[] { 41,51 });
		puList.get(1).setCArray(new int[] { 31, 41, 42, 121, 122, 123 });
		puList.get(2).setCArray(new int[] { 31 });
		for (final PU pu : puList) {
			System.out.println(pu);
		}

		final PU p1 = puList.parallelStream().filter(p -> p.getId() == 1).findAny().get();
		final PX p1Px = new PX(PXE.Dan, new int[]{31});
		p1.setPrPX(p1Px);
		System.out.println("----------p1-----------");
		System.out.println(p1);

		final PU p2 = p1.getNext();
		final int ms = 1000 * 2;
		final RRPU gg = RR.gg(p2, p1Px, ms);
		System.out.println("gg=" + gg);
	}
	@Test
	public void testbuyaofangqi1(){
		final int[] g = CU.g();
		final List<PU> puList = UU.wj(g);
		puList.get(0).setCArray(new int[] { 112, 153, 154 });
		puList.get(1).setCArray(new int[] { 81, 93, 122, 124, 141, 143 });
		puList.get(2).setCArray(new int[] { 31 });
		for (final PU pu : puList) {
			System.out.println(pu);
		}

		final PU p1 = puList.parallelStream().filter(p -> p.getId() == 1).findAny().get();
		final PX p1Px = new PX(PXE.Dan, new int[] { 72 });
		p1.setPrPX(p1Px);
		System.out.println("----------p1-----------");
		System.out.println(p1);

		final PU p2 = p1.getNext();
		final int ms = 1000 * 2;
		final RRPU gg = RR.gg(p2, p1Px, ms);
		System.out.println("gg=" + gg);
	}

}

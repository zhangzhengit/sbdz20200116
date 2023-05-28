package com.example.demo.p;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

/**
 * 计算出牌
 *
 * @author zhangzhen
 * @date 2020年2月6日
 *
 */
// TODO 两个电脑可以作为一个 手里有两组牌的一个玩家，这样模拟的时候就只有两个玩家，
// 并且AB两个电脑，模拟A以后就不用模拟B了，B怎么出直接在A的结果中取
// TODO 还没加入倍数的概念，电脑必输时尽量不要打出炸弹；电脑必胜时尽量打出炸弹；
public class RR {

	/**
	 * 表示NULL 牌型
	 */
	public static final PX NULL_PX = null;

	/**
	 * 玩家个数
	 */
	public static final int PS = 3;

	/**
	 * 所有牌型枚举
	 */
	private static final PXE[] PXE_ARRAY = PXE.values();

	private static final Random RANDOM = ThreadLocalRandom.current();

	/**
	 * 一轮中模拟的局数
	 */
	public static final int RN1 = 100;

	/**
	 * 模拟的最大轮数
	 */
	private static final int MAXJ = 10000 * 5000;


	public static RRPU gg(final PU pu, final PX ppx, final int ms) {
		final List<RRPU> rrpulist = gg_p(pu, ppx, ms);

//		rrpulist.sort(Comparator.comparing(RRPU::getWR));
//		System.out.println("===============rrpulist===============");
//		System.out.println("rrpulist.size=" + rrpulist.size());
//		for (final RRPU rrpu : rrpulist) {
//			System.out.println(rrpu.getId() + "\t" + rrpu.getUre()
//							+ "\t" + rrpu.getGc()
//							+ "\t" + rrpu.getWc()
//							+ "\t" + rrpu.wr()
//							+ "\t" + Arrays.toString(rrpu.getPx().getCArray())
//			);
//		}
//		System.out.println("rrpulist.size=" + rrpulist.size());

		final RRPU maxarRRPU = maxGGwrpx(pu, rrpulist);
		return maxarRRPU;
	}

	/**
	 * 计算跟牌
	 *
	 * @param pu
	 *            轮到跟牌的玩家
	 * @param ppx
	 *            上家出的牌
	 * @param ms
	 *            超时毫秒数
	 * @return
	 */
	public static List<RRPU> gg_p(final PU pu, final PX ppx, final int ms) {
		final YSYDQ gg_yishou = gg_yishou(pu, ppx);
		// 如果能一手出尽
		if (Objects.nonNull(gg_yishou.getYishou())) {
			final ArrayList<RRPU> newArrayListWithCapacity = Lists.newArrayListWithCapacity(1);
			newArrayListWithCapacity.add(gg_yishou.getYishou());
			return newArrayListWithCapacity;
		}
		final List<RRPU> rrpulist = gg_rrpulist_bingxing(pu, ms, gg_yishou);
		return rrpulist;
	}

	private static List<RRPU> gg_rrpulist_bingxing(final PU pu, final int ms, final YSYDQ gg_yishou) {
		final List<PX> yDQLIST = gg_yishou.getYdqlist();

		final List<RRPU> rrpulist = gg_list(pu, ms, yDQLIST);
		return rrpulist;
	}

	/**
	 * 能否一手出尽
	 *
	 * @param pu
	 * @param ppx
	 * @return
	 */
	private static YSYDQ gg_yishou(final PU pu, final PX ppx) {
		final PXE pxe = ppx.getPxe();
		if (pxe == PXE.Wangzha) {
			return YSYDQ.builder().yishou(new RRPU(pu.getId(), pu.getUre(), null, 0, 0)).build();
		}
		if (pxe == PXE.Zhadan) {
			final int[] pucarray = pu.getCArray();
			final int length = pucarray.length;
			if (length < 4 || pucarray[length - 1] != 170) {
				return YSYDQ.builder().yishou(new RRPU(pu.getId(), pu.getUre(), null, 0, 0)).build();
			}
		}
		final int[] pucarray = pu.getCArray();
		final int pucalength = pucarray.length;
		if (pucalength < ppx.getCArray().length && pucalength < 4) {
			return YSYDQ.builder().yishou(new RRPU(pu.getId(), pu.getUre(), null, 0, 0)).build();
		}

		final PXE[] pxA = { ppx.getPxe(), PXE.Zhadan, PXE.Wangzha };
		List<PX> g1list = G.g(pu.getCArray(), pxA);
		if (PXE.geshuxiangtong.contains(ppx.getPxe())) {
			g1list = g1list.parallelStream().filter(px -> px.size() == ppx.size()).collect(Collectors.toList());
		}

		final BitSet bitSet = new BitSet();
		final int dp = Arrays.binarySearch(D_ARRAY, ppx.getPxe().getIndex());
		if (dp > -1) {
			final int ppxsize = ppx.size();
			for (int i = 0; i < g1list.size(); i++) {
				final PX p1g = g1list.get(i);
				if (p1g.getPxe() == ppx.getPxe() && p1g.size() != ppxsize) {
					bitSet.set(i);
				}
			}
		}
		if (bitSet.length() >= g1list.size()) {
			return YSYDQ.builder().yishou(new RRPU(pu.getId(), pu.getUre(), null, 0, 0)).build();
		}
		final int ppxscore = ppx.score();
		for (int i = 0; i < g1list.size(); i++) {
			final PX p1g = g1list.get(i);
			if (p1g.score() <= ppxscore) {
				bitSet.set(i);
			}
		}
		if (bitSet.length() >= g1list.size()) {
			return YSYDQ.builder().yishou(new RRPU(pu.getId(), pu.getUre(), null, 0, 0)).build();
		}

		if (g1list.size() == 1 && g1list.get(0).getCArray().length == pucalength) {
			return YSYDQ.builder().yishou(new RRPU(pu.getId(), pu.getUre(), g1list.get(0), 1, 1)).build();
		}

		//
		final List<PX> yDQLIST = Lists.newArrayList();
		for (int i = 0; i < g1list.size(); i++) {
			if (!bitSet.get(i)) {
				yDQLIST.add(g1list.get(i));
			}
		}
		yDQLIST.add(null);
		return YSYDQ.builder().ydqlist(yDQLIST).build();
	}

	private static List<RRPU> gg_list(final PU pu, final int ms, final List<PX> yDQLIST) {
		List<RRPU> gaipaolist = yDQLIST.parallelStream().map(px -> new RRPU(pu.getId(), pu.getUre(), px, 0,0)).collect(Collectors.toList());

		final List<RPU> result = Lists.newArrayList();
		final long s = System.currentTimeMillis();
		for (int i = 1; i <= MAXJ; i++) {
			if (o(s, ms)) {
				break;
			}
			if (gaipaolist.isEmpty()) {
				break;
			}
			final List<PX> crpxlist = gaipaolist.parallelStream()
					.map(RRPU::getPx).collect(Collectors.toList());
			final List<RPU> ydlist = crpxlist.parallelStream()
					.flatMap(px -> gg0(pu, px).stream())
					.collect(Collectors.toList());
			result.addAll(ydlist);

			final List<RRPU> rrpulist = getrrpulist(pu, ydlist, PGE.G);
			gaipaolist = ET.builder().rrpulist(rrpulist).build().get();
			if (gaipaolist.size() == 1) {
				break;
			}
		}
		final List<RRPU> rrpulist = getrrpulist(pu, result, PGE.G);
		return rrpulist;
	}

	private static List<RPU> gg0(final PU pu, final PX px) {
		final List<RPU> list = Lists.newArrayListWithCapacity(RN1);
		for (int i = 1; i <= RN1; i++) {
			list.add(run(pu, px, PGE.G));
		}
		return list;
	}

	/**
	 * 模拟一局主动出牌/跟牌的情况，可以出现各种情况，只要是符合游戏规则的就可以；
	 * 如：开始出炸弹、拆炸弹出、压小牌用了炸弹等等。
	 *
	 * @param p1
	 *            主动出牌/跟牌 的玩家
	 * @param fPX
	 *            p1主动出的牌/跟的牌
	 * @param pge
	 *            动作枚举
	 * @return
	 */
	public static RPU run(final PU p1, final PX fPX, final PGE pge) {
		int s = pge == PGE.P ? PGE.P.getV()
				: (Objects.nonNull(p1.getPrevious().getPrevious().getPrPX()) ? 3 : 2);
		final PU cp1 = copypu(p1);
		cp1.setPrPX(fPX);
		cp1.remove(fPX);

		PU currentP = cp1;
		PX currentPX = fPX;
		while (!currentP.w()) {
			currentP = currentP.getNext();
			if (s >= PS && Objects.isNull(currentP.getPrevious().getPrPX())
					   && Objects.isNull(currentP.getPrevious().getPrevious().getPrPX())) {
				currentPX = RR.p(currentP);
			} else {
				currentPX = RR.g(currentP, currentPX);
			}
			s++;
		}
		// 本局结果
		final RPU rpu = new RPU(p1, fPX, currentP, currentPX, s);
		return rpu;
	}

	public static PU copypu(final PU p1) {

		final PU cp1 = new PU(p1.getId(), p1.getName(), p1.getUre(),
				Arrays.copyOf(p1.getCArray(), p1.getCArray().length), null, null, p1.getPrPX());

		final PU p1n = p1.getNext();
		final PU cp2 = new PU(p1n.getId(), p1n.getName(), p1n.getUre(),
				Arrays.copyOf(p1n.getCArray(), p1n.getCArray().length), null, null, p1n.getPrPX());

		final PU p1p = p1.getPrevious();
		final PU cp3 = new PU(p1p.getId(), p1p.getName(), p1p.getUre(),
				Arrays.copyOf(p1p.getCArray(), p1p.getCArray().length), null, null, p1p.getPrPX());

		cp1.setNext(cp2);
		cp1.setPrevious(cp3);

		cp2.setNext(cp3);
		cp2.setPrevious(cp1);

		cp3.setNext(cp1);
		cp3.setPrevious(cp2);

		return cp1;
	}

	/**
	 * 模拟一局中的跟牌，要可以要得起上家的牌的牌型才可以，也可以不出
	 *
	 * @param p
	 * @param ppx
	 * @return
	 */
	public static PX g(final PU p, final PX ppx) {
		final PX rppx = ppx == null ? p.getPrevious().getPrevious().getPrPX() : ppx;
		if (rppx.getPxe() == PXE.Wangzha) {
			p.setPrPX(null);
			return null;
		}
		final PX g0 = g0(p, rppx);
		p.setPrPX(g0);
		p.remove(g0);
		return g0;
	}

	/**
	 * 跟牌的一步
	 *
	 * @param p
	 *            跟牌玩家
	 * @param ppx
	 *            上家牌
	 * @return
	 */
	private static PX g0(final PU p, final PX ppx) {
		final PXE[] pxA = { ppx.getPxe(), PXE.Zhadan, PXE.Wangzha };
		final List<PX> g1list = G.g(p.getCArray(), pxA);
		if (g1list.isEmpty()) {
			return null;
		}
		// 2 remove
		final BitSet bitSet = new BitSet();
		final int dp = Arrays.binarySearch(D_ARRAY, ppx.getPxe().getIndex());
		if (dp > -1) {
			final int ppxsize = ppx.size();
			for (int i = 0; i < g1list.size(); i++) {
				final PX p1g = g1list.get(i);
				if (p1g.size() != ppxsize) {
					bitSet.set(i);
				}
			}
		}
		if (bitSet.length() >= g1list.size()) {
			return null;
		}
		final int ppxscore = ppx.score();
		for (int i = 0; i < g1list.size(); i++) {
			final PX p1g = g1list.get(i);
			if (p1g.score() <= ppxscore) {
				bitSet.set(i);
			}
		}
		if (bitSet.length() >= g1list.size()) {
			return null;
		}

		if (g1list.size() == 1 && g1list.get(0).getCArray().length == p.getCArray().length) {
			return g1list.get(0);
		}

		final Optional<PX> findAny = g1list.parallelStream()
				.filter(px -> px.getCArray().length == p.getCArray().length).findAny();

		if (findAny.isPresent()) {
			return findAny.get();
		}

		if (p.getPrevious().getUre() == p.getUre()) {
			g1list.add(null);
		} else {
			final double p0 = 1 - (1.0 / g1list.size());
			if (RANDOM.nextDouble() <= p0) {
				g1list.add(null);
			}
		}
		final int g1size2 = g1list.size();
		int g1index = -1;
		do {
			g1index = RANDOM.nextInt(g1size2);
		} while (bitSet.get(g1index));

		return g1list.get(g1index);
	}

	/**
	 * 模拟一局中的主动出牌的一步；轮到主动出牌了，可以出任何牌型，只要符合牌型规则就可以
	 *
	 * @param p
	 * @return 返回在所有牌型中随机选的一个牌型，如果能一手出尽就是所有的牌
	 */
	public static PX p(final PU p) {
		final int[] pucA = p.getCArray();
		if (pucA.length == 1) {
			final PX px = new PX(PXE.Dan, pucA);
			p.remove(px);
			return px;
		}

		// 能否一手出尽
		final ImmutableList<PXE> vl = PXE.VL;
		for (final PXE pxe : vl) {
			final List<PX> g = G.g(pucA, pxe);
			final Optional<PX> findAny = g.parallelStream().filter(px -> px.getCArray().length == pucA.length).findAny();
			if (findAny.isPresent()) {
				final PX get = findAny.get();
				p.setPrPX(get);
				p.remove(get);
				return get;
			}
		}

		List<PX> g = G.el();
		do {
			g = G.g(p.getCArray(), PXE_ARRAY[RANDOM.nextInt(PXE_ARRAY.length)]);
		} while (g.isEmpty());
		final PX pc = g.get(RANDOM.nextInt(g.size()));
		p.setPrPX(pc);
		p.remove(pc);
		return pc;
	}


	public static RRPU pp(final PU pu, final int maxms) {
		final List<RRPU> rrpulist = pp_p(pu, maxms);
		final RRPU maxPPwrpx = maxPPwrpx(pu, rrpulist);
		return maxPPwrpx;
	}

	/**
	 * 计算主动出牌，玩家最好出哪个牌型
	 *
	 * @param pu
	 *            轮到主动出牌的玩家
	 * @param maxms
	 *            超时毫秒数
	 * @return
	 */
	public static List<RRPU> pp_p(final PU pu, final int maxms) {
		final RRPU pp_yishou = pp_yishou(pu);
		// 如果能一手出尽
		if (Objects.nonNull(pp_yishou)) {
			final ArrayList<RRPU> newArrayListWithCapacity = Lists.newArrayListWithCapacity(1);
			newArrayListWithCapacity.add(pp_yishou);
			return newArrayListWithCapacity;
		}

		final List<RRPU> rrpulist = pp_rrpulist_bingxing(pu, maxms);
		return rrpulist;
	}

	public static RRPU maxPPwrpx(final PU pu, final List<RRPU> rrpulist) {
		if (CollectionUtils.isEmpty(rrpulist)) {
			return new RRPU(pu.getId(), pu.getUre(), null, 0, 0);
		}

		final RRPU maxwrpxrrpu = maxwrpxrrpu(rrpulist);
		return maxwrpxrrpu;
	}

	public static RRPU maxGGwrpx(final PU pu, final List<RRPU> rrpulist) {
		if (CollectionUtils.isEmpty(rrpulist)) {
			return new RRPU(pu.getId(), pu.getUre(), null, 0, 0);
		}
		final RRPU maxwrRRPU = maxwrpxrrpu(rrpulist);
		return maxwrRRPU;
	}

	private static RRPU maxwrpxrrpu(final List<RRPU> rrpulist) {
		final RRPU maxwrRRPU = rrpulist.parallelStream().max(Comparator.comparing(RRPU::getWR)).get();
		final double maxggwr = maxwrRRPU.getWR();
		if (maxggwr <= 0) {
			final Optional<RRPU> zerowrmaxlpxO = rrpulist.parallelStream()
					.filter(rrpu -> Objects.nonNull(rrpu.getPx()))
					.max(Comparator.comparing(rrpu -> rrpu.getPx().size()));
			if (zerowrmaxlpxO.isPresent()) {
				return zerowrmaxlpxO.get();
			}
		} else if (maxggwr >= 1) {
			final Optional<RRPU> zerowrmaxlpxO = rrpulist.parallelStream()
					.filter(rrpu -> Objects.nonNull(rrpu.getPx()))
					.filter(rrpu -> rrpu.getWR() >= 1)
					.max(Comparator.comparing(rrpu -> rrpu.getPx().size()));
			return zerowrmaxlpxO.get();
		}
		return maxwrRRPU;
	}

	/**
	 * 模拟主动出牌
	 *
	 * @param pu
	 *            主动出牌的玩家
	 * @param maxms
	 *            超时毫秒数
	 * @return
	 */
	private static List<RRPU> pp_rrpulist_bingxing(final PU pu, final int maxms) {
		// 玩家所有牌型
		final List<PX> pxlist = G.g(pu.getCArray());
		final long s = System.currentTimeMillis();
		final List<RPU> result = Lists.newArrayList();
		// 当前还应该继续模拟的牌型，随着轮数越来越多，gaipaolist.size会越来越小；每一轮以后每个牌型的胜率变化后，取胜率高的丢掉胜率低的
		List<RRPU> gaipaolist = pxlist.parallelStream()
					.map(px -> new RRPU(pu.getId(), pu.getUre(), px, 0, 0))
					.collect(Collectors.toList());
		for (int i = 1; i <= MAXJ; i++) {
			// 超时
			if (o(s, maxms)) {
				break;
			}

			// 没有需要继续模拟的
			if (gaipaolist.isEmpty()) {
				break;
			}

			// 还需要继续模拟的只有一个牌型了（胜率最高的一个牌型）
			if (gaipaolist.size() == 1) {
				break;
			}

			final List<PX> crpxlist = gaipaolist.parallelStream().map(RRPU::getPx).collect(Collectors.toList());
			final List<RPU> te1result = crpxlist
					.parallelStream()
					.flatMap(px -> pp0(pu, px).stream())
					.collect(Collectors.toList());
			result.addAll(te1result);

			final List<RRPU> rrpulist = getrrpulist(pu, te1result, PGE.P);
			gaipaolist = ET.builder().rrpulist(rrpulist).build().get();
			if (gaipaolist.isEmpty()) {
				break;
			}
			if (gaipaolist.size() == 1 && gaipaolist.get(0).getPx().getCArray().length == pu.getCArray().length) {
				break;
			}
			final Optional<RRPU> maxwrO = gaipaolist.parallelStream().max(Comparator.comparing(RRPU::getWR));
			if (maxwrO.get().getWR() >= 1) {
				final long maxwrc = gaipaolist.parallelStream().filter(r -> r.getWR() >= maxwrO.get().getWR()).count();
				if (maxwrc > 2) {
					break;
				}
			}
		}

		final List<RRPU> rrpulist = getrrpulist(pu, result, PGE.P);
		return rrpulist;
	}

	/**
	 * 判定玩家的牌能否一手出尽
	 *
	 * @param pu
	 * @return
	 */
	public static RRPU pp_yishou(final PU pu) {
		// 就一张了，一次出掉
		if (pu.getCArray().length == 1) {
			final PX d = new PX(PXE.Dan, pu.getCArray());
			pu.remove(d);
			return new RRPU(pu.getId(), pu.getUre(), d, 1, 1);
		}

		// 看玩家全部的牌能否组成一个牌型
		final ImmutableList<PXE> vl = PXE.VL;
		for (final PXE pxe : vl) {
			final List<PX> g = G.g(pu.getCArray(), pxe);
			final Optional<PX> findAny = g.parallelStream().filter(px -> px.getCArray().length == pu.getCArray().length).findAny();
			if (findAny.isPresent()) {
				final PX get = findAny.get();
				pu.setPrPX(get);
				pu.remove(get);
				return new RRPU(pu.getId(), pu.getUre(), get, 1, 1);
			}
		}
		return null;
	}

	/**
	 * 计算模拟出的所有牌局中，每个牌型的胜率
	 *
	 * @param pu
	 * @param result
	 * @param pge
	 * @return
	 */
	private static List<RRPU> getrrpulist(final PU pu, final List<RPU> result, final PGE pge) {
		final URE puure = pu.getUre();
		Map<PX, List<RPU>> map = null;
		if (pge == PGE.P) {
			map = result.parallelStream().collect(Collectors.groupingBy(rpu -> rpu.getFpx(), Collectors.toList()));
		} else {
			map = result.parallelStream()
					.filter(rpu -> Objects.nonNull(rpu.getFpx()))
					.collect(Collectors.groupingBy(rpu -> rpu.getFpx(), Collectors.toList()));

			final List<RPU> bugenlist = result.parallelStream().filter(rpu -> Objects.isNull(rpu.getFpx()))
					.collect(Collectors.toList());

			map.put(NULL_PX, bugenlist);

		}
		final Set<Entry<PX, List<RPU>>> set = map.entrySet();
		final List<RRPU> gaipaorrpulist = set.parallelStream().map(e -> {
			final PX px1 = e.getKey();
			final List<RPU> vs = e.getValue();
			final long wc = vs.parallelStream().filter(rpu1 -> rpu1.getWpu().getUre() == puure).count();
			return new RRPU(pu.getId(), puure, px1, vs.size(), wc);
		}).collect(Collectors.toList());
		return gaipaorrpulist;
	}

	/**
	 * 模拟一轮
	 *
	 * @param pu
	 * @param px
	 * @return
	 */
	private static List<RPU> pp0(final PU pu, final PX px) {
		final List<RPU> list = Lists.newArrayListWithCapacity(RN1);
		for (int i = 1; i <= RN1; i++) {
			list.add(run(pu, px, PGE.P));
		}
		return list;
	}



	public static final int[] D_ARRAY = {
			PXE.Sandaiyi.getIndex(),
			PXE.Sandaier.getIndex(), PXE.Sidaier.getIndex(),
			PXE.Feijichibang.getIndex(), PXE.Shunzi.getIndex() };


	public final static ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(
					Runtime.getRuntime().availableProcessors(),
					Runtime.getRuntime().availableProcessors(), 60L, TimeUnit.SECONDS,
					new LinkedBlockingQueue<Runnable>(), Executors.defaultThreadFactory());

	public static boolean o(final long start, final int maxms) {
		final long currentTimeMillis = System.currentTimeMillis();
		return currentTimeMillis - start >= maxms;
	}

	public static List<RRPU> rrrrrrrrrrrr(final PU p1, final int ms) {
		final List<RRPU> rrpulist = Lists.newArrayList();
		PU currentP = p1;
		RRPU currentRRPU = RR.pp(currentP, ms);
		currentP.remove(currentRRPU.getPx());
		currentP.setPrPX(currentRRPU.getPx());
		rrpulist.add(currentRRPU);
		while (!currentP.w()) {
			currentP = currentP.getNext();
			if (rrpulist.size() >= RR.PS
					&& currentP.getPrevious().getPrPX() == null
					&& currentP.getPrevious().getPrevious().getPrPX() == null) {
				currentRRPU = RR.pp(currentP, ms);
			} else {
				final PX gPX = currentRRPU.getPx() == null ? getnotnull(rrpulist) : currentRRPU.getPx();
				currentRRPU = RR.gg(currentP, gPX, ms);
			}
			if (currentP.w()) {
				break;
			}
			currentP.remove(currentRRPU.getPx());
			currentP.setPrPX(currentRRPU.getPx());
			rrpulist.add(currentRRPU);
		}

		return rrpulist;
	}


	public static PX getnotnull(final List<RRPU> rrpulist) {
		final int size = rrpulist.size();
		for (int i = size; i-- > 0;) {
			if (rrpulist.get(i).getPx() != null) {
				return rrpulist.get(i).getPx();
			}
		}
		return null;
	}


}


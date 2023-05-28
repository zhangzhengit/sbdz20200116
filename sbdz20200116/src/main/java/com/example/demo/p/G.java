package com.example.demo.p;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 *
 * 生成牌型，每个牌型都尽量少生成，如：333，三张牌，总共只生成三个牌型[单牌3] [对子33] [三张333]
 *
 * @author zhangzhen
 * @date 2020年2月6日
 *
 */
@SuppressWarnings("boxing")
public class G {

	private static final int FT = 6;

	private static final int SHZTT = 5;

	/**
	 * 生成所有牌型
	 *
	 * @param array
	 * @return
	 */
	public static List<PX> g(final int[] array) {
		return g(array, PXE.values());
	}

	/**
	 * 生成特定的几个牌型
	 *
	 * @param array
	 * @param pxe
	 * @return
	 */
	public static List<PX> g(final int[] array, final PXE... pxe) {
		final int[] a1c = Arrays.copyOf(array, array.length);
		final List<PX> rlist = Lists.newArrayList();
		for (final PXE e : pxe) {
			switch (e) {
			case Dan:
				rlist.addAll(dan(a1c));
				break;

			case Duzi:
				rlist.addAll(duizi(a1c));
				break;

			case Sanzhang:
				rlist.addAll(sanzhang(a1c));
				break;

			case Zhadan:
				rlist.addAll(zhadan(a1c));
				break;

			case Feiji:
				rlist.addAll(feiji(a1c));
				break;

			case Sandaiyi:
				rlist.addAll(sandaiyi(a1c));
				break;

			case Sandaier:
				rlist.addAll(sandaier(a1c));
				break;

			case Liandui:
				rlist.addAll(liandui(a1c));
				break;

			case Sidaier:
				rlist.addAll(sidaier(a1c));
				break;

			case Feijichibang:
				rlist.addAll(feijichibang(a1c));
				break;

			case Wangzha:
				rlist.addAll(wangzha(a1c));
				break;

			case Shunzi:
				rlist.addAll(shunzi(a1c));
				break;

			default:

				break;
			}

		}
		return rlist;

	}

	/**
	 * 生成顺子
	 *
	 * @param array
	 * @return
	 */
	public static List<PX> shunzi(final int[] array) {
		if (array.length < SHZTT) {
			return el();
		}
		int k = 0;
		for (int i = array.length - 1; i > 0; i--) {
			final int j = array[i];
			if (j == 160 || j == 170 || j / 10 == 15) {
				k++;
			}
		}
		if (array.length - k < SHZTT) {
			return el();
		}
		final int[] ncArray = Arrays.copyOfRange(array, 0, array.length - k);
		final List<PX> list = Lists.newArrayList();
		final List<Integer> oList = Lists.newArrayList(ncArray[0]);
		for (int i = 1; i < ncArray.length; i++) {
			final Integer last = oList.get(oList.size() - 1);
			final int currentV = ncArray[i];
			final int juli = (currentV / 10) - ((last / 10) + 1);
			if (juli == 0) {
				oList.add(currentV);
			} else if (juli > 0) {
				if (oList.size() >= SHZTT) {
					list.addAll(getshz(oList));
				}
				oList.clear();
				oList.add(currentV);
			}
		}
		list.addAll(getshz(oList));
		return list;
	}

	private static List<PX> getshz(final List<Integer> oList) {
		final List<PX> pxlist = Lists.newArrayList();
		for (int s = 0; s <= oList.size() - 5; s++) {
			for (int e = s + 5; e <= oList.size(); e++) {
				final int[] pa = new int[e - s];
				int pai = 0;
				for (int i = s; i < e; i++) {
					pa[pai++] = oList.get(i);
				}
				final PX px = new PX(PXE.Shunzi, pa);
				pxlist.add(px);
			}
		}
		return pxlist;
	}

	/**
	 * 生成飞机
	 *
	 * @param array
	 * @return
	 */
	public static List<PX> feiji(final int[] array) {
		return feiji(array, 15);

	}

	public static List<PX> feiji(final int[] array, final int size) {
		if (array.length < FT) {
			return el();
		}

		int k = 0;
		for (int i = array.length - 1; i > 0; i--) {
			final int j = array[i];
			if (j == 160 || j == 170 || j / 10 == 15) {
				k++;
			}
		}
		if (array.length - k < FT) {
			return el();
		}
		final int[] nc = Arrays.copyOfRange(array, 0, array.length - k);
		final List<PX> szlist = sanzhang(nc);
		if (szlist.size() <= 1) {
			return el();
		}
		final List<PX> list = Lists.newArrayListWithCapacity(size);
		for (int i = 0; i < szlist.size(); i++) {
			int j = i + 1;
			while (j < szlist.size()) {
				if ((szlist.get(i).getCArray()[0] / 10) + (j - i) == szlist.get(j).getCArray()[0] / 10) {
					j++;
					final int[] o = new int[(j - i) * 3];
					int oi = 0;
					for (int c = i; c < j; c++) {
						final PX p = szlist.get(c);
						final int[] pxA = p.getCArray();
						o[oi++] = pxA[0];
						o[oi++] = pxA[1];
						o[oi++] = pxA[2];
					}
					list.add(new PX(PXE.Feiji, o));
				} else {
					break;
				}
			}
		}

		return list;
	}

	/**
	 * 生成炸弹
	 *
	 * @param array
	 * @return
	 */
	public static List<PX> zhadan(final int[] array) {
		return zhadan(array, array.length / 4);
	}

	public static List<PX> zhadan(final int[] array, final int size) {
		if (array.length < 4) {
			return el();
		}
		final List<PX> list = Lists.newArrayListWithCapacity(size);
		final int length = array.length - 2;
		for (int i = 1; i < length && list.size() < size; i += 1) {
			final int first = array[i - 1];
			final int firstY = first / 10;
			if (firstY == array[i] / 10 && firstY == array[i + 1] / 10 && firstY == array[i + 2] / 10) {
				list.add(new PX(PXE.Zhadan, new int[] { first, array[i], array[i + 1], array[i + 2] }));
			}
		}
		return list;
	}

	/**
	 * 生成三张
	 *
	 * @param array
	 * @return
	 */
	public static List<PX> sanzhang(final int[] array) {
		return sanzhang(array, array.length / 3);
	}

	public static List<PX> sanzhang(final int[] array, final int size) {
		if (array.length < 3) {
			return el();
		}
		final List<PX> list = Lists.newArrayListWithCapacity(size);
		final int length = array.length - 1;
		for (int i = 1; i < length && list.size() < size; i++) {
			final int first = array[i - 1];
			final int firstY = first / 10;
			if (array[i] / 10 == firstY && array[i + 1] / 10 == firstY) {
				list.add(new PX(PXE.Sanzhang, new int[] { first, array[i], array[i + 1] }));
				while (i < length && array[i++] / 10 == firstY) {
				}
				i--;
			}
		}
		return list;
	}

	/**
	 * 生成对子
	 *
	 * @param array
	 * @return
	 */
	public static List<PX> duizi(final int[] array) {
		return duizi(array, array.length / 2);
	}

	public static List<PX> duizi(final int[] array, final int size) {
		if (array.length < 2) {
			return el();
		}
		final List<PX> list = Lists.newArrayListWithCapacity(size);
		final int length = array.length;
		for (int i = 1; list.size() < size && i < length; i++) {
			final int first = array[i - 1];
			final int firstY = first / 10;
			if (array[i] / 10 == firstY) {
				list.add(new PX(PXE.Duzi, new int[] { first, array[i] }));
				int n = i;
				while (n < length - 1 && array[++n] / 10 == firstY) {
				}
				i = n;
			}
		}
		return list;
	}

	/**
	 * 生成单张
	 *
	 * @param array
	 * @return
	 */
	public static List<PX> dan(final int[] array) {
		if (array.length == 1) {
			return Lists.newArrayList(new PX(PXE.Dan, array));
		}
		final List<PX> list = Lists.newArrayList();
		final BitSet bitSet = new BitSet();
		final int length = array.length;
		for (int i = 0; i < length; i++) {
			final int k = array[i];
			if (!bitSet.get(k / 10)) {
				list.add(new PX(PXE.Dan, new int[] { k }));
				bitSet.set(k / 10);
			}
		}
		return list;
	}

	public static List<PX> el() {
		return Collections.emptyList();
	}

	/**
	 * 三带一
	 *
	 * @param array
	 * @return
	 */
	public static List<PX> sandaiyi(final int[] array) {
		return sandaiyi(array, 45);
	}

	public static List<PX> sandaiyi(final int[] array, final int size) {
		if (array.length < 4) {
			return el();
		}
		final List<PX> szlist = sanzhang(array);
		if (szlist.isEmpty()) {
			return el();
		}
		final List<PX> list = Lists.newArrayListWithCapacity(size);
		for (final PX px : szlist) {
			for (final int i : array) {
				final int j = i / 10;
				if (j != px.getCArray()[0] / 10) {
					if (list.isEmpty() || (j != list.get(list.size() - 1).getCArray()[3] / 10)) {
						final int[] r1 = { px.getCArray()[0], px.getCArray()[1], px.getCArray()[2], i };
						list.add(new PX(PXE.Sandaiyi, r1));
					}
				}
			}
		}

		return list;
	}

	/**
	 * 三带对子
	 *
	 * @param array
	 * @return
	 */
	public static List<PX> sandaier(final int[] array) {
		return sandaier(array, 36);
	}

	public static List<PX> sandaier(final int[] array, final int size) {
		if (array.length < 5) {
			return el();
		}
		final List<PX> szlist = sanzhang(array);
		if (szlist.isEmpty()) {
			return el();
		}
		final List<PX> dzlist = duizi(array);
		final List<PX> list = Lists.newArrayListWithCapacity(size);
		for (final PX px : szlist) {
			for (final PX px2 : dzlist) {
				if (px.getCArray()[0] / 10 == px2.getCArray()[0] / 10) {
					continue;
				}
				final int[] a5 = { px.getCArray()[0], px.getCArray()[1], px.getCArray()[2], px2.getCArray()[0],
						px2.getCArray()[1] };
				list.add(new PX(PXE.Sandaier, a5));
			}
		}

		return list;
	}

	/**
	 * 连对
	 *
	 * @param array
	 * @return
	 */
	public static List<PX> liandui(final int[] array) {
		return liandui(array, 36);
	}

	public static List<PX> liandui(final int[] array, final int size) {
		if (array.length < 6) {
			return el();
		}
		// qu2w
		int k = 0;
		for (int i = array.length - 1; i > 0; i--) {
			final int j = array[i];
			if (j == 160 || j == 170 || j / 10 == 15) {
				k++;
			}
		}
		if (array.length - k < 6) {
			return el();
		}
		final int[] nc = Arrays.copyOfRange(array, 0, array.length - k);
		final List<PX> dzlist = duizi(nc);
		if (dzlist.size() < 3) {
			return el();
		}
		final List<PX> list = Lists.newArrayListWithCapacity(size);
		for (int s = 0; s <= dzlist.size() - 3; s++) {
			for (int e = s + 3; e <= dzlist.size(); e++) {
				if (lianxu(dzlist, s, e)) {
					final int[] rA = new int[(e - s) * 2];
					int r0 = 0;
					for (int i = s; i < e; i++) {
						final PX p = dzlist.get(i);
						rA[r0++] = p.getCArray()[0];
						rA[r0++] = p.getCArray()[1];
					}
					list.add(new PX(PXE.Liandui, rA));
				}
			}
		}
		return list;
	}

	public static boolean lianxu(final List<PX> pxlist, final int start, final int end) {
		for (int i = start; i < end - 1; i++) {
			if ((pxlist.get(i).getCArray()[0] / 10) + 1 != pxlist.get(i + 1).getCArray()[0] / 10) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 四带对子
	 *
	 * @param array
	 * @return
	 */
	public static List<PX> sidaier(final int[] array) {
		return sidaier(array, 15);
	}

	public static List<PX> sidaier(final int[] array, final int size) {
		if (array.length < 6) {
			return el();
		}
		final List<PX> bmlist = zhadan(array);
		if (bmlist.isEmpty()) {
			return el();
		}

		final List<PX> list = Lists.newArrayListWithCapacity(size);
		final List<PX> dzlist = duizi(array);
		for (final PX b : bmlist) {
			for (final PX d : dzlist) {
				if (b.getCArray()[0] / 10 == d.getCArray()[0] / 10) {
					continue;
				}
				final int[] rA = { b.getCArray()[0], b.getCArray()[1], b.getCArray()[2], b.getCArray()[3],
						d.getCArray()[0], d.getCArray()[1] };
				list.add(new PX(PXE.Sidaier, rA));
			}
		}

		return list;
	}

	/**
	 * 飞机带翅膀
	 *
	 * @param array
	 * @return
	 */
	public static List<PX> feijichibang(final int[] array) {
		return feijichibang(array, 66);
	}

	// TODO 这个方法太慢
	public static List<PX> feijichibang(final int[] array, final int size) {
		if (array.length < 10) {
			return el();
		}
		final List<PX> fjList = feiji(array);
		if (fjList.isEmpty()) {
			return el();
		}
		final List<PX> list = Lists.newArrayListWithCapacity(size);
		final List<PX> dzlist = duizi(array);
		for (final PX f : fjList) {
			final int fs = f.getCArray().length / 3;
			final long t1 = System.currentTimeMillis();
			final List<Set<PX>> ndzlist = ndz(dzlist, fs);
			final long t2 = System.currentTimeMillis();
			// System.out.println("ndz-ms:" + (t2-t1));
			if (ndzlist.isEmpty()) {
				continue;
			}
			for (final Set<PX> dzset : ndzlist) {
				final List<Integer> pxdzlist = dzset.parallelStream().flatMap(px -> {
					final int[] cArray = px.getCArray();
					final ArrayList<Integer> ll = Lists.newArrayListWithCapacity(cArray.length);
					for (final int element : cArray) {
						ll.add(element);
					}
					return ll.parallelStream();
				}).collect(Collectors.toList());
				pxdzlist.sort(Comparator.naturalOrder());
				final int[] cArray = f.getCArray();
				boolean cf = false;
				for (final int i : cArray) {
					if (pxdzlist.contains(i)) {
						cf = true;
						break;
					}
				}
				if (!cf) {
					final int pxdzlistsize = pxdzlist.size();
					final int[] rA = new int[cArray.length + pxdzlistsize];
					int rai = 0;
					for (final int element : cArray) {
						rA[rai++] = element;
					}
					for (int ks = 0; ks < pxdzlistsize; ks++) {
						rA[rai++] = pxdzlist.get(ks);
					}
					final PX jPx = new PX(PXE.Feijichibang, rA);
					list.add(jPx);
					// System.out.println("fjc" + "\t" + Arrays.toString(cArray)
					// + "\t" + pxdzlist);
				}
			}
		}
		return list;
	}

	/**
	 * 王炸
	 *
	 * @param array
	 * @return
	 */
	public static List<PX> wangzha(final int[] array) {
		if (array.length < 2) {
			return el();
		}
		final List<PX> list = Lists.newArrayListWithCapacity(1);
		if (array[array.length - 1] == 170 && array[array.length - 2] == 160) {
			list.add(new PX(PXE.Wangzha, new int[] { array[array.length - 2], array[array.length - 1] }));
		}

		return list;
	}


	@SuppressWarnings("unchecked")
	private static List<Set<PX>> ndz(final List<PX> dzlist, final int n) {
		final List<PX>[] lA = new ArrayList[n];
		Arrays.fill(lA, dzlist);
		final List<List<PX>> dkeList = Lists.cartesianProduct(lA);

		final List<Set<PX>> t2list = dkeList.parallelStream().map(Sets::newHashSet) // TODO
																					// taiman
				.filter(set -> set.size() == n).distinct().collect(Collectors.toList());

		return t2list;
	}

	/**
	 * 判定几张牌是否符合牌型规则，这几张牌是否正好组成一个牌型
	 * 比如 34567、33、555、QQQ可以；
	 *
	 * @param array
	 * @return
	 */
	public static Optional<PX> f(final int[] array) {
		final ImmutableList<PXE> vl = PXE.VL;
		for (final PXE pxe : vl) {
			final List<PX> g = g(array, pxe);
			if (g.isEmpty()) {
				continue;
			}
			final Optional<PX> findAny = g
					.parallelStream()
					.filter(px -> px.getCArray().length == array.length)
					.findAny();
			if (findAny.isPresent()) {
				return findAny;
			}
		}
		return Optional.empty();
	}

}

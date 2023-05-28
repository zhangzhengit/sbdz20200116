package com.example.demo.p;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *	获取一组牌中要得起某个牌型的所有牌型，如：牌型[对子 33]，一组牌[345666677899]，
 * 	要得起的牌型有四个，[66],[77],[99],[6666]
 *
 * @author zhangzhen
 * @date 2020年2月6日
 *
 */
public class GU {

	private static final int BM_THRESHOLD = 4;

	/**
	 * @param ppx
	 * @param array
	 * @return
	 */
	public static List<PX> ydq(final PX ppx, final int[] array) {
		final PXE ppee = ppx.getPxe();
		if (ppee == PXE.Wangzha) {
			return el();
		}
		if (ppee == PXE.Zhadan && (array.length < BM_THRESHOLD && array[array.length - 1] != 170)) {
			return el();
		}
		final List<PX> ydqList = G.g(array, ppee, PXE.Zhadan, PXE.Wangzha);
		final List<PX> ydqc = ydqList.parallelStream()
		.filter(y -> {
			if(y.getPxe() == PXE.Zhadan || y.getPxe() == PXE.Wangzha){
				return true;
			}
			if (PXE.geshuxiangtong.contains(ppee)) {
				return y.size() == ppx.size();
			}
			return true;
		})
		.filter(y -> y.score() > ppx.score())
		.collect(Collectors.toList());

		return ydqc;
	}

	public static List<PX> el() {
		return Collections.emptyList();
	}

}

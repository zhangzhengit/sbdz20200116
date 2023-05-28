package com.example.demo.p;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 去除模拟过程中胜率低的，保留胜率高的
 *
 * @author zhangzhen
 * @date 2020年2月6日
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ET {

	/**
	 * 模拟过程中的所有牌型胜率
	 */
	private List<RRPU> rrpulist;

	/**
	 * 取得胜率高的一部分
	 *
	 * @return
	 */
	public List<RRPU> get() {
		if (rrpulist.isEmpty()) {
			return el();
		}
		if (rrpulist.size() == 1) {
			return rrpulist;
		}
		// > random 取胜率大于某个牌型的
		final int index = ZR.nextInt(rrpulist.size());
		final RRPU rq= rrpulist.get(index);
		final double p = ZR.nextDouble(rq.getWR());
		final List<RRPU> cx = rrpulist.parallelStream().filter(r -> r.getWR() > p).collect(Collectors.toList());
		return cx;

		// > min 取胜率大于最小胜率的
//		final double min = rrpulist.stream()
//				.mapToDouble(RRPU::wr).min().getAsDouble();
//
//		final List<RRPU> collect = rrpulist.parallelStream()
//				.filter(r -> r.wr() > min)
//				.collect(Collectors.toList());
//
//		return collect;


		// >= avg 取胜率大于等于平均数的
//		final double avg = rrpulist.stream().mapToDouble(RRPU::wr).average().getAsDouble();
//		final List<RRPU> dayuavglist = rrpulist.parallelStream().filter(r -> r.wr() >= avg).collect(Collectors.toList());
//		return dayuavglist;
	}

	private static List<RRPU> el() {
		return Collections.emptyList();
	}

}

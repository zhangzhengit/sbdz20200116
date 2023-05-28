package com.example.demo.p;

import java.time.LocalTime;
import java.util.List;
import java.util.function.IntPredicate;

import org.junit.Test;

import com.google.common.collect.Lists;

import cn.hutool.db.ds.pooled.PooledDSFactory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *
 * @author zhangzhen
 * @date 2020年1月21日
 *
 */
public class Wxtest {

	@Test
	public void test1() {
		System.out.println(Thread.currentThread().getName() + "\t" + LocalTime.now() + "\t" + "Wxtest.test1()");
		System.out.println();

		final int[] g = CU.g();
		final List<PU> wj = UU.wj(g);
		wj.get(2).setCArray(new int[] { 31 });
		for (final SP sp : spList) {
			wj.get(0).setCArray(PFS.pfs(sp.getWo()));
			wj.get(1).setCArray(PFS.pfs(sp.getDs()));
			final PU p1 = wj.get(0);
			final int ms = 1500;
			final RRPU pp = RR.pp(p1, ms);
			System.out.println("pp=" + pp);
		}
	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	static class SP {
		private String wo;
		private String ds;
	}

	static List<SP> spList = Lists.newArrayList();

	static {
//		spList.add(new SP("2Q999643", "大王AAJ43"));
//		spList.add(new SP("AJJ877664", "2Q10105"));
	}
}

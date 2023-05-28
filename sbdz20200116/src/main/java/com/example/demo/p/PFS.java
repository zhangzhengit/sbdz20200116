package com.example.demo.p;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

/**
 *
 * 从字符串表示的牌解析出int[]
 *
 * @author zhangzhen
 * @date 2020年2月6日
 *
 */
public class PFS {

	/**
	 * 从字符串表示的牌解析出int[]，int数会重复
	 *
	 * @param string
	 *            CU.SIMAP的k表示的牌，如：33781010JKA2小王
	 * @return
	 */
	public static int[] pfs(final String string) {
		if (StringUtils.isBlank(string)) {
			throw new IllegalArgumentException(string);
		}

		final List<Integer> list = Lists.newArrayList();
		final int length = string.length();
		int i = 0;
		while (i < length) {
			if (i + 1 <= length) {
				final String s = string.substring(i, i + 1);
				final Integer n = CU.SIMAP.get(s);
				if (Objects.nonNull(n)) {
					list.add(n);
					i++;
				} else if (i + 2 <= length) {
					final String s2 = string.substring(i, i + 2);
					final Integer n2 = CU.SIMAP.get(s2);
					final boolean nonNull2 = Objects.nonNull(n2);
					if (nonNull2) {
						list.add(n2);
						i += 2;
					}
				}
			} else {
				break;
			}
		}
		list.sort(Comparator.comparing(Integer::valueOf));
		final int[] a = new int[list.size()];
		for (int x = 0; x < list.size(); x++) {
			a[x] = list.get(x);
		}
		return a;
	}

}

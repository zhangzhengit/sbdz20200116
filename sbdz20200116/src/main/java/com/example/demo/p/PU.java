package com.example.demo.p;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于计算出牌的玩家对象
 *
 * @author zhangzhen
 * @date 2020年2月6日
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PU implements Serializable {

	private static final long serialVersionUID = 2210407078580065937L;

	/**
	 * 	玩家ID
	 */
	private int id;

	/**
	 * 	玩家名称
	 */
	private String name;

	/**
	 * 	玩家角色
	 */
	private URE ure;

	/**
	 * 玩家的牌，要保证始终有序
	 */
	private int[] cArray;

	/**
	 * 下家
	 */
	private PU next;

	/**
	 * 上家
	 */
	private PU previous;

	/**
	 * 上次出的牌，没出为null
	 */
	private PX prPX;

	/**
	 * 是否赢了
	 *
	 * @return
	 */
	public boolean w() {
		return cArray.length == 0;
	}

	/**
	 * 删除掉出掉的牌
	 *
	 * @param px
	 */
	public void remove(final PX px) {
		if (px == null) {
			return;
		}
		if (px.getCArray().length > cArray.length) {
			return;
		}
		final int[] dA = px.getCArray();
		final int dalength = dA.length;
		final int[] ddA = new int[dalength];
		for (int i = 0; i < dalength; i++) {
			ddA[i] = Arrays.binarySearch(cArray, dA[i]);
		}
		this.cArray = ArrayUtils.removeAll(cArray, ddA);
	}

	@Override
	public String toString() {
		return "PU [id=" + id + ", name=" + name + ", ure=" + ure + ", cArray=" + Arrays.toString(cArray) + ", next="
				+ next.getId() + ", previous=" + previous.getId() + "]";
	}

}

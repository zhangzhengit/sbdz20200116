package com.example.demo.p;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 模拟出的一局的结果
 *
 * @author zhangzhen
 * @date 2020年2月6日
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RPU implements Serializable {

	private static final long serialVersionUID = -376432035100620983L;

	/**
	 * 第一个出牌的玩家
	 */
	private PU fpu;

	/**
	 * fpu最先出的牌
	 */
	private PX fpx;

	/**
	 * 最后赢得玩家
	 */
	private PU wpu;

	/**
	 * wpu最后出的牌
	 */
	private PX wpx;

	/**
	 * 从fpx到wpx一共经过了几步，每个玩家出一次牌算一步
	 */
	private int step;
}

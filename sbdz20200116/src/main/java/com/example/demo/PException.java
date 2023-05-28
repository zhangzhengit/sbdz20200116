package com.example.demo;

import lombok.Getter;

/**
 *
 * 异常类
 *
 * @author zhangzhen
 * @date 2020年2月6日
 *
 */
public class PException extends RuntimeException {

	private static final long serialVersionUID = 4141191337358469622L;

	@Getter
	private final PEE pee;

	public PException(final PEE pee) {
		this.pee = pee;
	}

}

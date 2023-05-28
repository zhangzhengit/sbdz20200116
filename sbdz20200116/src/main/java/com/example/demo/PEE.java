package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * 异常枚举
 *
 * @author zhangzhen
 * @date 2020年2月6日
 *
 */
@Getter
@AllArgsConstructor
public enum PEE {

	NU(1000, "没找到"),

	;

	private int code;
	private String message;
}

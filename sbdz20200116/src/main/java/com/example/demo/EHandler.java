package com.example.demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * 异常处理
 *
 * @author zhangzhen
 * @date 2020年2月6日
 *
 */
@Slf4j
@RestControllerAdvice
public class EHandler {

	@ExceptionHandler(value = PException.class)
	public R<String> peHandler(final HttpServletRequest request, final PException e) {
		e.printStackTrace();
		log.error("error={}", e);
		return R.error(e.getPee().getMessage(), e.getPee().getCode());
	}

}

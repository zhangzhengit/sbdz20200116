package com.example.demo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.ToString;

/**
 * 出牌接口请求内容
 *
 * @author zhangzhen
 * @date 2020年2月6日
 *
 */
@ToString
public class PUR implements Serializable {

	private static final long serialVersionUID = 2470323088083234331L;

	@NotNull(message = "ms不能为空")
	private Integer ms;

	@NotNull(message = "pId不能为空")
	private String pId;

	@NotNull(message = "tId不能为空")
	private Integer tId;

	@NotNull(message = "urList不能为空")
	private List<UR> urList;

	public Integer getMs() {
		return ms;
	}

	public void setMs(final Integer ms) {
		this.ms = ms;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(final String pId) {
		this.pId = pId;
	}

	public Integer gettId() {
		return tId;
	}

	public void settId(final Integer tId) {
		this.tId = tId;
	}

	public List<UR> getUrList() {
		return urList;
	}

	public void setUrList(final List<UR> urList) {
		this.urList = urList;
	}

	public PUR() {
		super();
	}

}

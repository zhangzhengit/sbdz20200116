package com.example.demo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.ToString;

/**
 *
 *
 * @author zhangzhen
 * @date 2020年2月6日
 *
 */
@ToString
@Builder
public class UR implements Serializable {

	private static final long serialVersionUID = 2374406438653407783L;

	@NotNull(message = "id不能为空")
	private Integer id;

	@NotNull(message = "ure不能为空")
	private String ure;

	@NotNull(message = "cArray不能为空")
	private int[] cArray;

	private int[] previousCArray;

	@NotNull(message = "nextId不能为空")
	private Integer nextId;

	@NotNull(message = "previousId不能为空")
	private Integer previousId;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getUre() {
		return ure;
	}

	public void setUre(final String ure) {
		this.ure = ure;
	}

	public int[] getcArray() {
		return cArray;
	}

	public void setcArray(final int[] cArray) {
		this.cArray = cArray;
	}

	public int[] getPreviousCArray() {
		return previousCArray;
	}

	public void setPreviousCArray(final int[] previousCArray) {
		this.previousCArray = previousCArray;
	}

	public Integer getNextId() {
		return nextId;
	}

	public void setNextId(final Integer nextId) {
		this.nextId = nextId;
	}

	public Integer getPreviousId() {
		return previousId;
	}

	public void setPreviousId(final Integer previousId) {
		this.previousId = previousId;
	}

	public UR() {
		super();
	}

	public UR(@NotNull(message = "id不能为空") final Integer id, @NotNull(message = "ure不能为空") final String ure,
			@NotNull(message = "cArray不能为空") final int[] cArray, final int[] previousCArray,
			@NotNull(message = "nextId不能为空") final Integer nextId, @NotNull(message = "previousId不能为空") final Integer previousId) {
		super();
		this.id = id;
		this.ure = ure;
		this.cArray = cArray;
		this.previousCArray = previousCArray;
		this.nextId = nextId;
		this.previousId = previousId;
	}

}

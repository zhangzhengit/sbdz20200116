package com.example.demo.p;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 判定能否一手出完的临时结果
 *
 * @author zhangzhen
 * @date 2020年2月6日
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YSYDQ {

	private RRPU yishou;

	private List<PX> ydqlist;
}

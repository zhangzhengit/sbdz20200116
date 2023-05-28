package com.example.demo;

import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 接口地址配置
 *
 * @author zhangzhen
 * @date 2020年2月6日
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "machine")
public class MachineConfiguration {

	private Set<String> url;

}

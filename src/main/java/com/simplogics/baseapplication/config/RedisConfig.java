package com.simplogics.baseapplication.config;

import java.time.Duration;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import lombok.Getter;
import lombok.Setter;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Getter
@Setter
@Configuration
@PropertySource("classpath:redis.properties")
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {

	@javax.validation.constraints.NotBlank
	private String host;

	private String password;

	@Min(1025)
	@Max(65536)
	private int port = 6379;

	@Min(1000)
	@Max(120000)
	private int timeout = 120000;

	@Min(1)
	@Max(400)
	@Value("${redis.max.connections}")
	private int maxConnections = 400;

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public JedisPool getJedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxWait(Duration.ofMillis(timeout));
		jedisPoolConfig.setMaxTotal(maxConnections);
		jedisPoolConfig.setMaxIdle(400);
		jedisPoolConfig.setTestOnReturn(true);
		jedisPoolConfig.setTestWhileIdle(true);
		JedisPool pool = new JedisPool(jedisPoolConfig, host, port, timeout);
		return pool;
	}
}

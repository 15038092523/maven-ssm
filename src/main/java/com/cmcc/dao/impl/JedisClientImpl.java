package com.cmcc.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.cmcc.dao.JedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientImpl implements JedisClient{

	@Autowired
	private JedisPool jedisPool; 
	
	//字符串get操作
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		jedis.auth("foobared");
		String string = jedis.get(key);
		jedis.close();
		return string;
	}

	//字符串set操作
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		jedis.auth("foobared");
		String string = jedis.set(key, value);
		jedis.close();
		return string;
	}

	//hash表get
	public String hget(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		jedis.auth("foobared");
		String string = jedis.hget(hkey, key);
		jedis.close();
		return string;
	}

	//hash表set
	public long hset(String hkey, String key, String value) {
		Jedis jedis = jedisPool.getResource();
		jedis.auth("foobared");
		Long result = jedis.hset(hkey, key, value);
		jedis.close();
		return result;
	}

	//自增
	public long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		jedis.auth("foobared");
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	//设置key的过期时间
	public long expire(String key, int second) {
		Jedis jedis = jedisPool.getResource();
		jedis.auth("foobared");
		Long result = jedis.expire(key, second);
		jedis.close();
		return result;
	}

	//返回 key 的剩余生存时间
	public long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		jedis.auth("foobared");
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	//删除
	public long del(String key) {
		Jedis jedis = jedisPool.getResource();
		jedis.auth("foobared");
		Long result = jedis.del(key);
		jedis.close();
		return result;
	}

	//删除hash中的某一元素
	public long hdel(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		jedis.auth("foobared");
		Long result = jedis.hdel(hkey, key);
		jedis.close();
		return result;
	}
}

package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class TliasApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testGenJwt() {
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", 1);
		claims.put("name", "Tom");

		String jwt = Jwts.builder()
				.signWith(SignatureAlgorithm.HS256, "itheima")	// 签名算法 和 密钥
				.setClaims(claims)	// 自定义内容（载荷）
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 3600))  // 设置令牌有效期1h
				.compact();
		System.out.println(jwt);
	}

	@Test
	public void testParseJwt() {
		Claims claims = Jwts.parser()
				.setSigningKey("itheima")
				.parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiVG9tIiwiaWQiOjEsImV4cCI6MTcwMjA0MzUzNX0.8ECyjkkE1e5oVYpReRWveu_8shOCgXL2ch1IxNXv4JI")
				.getBody();
		System.out.println(claims);
	}
}

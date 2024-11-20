package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

@EnableWireMock(@ConfigureWireMock(name = "myapp", filesUnderClasspath = "wiremock/myapp/mappings"))
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
	@Autowired private WebTestClient webClient;

	@Value("${wiremock.server.baseUrl}")
	private String url;

	@Test
	void contextLoads() {
		webClient.get().uri(url + "/test").exchange().expectStatus().isOk();
	}
}

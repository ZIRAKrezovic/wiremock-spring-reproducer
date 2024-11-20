package com.example.demo;

import com.maciejwalkowiak.wiremock.spring.ConfigureWireMock;
import com.maciejwalkowiak.wiremock.spring.EnableWireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@EnableWireMock(@ConfigureWireMock(name = "myapp", properties = "wiremock.server.baseUrl"))
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationLegacyTests {
	@Autowired private WebTestClient webClient;

	@Value("${wiremock.server.baseUrl}")
	private String url;

	@Test
	void contextLoads() {
		webClient.get().uri(url + "/test").exchange().expectStatus().isOk();
	}

}

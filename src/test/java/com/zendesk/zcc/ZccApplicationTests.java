package com.zendesk.zcc;

import com.zendesk.zcc.model.GetRequestHandler;
import com.zendesk.zcc.model.Request;
import com.zendesk.zcc.model.ZccRequestMethods;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;


@SpringBootTest(args = {"--user=test","--pass=test", "--sub_domain=test"})
class ZccApplicationTests {
	@Test
	public void testRequestMethod() {
		ZccRequestMethods method = ZccRequestMethods.GET;
		assertThat(method.getMethodHandler() instanceof GetRequestHandler).isTrue();
	}

	@Test
	public void testRequestBuilder() {
		Request request = Request.GET_TICKETS;
		Map<String, String> map = new HashMap<>();
		map.put("LIMIT","5");
		map.put("OFF_SET", "10");
		assertThat(request.getBuilder(map).build()).isEqualTo("https://test.zendesk.com/api/v2/tickets.json?per_page=5&page=10");
	}

	@Test
	public void testRequest() {
		Request request = Request.GET_TICKETS;
		Map<String, String> map = new HashMap<>();
		map.put("LIMIT","5");
		map.put("OFF_SET", "10");
		try {
			ZccRequestMethods.GET.getMethodHandler().sendRequest(request.getBuilder(map).build());
			fail("Expected IOException with 401");
		} catch (IOException ignored) {

		}
	}
}

package com.products.adapter.out.client;

import java.io.IOException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

class ProductClientPortAdapterTest {

  private MockWebServer mockWebServer;
  private ProductClientPortAdapter adapter;

  @BeforeEach
  void setUp() throws IOException {
    mockWebServer = new MockWebServer();
    mockWebServer.start();
    WebClient webClient = WebClient.builder()
        .baseUrl(mockWebServer.url("/").toString())
        .build();
    adapter = new ProductClientPortAdapter(webClient);
  }

  @AfterEach
  void tearDown() throws IOException {
    mockWebServer.shutdown();
  }

  @Test
  void getProductDetail_shouldReturnProduct_whenSuccess() {
    mockWebServer.enqueue(new MockResponse()
        .setBody("{\"id\":\"1\",\"name\":\"TV\",\"price\":100.0,\"availability\":true}")
        .addHeader("Content-Type", "application/json"));

    StepVerifier.create(adapter.getProductDetail("1"))
        .expectNextMatches(p -> p.id().equals("1") && p.name().equals("TV"))
        .verifyComplete();
  }

  @Test
  void getProductDetail_shouldReturnEmpty_whenNotFound() {
    mockWebServer.enqueue(new MockResponse().setResponseCode(404));

    StepVerifier.create(adapter.getProductDetail("1"))
        .verifyComplete();
  }

  @Test
  void getProductDetail_shouldReturnEmpty_whenServerError() {
    mockWebServer.enqueue(new MockResponse().setResponseCode(500));

    StepVerifier.create(adapter.getProductDetail("1"))
        .verifyComplete();
  }

  @Test
  void getSimilarProductIds_shouldReturnIds_whenSuccess() {
    mockWebServer.enqueue(new MockResponse()
        .setBody("[\"2\",\"3\",\"4\"]")
        .addHeader("Content-Type", "application/json"));

    StepVerifier.create(adapter.getSimilarProductIds("1"))
        .expectNext("2", "3", "4")
        .verifyComplete();
  }
}

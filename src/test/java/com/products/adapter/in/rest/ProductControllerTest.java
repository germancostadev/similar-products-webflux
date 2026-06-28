package com.products.adapter.in.rest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.products.domain.model.Product;
import com.products.domain.model.ProductDetail;
import com.products.domain.port.in.ProductUseCase;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

class ProductControllerTest {

  private final ProductUseCase productUseCase = mock(ProductUseCase.class);
  private WebTestClient webTestClient;

  @BeforeEach
  void setUp() {
    webTestClient = WebTestClient.bindToController(new ProductController(productUseCase)).build();
  }

  @Test
  void getProductSimilar_shouldReturn200WithProducts() {
    var product = new Product("2", "TV", BigDecimal.valueOf(100.0), true);
    when(productUseCase.getSimilarProducts("1")).thenReturn(Flux.just(product));

    webTestClient.get().uri("/product/1/similar")
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(ProductDetail.class)
        .hasSize(1);
  }

  @Test
  void getProductSimilar_shouldReturn200WithEmptyList() {
    when(productUseCase.getSimilarProducts("1")).thenReturn(Flux.empty());

    webTestClient.get().uri("/product/1/similar")
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(ProductDetail.class)
        .hasSize(0);
  }
}

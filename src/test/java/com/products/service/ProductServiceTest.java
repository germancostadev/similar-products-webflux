package com.products.service;

import static org.mockito.Mockito.when;

import com.products.domain.model.Product;
import com.products.domain.port.out.ProductClientPort;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @Mock
  ProductClientPort productClientPort;

  @InjectMocks
  ProductService productService;

  @Test
  void shouldReturnSimilarProducts() {
    var product = new Product("2", "TV", BigDecimal.valueOf(100.0), true);

    when(productClientPort.getSimilarProductIds("1")).thenReturn(Flux.just("2", "3"));
    when(productClientPort.getProductDetail("2")).thenReturn(Mono.just(product));
    when(productClientPort.getProductDetail("3")).thenReturn(Mono.empty());

    StepVerifier.create(productService.getSimilarProducts("1"))
        .expectNext(product)
        .verifyComplete();
  }

  @Test
  void shouldReturnEmptyWhenNoSimilarIds() {
    when(productClientPort.getSimilarProductIds("1")).thenReturn(Flux.empty());

    StepVerifier.create(productService.getSimilarProducts("1"))
        .verifyComplete();
  }

  @Test
  void shouldReturnAllProductsWhenAllDetailsFetched() {
    var product2 = new Product("2", "TV", BigDecimal.valueOf(100.0), true);
    var product3 = new Product("3", "Radio", BigDecimal.valueOf(50.0), false);

    when(productClientPort.getSimilarProductIds("1")).thenReturn(Flux.just("2", "3"));
    when(productClientPort.getProductDetail("2")).thenReturn(Mono.just(product2));
    when(productClientPort.getProductDetail("3")).thenReturn(Mono.just(product3));

    StepVerifier.create(productService.getSimilarProducts("1"))
        .expectNextCount(2)
        .verifyComplete();
  }
}

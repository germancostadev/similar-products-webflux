package com.products.adapter.in.rest;

import com.products.domain.model.ProductDetail;
import com.products.domain.port.in.ProductApi;
import com.products.domain.port.in.ProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {

  private final ProductUseCase productUseCase;

  @Override
  public Mono<ResponseEntity<Flux<ProductDetail>>> getProductSimilar(String productId,
      ServerWebExchange exchange) {
    return ProductApi.super.getProductSimilar(productId, exchange);
  }
//  public Flux<ProductDetail> getProductSimilar(@PathVariable String productId) {
//    return productUseCase.getSimilarProducts(productId);
//  }
}

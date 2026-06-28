package com.products.adapter.in.rest;

import com.products.domain.model.ProductDetail;
import com.products.domain.port.in.ProductApi;
import com.products.domain.port.in.ProductUseCase;
import com.products.service.ProductDetailMapper;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
  public Mono<ResponseEntity<Flux<ProductDetail>>> getProductSimilar(
      @Parameter(name = "productId") @PathVariable("productId") String productId,
      @Parameter(hidden = true) final ServerWebExchange exchange) {
    return Mono.just(ResponseEntity.ok().body(
        productUseCase.getSimilarProducts(productId)
            .map(ProductDetailMapper::mapProductDetail)));
  }
}

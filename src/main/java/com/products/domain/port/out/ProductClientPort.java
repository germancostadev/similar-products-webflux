package com.products.domain.port.out;

import com.products.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductClientPort {
  Flux<String> getSimilarProductIds(String productId);
  Mono<Product> getProductDetail(String productId);
}

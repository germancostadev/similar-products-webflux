package com.products.domain.port.in;

import com.products.domain.model.ProductDetail;
import reactor.core.publisher.Flux;

public interface ProductUseCase {
  Flux<ProductDetail> getSimilarProducts(String productId);
}

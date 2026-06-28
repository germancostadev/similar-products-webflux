package com.products.domain.port.in;

import com.products.domain.model.Product;
import reactor.core.publisher.Flux;

public interface ProductUseCase {

  Flux<Product> getSimilarProducts(String productId);
}

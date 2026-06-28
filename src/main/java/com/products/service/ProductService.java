package com.products.service;

import com.products.domain.model.Product;
import com.products.domain.port.in.ProductUseCase;
import com.products.domain.port.out.ProductClientPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService implements ProductUseCase {

  private final ProductClientPort productClientPort;

  @Override
  public Flux<Product> getSimilarProducts(String productId) {
    return productClientPort
        .getSimilarProductIds(productId)
        .flatMap(
            productClientPort::getProductDetail, 20);
  }
}

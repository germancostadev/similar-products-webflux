package com.products.service;

import com.products.domain.model.ProductDetail;
import com.products.domain.port.in.ProductUseCase;
import com.products.domain.port.out.ProductClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductUseCase {

  private final ProductClientPort productClientPort;

  @Override
  public Flux<ProductDetail> getSimilarProducts(String productId) {
    return productClientPort.getSimilarProductIds(productId)
        .flatMap(productClientPort::getProductDetail)
        .map(ProductDetailMapper::mapProductDetail);
  }
}

package com.products.service;

import com.products.domain.model.Product;
import com.products.domain.model.ProductDetail;

public class ProductDetailMapper {
  public static ProductDetail mapProductDetail(Product product) {
    return new ProductDetail(
        product.id(),
        product.name(),
        product.price(),
        product.availability()
    );
  }
}

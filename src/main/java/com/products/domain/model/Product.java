package com.products.domain.model;

import java.math.BigDecimal;

public record Product(String id, String name, BigDecimal price, Boolean availability) {
  public Product {
//    validate();
  }

  private void validate() {
    if (id == null || id.isBlank()) {
      throw new IllegalArgumentException("Product Id cannot be null or empty");
    }
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
    if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Price must be positive");
    }
  }
}

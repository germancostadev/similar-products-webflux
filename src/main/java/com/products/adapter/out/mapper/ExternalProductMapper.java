package com.products.adapter.out.mapper;

import com.products.domain.model.Product;
import com.products.infrastructure.adapter.out.client.generated.model.ProductDetail;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ExternalProductMapper {

    public static @NotNull Product toDomain(@NotNull ProductDetail response) {
        return new Product(
                response.getId(),
                response.getName(),
                response.getPrice(),
                response.getAvailability()
        );
    }
}
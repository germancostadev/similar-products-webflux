package com.products.adapter.out.client;

import com.products.adapter.out.mapper.ExternalProductMapper;
import com.products.domain.model.Product;
import com.products.domain.port.out.ProductClientPort;
import com.products.infrastructure.adapter.out.client.generated.model.ProductDetail;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductClientPortAdapter implements ProductClientPort {

  private final WebClient webClient;

  @Override
  @Cacheable(value = "similar-products", key = "#productId")
  public Flux<String> getSimilarProductIds(String productId) {
    return webClient.get()
        .uri("/product/{id}/similarids", productId)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<List<String>>() {
        })
        .flatMapMany(Flux::fromIterable);
  }

  @Override
  @Cacheable(value = "product-detail", key = "#productId")
  public Mono<Product> getProductDetail(String productId) {
    return webClient.get()
        .uri("/product/{id}", productId)
        .retrieve()
        .bodyToMono(ProductDetail.class)
        .map(ExternalProductMapper::toDomain)
        .timeout(Duration.ofSeconds(2))
        .onErrorResume(throwable -> {
          if (throwable instanceof TimeoutException
              || throwable instanceof WebClientResponseException.NotFound
              || throwable instanceof WebClientResponseException.InternalServerError) {
            return Mono.empty();
          }
          return Mono.error(throwable);
        });
  }
}

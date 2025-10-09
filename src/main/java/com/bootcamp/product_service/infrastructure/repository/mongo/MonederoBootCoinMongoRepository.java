package com.bootcamp.product_service.infrastructure.repository.mongo;

import com.bootcamp.product_service.domain.aggregate.wallet.MonederoBootCoin;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MonederoBootCoinMongoRepository extends ReactiveMongoRepository<MonederoBootCoin, String> {
}

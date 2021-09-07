package com.eriagacha.telemetry;

import com.eriagacha.models.GachaTelemetryModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface GachaTelemetryRepository
    extends ReactiveCrudRepository<GachaTelemetryModel, Long> {

  Flux<GachaTelemetryModel> findByUser(String user);

}

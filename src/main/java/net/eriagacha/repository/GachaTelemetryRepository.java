package net.eriagacha.repository;

import net.eriagacha.models.GachaTelemetryModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;


public interface GachaTelemetryRepository extends ReactiveCrudRepository<GachaTelemetryModel, Long> {

  Flux<GachaTelemetryModel> findByUser(String user);

}

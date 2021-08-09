package net.eriagacha.repository;

import net.eriagacha.models.GachaTelemetryModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface GachaTelemetryRepository extends ReactiveCrudRepository<GachaTelemetryModel, Long> {

}

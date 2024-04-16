package fau.datastreamMetric.Repository;

import fau.datastreamMetric.models.Metric;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MetricRepository extends ListCrudRepository<Metric,Integer> {

    Optional<Metric> findById(long id);

    Metric findByName(String name);


}
package fau.datastreamMetric.Repository;

import fau.datastreamMetric.models.Datastream;
import fau.datastreamMetric.models.Metric;
import fau.datastreamMetric.models.MetricFactTable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetricFactTableRepository extends ListCrudRepository<MetricFactTable,Integer> {

    List<MetricFactTable> findMetricFactTablesByDatastream(Datastream datastream);
    List<MetricFactTable> findMetricFactTablesByDatastreamAndMetric(Datastream datastream, Metric metric);
    @Query("SELECT DISTINCT m.metric FROM MetricFactTable m WHERE m.datastream = :datastream")
    List<Metric> findDistinctByDatastream(Datastream datastream);


}

package fau.datastreamMetric.Repository;

import fau.datastreamMetric.models.Datastream;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatastreamRepository extends ListCrudRepository<Datastream,Integer> {


    public Datastream findByName(String name);


}

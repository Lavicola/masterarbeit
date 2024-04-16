package fau.dataMart.Repository;

import fau.dataMart.models.Metadata;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetadataRepository extends ListCrudRepository<Metadata, Integer> {

    Metadata findByName(String name);
}

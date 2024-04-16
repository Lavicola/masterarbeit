package fau.dataMart.Repository;

import fau.dataMart.models.Executionplan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExecutionplanRepository extends ListCrudRepository<Executionplan,Integer> {

    @Query("SELECT e FROM Executionplan e WHERE e.hash = :statementHash")
    List<Executionplan> findExecutionplansByQueriesStatementHash(@Param("statementHash") String statementHash);

    Executionplan findByHash(String hash);

}

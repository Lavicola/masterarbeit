package fau.dataMart.Repository;

import fau.dataMart.models.Executionplan;
import fau.dataMart.models.Metadata;
import fau.dataMart.models.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface QueryRepository extends PagingAndSortingRepository<Query, Integer>, ListCrudRepository<Query, Integer> {


    Page<Query> findQueriesByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);


    @org.springframework.data.jpa.repository.Query(value =
            "SELECT u FROM Query u LEFT JOIN u.metadata sk WHERE sk IN :metadataList AND u.timestamp BETWEEN :startTimestamp AND :endTimestamp " +
                    "GROUP BY u HAVING COUNT(sk) = :metadataListSize")
    Page<Query> findByMetadataInAndTimestampBetween(
            @Param("metadataList") List<Metadata> metadataList,
            @Param("metadataListSize") long metadataListSize,
            @Param("startTimestamp") LocalDateTime startTimestamp,
            @Param("endTimestamp") LocalDateTime endTimestamp,
            Pageable pageable);

    List<Query> findQueriesByMetadataIn(List<Metadata> metadata);


    Query findQueryByStatementHashAndTimestampAfter(String statementHash, LocalDateTime after);

    List<Query> findByStatementHash(String statementHash);

    @org.springframework.data.jpa.repository.Query("SELECT q  FROM Query q")
   List<Executionplan> findDistinctExecutionPlansByStatementHash(@Param("statementHash") String statementHash);


    @org.springframework.data.jpa.repository.Query("SELECT DISTINCT(q.executionplan) FROM Query q WHERE q.statementHash=:statementHash ")
    List<Executionplan> findAllDistinctExecutionplanByStatementHash(String statementHash);

    // Page<Query> findByStartDateBetween(Timestamp startDate, Timestamp endDate, String statement_hash, Pageable pageable);


}


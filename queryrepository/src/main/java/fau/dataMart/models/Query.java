package fau.dataMart.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Query")
@Table(name = "query")
public class Query {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "query_id")
    private long id;

    @Column(name = "statement")
    private String statement;

    @Column(name = "statementHash")
    private String statementHash;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Metadata> metadata = new HashSet<>();


    @ManyToOne()
    @JoinColumn(name = "executionplan_hash", referencedColumnName = "hash")
    private Executionplan executionplan;

    private LocalDateTime timestamp;


    public Query(String statement, String statementHash, Executionplan executionplan, LocalDateTime timestamp) {
        this.statement = statement;
        this.statementHash = statementHash;
        this.executionplan = executionplan;
        this.timestamp = timestamp;
    }


    public Query() {

    }

    public String getStatementHash() {
        return statementHash;
    }

    public void setStatementHash(String statementHash) {
        this.statementHash = statementHash;
    }

    @Transient
    public void addMetadata(Metadata queryMetadata){
        this.metadata.add(queryMetadata);
    }

    public Set<Metadata> getMetadata(){
        return this.metadata;
    }

    public Executionplan getExecutionplan() {
        return executionplan;
    }

    public void setExecutionplan(Executionplan executionplan) {
        this.executionplan = executionplan;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatement() {
        return statement;
    }
}



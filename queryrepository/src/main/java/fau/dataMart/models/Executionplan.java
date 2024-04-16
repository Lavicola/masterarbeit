package fau.dataMart.models;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;


@Entity(name = "Executionplan")
@Table(name = "Executionplan")
public class Executionplan {

    @Id
    private String hash;

    @Type(JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private String executionplan;

    @OneToMany(mappedBy = "executionplan")
    private List<Query> queries = new ArrayList<>();

    @OneToMany(mappedBy = "executionplan", cascade = CascadeType.ALL)
    private List<ExecutionplanStep> steps = new ArrayList<>();


    public Executionplan(String hash, String executionplan) {
        this.hash = hash;
        this.executionplan = executionplan;
    }

    public Executionplan() {

    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getExecutionplan() {
        return executionplan;
    }

    public void setExecutionplan(String executionplan) {
        this.executionplan = executionplan;
    }

    @Transient
    public void addQuery(Query query){
        this.queries.add(query);
    }

    @Transient
    public void addExectuionplanStep(ExecutionplanStep executionplanStep){
        this.steps.add(executionplanStep);
    }

    public List<ExecutionplanStep> getSteps(){
        return this.steps;
    }

}


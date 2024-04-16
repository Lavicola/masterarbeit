package fau.datastreamMetric.models;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Datastream {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private String stream;

    @OneToMany(mappedBy = "datastream", cascade = CascadeType.ALL)
    private List<MetricFactTable> metricFactTables;



    public Datastream(String name, String stream) {
        this.name = name;
        this.stream = stream;
        this.metricFactTables = new ArrayList<>();
    }

    public Datastream() {

    }


    public String getStream() {
        return stream;
    }

    public void setStream(String datastream) {
        this.stream = datastream;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }


    public void addMetricValue(MetricFactTable datapoint){
        this.metricFactTables.add(datapoint);
    }

    public List<MetricFactTable> getMetricFactTables() {
        return metricFactTables;
    }

    public void setMetricFactTables(List<MetricFactTable> metricFactTables) {
        this.metricFactTables = metricFactTables;
    }


    public void setId(int id) {
        this.id = id;
    }
}
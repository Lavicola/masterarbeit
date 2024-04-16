package fau.datastreamMetric.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class MetricFactTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "metric_id")
    private Metric metric;

    @ManyToOne
    @JoinColumn(name = "datastream_id", referencedColumnName = "id")
    private Datastream datastream;

    private LocalDateTime timestamp;
    private double value;

    public MetricFactTable(Metric metric, Datastream datastream, double value, LocalDateTime timestamp) {
        this.metric = metric;
        this.datastream = datastream;
        this.value = value;
        this.timestamp = timestamp;
    }

    public MetricFactTable() {

    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
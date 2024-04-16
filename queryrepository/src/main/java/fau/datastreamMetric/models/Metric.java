package fau.datastreamMetric.models;
import jakarta.persistence.*;


@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Metric {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    protected String name;

    // km/h maybe?
    protected String unit;

    // maybe the metric has a special format
    protected String format;

    public Metric(String name, String unit,String format) {
        this.name = name;
        this.unit = unit;
        this.format = format;
    }

    public Metric() {

    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public long getId() {
        return id;
    }
}
package fau.datastreamMetric.Service;

import fau.datastreamMetric.Messages.MetricMessage;
import fau.datastreamMetric.models.Datastream;
import fau.datastreamMetric.models.Metric;
import fau.datastreamMetric.models.MetricFactTable;

import java.time.LocalDateTime;
import java.util.List;

public interface DatastreamFeatureService {

    public List<Datastream> getDatastreams();

    public Datastream createDatastream(String datastreamname,String datastream);

    public List<Metric> getDatastreamMetrics(String datastreamname);

    public List<MetricFactTable> getMetricDatapoints(String metricname, String datastreamname);
    // this way I can test the logic without needing a Connection or Object
    public boolean storeMetricDataPoint(String name, String unit, String format, double value, int datastreamId, LocalDateTime localDateTime);

    public void receiveMetric(MetricMessage metricMessage);



}

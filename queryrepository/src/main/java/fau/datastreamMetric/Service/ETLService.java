package fau.datastreamMetric.Service;

import fau.datastreamMetric.models.Metric;

public interface ETLService {

    public boolean checkIfMetricExists(Metric metric);
    public boolean createMetric(Metric metric);




}

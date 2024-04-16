package fau.datastreamMetric.Controller;

import fau.datastreamMetric.Service.DatastreamFeatureService;
import fau.datastreamMetric.dto.Datapoint;
import fau.datastreamMetric.models.Datastream;
import fau.datastreamMetric.models.Metric;
import fau.datastreamMetric.models.MetricFactTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DatastreamApiControllerImpl implements DatastreamApiDelegate {

    @Autowired
    private DatastreamFeatureService datastreamFeatureService;

    @Override
    public ResponseEntity<List<Datastream>> datastreamGet() {
        return ResponseEntity.status(HttpStatus.OK).body(this.datastreamFeatureService.getDatastreams());
    }

    @Override
    public ResponseEntity<List<Metric>> getAllMetrics(String datastreamName) {
        return ResponseEntity.status(HttpStatus.OK).body(this.datastreamFeatureService.getDatastreamMetrics(datastreamName));
    }

    @Override
    public ResponseEntity<Datastream> datastreamPost(Datastream datastream) {
        Datastream datastream1 = this.datastreamFeatureService.createDatastream(datastream.getName(),datastream.getStream());
        return ResponseEntity.status(HttpStatus.OK).body(datastream1);
    }


    @Override


    public ResponseEntity<List<Datapoint>> datastreamDatastreamNameMetricMetricNameGet(String metricName, String datastreamName) {
        List<MetricFactTable> entries = this.datastreamFeatureService.getMetricDatapoints(metricName, datastreamName);
        List<Datapoint> dto_list = new ArrayList<>();

        for (MetricFactTable entry : entries) {
            // Process each metric
            Datapoint datapoint = new Datapoint();
            datapoint.setTime(entry.getTimestamp());
            datapoint.setValue(entry.getValue());
            dto_list.add(datapoint);
        }

        return ResponseEntity.status(HttpStatus.OK).body(dto_list);
    }


}

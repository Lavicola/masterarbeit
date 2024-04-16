package fau.datastreamMetric.Service;

import fau.datastreamMetric.Messages.MetricMessage;
import fau.datastreamMetric.Messages.MetricPayload;
import fau.datastreamMetric.Repository.DatastreamRepository;
import fau.datastreamMetric.Repository.MetricFactTableRepository;
import fau.datastreamMetric.Repository.MetricRepository;
import fau.datastreamMetric.models.Datastream;
import fau.datastreamMetric.models.Metric;
import fau.datastreamMetric.models.MetricFactTable;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DatastreamFeatureServiceImpl implements DatastreamFeatureService {

    @Autowired
    DatastreamRepository datastreamRepository;

    @Autowired
    MetricFactTableRepository metricFactTableRepository;

    @Autowired
    MetricRepository metricRepository;

    @Override
    public List<Datastream> getDatastreams() {
        List<Datastream> datastreams = this.datastreamRepository.findAll();
        return this.datastreamRepository.findAll();
    }

    @Override
    public Datastream createDatastream(String datastreamname, String datastream) {
        Datastream datastream1 = new Datastream(datastreamname, datastream);
        return this.datastreamRepository.save(datastream1);
    }


    @Override
    public List<Metric> getDatastreamMetrics(String datastreamname) {
        Datastream datastream = this.datastreamRepository.findByName(datastreamname);
        if (datastream != null) {
            return this.metricFactTableRepository.findDistinctByDatastream(datastream);
        }
        return Collections.emptyList();
    }

    @Override
    public List<MetricFactTable> getMetricDatapoints(String metricname, String datastreamname) {
        Datastream datastream = this.datastreamRepository.findByName(datastreamname);
        Metric metric = this.metricRepository.findByName(metricname);
        if (datastream != null && metric != null) {
            return this.metricFactTableRepository.findMetricFactTablesByDatastreamAndMetric(datastream, metric);
        }
        return Collections.emptyList();
    }

    @Override
    @RabbitListener(queues = "${rabbitmq.datastream_feature.metric_queue}")
    public void receiveMetric(MetricMessage metricMessage){
        MetricPayload metricPayload = metricMessage.getPayload();
        this.storeMetricDataPoint(metricPayload.getName(),metricPayload.getUnit(),
                metricPayload.getFormat(),metricPayload.getValue(),
                metricPayload.getDatastreamId(),
                metricPayload.getTimestamp().toLocalDateTime());

    }

    @Override
    public boolean storeMetricDataPoint(String name, String unit, String format, double value, int datastreamId, LocalDateTime localDateTime) {
        Metric metric = this.metricRepository.findByName(name);
        Optional<Datastream> datastream = this.datastreamRepository.findById(datastreamId);
        if (metric == null) {
            metric = new Metric(name, unit, format);
            metric = this.metricRepository.save(metric);
        }
        if (!datastream.isPresent()) {
            return false;
        }

        MetricFactTable metricFactTable = new MetricFactTable(metric, datastream.get(),value, LocalDateTime.now());
        this.metricFactTableRepository.save(metricFactTable);

        return false;
    }


}
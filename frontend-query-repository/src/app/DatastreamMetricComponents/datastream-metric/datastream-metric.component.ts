import {Component, HostListener, NgModule} from '@angular/core';
import {Metric} from "../../models/metric";
import {Measurement} from "../../models/measurement";
import {ActivatedRoute} from "@angular/router";
import {DatastreamService} from "../../services/datastream.service";

@Component({
    selector: 'my-app',
    templateUrl: './datastream-metric.component.html',
    styleUrls: ['./datastream-metric.component.css']
})
export class DatastreamMetricComponent {
    // @ts-ignore
    multi: any[];
    view: [number, number] = [window.innerWidth, window.innerHeight];

    metricNames: String[] = []
    // @ts-ignore
    metric: Metric;
    router: any;
    private sub: any;
    datastreamService: DatastreamService | undefined;
    datastreamName = "";
    // options
    legend: boolean = true;
    animations: boolean = true;
    xAxis: boolean = true;
    yAxis: boolean = true;
    showYAxisLabel: boolean = true;
    showXAxisLabel: boolean = true;
    xAxisLabel: string = '';
    yAxisLabel: string = 'Number';
    selectedMetric: string = "";

    constructor(private route: ActivatedRoute, service: DatastreamService) {
        this.router = route;
        this.datastreamService = service;
        Object.assign(this, {multi});

    };


    ngOnInit(): void {
        this.sub = this.route.params.subscribe(params => {
            this.datastreamName = params['name'];
        });
        this.datastreamService?.getAllMetrics(
            {datastream_name: this.datastreamName})
            .subscribe((metrics: Metric[]) => {
                metrics.forEach((metric: Metric) => {
                    if (metric.name) {
                        this.metricNames.push(metric.name);
                    }
                });

            });
    }


    onSelect(data: any): void {
        console.log('Item clicked', JSON.parse(JSON.stringify(data)));
    }


    public updateChart(selectedMetric: string) {

        let name: string;
        let value: number;
        let series: [] = [];
        this.datastreamService?.datastreamDatastreamNameMetricMetricNameGet(
            {datastream_name: this.datastreamName, metric_name: selectedMetric}).subscribe(
            (measurements: Measurement[]) => {
                measurements.forEach((measure: Measurement) => {
                    // @ts-ignore
                    series.push({
                        name: measure.time,
                        value: measure.value
                    });
                });
            });

        // @ts-ignore
        this.yAxisLabel = this.metric.unit;
        const multi2 = [
            {
                name: this.metric.name,
                series: series
            }
        ];

        this.multi = [...multi2]


    }

    @HostListener('window:resize', ['$event'])
    onResize(event: Event): void {
        this.view = [window.innerWidth, window.innerHeight];
    }


}


export var multi = [
    {
        "name": "",
        "series": []
    }


];

/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { Datastream } from '../models/datastream';
import { datastreamDatastreamNameMetricMetricNameGet } from '../fn/datastream/datastream-datastream-name-metric-metric-name-get';
import { DatastreamDatastreamNameMetricMetricNameGet$Params } from '../fn/datastream/datastream-datastream-name-metric-metric-name-get';
import { datastreamGet } from '../fn/datastream/datastream-get';
import { DatastreamGet$Params } from '../fn/datastream/datastream-get';
import { datastreamPost } from '../fn/datastream/datastream-post';
import { DatastreamPost$Params } from '../fn/datastream/datastream-post';
import { getAllMetrics } from '../fn/datastream/get-all-metrics';
import { GetAllMetrics$Params } from '../fn/datastream/get-all-metrics';
import { Measurement } from '../models/measurement';
import { Metric } from '../models/metric';

@Injectable({ providedIn: 'root' })
export class DatastreamService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `datastreamGet()` */
  static readonly DatastreamGetPath = '/datastream';

  /**
   * Get all datastreams.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `datastreamGet()` instead.
   *
   * This method doesn't expect any request body.
   */
  datastreamGet$Response(params?: DatastreamGet$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<Datastream>>> {
    return datastreamGet(this.http, this.rootUrl, params, context);
  }

  /**
   * Get all datastreams.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `datastreamGet$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  datastreamGet(params?: DatastreamGet$Params, context?: HttpContext): Observable<Array<Datastream>> {
    return this.datastreamGet$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<Datastream>>): Array<Datastream> => r.body)
    );
  }

  /** Path part for operation `datastreamPost()` */
  static readonly DatastreamPostPath = '/datastream';

  /**
   * Create a new datastream.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `datastreamPost()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  datastreamPost$Response(params: DatastreamPost$Params, context?: HttpContext): Observable<StrictHttpResponse<Datastream>> {
    return datastreamPost(this.http, this.rootUrl, params, context);
  }

  /**
   * Create a new datastream.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `datastreamPost$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  datastreamPost(params: DatastreamPost$Params, context?: HttpContext): Observable<Datastream> {
    return this.datastreamPost$Response(params, context).pipe(
      map((r: StrictHttpResponse<Datastream>): Datastream => r.body)
    );
  }

  /** Path part for operation `getAllMetrics()` */
  static readonly GetAllMetricsPath = '/datastream/{datastream_name}/metric';

  /**
   * Get all metrics of a datastream without Measurements.
   *
   * Returns a list of all available metrics
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAllMetrics()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllMetrics$Response(params: GetAllMetrics$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<Metric>>> {
    return getAllMetrics(this.http, this.rootUrl, params, context);
  }

  /**
   * Get all metrics of a datastream without Measurements.
   *
   * Returns a list of all available metrics
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAllMetrics$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllMetrics(params: GetAllMetrics$Params, context?: HttpContext): Observable<Array<Metric>> {
    return this.getAllMetrics$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<Metric>>): Array<Metric> => r.body)
    );
  }

  /** Path part for operation `datastreamDatastreamNameMetricMetricNameGet()` */
  static readonly DatastreamDatastreamNameMetricMetricNameGetPath = '/datastream/{datastream_name}/metric/{metric_name}';

  /**
   * Get Datapoints of a specific metric.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `datastreamDatastreamNameMetricMetricNameGet()` instead.
   *
   * This method doesn't expect any request body.
   */
  datastreamDatastreamNameMetricMetricNameGet$Response(params: DatastreamDatastreamNameMetricMetricNameGet$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<Measurement>>> {
    return datastreamDatastreamNameMetricMetricNameGet(this.http, this.rootUrl, params, context);
  }

  /**
   * Get Datapoints of a specific metric.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `datastreamDatastreamNameMetricMetricNameGet$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  datastreamDatastreamNameMetricMetricNameGet(params: DatastreamDatastreamNameMetricMetricNameGet$Params, context?: HttpContext): Observable<Array<Measurement>> {
    return this.datastreamDatastreamNameMetricMetricNameGet$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<Measurement>>): Array<Measurement> => r.body)
    );
  }

}

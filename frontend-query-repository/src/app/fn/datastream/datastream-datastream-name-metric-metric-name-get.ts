/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { Measurement } from '../../models/measurement';

export interface DatastreamDatastreamNameMetricMetricNameGet$Params {

/**
 * name of the Metric
 */
  metric_name: string;

/**
 * name of the datastream
 */
  datastream_name: string;
}

export function datastreamDatastreamNameMetricMetricNameGet(http: HttpClient, rootUrl: string, params: DatastreamDatastreamNameMetricMetricNameGet$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<Measurement>>> {
  const rb = new RequestBuilder(rootUrl, datastreamDatastreamNameMetricMetricNameGet.PATH, 'get');
  if (params) {
    rb.path('metric_name', params.metric_name, {});
    rb.path('datastream_name', params.datastream_name, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<Measurement>>;
    })
  );
}

datastreamDatastreamNameMetricMetricNameGet.PATH = '/datastream/{datastream_name}/metric/{metric_name}';

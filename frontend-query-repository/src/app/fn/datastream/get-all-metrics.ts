/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { Metric } from '../../models/metric';

export interface GetAllMetrics$Params {

/**
 * Datastream name
 */
  datastream_name: string;
}

export function getAllMetrics(http: HttpClient, rootUrl: string, params: GetAllMetrics$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<Metric>>> {
  const rb = new RequestBuilder(rootUrl, getAllMetrics.PATH, 'get');
  if (params) {
    rb.path('datastream_name', params.datastream_name, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<Metric>>;
    })
  );
}

getAllMetrics.PATH = '/datastream/{datastream_name}/metric';

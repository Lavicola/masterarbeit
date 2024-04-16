/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { Datastream } from '../../models/datastream';

export interface DatastreamGet$Params {
}

export function datastreamGet(http: HttpClient, rootUrl: string, params?: DatastreamGet$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<Datastream>>> {
  const rb = new RequestBuilder(rootUrl, datastreamGet.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<Datastream>>;
    })
  );
}

datastreamGet.PATH = '/datastream';

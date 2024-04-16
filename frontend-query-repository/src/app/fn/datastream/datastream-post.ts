/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { Datastream } from '../../models/datastream';

export interface DatastreamPost$Params {
      body: Datastream
}

export function datastreamPost(http: HttpClient, rootUrl: string, params: DatastreamPost$Params, context?: HttpContext): Observable<StrictHttpResponse<Datastream>> {
  const rb = new RequestBuilder(rootUrl, datastreamPost.PATH, 'post');
  if (params) {
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Datastream>;
    })
  );
}

datastreamPost.PATH = '/datastream';

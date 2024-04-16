/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { QueryDto } from '../../models/query-dto';

export interface QueryPost$Params {
      body: QueryDto
}

export function queryPost(http: HttpClient, rootUrl: string, params: QueryPost$Params, context?: HttpContext): Observable<StrictHttpResponse<QueryDto>> {
  const rb = new RequestBuilder(rootUrl, queryPost.PATH, 'post');
  if (params) {
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<QueryDto>;
    })
  );
}

queryPost.PATH = '/query';

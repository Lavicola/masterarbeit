/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { QueryDtoPage } from '../../models/query-dto-page';

export interface QueryGet$Params {

/**
 * The start Date of the Queries to search for
 */
  startDate?: string;

/**
 * The end Date of the Queries to search for
 */
  endDate?: string;

/**
 * The page number (zero-based index) for paginated results.
 */
  page?: number;

/**
 * The number of items per page.
 */
  size?: number;

/**
 * Sorting criteria for the results.
 */
  sort?: string;

/**
 * List of Tablenames a Query shall own
 */
  tablenames?: Array<string>;
}

export function queryGet(http: HttpClient, rootUrl: string, params?: QueryGet$Params, context?: HttpContext): Observable<StrictHttpResponse<QueryDtoPage>> {
  const rb = new RequestBuilder(rootUrl, queryGet.PATH, 'get');
  if (params) {
    rb.query('startDate', params.startDate, {"style":"form"});
    rb.query('endDate', params.endDate, {"style":"form"});
    rb.query('page', params.page, {"style":"form"});
    rb.query('size', params.size, {"style":"form"});
    rb.query('sort', params.sort, {"style":"form"});
    rb.query('tablenames', params.tablenames, {"style":"form","explode":true});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<QueryDtoPage>;
    })
  );
}

queryGet.PATH = '/query';

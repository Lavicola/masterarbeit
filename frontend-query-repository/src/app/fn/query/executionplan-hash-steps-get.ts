/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ExecutionplanStep } from '../../models/executionplan-step';

export interface ExecutionplanHashStepsGet$Params {

/**
 * Get steps of Executionplan
 */
  hash: string;
}

export function executionplanHashStepsGet(http: HttpClient, rootUrl: string, params: ExecutionplanHashStepsGet$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<ExecutionplanStep>>> {
  const rb = new RequestBuilder(rootUrl, executionplanHashStepsGet.PATH, 'get');
  if (params) {
    rb.path('hash', params.hash, {"style":"simple"});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<ExecutionplanStep>>;
    })
  );
}

executionplanHashStepsGet.PATH = '/executionplan/{hash}/steps';

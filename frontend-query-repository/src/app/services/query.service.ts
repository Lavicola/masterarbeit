/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { executionplanHashStepsGet } from '../fn/query/executionplan-hash-steps-get';
import { ExecutionplanHashStepsGet$Params } from '../fn/query/executionplan-hash-steps-get';
import { ExecutionplanStep } from '../models/executionplan-step';
import { QueryDto } from '../models/query-dto';
import { QueryDtoPage } from '../models/query-dto-page';
import { queryGet } from '../fn/query/query-get';
import { QueryGet$Params } from '../fn/query/query-get';
import { queryPost } from '../fn/query/query-post';
import { QueryPost$Params } from '../fn/query/query-post';
import { tablereferencesGet } from '../fn/query/tablereferences-get';
import { TablereferencesGet$Params } from '../fn/query/tablereferences-get';

@Injectable({ providedIn: 'root' })
export class QueryService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `queryGet()` */
  static readonly QueryGetPath = '/query';

  /**
   * Get queries.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `queryGet()` instead.
   *
   * This method doesn't expect any request body.
   */
  queryGet$Response(params?: QueryGet$Params, context?: HttpContext): Observable<StrictHttpResponse<QueryDtoPage>> {
    return queryGet(this.http, this.rootUrl, params, context);
  }

  /**
   * Get queries.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `queryGet$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  queryGet(params?: QueryGet$Params, context?: HttpContext): Observable<QueryDtoPage> {
    return this.queryGet$Response(params, context).pipe(
      map((r: StrictHttpResponse<QueryDtoPage>): QueryDtoPage => r.body)
    );
  }

  /** Path part for operation `queryPost()` */
  static readonly QueryPostPath = '/query';

  /**
   * redirect a new resource to  AMQP.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `queryPost()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  queryPost$Response(params: QueryPost$Params, context?: HttpContext): Observable<StrictHttpResponse<QueryDto>> {
    return queryPost(this.http, this.rootUrl, params, context);
  }

  /**
   * redirect a new resource to  AMQP.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `queryPost$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  queryPost(params: QueryPost$Params, context?: HttpContext): Observable<QueryDto> {
    return this.queryPost$Response(params, context).pipe(
      map((r: StrictHttpResponse<QueryDto>): QueryDto => r.body)
    );
  }

  /** Path part for operation `executionplanHashStepsGet()` */
  static readonly ExecutionplanHashStepsGetPath = '/executionplan/{hash}/steps';

  /**
   * Get Executionplan and Steps.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `executionplanHashStepsGet()` instead.
   *
   * This method doesn't expect any request body.
   */
  executionplanHashStepsGet$Response(params: ExecutionplanHashStepsGet$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<ExecutionplanStep>>> {
    return executionplanHashStepsGet(this.http, this.rootUrl, params, context);
  }

  /**
   * Get Executionplan and Steps.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `executionplanHashStepsGet$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  executionplanHashStepsGet(params: ExecutionplanHashStepsGet$Params, context?: HttpContext): Observable<Array<ExecutionplanStep>> {
    return this.executionplanHashStepsGet$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<ExecutionplanStep>>): Array<ExecutionplanStep> => r.body)
    );
  }

  /** Path part for operation `tablereferencesGet()` */
  static readonly TablereferencesGetPath = '/tablereferences';

  /**
   * Retrieve all table references.
   *
   * Retrieve all table references
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `tablereferencesGet()` instead.
   *
   * This method doesn't expect any request body.
   */
  tablereferencesGet$Response(params?: TablereferencesGet$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<string>>> {
    return tablereferencesGet(this.http, this.rootUrl, params, context);
  }

  /**
   * Retrieve all table references.
   *
   * Retrieve all table references
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `tablereferencesGet$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  tablereferencesGet(params?: TablereferencesGet$Params, context?: HttpContext): Observable<Array<string>> {
    return this.tablereferencesGet$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<string>>): Array<string> => r.body)
    );
  }

}

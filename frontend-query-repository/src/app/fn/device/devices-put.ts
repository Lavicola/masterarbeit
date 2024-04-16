/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { DeviceDto } from '../../models/device-dto';

export interface DevicesPut$Params {
      body: DeviceDto
}

export function devicesPut(http: HttpClient, rootUrl: string, params: DevicesPut$Params, context?: HttpContext): Observable<StrictHttpResponse<DeviceDto>> {
  const rb = new RequestBuilder(rootUrl, devicesPut.PATH, 'put');
  if (params) {
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<DeviceDto>;
    })
  );
}

devicesPut.PATH = '/devices';
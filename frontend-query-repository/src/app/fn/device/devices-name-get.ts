/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { DeviceDto } from '../../models/device-dto';

export interface DevicesNameGet$Params {

/**
 * name of the Device
 */
  name: string;
}

export function devicesNameGet(http: HttpClient, rootUrl: string, params: DevicesNameGet$Params, context?: HttpContext): Observable<StrictHttpResponse<DeviceDto>> {
  const rb = new RequestBuilder(rootUrl, devicesNameGet.PATH, 'get');
  if (params) {
    rb.path('name', params.name, {});
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

devicesNameGet.PATH = '/devices/{name}';

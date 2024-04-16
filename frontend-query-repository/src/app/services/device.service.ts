/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { DeviceDto } from '../models/device-dto';
import { devicesGet } from '../fn/device/devices-get';
import { DevicesGet$Params } from '../fn/device/devices-get';
import { devicesNameGet } from '../fn/device/devices-name-get';
import { DevicesNameGet$Params } from '../fn/device/devices-name-get';
import { devicesPost } from '../fn/device/devices-post';
import { DevicesPost$Params } from '../fn/device/devices-post';
import { devicesPut } from '../fn/device/devices-put';
import { DevicesPut$Params } from '../fn/device/devices-put';

@Injectable({ providedIn: 'root' })
export class DeviceService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `devicesGet()` */
  static readonly DevicesGetPath = '/devices';

  /**
   * Get all Devices.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `devicesGet()` instead.
   *
   * This method doesn't expect any request body.
   */
  devicesGet$Response(params?: DevicesGet$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<DeviceDto>>> {
    return devicesGet(this.http, this.rootUrl, params, context);
  }

  /**
   * Get all Devices.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `devicesGet$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  devicesGet(params?: DevicesGet$Params, context?: HttpContext): Observable<Array<DeviceDto>> {
    return this.devicesGet$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<DeviceDto>>): Array<DeviceDto> => r.body)
    );
  }

  /** Path part for operation `devicesPut()` */
  static readonly DevicesPutPath = '/devices';

  /**
   * Update Device.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `devicesPut()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  devicesPut$Response(params: DevicesPut$Params, context?: HttpContext): Observable<StrictHttpResponse<DeviceDto>> {
    return devicesPut(this.http, this.rootUrl, params, context);
  }

  /**
   * Update Device.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `devicesPut$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  devicesPut(params: DevicesPut$Params, context?: HttpContext): Observable<DeviceDto> {
    return this.devicesPut$Response(params, context).pipe(
      map((r: StrictHttpResponse<DeviceDto>): DeviceDto => r.body)
    );
  }

  /** Path part for operation `devicesPost()` */
  static readonly DevicesPostPath = '/devices';

  /**
   * Create a new Device.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `devicesPost()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  devicesPost$Response(params: DevicesPost$Params, context?: HttpContext): Observable<StrictHttpResponse<DeviceDto>> {
    return devicesPost(this.http, this.rootUrl, params, context);
  }

  /**
   * Create a new Device.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `devicesPost$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  devicesPost(params: DevicesPost$Params, context?: HttpContext): Observable<DeviceDto> {
    return this.devicesPost$Response(params, context).pipe(
      map((r: StrictHttpResponse<DeviceDto>): DeviceDto => r.body)
    );
  }

  /** Path part for operation `devicesNameGet()` */
  static readonly DevicesNameGetPath = '/devices/{name}';

  /**
   * Get one Device with the name.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `devicesNameGet()` instead.
   *
   * This method doesn't expect any request body.
   */
  devicesNameGet$Response(params: DevicesNameGet$Params, context?: HttpContext): Observable<StrictHttpResponse<DeviceDto>> {
    return devicesNameGet(this.http, this.rootUrl, params, context);
  }

  /**
   * Get one Device with the name.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `devicesNameGet$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  devicesNameGet(params: DevicesNameGet$Params, context?: HttpContext): Observable<DeviceDto> {
    return this.devicesNameGet$Response(params, context).pipe(
      map((r: StrictHttpResponse<DeviceDto>): DeviceDto => r.body)
    );
  }

}

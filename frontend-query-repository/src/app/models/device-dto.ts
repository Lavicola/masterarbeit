/* tslint:disable */
/* eslint-disable */
import { Costmodel } from '../models/costmodel';
import { DatastreamPort } from '../models/datastream-port';
import { RpuCapabilities } from '../models/rpu-capabilities';
export interface DeviceDto {

  /**
   * Capabilities of the device
   */
  capabilities?: Array<RpuCapabilities>;

  /**
   * Costmodells of the device
   */
  costmodels?: Array<Costmodel>;
  ip?: string;

  /**
   * Device name
   */
  name?: string;

  /**
   * List of ports connected to the device
   */
  ports?: Array<DatastreamPort>;
}

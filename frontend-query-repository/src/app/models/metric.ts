/* tslint:disable */
/* eslint-disable */
import { Measurement } from '../models/measurement';
export interface Metric {

  /**
   * id of Metric
   */
  id?: number;
  mesurements?: Array<Measurement>;

  /**
   * Name of the Metric
   */
  name?: string;

  /**
   * Unit of the Metric
   */
  unit?: string;
}

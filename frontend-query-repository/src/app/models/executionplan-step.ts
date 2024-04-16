/* tslint:disable */
/* eslint-disable */
export interface ExecutionplanStep {

  /**
   * json of the capabilities for this certain step
   */
  capabilities?: string;

  /**
   * json of the costmodel for this certain step
   */
  costmodel?: string;

  /**
   * name of the Device which executed this step
   */
  deviceName?: string;

  /**
   * json executionplan step
   */
  executionstep?: string;

  /**
   * number of the step
   */
  step?: number;
}

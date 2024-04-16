/* tslint:disable */
/* eslint-disable */
import { ExecutionplanStep } from '../models/executionplan-step';
export interface Executionplan {

  /**
   * execution plan as JSON
   */
  executionplan?: string;

  /**
   * hashed execution plan
   */
  hash?: string;
  steps?: Array<ExecutionplanStep>;
}

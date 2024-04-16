/* tslint:disable */
/* eslint-disable */
import { Executionplan } from '../models/executionplan';
export interface QueryDto {

  /**
   * Hash of a Costplan
   */
  executionplanHash?: string;
  executionplans?: Array<Executionplan>;

  /**
   * Statement of an query
   */
  statement?: string;

  /**
   * Statementhash of an query
   */
  statementHash?: string;

  /**
   * Incoming Time of the Query
   */
  timestamp?: string;
}

/* tslint:disable */
/* eslint-disable */
export interface Query {

  /**
   * Hash of a Costplan
   */
  executionplanHash?: string;

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

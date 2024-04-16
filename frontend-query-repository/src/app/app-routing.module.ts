import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DatastreamConfigComponent} from "./DatastreamMetricComponents/datastream-config/datastream-config.component";
import {DatastreamMetricComponent} from "./DatastreamMetricComponents/datastream-metric/datastream-metric.component";
import {DevicesOverviewComponent} from "./DeviceConfigComponents/devices-overview/devices-overview.component";
import {DeviceOverviewComponent} from "./DeviceConfigComponents/device-overview/device-overview.component";
import {QueryExecutionComponent} from "./dataMartComponents/query-execution/query-execution.component";
import {QueryHistoryComponent} from "./dataMartComponents/query-history/query-history.component";
import {QueryAnalyzerComponent} from "./dataMartComponents/query-analyzer/query-analyzer.component";

const routes: Routes = [
  { path: 'datastreams', component: DatastreamConfigComponent },
  {path: 'datastream/:name', component: DatastreamMetricComponent },
  {path: 'devices', component: DevicesOverviewComponent },
  {path: 'device/:name', component: DeviceOverviewComponent },
  {path: 'query-History', component: QueryHistoryComponent },
  {path: 'executionplan/:hash/step', component: QueryAnalyzerComponent },

  {path: 'query-execution', component: QueryExecutionComponent },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

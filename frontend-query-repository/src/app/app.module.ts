import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatInputModule} from "@angular/material/input";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatNativeDateModule} from "@angular/material/core";
import {MatTableModule} from "@angular/material/table";
import {MatIconModule} from "@angular/material/icon";
import {MatPaginatorModule} from "@angular/material/paginator";
import {AngularSplitModule} from "angular-split";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CodemirrorModule} from "@ctrl/ngx-codemirror";
import {DatastreamConfigComponent} from "./DatastreamMetricComponents/datastream-config/datastream-config.component";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import { DatastreamFormComponent } from './DatastreamMetricComponents/datastream-form/datastream-form.component';
import {MatDialog, MatDialogModule} from "@angular/material/dialog";
import { CodeMirrorFullscreenComponent } from './shared/code-mirror-fullscreen/code-mirror-fullscreen.component';
import { ErrorDialogComponent } from './shared/errors/error-dialog/error-dialog.component';
import { LoadingDialogComponent } from './shared/loading/loading-dialog/loading-dialog.component';
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {CoreModule} from "./core/core.module";
import {ErrorDialogService} from "./shared/errors/error-dialog.service";
import {LoadingDialogService} from "./shared/loading/loading-dialog.service";
import { DatastreamMetricComponent } from './DatastreamMetricComponents/datastream-metric/datastream-metric.component';
import {MatSelectModule} from "@angular/material/select";
import { DevicesOverviewComponent } from './DeviceConfigComponents/devices-overview/devices-overview.component';
import { EmptyDeviceFormComponent } from './DeviceConfigComponents/empty-device-form/empty-device-form.component';
import { DeviceOverviewComponent } from './DeviceConfigComponents/device-overview/device-overview.component';
import {MatTabsModule} from "@angular/material/tabs";
import { PortFormComponent } from './DeviceConfigComponents/port-form/port-form.component';
import { NewWindowComponent } from './DeviceConfigComponents/new-window/new-window.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import { QueryExecutionComponent } from './dataMartComponents/query-execution/query-execution.component';
import { QueryHistoryComponent } from './dataMartComponents/query-history/query-history.component';
import { QueryAnalyzerComponent } from './dataMartComponents/query-analyzer/query-analyzer.component';
import { NgChartsModule } from 'ng2-charts';
import {ChartCommonModule, LineChartModule} from "@swimlane/ngx-charts";

@NgModule({
  declarations: [
    AppComponent,
    DatastreamConfigComponent,
    DatastreamFormComponent,
    CodeMirrorFullscreenComponent,
    ErrorDialogComponent,
    LoadingDialogComponent,
    DatastreamMetricComponent,
    DevicesOverviewComponent,
    EmptyDeviceFormComponent,
    DeviceOverviewComponent,
    PortFormComponent,
    NewWindowComponent,
    QueryExecutionComponent,
    QueryHistoryComponent,
    QueryAnalyzerComponent,

  ],
    imports: [
        BrowserModule,
        FormsModule,
        CodemirrorModule,
        MatDialogModule,
        ReactiveFormsModule,
        HttpClientModule,
        AngularSplitModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatDatepickerModule,
        MatInputModule,
        MatNativeDateModule,
        MatGridListModule,
        MatTableModule,
        MatIconModule,
        MatPaginatorModule,
        ReactiveFormsModule,
        FormsModule,
        CodemirrorModule,
        MatProgressSpinnerModule,
        CoreModule,
        MatSelectModule,
        MatTabsModule,
        MatToolbarModule,
        NgChartsModule,
        ChartCommonModule,
        LineChartModule
    ],
  providers: [MatDatepickerModule,
      HttpClient,
      MatDialog,
      ErrorDialogService,
      LoadingDialogService,
      ],
  bootstrap: [AppComponent]
})
export class AppModule { }

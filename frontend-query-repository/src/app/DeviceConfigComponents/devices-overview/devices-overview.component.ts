import { Component } from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {Datastream} from "../../models/datastream";
import {DatastreamService} from "../../services/datastream.service";
import {MatDialog} from "@angular/material/dialog";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {DeviceService} from "../../services/device.service";
import {DatastreamFormComponent} from "../../DatastreamMetricComponents/datastream-form/datastream-form.component";
import {EmptyDeviceFormComponent} from "../empty-device-form/empty-device-form.component";
import {DtoDevice} from "../../models/dto-device";
import {RowAction} from "../../models/rowAction";

@Component({
  selector: 'app-device-overview',
  templateUrl: './devices-overview.component.html',
  styleUrls: ['./devices-overview.component.css']
})
export class DevicesOverviewComponent {
  devices: DtoDevice[] = [];
  dataSource: MatTableDataSource<Datastream> = new MatTableDataSource<Datastream>(this.devices);
  // overview
  public displayedColumns: string[] = ['name',"ip"];
  public columnsToDisplay: string[] = [...this.displayedColumns, 'actions'];
  // actions
  rowActions: RowAction[] = [{
    text: '',
    buttonAsText: 'Change',
    performAction: (object: Datastream) => {
      this.router.navigate(['/device', object.name]);
      return;
    }
  }
  ];
  // Port overview
  private deviceService: DeviceService;
  showPortal: boolean = false;
  constructor(private service: DeviceService, private dialog: MatDialog,private http: HttpClient,private router: Router){
  this.deviceService = service;
  this.dialog = dialog;
  }


  ngOnInit(): void {
    this.deviceService.devicesGet().subscribe((data: DtoDevice[]) => {
      console.log(data)
      this.devices = data;
      this.dataSource = new MatTableDataSource<Datastream>(this.devices);
    });

  }


  AddDevice(object: DtoDevice) {
    const dialogRef = this.dialog.open(EmptyDeviceFormComponent, {
      data: {device: object} // Pass the object here
    });


    dialogRef.afterClosed().subscribe((device_dialog: DtoDevice) => {
      if (device_dialog) {
        this.deviceService.devicesPost({body: device_dialog })
          .subscribe(
            (result: DtoDevice) => {
              if (result) {
                this.devices.push(device_dialog);
                this.dataSource.data = this.devices;
                // Success: Do something after a successful response
                // You can also show a success message to the user
                //this.loadingService.hideLoadingDialog(); // Hide loading dialog on success
              }
            },
            (error: any) => {
              console.log(error)
              // Error: Handle the error and show a relevant message to the user

            }
          );

        dialogRef.close()
      }
    });
  }
}

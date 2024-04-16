import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {MatTableDataSource} from "@angular/material/table";
import {RowAction} from "../../models/rowAction";
import {DeviceService} from "../../services/device.service";
import {DtoDevice} from "../../models/dto-device";
import {ActivatedRoute} from "@angular/router";
import {PortFormComponent} from "../port-form/port-form.component";
import {MatDialog} from "@angular/material/dialog";
import {WindowService} from "../window.service";
import {DatastreamPort} from "../../models/datastream-port";

@Component({
    selector: 'app-device-overview',
    templateUrl: './device-overview.component.html',
    styleUrls: ['./device-overview.component.css']
})
export class DeviceOverviewComponent implements OnInit {

    public device: DtoDevice = {};
    public ports: DatastreamPort[] = [];
    private sub: any;

    formGroup: FormGroup;
    public displayedColumns: string[] = ["ip", "internalip", "number", "datastreamName"]//['Port','IP','InternalIp',"Description","Datastream"];
    public columnsToDisplay: string[] = [...this.displayedColumns, "actions"];
    rowActions: RowAction[] = [{
        text: '',
        buttonAsText: 'Delete',
        performAction: (port: DatastreamPort) => {
            if (port) {
                // @ts-ignore
                const index = this.device.ports.findIndex(l_port => l_port.number === port.number);
                this.device.ports?.splice(index, 1);
                this.dataSource = new MatTableDataSource<DatastreamPort>(this.device.ports);
            }
        }
    }, {
        text: '',
        buttonAsText: 'Edit',
        performAction: (port: DatastreamPort) => {
            // @ts-ignore
            const index = this.device.ports.findIndex(l_port => l_port.number === port.number);
            this.EditPort(port, index);
        }
    }

    ];
    private deviceService: DeviceService;
    dataSource: any;

    constructor(private fb: FormBuilder,
                service: DeviceService,
                private route: ActivatedRoute,
                private dialog: MatDialog,
                private windowService: WindowService) {
        this.dialog = dialog
        this.deviceService = service;
        this.formGroup = this.fb.group({
            name: "",
            ip: "",
            costmodel: [],
            capability: [],
            ports: []
        });

    }

    openComponentInNewWindow(dynamicContent: string): void {
        this.windowService.openComponentInNewWindow(dynamicContent);
    }


    onSubmit(myForm: FormGroup) {
        // @ts-ignore
        this.formGroup.patchValue({ports: this.device.ports});

        let device: DtoDevice = {
            name: this.formGroup.get("name")?.value ?? "",
            ip: this.formGroup.get("ip")?.value ?? "",
            capabilities: [this.formGroup.get("capability")?.value] ?? [],
            costmodels: [this.formGroup.get("costmodel")?.value] ?? [],
            ports: this.formGroup.get("ports")?.value ?? [],
        };

        this.deviceService.devicesPut({body: device})
            .subscribe(
                (result: DtoDevice) => {
                    if (result) {
                    }
                },
                (error: any) => {
                    console.log(error)
                }
            );


    }

    ngOnInit(): void {
        this.sub = this.route.params.subscribe(params => {
            let device_name: string = params['name'];

            this.deviceService.devicesNameGet({"name": device_name})
                .subscribe((device: DtoDevice) => {
                    this.device = device;
                    if (this.device.ports == null) {
                        this.device.ports = []
                    }
                    // @ts-ignore
                    this.formGroup.get("name").setValue(this.device.name);
                    // @ts-ignore
                    this.formGroup.get("ip").setValue(this.device.ip);
                    this.dataSource = new MatTableDataSource<DatastreamPort>(this.device.ports);


                });


        });
    }


    public AddPort(port: Object): void {
        const dialogRef = this.dialog.open(PortFormComponent, {
            data: {Port: port}
        });
        dialogRef.afterClosed().subscribe((port_form_dialog: DatastreamPort) => {
                console.log(port_form_dialog)

                if (port_form_dialog) {
                    console.log(port_form_dialog)
                    this.device.ports?.push(port_form_dialog)
                    console.log(this.device.ports)
                    this.dataSource = new MatTableDataSource<DatastreamPort>(this.device.ports);
                }
            },
            (error: any) => {
            }
        );
    }

    public EditPort(port: DatastreamPort, index: number): void {
        const dialogRef = this.dialog.open(PortFormComponent, {
            data: {Port: port}
        });
        dialogRef.afterClosed().subscribe((port_form_dialog: DatastreamPort) => {
                if (port_form_dialog) {
                    // @ts-ignore
                    this.device.ports[index] = port_form_dialog;
                    // @ts-ignore
                    this.dataSource = new MatTableDataSource<Port>(this.device.ports);
                }
            },
            (error: any) => {
            }
        );
    }


    onSelectionChangeCapabilities(event: any) {
        // @ts-ignore
        this.openComponentInNewWindow(this.device.capabilities[event.value - 1].capabilities)
        //unselect
        event.source.writeValue(null);


    }

    onSelectionChangeCostmodel(event: any) {

        // @ts-ignore
        this.openComponentInNewWindow(this.device.costmodels[event.value - 1].costmodel)
        // @ts-ignore
        this.formGroup.get('costmodel').setValue(null);
        //unselect
        event.source.writeValue(null);


    }

    onFilePicked(event: Event, select: number) {
        // @ts-ignore
        const file = (event.target as HTMLInputElement).files[0]; // Here we use only the first file (single file)


        const reader = new FileReader();
        reader.readAsText(file);
        reader.onload = (e: any) => {
            let fileContent = e.target.result;
            if (select == 0) {
                this.formGroup.patchValue({capability: fileContent});
            } else {
                this.formGroup.patchValue({costmodel: fileContent});
            }
        };

    }


}

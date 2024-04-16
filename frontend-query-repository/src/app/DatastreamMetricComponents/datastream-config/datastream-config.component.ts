import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {Datastream} from "../../models/datastream";
import {DatastreamService} from "../../services/datastream.service";
import {MatDialog} from "@angular/material/dialog";
import {Router} from '@angular/router';
import {RowAction} from "../../models/rowAction";
import {DatastreamFormComponent} from "../datastream-form/datastream-form.component";
import {HttpClient} from '@angular/common/http';

@Component({
    selector: 'app-datastream-config',
    templateUrl: './datastream-config.component.html',
    styleUrls: ['./datastream-config.component.css']
})
export class DatastreamConfigComponent implements OnInit {


    datastreamService: DatastreamService;
    datastreams: Datastream[] = [];

    dataSource: MatTableDataSource<Datastream> = new MatTableDataSource<Datastream>(this.datastreams);


    public stream: string = "";


    constructor(private service: DatastreamService, private dialog: MatDialog, private http: HttpClient, private router: Router) {
        this.datastreamService = service;
        this.dialog = dialog;

    }

    public displayedColumns: string[] = ['name'];
    public columnsToDisplay: string[] = [...this.displayedColumns, 'actions'];
    rowActions: RowAction[] = [{
        text: '',
        buttonAsText: 'Show',
        performAction: (object: Datastream) => {
            if (object.stream) {
                this.stream = object.stream;
            } else {
                this.stream = "No Stream found"
            }
        }
    }, {
        text: '',
        buttonAsText: 'Go to Metrics',
        performAction: (object: Datastream) => {
            this.router.navigate(['/datastream', object.name]);
            return;
        }
    }

    ];


    ngOnInit(): void {
        this.datastreamService.datastreamGet().subscribe((data: Datastream[]) => {
            this.datastreams = data;
            this.dataSource = new MatTableDataSource<Datastream>(this.datastreams);
        });

    }

    public AddDatastream(object: Datastream): void {


        const dialogRef = this.dialog.open(DatastreamFormComponent, {
            data: {datastream: object} // Pass the object here
        });

        dialogRef.afterClosed().subscribe((datastream_dialog: Datastream) => {
            if (datastream_dialog) {
                this.datastreamService.datastreamPost({body: datastream_dialog})
                    .subscribe(
                        (result: Datastream) => {
                            if (result) {
                                this.datastreams.push(datastream_dialog);
                                this.dataSource.data = this.datastreams;
                                // Success: Do something after a successful response
                                // You can also show a success message to the user
                                //this.loadingService.hideLoadingDialog(); // Hide loading dialog on success
                            }
                        },
                        (error: any) => {
                            // Error: Handle the error and show a relevant message to the user

                        }
                    );

                console.log("test")

                dialogRef.close()
            }
        });

    }

}


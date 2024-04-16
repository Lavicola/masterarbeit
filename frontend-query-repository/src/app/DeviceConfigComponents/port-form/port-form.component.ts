import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DatastreamService} from "../../services/datastream.service";
import {Data} from "@angular/router";
import {Datastream} from "../../models/datastream";
import {DatastreamPort} from "../../models/datastream-port";

@Component({
  selector: 'app-port-form',
  templateUrl: './port-form.component.html',
  styleUrls: ['./port-form.component.css']
})
export class PortFormComponent implements OnInit{


  port: DatastreamPort = {} as DatastreamPort;
  myForm: FormGroup;
  datastreamNames: String[] = [];


  constructor(
      datastreamService:DatastreamService,
    private dialogRef: MatDialogRef<PortFormComponent>,
    @Inject(MAT_DIALOG_DATA)  public data: { Port: DatastreamPort },
    fb: FormBuilder) {
    // a Port was given, either Port or DatastreamPort
    if(data.Port){
      this.port = data.Port
    }
    // in order to be able to add a Datastream, we need to get all possible Datastreams

    datastreamService.datastreamGet().subscribe((datastreams) => {
      for(const datastream of datastreams){
        // @ts-ignore
        this.datastreamNames.push(datastream.name);
      }
    });

    this.myForm = new FormGroup({
      ip: new FormControl(this.port.ip),
      number: new FormControl(this.port.number),
      description: new FormControl(this.port.description),
      internalip: new FormControl(this.port.internalip),
      datastreamName: new FormControl(this.port?.datastreamName ?? ""),

    });


  }

  onSubmit(myForm: FormGroup) {
    this.port = {...this.port, ...this.myForm.value};
    this.dialogRef.close(this.port); // Close the dialog with the updated data

  }

  ngOnInit(): void {


  }


}

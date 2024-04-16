import { Component, Inject } from '@angular/core';
import { Datastream } from "../../models/datastream";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-datastream-form',
  templateUrl: './datastream-form.component.html',
  styleUrls: ['./datastream-form.component.css']
})
export class DatastreamFormComponent {

  datastream: Datastream = {};
  myForm: FormGroup;


  constructor(
    private dialogRef: MatDialogRef<DatastreamFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { datastream: Datastream },
    fb: FormBuilder) {


    this.myForm = new FormGroup({
      name: new FormControl(this.datastream.name),
      stream: new FormControl(this.datastream.stream),
    });



  }
  onSubmit(myForm: FormGroup) {
    this.datastream = {...this.datastream, ...this.myForm.value};
    this.dialogRef.close(this.datastream); // Close the dialog with the updated data

  }


  onFilePicked(event: Event) {
    // @ts-ignore
    const file = (event.target as HTMLInputElement).files[0]; // Here we use only the first file (single file)


    const reader = new FileReader();
    reader.readAsText(file);
    reader.onload = (e: any) => {
      this.myForm.patchValue({stream: e.target.result});
    };

  }
}

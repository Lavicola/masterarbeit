import {Component, Inject} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DtoDevice} from "../../models/dto-device";

@Component({
    selector: 'app-empty-device-form',
    templateUrl: './empty-device-form.component.html',
    styleUrls: ['./empty-device-form.component.css']
})
export class EmptyDeviceFormComponent {

    device: DtoDevice = {};
    formGroup: FormGroup;


    constructor(
        private dialogRef: MatDialogRef<EmptyDeviceFormComponent>,
        @Inject(MAT_DIALOG_DATA) public data: { device: DtoDevice },
        fb: FormBuilder) {


        this.formGroup = new FormGroup({
            name: new FormControl(this.device.name),
            ip: new FormControl(this.device.ip),
        });


    }

    onSubmit() {
        this.device = {...this.device, ...this.formGroup.value};
        this.dialogRef.close(this.device);

    }


}

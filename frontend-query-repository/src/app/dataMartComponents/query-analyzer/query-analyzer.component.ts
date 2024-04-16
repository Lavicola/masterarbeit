import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {QueryService} from "../../services/query.service";
import {ExecutionplanStep} from "../../models/executionplan-step";
import {MatTabGroup} from "@angular/material/tabs";

@Component({
    selector: 'app-query-analyzer',
    templateUrl: './query-analyzer.component.html',
    styleUrls: ['./query-analyzer.component.css']
})
export class QueryAnalyzerComponent implements AfterViewInit {
    // @ts-ignore
    @ViewChild('tabGroup') tabGroup: MatTabGroup;

    steps: ExecutionplanStep[] = [];
    current_step: number = 0;
    queryService: QueryService | undefined;
    executionplan: string = "";
    currentGeneric: string = "";


    // @ts-ignore
    @ViewChild('jsoneditorGeneric') private jsoneditorGeneric;
    // @ts-ignore
    @ViewChild('jsoneditorStep') private jsoneditorStep;


    constructor(service: QueryService) {
        this.queryService = service;


    }

    ngAfterViewInit(): void {

        this.queryService?.executionplanHashStepsGet({hash: "test"}).subscribe(l_steps => {
            this.steps = l_steps;


            console.log("test");
        })


    }


    onTabChange(event: any): void {
        if (event.index == 0) {
            // @ts-ignore
            this.jsoneditorGeneric.setContent(this.steps.at(this.current_step).costmodel)
        } else {
            // @ts-ignore
            this.jsoneditorGeneric.setContent(this.steps.at(this.current_step).capabilities)

        }
    }

    onStepChange(event: any): void {
        this.tabGroup.selectedIndex = 0;

        this.current_step = event.value - 1;
        // @ts-ignore
        this.jsoneditorStep.setContent(this.steps.at(this.current_step).executionstep)
        // @ts-ignore
        this.jsoneditorGeneric.setContent(this.steps.at(this.current_step).costmodel)

    }


}
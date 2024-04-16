import {Component, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {QueryDto} from "../../models/query-dto";
import {QueryService} from '../../services/query.service';

@Component({
    selector: 'app-query-execution',
    templateUrl: './query-execution.component.html',
    styleUrls: ['./query-execution.component.css']
})
export class QueryExecutionComponent {
    executionplan: string = "";
    // @ts-ignore
    @ViewChild('sqleditor') private sqleditor;

    // @ts-ignore
    @ViewChild('jsoneditor') private jsoneditor;


    private queryService: QueryService;
    public statement = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
    protected readonly Infinity = Infinity;
    tabs = new Map<string, string>();
    public selected_executionplanhash: string = "";


    constructor(service: QueryService, private router: Router) {
        this.queryService = service;

    }


    public executeQuery() {

        let query: QueryDto = {
            statement: this.sqleditor.codeMirror.getValue(),
            statementHash: "",
            executionplanHash: "",
            timestamp: ""
        };
        this.tabs.clear()
        this.queryService.queryPost({body: query}).subscribe(
            (queryDto: QueryDto) => {
                if (queryDto.executionplans != undefined) {
                    queryDto.executionplans.forEach(executionplan => {
                        // @ts-ignore
                        this.tabs.set(executionplan.hash, executionplan.executionplan);
                    });
                    // @ts-ignore
                    this.selected_executionplanhash = queryDto.executionplanHash;

                }
                // set default plan
                // @ts-ignore
                this.jsoneditor.setContent(this.tabs.get(queryDto.executionplanHash))
            },
            (error: any) => {
                console.error('Error:', error);
            }
        );
    }


    analyze() {
        this.router.navigate(['/executionplan', this.selected_executionplanhash]);

    }

    onTabClick(key: any) {
        this.jsoneditor.setContent(this.tabs.get(key))
        this.selected_executionplanhash = key;

    }
}

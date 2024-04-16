import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {QueryService} from "../../services/query.service";
import {RowAction} from "../../models/rowAction";
import {QueryDto} from "../../models/query-dto";
import {QueryDtoPage} from "../../models/query-dto-page";
import {Router} from '@angular/router';
import {Executionplan} from "../../models/executionplan";

@Component({
    selector: 'app-query-history',
    templateUrl: './query-history.component.html',
    styleUrls: ['./query-history.component.css']
})
export class QueryHistoryComponent implements AfterViewInit {
    // @ts-ignore
    @ViewChild(MatPaginator) paginator: MatPaginator;
    totalItems: number = 0;
    pageSize: number = 20;
    pageNumber: number = 0;

    // @ts-ignore
    @ViewChild('jsoneditor') private jsoneditor;
    selectedTablesControl = new FormControl(); // FormControl for the mat-select
    tabs = new Map<string, string>();
    selected_executionplanhash: string = "";
    dataSource: any;
    tables: string[] = [];
    executionplan: any;
    private queryService: QueryService;
    //for date
    range: FormGroup;
    // table
    public displayedColumns: string[] = ['statement'];
    public columnsToDisplay: string[] = [...this.displayedColumns, 'actions'];
    rowActions: RowAction[] = [{
        text: '',
        buttonAsText: 'Show',
        performAction: (queryDto: QueryDto) => {
            if (queryDto) {
                this.tabs.clear();
                // @ts-ignore
                queryDto.executionplans.forEach(executionplan => {
                    // @ts-ignore
                    this.tabs.set(executionplan.hash, executionplan.executionplan);
                });
                // @ts-ignore
                this.jsoneditor.setContent(this.tabs.get(queryDto.executionplanHash))
                // @ts-ignore
                this.selected_executionplanhash = queryDto.executionplanHash;

            }
        }
    }, {
        text: '',
        buttonAsText: 'Analyze',
        performAction: (object: QueryDto) => {
            // analyze was clicked AFTER show
            if (this.selected_executionplanhash) {
                this.router.navigate([`/executionplan/${this.selected_executionplanhash}/step`]);
                this.selected_executionplanhash = "";
            } else {
                // analyze was clicked WITHOUT show
                this.router.navigate([`/executionplan/${object.executionplanHash}/step`]);
            }
            return;
        }
    }

    ];


    constructor(
        private formBuilder: FormBuilder,
        private service: QueryService,
        private router: Router
    ) {
        this.queryService = service;
        //date
        this.range = this.formBuilder.group({
            start: new FormControl<Date | null>(null),
            end: new FormControl<Date | null>(null),
        });
        // search

    }

    search() {
        // @ts-ignore
        const startDateValue = this.range.get('start').value;
        // @ts-ignore
        const endDateValue = this.range.get('end').value;


        this.queryService.queryGet$Response({
            startDate: startDateValue !== null ? startDateValue.toISOString() : startDateValue,
            endDate: endDateValue !== null ? endDateValue.toISOString() : endDateValue,
            tablenames: this.selectedTablesControl.value,
            page: this.paginator.pageIndex,
            size: this.paginator.pageSize
        }).subscribe(response => {
            // Handle the response as needed
            const queryDtoPage: QueryDtoPage = response.body;
            this.dataSource = queryDtoPage.queries;
            // @ts-ignore
            this.totalItems = queryDtoPage.totalPages * this.pageSize;
        });

    }

    onPageChange(event: PageEvent) {

        this.pageSize = event.pageSize
        this.search();
    }

    ngAfterViewInit(): void {
        this.search();
        this.queryService.tablereferencesGet().subscribe(response => {
            this.tables = response;
        });

    }

    onTabClick(key: any) {
        this.jsoneditor.setContent(this.tabs.get(key))
        // @ts-ignore
        this.selected_executionplanhash = key;

    }

}

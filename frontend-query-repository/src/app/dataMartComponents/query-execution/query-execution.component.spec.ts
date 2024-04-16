import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QueryExecutionComponent } from './query-execution.component';

describe('QueryExecutionComponent', () => {
  let component: QueryExecutionComponent;
  let fixture: ComponentFixture<QueryExecutionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QueryExecutionComponent]
    });
    fixture = TestBed.createComponent(QueryExecutionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

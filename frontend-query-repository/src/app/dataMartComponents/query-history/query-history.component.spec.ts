import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QueryHistoryComponent } from './query-history.component';

describe('QueryHistoryComponent', () => {
  let component: QueryHistoryComponent;
  let fixture: ComponentFixture<QueryHistoryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QueryHistoryComponent]
    });
    fixture = TestBed.createComponent(QueryHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

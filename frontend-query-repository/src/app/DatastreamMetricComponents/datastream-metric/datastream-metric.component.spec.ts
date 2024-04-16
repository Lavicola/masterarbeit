import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DatastreamMetricComponent } from './datastream-metric.component';

describe('DatastreamMetricComponent', () => {
  let component: DatastreamMetricComponent;
  let fixture: ComponentFixture<DatastreamMetricComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DatastreamMetricComponent]
    });
    fixture = TestBed.createComponent(DatastreamMetricComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

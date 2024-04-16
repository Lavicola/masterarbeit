import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DatastreamFormComponent } from './datastream-form.component';

describe('DatastreamFormComponent', () => {
  let component: DatastreamFormComponent;
  let fixture: ComponentFixture<DatastreamFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DatastreamFormComponent]
    });
    fixture = TestBed.createComponent(DatastreamFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

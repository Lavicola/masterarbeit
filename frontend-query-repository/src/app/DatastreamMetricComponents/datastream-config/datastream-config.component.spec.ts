import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DatastreamConfigComponent } from './datastream-config.component';

describe('DatastreamConfigComponent', () => {
  let component: DatastreamConfigComponent;
  let fixture: ComponentFixture<DatastreamConfigComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DatastreamConfigComponent]
    });
    fixture = TestBed.createComponent(DatastreamConfigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

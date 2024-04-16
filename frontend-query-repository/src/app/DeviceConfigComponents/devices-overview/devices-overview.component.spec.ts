import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DevicesOverviewComponent } from './devices-overview.component';

describe('DeviceOverviewComponent', () => {
  let component: DevicesOverviewComponent;
  let fixture: ComponentFixture<DevicesOverviewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DevicesOverviewComponent]
    });
    fixture = TestBed.createComponent(DevicesOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

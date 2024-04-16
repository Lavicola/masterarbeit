import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmptyDeviceFormComponent } from './empty-device-form.component';

describe('EmptyDeviceFormComponent', () => {
  let component: EmptyDeviceFormComponent;
  let fixture: ComponentFixture<EmptyDeviceFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EmptyDeviceFormComponent]
    });
    fixture = TestBed.createComponent(EmptyDeviceFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

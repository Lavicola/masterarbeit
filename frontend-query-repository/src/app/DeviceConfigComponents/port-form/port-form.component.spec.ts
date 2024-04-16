import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PortFormComponent } from './port-form.component';

describe('PortFormComponent', () => {
  let component: PortFormComponent;
  let fixture: ComponentFixture<PortFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PortFormComponent]
    });
    fixture = TestBed.createComponent(PortFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

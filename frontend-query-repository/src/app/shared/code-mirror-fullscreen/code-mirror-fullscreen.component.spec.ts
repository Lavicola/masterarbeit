import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CodeMirrorFullscreenComponent } from './code-mirror-fullscreen.component';

describe('CodeMirrorFullscreenComponent', () => {
  let component: CodeMirrorFullscreenComponent;
  let fixture: ComponentFixture<CodeMirrorFullscreenComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CodeMirrorFullscreenComponent]
    });
    fixture = TestBed.createComponent(CodeMirrorFullscreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

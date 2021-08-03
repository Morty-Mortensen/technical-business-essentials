import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AceTextEditorComponent } from './ace-text-editor.component';

describe('AceTextEditorComponent', () => {
  let component: AceTextEditorComponent;
  let fixture: ComponentFixture<AceTextEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AceTextEditorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AceTextEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

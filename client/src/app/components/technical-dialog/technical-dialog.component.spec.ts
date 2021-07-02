import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TechnicalDialogComponent } from './technical-dialog.component';

describe('TechnicalDialogComponent', () => {
  let component: TechnicalDialogComponent;
  let fixture: ComponentFixture<TechnicalDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TechnicalDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TechnicalDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
